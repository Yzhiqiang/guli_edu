package com.yu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:38 2021/11/13
 * @Modified By:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)    //默认不加载数据库
@ComponentScan(basePackages = {"com.yu"})
public class Vodapplication {
    public static void main(String[] args) {
        SpringApplication.run(Vodapplication.class, args);
    }
}
