package com.example.dockerdemo;


import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDownloadUtils {
    /**
     * @param inputFileName 输入一个文件夹
     * @param zipFileName   输出一个压缩文件夹，打包后文件名字
     * @throws Exception
     */
    public static void zip(String inputFileName, String zipFileName,String fileName) throws Exception {
        zip(zipFileName, new File(inputFileName),fileName);
    }

    private static void zip(String zipFileName, File inputFile,String fileName) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "",fileName);
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base,String fileName) throws Exception {
        if (f.isDirectory()) { // 判断是否为目录
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            //base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName(),fileName);
            }
        } else {
            if(f.getName().contains(fileName)||f.getName().contains(".wav")){
                System.out.println("压缩文件"+f.getName());
                //文件
                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f))) {
                    //指定zip文件夹
                    ZipEntry zipEntry = new ZipEntry(base);
                    out.putNextEntry(zipEntry);
                    int len;
                    byte[] buffer = new byte[1024 * 10];
                    while ((len = bis.read(buffer, 0, buffer.length)) != -1) {
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage(), e.getCause());
                }
            }
        }
    }

    /**
     * 下载文件
     *
     * @param file
     * @param response
     */
    public static void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if (isDelete) {
                file.delete(); // 是否将生成的服务器端文件删除
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        //ZipDownloadUtils.moveFile("E:\\mylog\\1253.txt", "E:\\pufa_invote");
        String data = "helloWorld";
        String path = "E:/mylog/1598517843395/";
        String filename = "test.txt";
        File file = new File(path+filename);
        try {
            byte[] sourceBytes = data.getBytes("UTF-8");
            if(null!=sourceBytes){
                FileUtils.writeByteArrayToFile( file, sourceBytes,false);//这里的false代表写入的文件是从头开始重新写入，或者理解为清空文件内容后重新写；若为true,则是接着原本文件内容的结尾开始写
            }
        } catch (UnsupportedEncodingException e) {
            // do something
        }
        ZipDownloadUtils.zip("E:\\mylog\\1598517843395","E:\\pufa.zip","test.txt");
        if(file.exists()){
            file.delete();
            System.out.println("文件删除成功"+file.getName());
        }
    }

    public static void moveFile(String startFilePath,String endDirectionPath) {
        //源文件路径
        File startFile = new File(startFilePath);
        //目的目录路径
        File endDirection = new File(endDirectionPath);
        //如果目的目录路径不存在，则进行创建
        if (!endDirection.exists()) {
            endDirection.mkdirs();
        }
        //目的文件路径=目的目录路径+源文件名称
        File endFile = new File(endDirection + File.separator + startFile.getName());
        try {
            //调用File类的核心方法renameTo
            if (startFile.renameTo(endFile)) {
                System.out.println("文件移动成功！目标路径：{" + endFile.getAbsolutePath() + "}");
            } else {
                System.out.println("文件移动失败！起始路径：{" + startFile.getAbsolutePath() + "}");
            }
        } catch (Exception e) {
            System.out.println("文件移动出现异常！起始路径：{" + startFile.getAbsolutePath() + "}");
        }
    }
}
