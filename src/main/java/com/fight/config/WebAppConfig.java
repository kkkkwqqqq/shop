package com.fight.config;

import com.fight.Constants.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//添加图片资源隐射
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:"+Constants.RESOURCE_WIN_PATH+"/img/");
        registry.addResourceHandler("/user/**")
                .addResourceLocations("file:"+Constants.RESOURCE_WIN_PATH+"/user/");
        registry.addResourceHandler("/view/**")
                .addResourceLocations("file:"+Constants.RESOURCE_WIN_PATH+"/view/");
        super.addResourceHandlers(registry);
    }
}
