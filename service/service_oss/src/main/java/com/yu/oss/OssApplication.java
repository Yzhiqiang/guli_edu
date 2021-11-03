package com.yu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:15 2021/11/2
 * @Modified By:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)   //让数据源不自动配置
@ComponentScan(basePackages = {"com.yu"})
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}
