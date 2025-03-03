package com.uestc.product.controller;

import com.uestc.pojo.Product;
import com.uestc.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@RestController
@RequestMapping("product")
public class ProductSearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public List<Product> allList() {

        return productService.allList();
    }
}
