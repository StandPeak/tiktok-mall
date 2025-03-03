package com.uestc.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author llf
 * @school uestc
 */
@Data
public class CartSaveParam {

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

}
