package com.uestc.search.service;

import com.uestc.param.ProductSearchParam;
import com.uestc.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author llf
 * @school uestc
 */
public interface SearchService {

    /**
     * 根据关键字和分页进行数据库查询
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);
}
