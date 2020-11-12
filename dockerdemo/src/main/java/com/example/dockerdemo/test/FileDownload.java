package com.example.dockerdemo.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

/**
 *
 * @return
 */
public class FileDownload {

    public static File saveUrlAs(String url, String filePath,String fileName,String method) {
        //创建不同的文件夹目录
        File file = new File(filePath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            // 建立链接
            URL httpUrl = new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            //判断文件的保存路径后面是否以/结尾
            if (!filePath.endsWith("/")) {
                filePath += "/";
            }
            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            fileOut = new FileOutputStream(filePath + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);
            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while (length != -1) {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }
        return file;
    }

    public static void main(String[] args) {
        String photoUrl = "http://172.31.98.100:10086/shuzimima/8L/8L_chaojiang_12585490.pcm;http://172.31.98.100:10086/shuzimima/8L/8L_chaojiang_20848499.pcm";
        String[] urls = photoUrl.split(";");
        String filePath = "E:\\pcm_txt\\";
        //String fileName =photoUrl.substring(photoUrl.lastIndexOf("/")+1, photoUrl.length());
        for(int i=0;i<urls.length;i++){
            String url = urls[i];
            String fileName =url.substring(url.lastIndexOf("/")+1, url.length());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    File file = saveUrlAs(url, filePath,fileName,"GET");
                }
            });
            thread.start();
        }
        //System.out.println("fileName---->"+fileName);
    }


}
