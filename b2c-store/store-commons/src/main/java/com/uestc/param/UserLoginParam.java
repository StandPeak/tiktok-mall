package com.uestc.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author llf
 * @school uestc
 */
@Data
public class UserLoginParam {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;


}
