package com.uestc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.pojo.Address;
import com.uestc.user.mapper.AddressMapper;
import com.uestc.user.service.AddressService;
import com.uestc.user.service.UserService;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据用户id查询地址数据
     *
     * @param userId 用户id 已经校验完毕
     * @return
     */
    @Override
    public R list(Integer userId) {

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<Address> addresses = addressMapper.selectList(queryWrapper);

        R ok = R.ok("查询成功", addresses);
        log.info("AddressServiceImpl.list业务结束，结果:{}", ok);
        return ok;
    }

    /**
     * 插入地址数据，插入成功后，返回数据集合
     *
     * @param address
     * @return
     */
    @Override
    public R save(Address address) {

        int rows = addressMapper.insert(address);
        if (rows == 0) {
            log.info("AddressServiceImpl.save业务结束，结果:{}", "插入地址失败！");
            return R.fail("插入地址失败！");
        }

        return list(address.getUserId());
    }

    /**
     * 根据id 删除地址数据
     *
     * @param id 地址id
     * @return
     */
    @Override
    public R remove(Integer id) {

        int rows = addressMapper.deleteById(id);

        if (rows == 0) {
            log.info("AddressServiceImpl.remove业务结束，结果:{}", "删除地址数据失败！");
            return R.fail("删除地址数据失败！");
        }

        log.info("AddressServiceImpl.remove业务结束，结果:{}", "删除地址数据成功！");
        return R.ok("地址删除成功！");
    }
}
