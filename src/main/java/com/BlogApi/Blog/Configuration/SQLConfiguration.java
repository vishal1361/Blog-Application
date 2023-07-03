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
                .url("jdbc:postgresql://dpg-cih6ud59aq012eqtbb9g-a:5432/blogdb_nhg9")
                .username("blogdb_nhg9_user")
                .password("47jpkMi32brJBapTk8txP6JUjBoGcpKx")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
