package com.fight.mapper;

import com.fight.pojo.Friends;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friends record);

    int insertSelective(Friends record);

    Friends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friends record);

    int updateByPrimaryKey(Friends record);

    Integer addFriendWithBusinesses(Integer userid, Integer fuserid, String username, String sjName);

    Friends isExistFriend(String userId, Integer fuserid);

    List<Friends> showFriends(int userid);
}
