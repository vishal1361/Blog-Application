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
                .url("jdbc:postgresql://localhost:5432/blogDB")
                .username("postgres")
                .password("apple")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
