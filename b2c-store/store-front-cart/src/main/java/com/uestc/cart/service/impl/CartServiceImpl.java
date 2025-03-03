package com.uestc.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.cart.mapper.CartMapper;
import com.uestc.cart.service.CartService;
import com.uestc.clients.ProductClient;
import com.uestc.param.CartSaveParam;
import com.uestc.param.ProductCollectParam;
import com.uestc.param.ProductIdParam;
import com.uestc.pojo.Cart;
import com.uestc.pojo.Product;
import com.uestc.utils.R;
import com.uestc.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.MultiCollectorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {


    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductClient productClient;



    /**
     * 购物车数据添加方法
     *
     * @param cartSaveParam
     * @return 001 003没有库存 004
     */
    @Override
    public R save(CartSaveParam cartSaveParam) {

        //查询商品数据

        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());

        Product product = productClient.productDetail(productIdParam);

        if (product == null) {
            return R.fail("商品已经被删除，无法添加到购物车！");
        }

        //检查库存

        if (product.getProductNum() == 0) {
            R ok = R.ok("没有库存数据，无法购物！");
            ok.setCode("003");
            log.info("CartServiceImpl.save业务结束，结果:{}", ok);
            return ok;
        }

        // 检查是否添加过
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cartSaveParam.getUserId());
        queryWrapper.eq("product_id", cartSaveParam.getProductId());

        Cart cart = cartMapper.selectOne(queryWrapper);

        if (cart != null) {
            // 购物车存在存在过
            // 原有数量加1
            cart.setNum(cart.getNum() + 1);
            cartMapper.updateById(cart);
            R ok = R.ok("购物车存在商品，数量+1");
            ok.setCode("002");
            log.info("CartServiceImpl.save业务结束，结果:{}", ok);
            return ok;
        }

        // 添加购物车

        cart =  new Cart();
        cart.setNum(1);
        cart.setUserId(cartSaveParam.getUserId());
        cart.setProductId(cartSaveParam.getProductId());
        int insert = cartMapper.insert(cart);
        log.info("CartServiceImpl.save业务结束，结果:{}", insert);
        // 结果封装和返回

        CartVo cartVo = new CartVo(product, cart);
        return R.ok("购物车数据添加成功！", cartVo);
    }

    /**
     * 返回购物车数据
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {

        // 根据用户id 查询购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<Cart> carts = cartMapper.selectList(queryWrapper);
        if(carts == null || carts.size() == 0) {
            carts = new ArrayList<>();
            return R.ok("购物车空空如也！", carts);
        }

        List<Integer> productIds = new ArrayList<>();

        for (Cart cart : carts) {
            productIds.add(cart.getProductId());
        }

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);

        // 进行VO的封装

        // jdk 8 stream
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        List<CartVo> cartVoList = new ArrayList<>();
        for (Cart cart : carts) {
            CartVo cartVo = new CartVo(productMap.get(cart.getProductId()), cart);
            cartVoList.add(cartVo);
        }

        R ok = R.ok("数据库数据查询成功！", cartVoList);
        log.info("CartServiceImpl.list业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 更新购物车业务
     *
     * @param cart
     * @return
     */
    @Override
    public R update(Cart cart) {

        // 查询商品数量

        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());

        Product product = productClient.productDetail(productIdParam);

        if (cart.getNum() > product.getProductNum()) {
            log.info("CartServiceImpl.update业务结束，结果:{}", "修改失败，库存不足！");
            return R.fail("修改失败，库存不足！");
        }
        // 查询库存是否可用
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());
        Cart newCart =  cartMapper.selectOne(queryWrapper);

        newCart.setNum(cart.getNum());
        int update = cartMapper.updateById(newCart);
        log.info("CartServiceImpl.update业务结束，结果:{}", update);
        return R.ok("修改购物车数量成功！");
    }

    /**
     * 删除购物车数据
     *
     * @param cart
     * @return
     */
    @Override
    public R remove(Cart cart) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());

        int delete = cartMapper.delete(queryWrapper);
        log.info("CartServiceImpl.remove业务结束，结果:{}", delete);

        return R.ok("删除购物车数据成功！");
    }

    /**
     * 清空对应Id的购物车项
     *
     * @param cartIds
     */
    @Override
    public void clearIds(List<Integer> cartIds) {

        cartMapper.deleteBatchIds(cartIds);
        log.info("CartServiceImpl.clearIds业务结束，结果:{}", cartIds);
    }
}
