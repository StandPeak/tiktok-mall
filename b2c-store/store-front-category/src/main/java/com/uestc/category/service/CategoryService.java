package com.uestc.category.service;

import com.uestc.param.ProductHotParam;
import com.uestc.utils.R;

/**
 * @author llf
 * @school uestc
 */
public interface CategoryService {

    /**
     * 根据类别名称，查询类别对象
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据类别名称集合，查询类别对象，返回对应的id集合
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据进行返回
     * @return
     */
    R list();
}
