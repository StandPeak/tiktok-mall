package com.uestc.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.clients.ProductClient;
import com.uestc.collect.mapper.CollectMapper;
import com.uestc.collect.service.CollectService;
import com.uestc.param.ProductCollectParam;
import com.uestc.pojo.Collect;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 收藏添加的方法
     *
     * @param collect
     * @return 001 004
     */
    @Override
    public R save(Collect collect) {

        // 先查询是否存在
        QueryWrapper<Collect> queryWrapper =
                new QueryWrapper<>();

        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("product_id", collect.getProductId());

        Long count = collectMapper.selectCount(queryWrapper);

        if(count > 0) {
            return R.fail("收藏已经添加，无需二次添加！");
        }

        // 不存在进行添加
        // 补充下时间

        collect.setCollectTime(System.currentTimeMillis());
        int rows = collectMapper.insert(collect);
        log.info("CollectServiceImpl.save业务结束，结果:{}", rows);
        return R.ok("收藏添加成功！");
    }

    /**
     * 根据用户id查询商品集合
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {

        QueryWrapper<Collect> queryWrapper =
                new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.select("product_id");

        List<Object> objects = collectMapper.selectObjs(queryWrapper);

        ProductCollectParam productCollectParam = new ProductCollectParam();

        ArrayList<Integer> ids = new ArrayList<>();
        for (Object o : objects) {
            ids.add((Integer) o);
        }
        productCollectParam.setProductIds(ids);

        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束，结果:{}", r);
        return r;
    }

    /**
     * 根据用户id和商品Id删除收藏
     *
     * @param collect
     * @return
     */
    @Override
    public R remove(Collect collect) {

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("product_id", collect.getProductId());

        int delete = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.remove业务结束，结果:{}",delete);
        return R.ok("收藏移除成功！");
    }
}
