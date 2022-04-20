package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.exception.ShopException;
import com.fight.pojo.Friends;
import com.fight.service.FriendService;
import com.fight.util.JwtUtil;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class FriendController {
    @Autowired
  private FriendService friendService;

    //添加好友
    @RequestMapping("/addFriendWithBusinesses")
    @ResponseBody
    @Transactional
    public ResultVo addFriendWithBusinesses(HttpServletRequest request, Integer fuserid,String sjName) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        int userid = Integer.parseInt(userId);
        Map<String, Object> info = JwtUtil.getInfo(token);
        String username = (String)info.get("username");
        //不能添加自己为好友
        if (userid == fuserid) {
            return new ResultVo(Constants.FAILED_STATUS, "不能对自己的商品感兴趣");
        }
        //判断两人是否已经是好友了
        Friends friend = friendService.isExistFriend(userId, fuserid);
        if (friend!=null){
        }
        else {
            //添加两人为好友
        Integer count= friendService.addFriendWithBusinesses(userid,fuserid,username,sjName);
        if (count==0){
            throw new ShopException(Constants.FAILED_STATUS,"添加好友失败");
        }
        Integer count1= friendService.addFriendWithBusinesses(fuserid,userid,sjName,username);
        if (count1==0){
            throw new ShopException(Constants.FAILED_STATUS,"添加好友失败");
        }
        }

        return new ResultVo(Constants.SUCCESS_STATUS,"添加好友成功");
    }


    //展示好友列表
    @RequestMapping("/showFriends")
    @ResponseBody
    public ResultVo showFriends(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        int userid = Integer.parseInt(userId);
       //根据用户id查询所有的好友
        List<Friends> friends=friendService.showFriends(userid);
        return new ResultVo(Constants.SUCCESS_STATUS,"成功返回好友列表",friends);

    }

}
