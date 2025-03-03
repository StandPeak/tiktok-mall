package com.uestc.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author llf
 * @school uestc
 */
@Data
public class CartListParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
}
