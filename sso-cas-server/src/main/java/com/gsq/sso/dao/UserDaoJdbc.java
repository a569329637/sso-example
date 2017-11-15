package com.gsq.sso.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/11/15.
 */
@Repository
public class UserDaoJdbc {
    private static final String SQL_VERIFY_ACCOUNT = "SELECT COUNT(*) FROM sys_users WHERE username=? AND password=?";
    private JdbcTemplate jdbcTemplate;
    @Resource
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public boolean verifyAccount(String username, String password){
        try{
            //验证用户名和密码是否正确
            return 1==this.jdbcTemplate.queryForObject(SQL_VERIFY_ACCOUNT, new Object[]{username, password}, Integer.class);
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }
}
