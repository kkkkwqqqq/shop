package com.fight.controller;

import com.fight.pojo.User;
import com.fight.service.UserService;

import com.fight.util.JustPhone;
import com.fight.util.ValidateCode;
import com.fight.vo.ResultVo;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@RestController
@Api(value = "测试接口", tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;


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
        ValidateCode vCode = new ValidateCode(820, 200, 5, 80);
        vCode.write(response.getOutputStream());
    }


  /*  //验证用户登录

    @ResponseBody
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public ResultVo login(String code, String username, String password, HttpSession httpSession) {
        if (code == null) {
            return new ResultVo(100, "请输入验证码");
        }
        if (ValidateCode.code == null || !ValidateCode.code.equals(code)) {
            return new ResultVo(100, "验证码错误，请重新输入");
        }
        httpSession.removeAttribute("code");
        if (username == null || password == null) {
            return new ResultVo(100, "账号或者密码不能为空");
        }
        User user = userService.login(username, password);

        if (user != null) {
            user.setStatus("online");
            return new ResultVo(200, "登录成功", user);
        } else {
            return new ResultVo(100, "账号或密码错误");
        }
    }*/

    //用户注册
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)

    public ResultVo register(HttpServletRequest req) {
        String code = req.getParameter("code");
        if (code == null) {
            return new ResultVo(100, "请输入验证码");
        }
        if (ValidateCode.code == null || !ValidateCode.code.equals(code)) {
            return new ResultVo(100, "验证码错误，请重新输入");
        }
        String username = req.getParameter("username");
        if (userService.nameExornot(username) != null) {
            return new ResultVo(100, "用户名已被占用");
        }
        String password = req.getParameter("password");
        if (username == null || password == null) {
            return new ResultVo(100, "账号或者密码不能为空");
        }

        String mobilephone = req.getParameter("mobilephone");
        if (!JustPhone.isChinaPhoneLegal(mobilephone)) {
            return new ResultVo(100, "手机号码不合法，请重新输入");
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
            return new ResultVo(200, "注册成功");
        } else {
            return new ResultVo(100, "注册失败");
        }


    }

    //验证用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Object login(String username,String password,String code) {
        if (username == null || password == null) {
            return new ResultVo(100, "账号或者密码不能为空");
        }
        if (code == null) {
            return new ResultVo(100, "请输入验证码");
        }
        if (ValidateCode.code == null || !ValidateCode.code.equals(code)) {
            return new ResultVo(100, "验证码错误，请重新输入");
        }
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token); //执行登录方法，如果没有异常就说明ok了
        } catch (UnknownAccountException e) {//用户名不存在
            return new ResultVo(100,"用户名输入错误");
        } catch (IncorrectCredentialsException e) {//密码不存在
            return new ResultVo(100,"密码输出错误");
        }
        User user = userService.login(username, password);

        return new ResultVo(200,"登录成功",user);}


    //修改用户信息
    @ResponseBody
    @RequestMapping(value = "/user/update")
    public ResultVo update(HttpServletRequest req) {
        String username = req.getParameter("username");
        String userId1 = req.getParameter("userId");
        if (userId1 == null) {
            return new ResultVo(100, "用户id不能为空");
        }
        Integer userId = Integer.valueOf(userId1);
        String password = req.getParameter("password");
        if (username == null) {
            return new ResultVo(100, "账号不能为空");
        }
        if (password == null) {
            return new ResultVo(100, "密码不能为空");
        }

        String mobilephone = req.getParameter("mobilephone");
        if (!JustPhone.isChinaPhoneLegal(mobilephone)) {
            return new ResultVo(100, "手机号码不合法，请重新输入");
        }

        String sex = req.getParameter("sex");
        if (sex == null) {
            return new ResultVo(100, "性别不能为空");
        }
        String email = req.getParameter("email");
        if (email == null) {
            return new ResultVo(100, "邮箱不能为空");
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
            return new ResultVo(100, "修改失败");
        } else {
            return new ResultVo(200, "修改成功", user);
        }


    }

    //    更新用户头像
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update")
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("userId") int userId) {
        if (avatorFile.isEmpty()) {
            return new ResultVo(100, "文件上传失败");

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
            user.setUserid(userId);
            user.setUimage(imagPath);
            Integer count = userService.updateUser(user);
            if (count == 1) {
                return new ResultVo(200, "上传成功");
            } else {
                return new ResultVo(100, "上传失败");
            }
        } catch (IOException e) {
            return new ResultVo(100, "上传失败");
        }
    }
}













