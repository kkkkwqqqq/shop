package com.fight.controller;

import com.fight.pojo.Commimages;
import com.fight.pojo.Commodity;
import com.fight.service.CommimagesService;
import com.fight.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CommimagesController {
    @Autowired
    private CommimagesService commimagesService;

    @RequestMapping("/commimage")
    public Object selectCommimageBycommId(Integer commid) {
        List<Commimages> commimages = commimagesService.selectImageBy(commid);
        return new ResultVo(200, "查询子图成功", commimages);
    }

    //修改子图
    @RequestMapping("/update/commimage")
    //修改商品清单的某一条内容的附图(发布商品的时候用)
    public Object updateCommimage(@RequestParam("files") MultipartFile[] uploadFiles, Commimages commimages) {
        System.out.println(uploadFiles.length);
        for (int i = 0; i < uploadFiles.length; i++) {
            String fileName = System.currentTimeMillis() + uploadFiles[i].getOriginalFilename();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator");
            System.out.println(filePath);
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.mkdir();
            }

            File dest = new File(filePath + "" + fileName);
            String imagPath = "/img/" + fileName;
            try {
                uploadFiles[i].transferTo(dest);
                commimages.setImage(imagPath);
                Integer count = commimagesService.insertAllCommimages(commimages);
                if (count == 1) {
                } else {
                    return new ResultVo(100, "上传失败");
                }
            } catch (IOException e) {
                return new ResultVo(100, "上传失败");
            }
        }
        return new ResultVo(200, "上传成功");
    }

    //删除子图
    @RequestMapping("/delete/commimage")
    //修改商品清单的某一条内容的附图(发布商品的时候用)
    public Object deleteCommimage(int id) {
        Integer count = commimagesService.deleteImagByid(id);
        if (count==1){
            return new ResultVo(200,"删除成功");
        }else return new ResultVo(100,"删除失败");

    }
}

