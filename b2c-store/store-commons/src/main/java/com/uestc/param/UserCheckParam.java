package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author llf
 * @school uestc
 * TODO: 要使用jsr 303的注解 进行参数校验
 */
@Data
public class UserCheckParam {

    @NotBlank
    private String userName;
}
