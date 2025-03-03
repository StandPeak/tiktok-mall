package com.uestc.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uestc.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author llf
 * @school uestc
 */
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersion = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;
}
