package com.yu.eduorder.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 20:31 2021/11/25
 * @Modified By:
 */
@Configuration
@MapperScan("com.yu.eduorder.mapper")
public class GlobalConfig {

}
