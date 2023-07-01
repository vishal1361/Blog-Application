package com.BlogApi.Blog.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
@Configuration
public class SQLConfiguration {
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:postgresql://dpg-cig1lat9aq012et1g3ug-a:5432/blogdb_lqtr")
                .username("blogdb_lqtr_user")
                .password("MoyWABvESn1ZFGJmITkNRaQh4TUgKSkv")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
