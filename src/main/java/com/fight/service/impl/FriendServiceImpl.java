package com.fight.service.impl;

import com.fight.mapper.FriendsMapper;
import com.fight.pojo.Friends;
import com.fight.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendsMapper friendsMapper;

    @Override
    public Integer addFriendWithBusinesses(Integer userid, Integer fuserid, String username, String sjName) {
        return friendsMapper.addFriendWithBusinesses(userid,fuserid,username,sjName);
    }

    @Override
    public Friends isExistFriend(String userId, Integer fuserid) {
        return friendsMapper.isExistFriend(userId,fuserid);
    }

    @Override
    public List<Friends> showFriends(int userid) {
        return friendsMapper.showFriends(userid);
    }
}
