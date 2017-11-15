package com.gsq.sso.dao;

import com.gsq.sso.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/11/15.
 */
@Repository
public class UserDaoJdbc {

    private static final String SQL_VERIFY_ACCOUNT = "SELECT COUNT(*) FROM sys_users WHERE username=? AND password=?";
    private static final String SQL_USER_GET = "SELECT * FROM sys_users WHERE username=?";
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

    public User getByUsername(String username){
        try{
            //根据用户名获取用户信息
            return (User)this.jdbcTemplate.queryForObject(SQL_USER_GET, new Object[]{username}, new UserRowMapper());
        }catch(EmptyResultDataAccessException e){
            return new User();
        }
    }
}

class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int index) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setLocked(rs.getInt("locked"));
        return user;
    }
}
