package com.uestc.category.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.uestc.category.service.CategoryService;
import com.uestc.param.ProductHotParam;
import com.uestc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author llf
 * @school uestc
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R byName(@PathVariable String categoryName) {

        if (StringUtils.isEmpty(categoryName)) {
            return R.fail("数据类别为null, 无法查询类别数据！");
        }

        return categoryService.byName(categoryName);
    }

    @PostMapping("hots")
    public R hostCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("类别集合查询失败！");
        }

        return categoryService.hotsCategory(productHotParam);
    }

    @GetMapping("list")
    public R list() {

        return categoryService.list();
    }

}
