package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author llf
 * @school uestc
 */
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
