package com.uestc.doc;

import com.uestc.pojo.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author llf
 * @school uestc
 */

/**
 * 存储商品搜索数据的实体类
 */
@Data
public class ProductDoc extends Product {

    /**
     * 商品名称和商品标题和商品描述的综合值
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(), product.getProductName(), product.getCategoryId(),
                product.getProductTitle(), product.getProductIntro(), product.getProductPicture(),
                product.getProductPrice(), product.getProductSellingPrice(), product.getProductNum(),
                product.getProductSales());

        this.all = product.getProductName() + product.getProductId() + product.getProductTitle() +
                getProductIntro() + product.getCategoryId() + product.getProductPicture() +
                product.getProductNum() + product.getProductSales() + product.getProductPrice() +
                product.getProductSellingPrice();
    }
}
