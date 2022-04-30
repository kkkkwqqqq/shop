package com.fight.service.impl;

import com.fight.mapper.UserMapper;
import com.fight.pojo.User;
import com.fight.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        System.out.println(userMapper);
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

    @Override
    public User selectByNameAndPwd(String username, String password) {
        return userMapper.selectByNameAndPwd(username,password);
    }

   /* @Override
    public Integer selectUserStatusById(String status, Integer userid) {
        return userMapper.updateUserStatus(status,userid);
    }*/

    @Override
    public List<Integer> seleceAllUserId() {
        return userMapper.seleceAllUserId();
    }

    @Override
    public User selectGlyByNameAndPwd(String username, String password) {
        return userMapper.selectGlyByNameAndPwd(username,password);
    }

    @Override
    public String selectNameById(int userid) {
        return userMapper.selectNameById(userid);
    }
}
