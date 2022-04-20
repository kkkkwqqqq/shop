/*
package com.fight.shiro;

import com.fight.pojo.User;
import com.fight.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义 UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthorizationInfo");
        //用户名 密码 数据库中取
     */
/*   String name="root";
        String password="123456";*//*

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //去数据库中查
        User user = userService.selectByName(userToken.getUsername());
        if(user==null){
            return null; //抛出异常   UnknownAccountException
        }
        //密码认证，shiro帮我们做
        return new SimpleAuthenticationInfo("",user.getPassword(),"");

    }
}

*/
