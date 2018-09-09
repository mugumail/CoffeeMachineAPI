package com.mugu.coffee.config;

import com.mugu.coffee.common.ConvertSqlDialectToDerby;
import com.mugu.coffee.common.ItemsDataRowMapper;
import com.mugu.coffee.service.CoffeeMachineService;
import java.util.Properties;
import java.util.UUID;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("test")
@EnableTransactionManagement
@ComponentScan(basePackages={"com.mugu"}, excludeFilters =
@ComponentScan.Filter(value = SpringBootApplication.class, type = FilterType.ANNOTATION))
public class TestContextConfiguration {

    @Bean
    public DriverManagerDataSource dataSource() {
        Properties p = new Properties();
        p.setProperty("driverClassName", "org.apache.derby.jdbc.EmbeddedDriver");
        String url = String.format("jdbc:derby:memory:%s;create=true", UUID.randomUUID().toString());
        return new DriverManagerDataSource(url, p);
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public ItemsDataRowMapper dataRowMapper(){
        return new ItemsDataRowMapper();
    }

    @Bean
    public CoffeeMachineService coffeeMachineService() {
        return new CoffeeMachineService(jdbcTemplate(), dataRowMapper());
    }

}
