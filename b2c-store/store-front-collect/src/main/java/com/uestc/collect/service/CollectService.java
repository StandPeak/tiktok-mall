package com.uestc.collect.service;

import com.uestc.pojo.Collect;
import com.uestc.utils.R;

/**
 * @author llf
 * @school uestc
 */
public interface CollectService {

    /**
     * 收藏添加的方法
     * @param collect
     * @return 001 004
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品集合
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id和商品Id删除收藏
     * @param collect
     * @return
     */
    R remove(Collect collect);
}
