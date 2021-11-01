package com.yu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 22:11 2021/10/30
 * @Modified By:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yu"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
