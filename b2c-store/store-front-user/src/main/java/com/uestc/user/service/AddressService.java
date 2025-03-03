package com.uestc.user.service;

import com.uestc.pojo.Address;
import com.uestc.utils.R;

/**
 * @author llf
 * @school uestc
 */
public interface AddressService {

    /**
     * 根据用户id查询地址数据
     * @param userId 用户id 已经校验完毕
     * @return
     */
    R list(Integer userId);

    /**
     * 插入地址数据，插入成功后，返回数据集合
     * @param address
     * @return
     */
    R save(Address address);

    /**
     * 根据id 删除地址数据
     * @param id 地址id
     * @return
     */
    R remove(Integer id);
}
