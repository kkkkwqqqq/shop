package com.fight.controller.AdminController;

import com.fight.Constants.Constants;
import com.fight.pojo.Commodity;
import com.fight.pojo.User;
import com.fight.service.CommimagesService;
import com.fight.service.CommodityService;
import com.fight.service.SoldrecordService;
import com.fight.service.UserService;
import com.fight.util.JwtUtil;
import com.fight.vo.Result1;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AddminCommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate redisTemplate;
    @Autowired
    private CommimagesService commimagesService;




    //管理员登录
    //验证用户登录
    @RequestMapping(value = "/Login")
    @ResponseBody
    public Object adminLogin(String username, String password, String code) {
        if (username == null || password == null) {
            return new ResultVo(Constants.FAILED_STATUS, "账号或者密码不能为空");
        }
        //去数据库中查询数据
        User user = userService.selectGlyByNameAndPwd(username, password);
        if (user == null) {
            return new ResultVo(Constants.FAILED_STATUS, "非管理员账号");
        }
        String userId = user.getUserid() + "";

        //准备存放在IWT中的自定义数据
        Map<String, Object> info = new HashMap<>();
        info.put("username", username);
        Integer degree = user.getDegree();
        info.put("degree", degree);
        /*info.put("password", password);*/

        //生成JWT字符串
        String token = JwtUtil.sign(userId, info);
        //将信息放到redis中
      /*  redisTemplate.opsForValue().set(userId, token.toString(), 60 * 60 * 1000);*/
        List<Object> list = new ArrayList<Object>();
        user.setStatus("online");
        return new Result1(Constants.SUCCESS_STATUS, "登录成功", token, user);
    }

    //管理员查看商品清单（所有）
    @RequestMapping("/selectAllPro")
    public Object selectMyProByid(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getInfo(token);
        Integer degree = (Integer) info.get("degree");
        if (degree == null || degree == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "权限不足");
        }
        List<Commodity> commodities = commodityService.selectAllPro();
        return new ResultVo(Constants.SUCCESS_STATUS, "查询成功", commodities);
    }

    //管理员查看商品清单（未审核）
    @RequestMapping("/selectweishenPro")
    public Object selectweishenPro(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getInfo(token);
        Integer degree = (Integer) info.get("degree");
        if (degree == null || degree == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "权限不足");
        }
        List<Commodity> commodities = commodityService.selectweishenPro(0);
        return new ResultVo(Constants.SUCCESS_STATUS, "查询成功", commodities);
    }

    //管理员删除商品
    @RequestMapping("/deleteMyProByid")
    public Object deleteMyProByid(HttpServletRequest request, Integer commid) {
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getInfo(token);
        Integer degree = (Integer) info.get("degree");
        if (degree == null || degree == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "权限不足");
        }

        Integer count = commimagesService.deleteImagByCommid(commid);

        //再删商品
        Integer count1 = commodityService.deleteProByid1(commid);

        if (count1 == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "删除成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "删除失败");
        }
    }

    //管理员通过商品
    @RequestMapping("/passPro")
    public Object passPro(HttpServletRequest request, Integer commid) {
        String token = request.getHeader("token");
        Map<String, Object> info = JwtUtil.getInfo(token);
        Integer degree = (Integer) info.get("degree");
        if (degree == null || degree == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "权限不足");
        }
        Integer count = commodityService.updateMyProByAdmin(commid);
        if (count == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "修改成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "修改失败");
        }

    }

    //管理员退出登录
    @ResponseBody
    @RequestMapping(value = "/logout")   //前端需要传入用户id
    public ResultVo logout(HttpServletRequest request) {
        //修改用户状态为offline
      /*  user.setStatus("offline");
        userService.updateUser(user);*/
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        redisTemplate.delete(userId + "");
        return new ResultVo(Constants.SUCCESS_STATUS, "退出成功");
    }
}
