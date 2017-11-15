package com.gsq.sso.credential;

import org.jasig.cas.authentication.UsernamePasswordCredential;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2017/11/15.
 * 自定义用户名密码验证码接受实体
 */
public class UsernamePasswordCaptchaCredential extends UsernamePasswordCredential {

    private static final long serialVersionUID = 8317889802836113837L;

    @NotNull
    @Size(
        min = 1,
        message = "required.captcha"
    )
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "UsernamePasswordCaptchaCredential{" +
                "username='" + getUsername() + '\'' +
                "password='" + getPassword() + '\'' +
                "captcha='" + captcha + '\'' +
                '}';
    }
}
