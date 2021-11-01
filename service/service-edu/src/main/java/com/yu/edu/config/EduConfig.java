package com.yu.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 22:15 2021/10/30
 * @Modified By:
 */
@Configuration
@MapperScan("com.yu.edu.mapper")
@ComponentScan("com.yu")
public class EduConfig {
    /**
     * 逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationIngerceptor()
    {
        return new PaginationInterceptor();
    }
}
