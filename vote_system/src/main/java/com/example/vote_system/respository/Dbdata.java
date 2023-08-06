package com.example.vote_system.respository;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Dbdata {
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/mydb")
                .username("username")
                .password("password")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}

