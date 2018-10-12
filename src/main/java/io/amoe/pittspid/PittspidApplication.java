package io.amoe.pittspid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan

@SpringBootApplication
public class PittspidApplication {
    public static void main(String[] args) {
        SpringApplication.run(PittspidApplication.class, args);
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/pittsp?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setUsername( "root" );
        dataSource.setPassword( "-798019912w21c12" );
        return dataSource;
    }
}
