package com.fight.service;

import com.fight.pojo.Friends;

import java.util.List;

public interface FriendService {
    Integer addFriendWithBusinesses(Integer userid, Integer fuserid, String username, String sjName);

    Friends isExistFriend(String userId, Integer fuserid);

    List<Friends> showFriends(int userid);

}
