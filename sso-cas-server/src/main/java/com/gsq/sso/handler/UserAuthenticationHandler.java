package com.gsq.sso.handler;

import com.gsq.sso.dao.UserDaoJdbc;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

/**
 * Created by Administrator on 2017/11/15.
 * 自定义用户认证
 */
@Component(value="primaryAuthenticationHandler")
public class UserAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    @Resource
    private UserDaoJdbc userDaoJdbc;

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential) throws GeneralSecurityException, PreventedException {
        //UsernamePasswordCredential参数包含了前台页面输入的用户信息
        String username = transformedCredential.getUsername();
        String password = transformedCredential.getPassword();
        //认证用户名和密码是否正确
        if(userDaoJdbc.verifyAccount(username, password)){
            System.out.println("登录失败!");
            return createHandlerResult(transformedCredential, new SimplePrincipal(username), null);
        }
        System.out.println("登录成功!");
        throw new FailedLoginException();
    }
}
