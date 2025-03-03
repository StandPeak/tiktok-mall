package com.uestc.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uestc.param.OrderParam;
import com.uestc.pojo.Order;
import com.uestc.utils.R;

/**
 * @author llf
 * @school uestc
 */
public interface OrderService extends IService<Order> {

    /**
     *
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 分组查询购物车订单数据
     * @param userId
     * @return
     */
    R list(Integer userId);
}
