package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author llf
 * @school uestc
 */
@Data
public class ProductIdParam {

    @NotNull
    private Integer productID;
}
