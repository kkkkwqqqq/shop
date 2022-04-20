package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.pojo.User;
import com.fight.service.UserService;

import com.fight.util.JustPhone;
import com.fight.util.JwtUtil;
import com.fight.util.ValidateCode;
import com.fight.vo.Result1;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    @Qualifier("redisTemplate1")
    private RedisTemplate redisTemplate;


    @GetMapping("/getCodeImg")
    /**
     *图片验证码
     * */
    public void images(HttpServletResponse response) throws IOException {
        System.out.println("aa");
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ValidateCode vCode = new ValidateCode(820, Constants.SUCCESS_STATUS, 5, 80);
        vCode.write(response.getOutputStream());
    }




    //用户注册
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)

    public ResultVo register(HttpServletRequest req) {
     /*   String code = req.getParameter("code");
        if (code == null) {
            return new ResultVo(Constants.FAILED_STATUS, "请输入验证码");
        }*/
       /* if (ValidateCode.code == null || !ValidateCode.code.equals(code)) {
            return new ResultVo(Constants.FAILED_STATUS, "验证码错误，请重新输入");
        }*/
        String username = req.getParameter("username");
        if (userService.nameExornot(username) != null) {
            return new ResultVo(Constants.FAILED_STATUS, "用户名已被占用");
        }
        String password = req.getParameter("password");
        if (username == null || password == null) {
            return new ResultVo(Constants.FAILED_STATUS, "账号或者密码不能为空");
        }

        String mobilephone = req.getParameter("mobilephone");
        if (!JustPhone.isChinaPhoneLegal(mobilephone)) {
            return new ResultVo(Constants.FAILED_STATUS, "手机号码不合法，请重新输入");
        }

        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        User user = new User();
        user.setSex(sex);
        System.out.println(password);
        user.setEmail(email);
        user.setMobilephone(mobilephone);
        user.setUsername(username);
        user.setPassword(password);

        if (userService.register(user)) {
            return new ResultVo(Constants.SUCCESS_STATUS, "注册成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "注册失败");
        }


    }

    //验证用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(String username,String password,String code) {
        if (username == null || password == null) {
            return new ResultVo(Constants.FAILED_STATUS, "账号或者密码不能为空");
        }
        //去数据库中查询数据
        User user = userService.selectByNameAndPwd(username, password);
        if (user == null) {
            return new ResultVo(Constants.FAILED_STATUS, "账号或密码错误");
        }
        String userId = user.getUserid() + "";

        //准备存放在IWT中的自定义数据
        Map<String, Object> info = new HashMap<>();
        info.put("username", username);
        /*info.put("password", password);*/

        //生成JWT字符串
        String token = JwtUtil.sign(userId, info);
        //将信息放到redis中
        redisTemplate.opsForValue().set(userId,token.toString(),60*60*1000);

        List<Object> list = new ArrayList<Object>();
        user.setStatus("online");
    return new Result1(Constants.SUCCESS_STATUS,"登录成功",token,user);
    }



    //修改用户信息
   /* @ResponseBody
    @RequestMapping(value = "/user/update")
    public ResultVo update(HttpServletRequest req) {
        String username = req.getParameter("username");
        String userId1 = req.getParameter("userId");
        System.out.println(userId1);
        System.out.println(username);
        if (userId1 == null) {
            return new ResultVo(Constants.FAILED_STATUS, "用户id不能为空");
        }
        Integer userId = Integer.valueOf(userId1);
        String password = req.getParameter("password");
        if (username == null) {
            return new ResultVo(Constants.FAILED_STATUS, "账号不能为空");
        }
        if (password == null) {
            return new ResultVo(Constants.FAILED_STATUS, "密码不能为空");
        }

        String mobilephone = req.getParameter("mobilephone");
        if (!JustPhone.isChinaPhoneLegal(mobilephone)) {
            return new ResultVo(Constants.FAILED_STATUS, "手机号码不合法，请重新输入");
        }

        String sex = req.getParameter("sex");
        if (sex == null) {
            return new ResultVo(Constants.FAILED_STATUS, "性别不能为空");
        }
        String email = req.getParameter("email");
        if (email == null) {
            return new ResultVo(Constants.FAILED_STATUS, "邮箱不能为空");
        }
        User user = new User();
        user.setSex(sex);
        user.setEmail(email);
        user.setMobilephone(mobilephone);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserid(userId);

        Integer count = userService.updateUser(user);
        if (count == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "修改失败");
        } else {
            return new ResultVo(Constants.SUCCESS_STATUS, "修改成功", user);
        }


    }*/

    //    更新用户头像
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update")
    public Object updateUserPic(HttpServletRequest request,@RequestParam("file") MultipartFile avatorFile) {
        if (avatorFile.isEmpty()) {
            return new ResultVo(Constants.FAILED_STATUS, "文件上传失败");

        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "user" + System.getProperty("file.separator");
        System.out.println(filePath);
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + "" + fileName);
        String imagPath = "/user/" + fileName;
        try {
            avatorFile.transferTo(dest);
            User user = new User();
            /*防止篡改他人数据*/
           /* user.setUserid(userId);*/
            String token = request.getHeader("token");
            String userId = JwtUtil.getUserId(token);
            user.setUserid(Integer.parseInt(userId));
            user.setUimage(imagPath);
            Integer count = userService.updateUser(user);
            if (count == 1) {
                return new ResultVo(Constants.SUCCESS_STATUS, "上传成功");
            } else {
                return new ResultVo(Constants.FAILED_STATUS, "上传失败");
            }
        } catch (IOException e) {
            return new ResultVo(Constants.FAILED_STATUS, "上传失败");
        }
    }
    //修改用户信息
    @ResponseBody
    @RequestMapping(value = "/user/update")
    public ResultVo update(HttpServletRequest request,User user) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        user.setUserid(Integer.parseInt(userId));

        if (user.getUsername() == null) {
            return new ResultVo(Constants.FAILED_STATUS, "账号不能为空");
        }

        if (!JustPhone.isChinaPhoneLegal(user.getMobilephone())) {
            return new ResultVo(Constants.FAILED_STATUS, "手机号码不合法，请重新输入");
        }

        System.out.println(userService);
        Integer count = userService.updateUser(user);
        user.setPassword(null);
        if (count == 0) {
            return new ResultVo(Constants.FAILED_STATUS, "修改失败");
        } else {
            return new ResultVo(Constants.SUCCESS_STATUS, "修改成功", user);
        }


    }

    //用户退出
    @ResponseBody
    @RequestMapping(value = "/user/logout")   //前端需要传入用户id
    public ResultVo logout(HttpServletRequest request) {
       //修改用户状态为offline
      /*  user.setStatus("offline");
        userService.updateUser(user);*/
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        redisTemplate.delete(userId+"");
        return new ResultVo(Constants.SUCCESS_STATUS,"退出成功");
    }
}













