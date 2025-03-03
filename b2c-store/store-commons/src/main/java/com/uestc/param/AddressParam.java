package com.uestc.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uestc.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author llf
 * @school uestc
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}
