package com;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class JpaConfig {

    @Bean
    public DataSource postgresDataSource() {
        return new DriverManagerDataSource();
    }

}
