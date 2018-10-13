package io.amoe.pittspid;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@MapperScan("io.amoe.pittspid.*.mapper")

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
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        //可以通过环境变量获取你的mapper路径,这样mapper扫描可以通过配置文件配置了
        scannerConfigurer.setBasePackage("io.amoe.pittspid.*.mapper");
        return scannerConfigurer;
    }
}
