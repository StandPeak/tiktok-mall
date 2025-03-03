package com.uestc.param;

import lombok.Data;

/**
 * @author llf
 * @school uestc
 */
@Data
public class PageParam {

    private int currentPage = 1;
    private int pageSize = 15;
}
