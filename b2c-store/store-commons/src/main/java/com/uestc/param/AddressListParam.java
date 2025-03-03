package com.uestc.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author llf
 * @school uestc
 */
@Data
public class AddressListParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
