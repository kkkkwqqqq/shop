package com.fight.service.impl;

import com.fight.mapper.UserMapper;
import com.fight.pojo.User;
import com.fight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
      return  userMapper.login(username,password);
    }

    @Override
    public boolean register(User user) {
       if(userMapper.insertSelective(user)==1){
            return true;
        }
       return false;
    }

    @Override
    public User nameExornot(String username) {
        User user = userMapper.nameExornot(username);
        if (user==null){
            return null;
        }else {
            return user;
        }
    }

    @Override
    public Integer updateUser(User user) {
       return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }
}
