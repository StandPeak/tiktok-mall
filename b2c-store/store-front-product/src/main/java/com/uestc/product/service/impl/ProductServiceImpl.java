package com.uestc.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uestc.clients.CategoryClient;
import com.uestc.clients.SearchClient;
import com.uestc.param.ProductIdParam;
import com.uestc.param.ProductIdsParam;
import com.uestc.param.ProductSearchParam;
import com.uestc.pojo.Picture;
import com.uestc.pojo.Product;
import com.uestc.param.ProductHotParam;
import com.uestc.product.mapper.PictureMapper;
import com.uestc.product.mapper.ProductMapper;
import com.uestc.product.service.ProductService;
import com.uestc.to.OrderToProduct;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author llf
 * @school uestc
 */

@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private SearchClient searchClient;

    /**
     * 根据单类别名称，查询热门商品 至多7条数据
     * 1. 根据类别名称 调用feign客户端访问类别服务获取类别的数据
     * 2. 成功 继续根据类别id查询商品数据
     * 3. 结果进行封装即可
     * @param categoryName
     * @return
     */
    @Cacheable( value = "list.product", key = "#categoryName", cacheManager = "cacheManagerDay")
    @Override
    public R promo(String categoryName) {

        R r = categoryClient.byName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果:{}", "类别查询失败！");
            return r;
        }

        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>)r.getData();

        Integer categoryId = (Integer) map.get("category_id");

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);

        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords(); // 指定页的数据
        long total = page.getTotal(); // 获取总条数

        log.info("ProductServiceImpl.promo业务结束，结果:{}", productList);
        return R.ok("数据查询成功", productList);
    }

    /**
     * 根据多类别名称，查询热门商品 至多7条数据
     *
     * @param productHotParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    @Override
    public R hots(ProductHotParam productHotParam) {

        R r = categoryClient.hots(productHotParam);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果:{}", "类别查询失败！");
            return r;
        }

        List<Object> ids = (List<Object>) r.getData();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids);
        queryWrapper.orderByDesc("product_sales");

        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> records = page.getRecords();

        R ok = R.ok("多类别热门商品查询成功！", records);
        log.info("ProductServiceImpl.hots业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 查询类别商品集合
     *
     * @return
     */
    @Override
    public R clist() {

        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist业务结束，结果:{}", r);
        return r;
    }

    /**
     * 通用性业务
     * 1. 传入了类别id，根据id查询并分页
     * 2. 没有传入类别id,查询全部
     *
     * @param productIdsParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID + '-' + #productIdsParam.currentPage + '-' + #productIdsParam.pageSize")
    @Override
    public R byCategory(ProductIdsParam productIdsParam) {

        List<Integer> categoryID = productIdsParam.getCategoryID();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        if (!categoryID.isEmpty()) {
            queryWrapper.in("category_id", categoryID);
        }

        IPage<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());
        page = productMapper.selectPage(page, queryWrapper);

        R ok = R.ok("查询成功！", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.byCategory业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 根据商品Id查询详情
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "product", key = "#productID")
    @Override
    public R detail(Integer productID) {

        Product product = productMapper.selectById(productID);

        R ok = R.ok(product);
        log.info("ProductServiceImpl.detail业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 查询商品对应图片详情集合
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "picture", key = "#productID")
    @Override
    public R pictures(Integer productID) {

        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productID);

        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);

        R ok = R.ok(pictureList);
        log.info("ProductServiceImpl.pictures业务结束，结果:{}", ok);

        return ok;

    }

    /**
     * 搜索服务调用，获取全部商品数据集合
     *
     * @return
     */
    @Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public List<Product> allList() {

        List<Product> productList = productMapper.selectList(null);
        log.info("ProductServiceImpl.allList业务结束，结果:{}", productList.size());
        return productList;
    }

    /**
     * 搜索业务，需要调用搜索服务！
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        R search = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果:{}", search);
        return search;
    }

    /**
     * 根据商品id集合查询商品信息
     *
     * @param productIds
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper =
                new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);
        List<Product> productList = productMapper.selectList(queryWrapper);

        R ok = R.ok("类别信息查询成功!", productList);
        log.info("ProductServiceImpl.ids业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 根据商品id，查询商品id集合
     *
     * @param productIds
     * @return
     */
    @Override
    public List<Product> cartList(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("ProductServiceImpl.cartList业务结束，结果:{}",productList);
        return productList;
    }

    /**
     * 修改库存和增加销售量
     *
     * @param orderToProducts
     */
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {

        // 将集合转成map productId orderToProduct
        Map<Integer, OrderToProduct> map =
                orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));

        // 获取商品id集合
        Set<Integer> productIds = map.keySet();
        List<Product> productList = productMapper.selectBatchIds(productIds);

        //修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum() - num);
            product.setProductSales(product.getProductSales() + num);
        }

        this.updateBatchById(productList);
        log.info("ProductServiceImpl.subNumber业务结束，结果:{}", "库存和销售量的修改完毕");

    }
}
