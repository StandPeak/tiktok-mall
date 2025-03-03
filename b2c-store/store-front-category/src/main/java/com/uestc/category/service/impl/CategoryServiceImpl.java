package com.uestc.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.uestc.category.mapper.CategoryMapper;
import com.uestc.category.service.CategoryService;
import com.uestc.pojo.Category;
import com.uestc.param.ProductHotParam;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.logging.LoggingRebinder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private LoggingRebinder loggingRebinder;

    /**
     * 根据类别名称，查询类别对象
     *
     * @param categoryName
     * @return
     */
    @Override
    public R byName(String categoryName) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName);

        Category category = categoryMapper.selectOne(queryWrapper);
        if (category == null) {

            log.info("CategoryServiceImpl.byName业务结束，结果:{}","类别查询失败！");
            return R.fail("类别查询失败！");
        }
        log.info("CategoryServiceImpl.byName业务结束，结果:{}", "类别查询成功！");
        return R.ok("类别查询成功！", category);
    }

    /**
     * 根据类别名称集合，查询类别对象，返回对应的id集合
     *
     * @param productHotParam
     * @return
     */
    @Override
    public R hotsCategory(ProductHotParam productHotParam) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name", productHotParam.getCategoryName());
        queryWrapper.select("category_id");

        List<Object> objects = categoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别集合查询成功", objects);
        log.info("CategoryServiceImpl.hotsCategory业务结束，结果:{}",ok);
        return ok;

    }

    /**
     * 查询类别数据进行返回
     *
     * @return
     */
    @Override
    public R list() {

        List<Category> categoryList = categoryMapper.selectList(null);

        R ok = R.ok("类别全部数据查询成功!", categoryList);
        log.info("CategoryServiceImpl.list业务结束，结果:{}", ok);
        return ok;
    }
}
