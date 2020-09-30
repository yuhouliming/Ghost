package com.example.dockerdemo.controller;

import com.example.dockerdemo.common.ResponseVo;
import com.example.dockerdemo.domain.AudioIatTraInfo;
import com.example.dockerdemo.mapper.AudioIatTraInfoDao;
import com.example.dockerdemo.utils.ZipDownloadUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RequestMapping(value = "/downfile")
@RestController
public class dowmLoadController {

    private static final Logger logger = LoggerFactory.getLogger(dowmLoadController.class);

    @Value("${hello.test}")
    private String test;

    @Autowired
    private AudioIatTraInfoDao audioIatTraInfoDao;

    @GetMapping("/test")
    @ApiOperation(value = "视频下载", notes = "视频下载")
    public void hello(HttpServletResponse response) throws Exception {
        //先压缩文件
        //ZipDownloadUtils.zip("E:\\1598517843395","E:\\pufa.zip");
        //下载文件
        File temp = new File("E:\\pufa.zip");
        ZipDownloadUtils.downloadFile(temp, response, true);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ApiOperation(value = "测试接口2", notes = "多语种测试")
    public String test() {
        return test;
    }

    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    @ApiOperation(value = "数据库测试", notes = "数据库测试")
    public ResponseVo insertData(@RequestBody AudioIatTraInfo audioIatTraInfo) {
        ResponseVo responseVo = new ResponseVo();
        try {
            audioIatTraInfoDao.insertSelective(audioIatTraInfo);
        }catch (Exception e){
        logger.error("数据库插入失败", e);
        }
        return responseVo;
    }

}
