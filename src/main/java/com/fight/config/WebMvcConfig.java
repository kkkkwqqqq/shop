/*
package com.fight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public MiniInterceptor miniInterceptor(){
        return new MiniInterceptor();
    }


    */
/**
     * 设置拦截的url路径
     *  暂时只针对前端用户评论、收藏、评分功能进行拦截
     * @param registry
     *//*

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List listOfVerify = Arrays.asList("/consumer/**","/rank/rateSongList","/collect/**","/comment/**");
        List listOfExc = Arrays.asList("/consumer/login","/consumer/queryUserById","/consumer/getAllConsumer","/collect/getUserCollect","/comment/query**","/comment/allComment");
        registry.addInterceptor(miniInterceptor()).addPathPatterns(listOfVerify)
                .excludePathPatterns(listOfExc);
        super.addInterceptors(registry);
    }
}
*/
