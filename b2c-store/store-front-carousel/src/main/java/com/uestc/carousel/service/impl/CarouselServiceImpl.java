package com.uestc.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.carousel.mapper.CarouselMapper;
import com.uestc.carousel.service.CarouselService;
import com.uestc.pojo.Carousel;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 查询优先级最高的六条轮播图数据
     * 使用stream流，进行内存数据切割，保留6条数据
     * @return
     */
    @Cacheable(value = "list.carousel", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public R list() {
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("priority");

        List<Carousel> list = carouselMapper.selectList(queryWrapper);

        List<Carousel> collect = list.stream().limit(6).collect(Collectors.toList());

        R ok = R.ok(collect);

        log.info("CarouselServiceImpl.list业务结束，结果:{}", "ok");
        return ok;
    }
}
