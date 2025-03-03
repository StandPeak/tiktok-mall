package com.uestc.clients;

import com.uestc.param.ProductCollectParam;
import com.uestc.param.ProductIdParam;
import com.uestc.pojo.Product;
import com.uestc.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@FeignClient(value = "product-service")
public interface ProductClient {

    /**
     * 搜索服务调用进行全部数据查询，用于搜索数据库同步数据
     *
     * @return
     */
    @GetMapping("/product/list")
    List<Product> allList();

    @PostMapping("/product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("/product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    @PostMapping("/product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);
}
