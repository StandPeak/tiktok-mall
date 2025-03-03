package com.uestc.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uestc.param.ProductHotParam;
import com.uestc.param.ProductIdParam;
import com.uestc.param.ProductIdsParam;
import com.uestc.param.ProductSearchParam;
import com.uestc.pojo.Product;
import com.uestc.to.OrderToProduct;
import com.uestc.utils.R;

import java.util.List;

/**
 * @author llf
 * @school uestc
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据单类别名称，查询热门商品 至多7条数据
     * @param categoryName
     * @return
     */
    R promo(String categoryName);

    /**
     * 根据多类别名称，查询热门商品 至多7条数据
     * @param productHotParam
     * @return
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     * @return
     */
    R clist();

    /**
     * 通用性业务
     * 1. 传入了类别id，根据id查询并分页
     * 2. 没有传入类别id,查询全部
     * @param productIdsParam
     * @return
     */
    R byCategory(ProductIdsParam productIdsParam);

    /**
     * 根据商品Id查询详情
     * @param productID
     * @return
     */
    R detail(Integer productID);

    /**
     * 查询商品对应图片详情集合
     *
     * @param productID
     * @return
     */
    R pictures(Integer productID);

    /**
     * 搜索服务调用，获取全部商品数据集合
     * @return
     */
    List<Product> allList();

    /**
     * 搜索业务，需要调用搜索服务！
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品id集合查询商品信息
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);


    /**
     * 根据商品id，查询商品id集合
     * @param productIds
     * @return
     */
    List<Product> cartList(List<Integer> productIds);

    /**
     * 修改库存和增加销售量
     * @param orderToProducts
     */
    void subNumber(List<OrderToProduct> orderToProducts);
}
