package com.github.gsq.auth;

import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.springframework.dao.DataAccessException;

import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

public class MyUserAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    private static String sql = "select count(*) from sys_users where username='%s' and password='%s'";

    public MyUserAuthenticationHandler() {
    }

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential usernamePasswordCredential) throws GeneralSecurityException, PreventedException {
        // 用户名
        String username = usernamePasswordCredential.getUsername();
        // 密码
        String password = usernamePasswordCredential.getPassword();

        if (StringUtils.isEmpty(password)) {
            throw new FailedLoginException(username + " password is null");
        }

        int count;
        String execSql = String.format(sql, username, password);
        try {
            count = getJdbcTemplate().queryForObject(execSql, Integer.class);
        } catch (DataAccessException e) {
            throw new PreventedException("sql exec exception, execSql = " + execSql, e);
        }

        if (count == 0) {
            throw new FailedLoginException(username + " failed login");
        }

        // 返回用户信息
        return createHandlerResult(usernamePasswordCredential, this.principalFactory.createPrincipal(username), null);
    }
}
