package com.fight.service;

import com.fight.pojo.User;

public interface UserService {
    //用户登录
    public User login(String username,String password);
    // 用户注册
  public boolean  register(User user);
  //判断是否用户名存在
    public User nameExornot(String username);

    //修改用户信息
    public Integer updateUser(User user);

    /*根据用户名查询是否存在账户*/
    User selectByName(String username);
}
