package com.uestc.cart.controller;

import com.uestc.cart.service.CartService;
import com.uestc.param.CartListParam;
import com.uestc.param.CartSaveParam;
import com.uestc.pojo.Cart;
import com.uestc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author llf
 * @school uestc
 */
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("save")
    public R save(@RequestBody @Validated CartSaveParam cartSaveParam,
                  BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("核心参数为空，添加失败！");
        }

        return cartService.save(cartSaveParam);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("购物车数据查询失败！");
        }

        return cartService.list(cartListParam.getUserId());
    }

    @PostMapping("update")
    public R update(@RequestBody Cart cart) {

        return cartService.update(cart);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Cart cart) {

        return cartService.remove(cart);
    }
}
