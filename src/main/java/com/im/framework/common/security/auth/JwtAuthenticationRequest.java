package com.im.framework.common.security.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class JwtAuthenticationRequest {

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("验证码")
    private String verifycode;

}
