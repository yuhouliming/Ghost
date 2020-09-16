package com.example.dockerdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RequestMapping(value = "/downfile")
@Controller
public class dowmLoadController {

    @GetMapping("/test")
    public void hello(HttpServletResponse response) throws Exception {
        //先压缩文件
        ZipDownloadUtils.zip("E:\\1598517843395","E:\\pufa.zip");
        //下载文件
        File temp = new File("E:\\pufa.zip");
        ZipDownloadUtils .downloadFile(temp, response, true);
    }

}
