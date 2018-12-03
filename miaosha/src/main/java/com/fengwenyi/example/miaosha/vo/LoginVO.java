package com.fengwenyi.example.miaosha.vo;

import com.fengwenyi.example.miaosha.validator.IsMobile;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author Wenyi Feng
 * @since 2018-12-01
 */
public class LoginVO {

    @NotNull
    @IsMobile
    private String mobile;

    @NotNull
    @Length(min = 32)
    private String password;

}
