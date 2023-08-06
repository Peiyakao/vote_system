package com.example.vote_system.respository;

import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
@Component
public class DboneConn {
    private JdbcTemplate jdbcTemplate;
    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
