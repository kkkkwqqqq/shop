package com.fight.controller;
import com.fight.pojo.Commimages;
import com.fight.pojo.Commodity;
import com.fight.pojo.User;
import com.fight.service.CommimagesService;
import com.fight.service.CommodityService;
import com.fight.vo.ResultVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CommodityController {
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private CommimagesService commimagesService;

    /*查询最新 展示再页面*/
    @RequestMapping("/commodityIndex")
    public Object selectNew(Integer start, Integer end) {
        if (start == null) {
            return new ResultVo(100, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(100, "每页多少条数据不能为空");
        }
        PageHelper.startPage(start,end);
        List<Commodity> Commoditys = commodityService.selectNew();
        PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(Commoditys);
        return new ResultVo(200, "成功返回啦", pageInfo);
    }

    /*根据商品类别进行分类*/
    @RequestMapping("/selectByType")
    public Object selectByType(String type, Integer start, Integer end) {
        if (type == null) {
            return new ResultVo(100, "商品类别不能为空");
        }
        if (start == null) {
            return new ResultVo(100, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(100, "每页多少条数据不能为空");
        }
        PageHelper.startPage(start,end);
        List<Commodity> commodities = commodityService.selectByType(type, start, end);
        PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(commodities);
        return new ResultVo(200, "成功返回啦", pageInfo);
    }

    /*根据商品类别以及价格进行分类*/
    @RequestMapping("/selectByTypeAndPrice")
    public Object selectByTypeAndPrice(String type, String order, Integer start, Integer end) {
        if (type == null) {
            return new ResultVo(100, "商品类别不能为空");
        }
        if (start == null) {
            return new ResultVo(100, "页数不能为空");
        }
        if (end == null) {
            return new ResultVo(100, "每页多少条数据不能为空");
        }
        if (order.equals("1")) {
            PageHelper.startPage(start,end);
            List<Commodity> commodities = commodityService.selectByTypeAndPriceDesc(type, start, end);
            PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(commodities);
            return new ResultVo(200, "成功返回啦", pageInfo);
        } else {
            PageHelper.startPage(start,end);
            List<Commodity> commodities = commodityService.selectByTypeAndPriceAsc(type, start, end);
            PageInfo<Commodity> pageInfo = new PageInfo<Commodity>(commodities);
            return new ResultVo(200, "成功返回啦", pageInfo);
        }

    }

    /*查询某个指定的商品*/
    @RequestMapping("/selectProBycommid")
    public Object selectByTypeAndPrice(Integer commid,Integer page,Integer count) {
        List<Commodity> commodities = commodityService.selectProById(commid,0,1);
        if (commodities.get(0) == null) {
            return new ResultVo(100, "没有该商品");
        } else
            return new ResultVo(200, "查询成功", commodities.get(0));

    }


    //用户的商品清单（全部）  根据用户id查询所有自己发布的商品
    @RequestMapping("/Commodity/selectMyProByid")
    public Object selectMyProByid(Integer userid,Integer page,Integer count) {
        List<Commodity> commodities = commodityService.selectProById(userid,page,count);
        return new ResultVo(200, "查询成功", commodities);

    }

    //编辑商品清单的某一条内容/设置为已售
    @RequestMapping("/updateMyProByid")
    public Object updateMyProByid(Commodity commodity) {
        Integer count = commodityService.updateMyProByid(commodity);
        if (count == 1) {
            return new ResultVo(200, "修改成功");
        } else {
            return new ResultVo(100, "修改失败");
        }
    }

    //修改商品清单的某一条内容的视频
    @RequestMapping("/updateMyProViewByid")
    public Object updateMyProViewByid(@RequestParam("file") MultipartFile avatorFile, Commodity commodity) {
        System.out.println(avatorFile);
        if (avatorFile.isEmpty()) {
            return new ResultVo(100, "文件上传失败");
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "view" + System.getProperty("file.separator");
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
                return new ResultVo(200, "上传成功");
            } else {
                return new ResultVo(100, "上传失败");
            }
        } catch (IOException e) {
            return new ResultVo(100, "上传失败");
        }
    }

    //用户的商品清单（待审核）  根据用户id查询所有自己发布的商品
    @RequestMapping("/selectMyProByid1")
    public Object selectMyProByid1(Commodity commodity) {
        List<Commodity> commodities = commodityService.selectweishen(commodity);
        return new ResultVo(200, "查询成功", commodities);

    }

    //删除指定商品
    @RequestMapping("/deleteMyProByid")
    public Object deleteMyProByid(Integer commid) {
        Integer count = commodityService.deleteProByid(commid);
        if (count == 1) {
            return new ResultVo(200, "删除成功");
        } else {
            return new ResultVo(100, "删除失败");
        }
    }

    //管理员查看商品清单（所有）
    @RequestMapping("/selectAllPro")
    public Object selectMyProByid() {
        List<Commodity> commodities = commodityService.selectAllPro();
        return new ResultVo(200, "查询成功", commodities);
    }

    //管理员查看商品清单（未审核）
    @RequestMapping("/selectweishenPro")
    public Object selectweishenPro() {
        List<Commodity> commodities = commodityService.selectweishenPro(0);
        return new ResultVo(200, "查询成功", commodities);
    }

    //修改商品清单的某一条内容的主图
    @RequestMapping("/updatePrimaryTu")
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("commid") int commid) {
        if (avatorFile.isEmpty()) {
            return new ResultVo(100, "文件上传失败");

        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator");
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
            Integer count = commodityService.updateMyProByid(commodity);
            if (count == 1) {
                return new ResultVo(200, "上传成功");
            } else {
                return new ResultVo(100, "上传失败");
            }
        } catch (IOException e) {
            return new ResultVo(100, "上传失败");
        }
    }


    // 发布商品  --》需要管理员审核
    @RequestMapping("/publishCommodity")
    public Object publishCommiage(Commodity commodity, @RequestParam("file")
            MultipartFile primary, @RequestParam("files") MultipartFile[] futu,
                                  @RequestParam("video") MultipartFile video) throws IOException {
        if (primary.isEmpty()) {
            return new ResultVo(100, "发布商品失败");
        }
        if (video.isEmpty()) {
            return new ResultVo(100, "发布商品失败");
        }
        String fileName = System.currentTimeMillis() + primary.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator");
        System.out.println(filePath);
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }
        File dest = new File(filePath + "" + fileName);
        String imagPath = "/img/" + fileName;

        primary.transferTo(dest);
        commodity.setImage(imagPath);
        String fileName1 = System.currentTimeMillis() + video.getOriginalFilename();
        String filePath1 = System.getProperty("user.dir") + System.getProperty("file.separator") + "view" + System.getProperty("file.separator");
        System.out.println(filePath1);
        File file2 = new File(filePath1);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File dest1 = new File(filePath + "" + fileName);
        String imagPath1 = "/view/" + fileName;
        video.transferTo(dest1);
        commodity.setVideourl(imagPath1);
        commodityService.publishCommodity(commodity);
        //根据用户名和商品名查询出commid 然后放子图
        Commodity commodity1 = commodityService.selectProBynameAnduserId(commodity.getCommname(), commodity.getUserid());
        for (int i = 0; i < futu.length; i++) {
            String fileName3 = System.currentTimeMillis() + futu[i].getOriginalFilename();
            String filePath3 = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator");
            System.out.println(filePath3);
            File file3 = new File(filePath3);
            if (!file3.exists()) {
                file3.mkdir();
            }

            File dest3 = new File(filePath + "" + fileName3);
            String imagPath3 = "/img/" + fileName;
            Commimages commimages = new Commimages();
            futu[i].transferTo(dest3);
            commimages.setImage(imagPath3);
            commimages.setCommid(commodity1.getCommid());
            Integer count = commimagesService.insertAllCommimages(commimages);
            if (count == 1) {
            } else {
                return new ResultVo(100, "商品发布失败");
            }
        }
        return new ResultVo(200, "商品发布成功");

    }


}



