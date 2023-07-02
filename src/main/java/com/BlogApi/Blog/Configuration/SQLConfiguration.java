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
                .url("jdbc:postgresql://dpg-cigss9l9aq012ev9h9c0-a:5432/blogdb_paxg")
                .username("blogdb_paxg_user")
                .password("NWLQTqmUu3CFV1UU3mNFlU81CGJebNS8")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
