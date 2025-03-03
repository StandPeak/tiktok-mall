package com.uestc.cart.service;

import com.uestc.param.CartSaveParam;
import com.uestc.pojo.Cart;
import com.uestc.utils.R;

import java.util.List;

/**
 * @author llf
 * @school uestc
 */
public interface CartService {


    /**
     * 购物车数据添加方法
     * @param cartSaveParam
     * @return 001 002
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 返回购物车数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 更新购物车业务
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应Id的购物车项
     * @param cartIds
     */
    void clearIds(List<Integer> cartIds);
}
