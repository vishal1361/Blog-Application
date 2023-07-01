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
                .url("jdbc:postgresql://dpg-cig6qpt9aq012eu6a2m0-a:5432/blogdb_v3wo")
                .username("blogdb_v3wo_user")
                .password("NnHlwM0eiufEanKC9d5Xx68KqGYS9yyc")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
