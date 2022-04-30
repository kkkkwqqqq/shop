package com.fight.controller;
import com.fight.Constants.Constants;
import com.fight.pojo.Commimages;
import com.fight.pojo.Commodity;
import com.fight.service.CommimagesService;
import com.fight.service.CommodityService;
import com.fight.util.JwtUtil;
import com.fight.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CommimagesService commimagesService;
    @Autowired
    private RedisTemplate redisTemplate;

    /*查询最新 展示再页面*/
    @RequestMapping("/commodityIndex")
    public Object selectNew(Integer start, Integer end) {
        if (start == null) {
            return new ResultVo(Constants.FAILED_STATUS, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(Constants.FAILED_STATUS, "每页多少条数据不能为空");
        }
        PageHelper.startPage(start, end);
        List<Commodity> Commoditys = commodityService.selectNew();
        PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(Commoditys);
        return new ResultVo(Constants.SUCCESS_STATUS, "成功返回啦", pageInfo);
    }

    /*根据商品类别进行分类*/
    @RequestMapping("/selectByType")
    public Object selectByType(String type, Integer start, Integer end) {
        if (type == null) {
            return new ResultVo(Constants.FAILED_STATUS, "商品类别不能为空");
        }
        if (start == null) {
            return new ResultVo(Constants.FAILED_STATUS, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(Constants.FAILED_STATUS, "每页多少条数据不能为空");
        }
        PageHelper.startPage(start, end);
        List<Commodity> commodities = commodityService.selectByType(type, start, end);
        PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(commodities);
        return new ResultVo(Constants.SUCCESS_STATUS, "成功返回啦", pageInfo);
    }

    /*根据商品类别以及价格进行分类*/
    @RequestMapping("/selectByTypeAndPrice")
    public Object selectByTypeAndPrice(String type, String order, Integer start, Integer end) {
        if (type == null) {
            return new ResultVo(Constants.FAILED_STATUS, "商品类别不能为空");
        }
        if (start == null) {
            return new ResultVo(Constants.FAILED_STATUS, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(Constants.FAILED_STATUS, "每页多少条数据不能为空");
        }
        if (order.equals("1")) {
            PageHelper.startPage(start, end);
            List<Commodity> commodities = commodityService.selectByTypeAndPriceDesc(type, start, end);
            PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(commodities);
            return new ResultVo(Constants.SUCCESS_STATUS, "成功返回啦", pageInfo);
        } else {
            PageHelper.startPage(start, end);
            List<Commodity> commodities = commodityService.selectByTypeAndPriceAsc(type, start, end);
            PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(commodities);
            return new ResultVo(Constants.SUCCESS_STATUS, "成功返回啦", pageInfo);
        }

    }

    /*查询某个指定的商品*/
    @RequestMapping("/selectProBycommid")
    public Object selectByTypeAndPrice(Integer commid, Integer page, Integer count) {
        List<Commodity> commodities = commodityService.selectProById1(commid);
        if (commodities.get(0) == null) {
            return new ResultVo(Constants.FAILED_STATUS, "没有该商品");
        } else {
            Integer rednumber = commodities.get(0).getRednumber();
            String liulan = (String) redisTemplate.opsForValue().get(commid + "");
            if (liulan == null) {
                rednumber++;
                redisTemplate.opsForValue().set(commid + "", rednumber + "");
            } else {
                Integer rednum = Integer.valueOf(liulan);
                rednum++;
                redisTemplate.opsForValue().set(commid + "", rednum + "");
            }
            return new ResultVo(Constants.SUCCESS_STATUS, "查询成功", commodities.get(0));
        }


    }


    //用户的商品清单（全部）  根据用户id查询所有自己发布的商品
    @RequestMapping("/selectMyProByid")
    public Object selectMyProByid(HttpServletRequest request) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        List<Commodity> commodities = commodityService.selectProById(Integer.parseInt(userId));
        return new ResultVo(Constants.SUCCESS_STATUS, "查询成功", commodities);

    }

    //编辑商品清单的某一条内容/设置为已售
    @RequestMapping("/updateMyProByid")
    public Object updateMyProByid(Commodity commodity) {
        Integer count = commodityService.updateMyProByid(commodity);
        if (count == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "修改成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "修改失败");
        }
    }

    //修改商品清单的某一条内容的视频
    @RequestMapping("/updateMyProViewByid")
    public Object updateMyProViewByid(HttpServletRequest request, @RequestParam("file") MultipartFile avatorFile, Commodity commodity) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        commodity.setUserid(Integer.parseInt(userId));
        System.out.println(avatorFile);
        if (avatorFile.isEmpty()) {
            return new ResultVo(Constants.FAILED_STATUS, "文件上传失败");
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
       // String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "view" + System.getProperty("file.separator");
        String filePath = Constants.RESOURCE_WIN_PATH+"/view/" ;
        System.out.println(filePath);
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + "" + fileName);
        String imagPath = "/view/" + fileName;
        try {
            avatorFile.transferTo(dest);
            commodity.setVideourl(imagPath);
            Integer count = commodityService.updateMyProByid(commodity);
            if (count == 1) {
                return new ResultVo(Constants.SUCCESS_STATUS, "修改成功");
            } else {
                return new ResultVo(Constants.FAILED_STATUS, "修改失败");
            }
        } catch (IOException e) {
            return new ResultVo(Constants.FAILED_STATUS, "修改失败");
        }
    }

    //用户的商品清单（待审核）  根据用户id查询所有自己发布的商品
    @RequestMapping("/selectMyProDsh")
    public Object selectMyProByid1(HttpServletRequest request,Commodity commodity) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        commodity.setUserid(Integer.parseInt(userId));
        List<Commodity> commodities = commodityService.selectweishen(commodity);
        return new ResultVo(Constants.SUCCESS_STATUS, "查询成功", commodities);

    }


    //删除指定商品
    @RequestMapping("/deleteMyProByid")
    public Object deleteMyProByid(HttpServletRequest request,Integer commid) {
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        //先判断能否查到  能的话删除子图
        Commodity commodity = commodityService.selectProByIdAndCid(commid, Integer.parseInt(userId));
        if (commodity==null){
            return  new ResultVo(Constants.FAILED_STATUS,"删除失败");
        }
        Integer count = commimagesService.deleteImagByCommid(commid);

        //再删商品
        Integer count1 = commodityService.deleteProByid(commid,Integer.parseInt(userId));

        if (count == 1) {
            return new ResultVo(Constants.SUCCESS_STATUS, "删除成功");
        } else {
            return new ResultVo(Constants.FAILED_STATUS, "删除失败");
        }
    }





    //修改商品清单的某一条内容的主图
    /**/
    @RequestMapping("/updatePrimaryTu")
    public Object updateUserPic(HttpServletRequest request,
                                @RequestParam("file") MultipartFile avatorFile,
                                @RequestParam("commid") int commid) {
        if (avatorFile.isEmpty()) {
            return new ResultVo(Constants.FAILED_STATUS, "文件上传失败");

        }
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = Constants.RESOURCE_WIN_PATH + "/" + "img" + "/";
        System.out.println(filePath);
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + "" + fileName);
        String imagPath = "/img/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Commodity commodity = new Commodity();
            commodity.setCommid(commid);
            commodity.setImage(imagPath);
            commodity.setUserid(Integer.parseInt(userId));
            Integer count = commodityService.updateMyProByid(commodity);
            if (count == 1) {
                return new ResultVo(Constants.SUCCESS_STATUS, "上传成功");
            } else {
                return new ResultVo(Constants.FAILED_STATUS, "上传失败");
            }
        } catch (IOException e) {
            return new ResultVo(Constants.FAILED_STATUS, "上传失败");
        }
    }


    // 发布商品  --》需要管理员审核
    @RequestMapping("/publishCommodity")
    public Object publishCommiage(HttpServletRequest request,Commodity commodity, @RequestParam("file")
            MultipartFile primary, @RequestParam("files") MultipartFile[] futu,
                                  @RequestParam("video") MultipartFile video) throws IOException {
        if (primary.isEmpty()) {
            return new ResultVo(Constants.FAILED_STATUS, "发布商品失败");
        }
        if (video.isEmpty()) {
            return new ResultVo(Constants.FAILED_STATUS, "发布商品失败");
        }
        String fileName = System.currentTimeMillis() + primary.getOriginalFilename();
      //  String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator");
        String filePath = Constants.RESOURCE_WIN_PATH+"/img/" ;
        System.out.println(System.getProperty("user.dir"));
        System.out.println(filePath);
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + "" + fileName);
        String imagPath = "/img/" + fileName;

        primary.transferTo(dest);
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);
        commodity.setUserid(Integer.parseInt(userId));
        commodity.setImage(imagPath);
        String fileName1 = System.currentTimeMillis() + video.getOriginalFilename();
       // String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator") + "view" + System.getProperty("file.separator");
        String filePath1 = Constants.RESOURCE_WIN_PATH+"/view/" ;
        System.out.println(filePath1);
        File file2 = new File(filePath1);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File dest1 = new File(filePath1 + "" + fileName1);
        String imagPath1 = "/view/" + fileName1;
        video.transferTo(dest1);
        commodity.setVideourl(imagPath1);
        commodityService.publishCommodity(commodity);
        //根据用户名和商品名查询出commid 然后放子图
        Commodity commodity1 = commodityService.selectProBynameAnduserId(commodity.getCommname(), commodity.getUserid());
        for (int i = 0; i < futu.length; i++) {
            String fileName3 = System.currentTimeMillis() + futu[i].getOriginalFilename();
           // String filePath3 = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator");
            String filePath3 = Constants.RESOURCE_WIN_PATH+"/img/" ;
            System.out.println(filePath3);
            File file3 = new File(filePath3);
            if (!file3.exists()) {
                file3.mkdir();
            }

            File dest3 = new File(filePath + "" + fileName3);
            String imagPath3 = "/img/" + fileName3;
            Commimages commimages = new Commimages();
            futu[i].transferTo(dest3);
            commimages.setImage(imagPath3);
            commimages.setCommid(commodity1.getCommid());
            Integer count = commimagesService.insertAllCommimages(commimages);
            if (count == 1) {
            } else {
                return new ResultVo(Constants.FAILED_STATUS, "商品发布失败");
            }
        }
        return new ResultVo(Constants.SUCCESS_STATUS, "商品发布成功");

    }

//修改商品内容 不包含文件的内容
        @RequestMapping("/updateExcepFile")
public Object updateExcepFile(HttpServletRequest request,Commodity commodity){
            String token = request.getHeader("token");
            String userId = JwtUtil.getUserId(token);
            commodity.setUserid(Integer.parseInt(userId));
            Integer count = commodityService.updateMyProByid(commodity);
            if(count==1){
                return new ResultVo(Constants.SUCCESS_STATUS,"修改成功");
            }else {
                return  new ResultVo(Constants.FAILED_STATUS,"修改失败");
            }
        }
}







