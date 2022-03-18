package com.fight.mapper;

import com.fight.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);
/*注册*/
    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);
//修改用户信息
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(String username,String password);

    User nameExornot(String name);

    List<User> rowBounds(String type);

    /*根据用户名查询是否存在账户*/
    User selectByName(String username);




}
