package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.entity.User;
import com.atguigu.springcloud.exception.DatabaseOperateException;
import com.atguigu.springcloud.util.EntityFieldUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {


    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_COLS = EntityFieldUtil.fieldSplit(User.class, ",");
    private static final String WILDCARDS = EntityFieldUtil.wildcardSplit(User.class, ",");
    private static final String TABLE_NAME = "USER";

    public User getUser(Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select ");
        sb.append(SQL_COLS);
        sb.append(" from ");
        sb.append(TABLE_NAME);
        sb.append(" where id = ? ");
        User user = jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        if (user == null) {
            throw new DatabaseOperateException();
        }
        return user;
    }

    public Integer insertUser(User entity){
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(TABLE_NAME);
        sb.append("(");
        sb.append(SQL_COLS);
        sb.append(") values (");
        sb.append(WILDCARDS);
        sb.append(")");
        int row = jdbcTemplate.update(sb.toString(),EntityFieldUtil.fieldSplitValue(User.class, entity));
        if (row <= 0){
            throw new DatabaseOperateException();
        }
        return row;
    }
}
