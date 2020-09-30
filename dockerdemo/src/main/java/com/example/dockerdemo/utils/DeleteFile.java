package com.example.dockerdemo.utils;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PushbackInputStream;
import java.text.SimpleDateFormat;

@Component
@EnableScheduling
public class DeleteFile {


    public static void main(String[] args) {

        String fliePath="E:\\mylog\\pufa";
        File file = new File(fliePath);
        deleteFile(file);
    }
    @Scheduled(cron = "*/60 * * * * ?")
    public void SchedulDelFile(){
        String fliePath="E:\\mylog\\pufa";
        File file = new File(fliePath);
        deleteFile(file);
        System.out.println("删除成功");
    }
    public static void deleteFile(File file){
        if(!file.exists()){
            return;
        }
        if(!file.isDirectory()){
            delFile(file);
        }else{
            File[] files = file.listFiles();
            for(int i=0;i<files.length;i++){
                deleteFile(files[i]);
            }
        }
        //删除目录下空文件夹
        if(file.isDirectory()&&file.listFiles().length<=0){
            if(!file.getPath().equals(file.getPath())){
                file.getAbsoluteFile().delete();
            }
        }

    }
    private static void delFile(File f){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        long currentTime = System.currentTimeMillis();
        long fileUpdateTime = f.lastModified();//最后的访问时间
        String current = simpleDateFormat.format(currentTime);
        String fileTime = simpleDateFormat.format(fileUpdateTime);
        long time = 1000L;
        if(currentTime-fileUpdateTime>time){
            f.delete();
        }
    }

}
