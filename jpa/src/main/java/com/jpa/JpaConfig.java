package com.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

    @Bean
    public DataSource postgresDataSource() {
        return new DriverManagerDataSource();
    }
}
