package com.example.dockerdemo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PushbackInputStream;

@Component
@EnableScheduling
public class DeleteFile {
    public static void main(String[] args) {
        String fliePath="E:\\mylog\\pufa";
        File file = new File(fliePath);
        deleteFile(file);
    }

    @Scheduled(cron = "*/10 * * * * ?")
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
            if(!file.getPath().equals("E:\\mylog\\pufa")){
                file.getAbsoluteFile().delete();
            }
        }

    }
    private static void delFile(File f){
        long currentTime = System.currentTimeMillis();
        long fileUpdateTime = f.lastModified();//最后的访问时间
        //long time = 30*24*60*60*1000;
        long time = 30*24*60*60*1000;
        if(currentTime-fileUpdateTime>time){
            f.delete();
        }
    }

}
