package com.fight.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {
    @Bean
    //ShiroFilerFactoryBean：3
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加内置过滤器
        /*
        * anon：无需认证就可以访问
        * authc：必须认证了才能访问
        */
        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权，正常情况下会跳转到未授权页面
         filterMap.put("/user/*", "authc");
         filterMap.put("/Commodity/*", "authc");

        bean.setFilterChainDefinitionMap(filterMap);

        return bean;
    }





    //DafaultWebSecurityManager:2
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象，需要自定义类:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


}
