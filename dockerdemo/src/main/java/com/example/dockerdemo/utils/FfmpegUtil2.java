package com.example.dockerdemo.utils;

import cn.hutool.json.JSONUtil;
import com.example.dockerdemo.domain.AudioCodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @desc:ffmpeg工具类
 * @author: htxu4
 * 
 */

public class FfmpegUtil2 {


	 @Value("${ffmpeg.patch.linux}")
	 private String ffmpeg;

	private final static Logger logger = LoggerFactory.getLogger(FfmpegUtil2.class);

	// 视频提取wav格式音频
	public  boolean  videoToWav(String inputPath, String outputPath) {
		String ffmpegPath = ffmpeg;
		
		File ffmpegFile = new File(ffmpegPath);
		if (!ffmpegFile.exists()) {
			logger.error("FFmpegWarpper.transcodeToWav: ffmpeg转码工具缺失");
			return false;
		}
		File inputFile = new File(inputPath);
		if (!inputFile.exists()) {
			logger.error("FFmpegWarpper.transcodeToWav: 输入音频文件不存在");
			return false;
		}
		if (inputFile.isDirectory()) {
			logger.error("FFmpegWarpper.transcodeToWav: 输入不能是文件夹");
			return false;
		}
		Runtime runtime = Runtime.getRuntime();
		String[] commandWithArgs = { ffmpegPath, "-y", "-i",inputPath, "-f", "-acodec","pcm_s16le","s16le", "-ac", "1", "-ar", "16000",
				outputPath };
		try {
			Process process = runtime.exec(commandWithArgs);
			process.waitFor();
			int exitValue = process.exitValue();
			logger.debug("exitValue: " + exitValue);
			if (exitValue != 0) {
				InputStream is = process.getErrorStream();
				byte[] err = new byte[is.available()];
				is.read(err);
				logger.debug(new String(err));
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		FfmpegUtil2 ffmpegUtil2 = new FfmpegUtil2();
		//ffmpegUtil2.audioTranscoding("C:\\Users\\lmwu5\\Desktop\\ningbo\\dialogicadpcm\\4bit16k.vox", "C:\\Users\\lmwu5\\Desktop\\ningbo\\dialogicadpcm\\9527.vox");
		//ffmpegUtil2.audioTranscoding("C:\\Users\\lmwu5\\Desktop\\ningbo\\raw8k16bit\\iflytek-16k.wav", "C:\\Users\\lmwu5\\Desktop\\ningbo\\raw8k16bit\\9527.wav");

	}
	public   boolean  audioTranscoding(AudioCodeInfo audioCodeInfo) throws Exception {
		logger.info("音频格式转码入参：{}", JSONUtil.toJsonStr(audioCodeInfo));
		String ffmpegPath = "E:\\pufa_invote\\ffmpeg_pufa\\windows\\ffmpeg.exe";
		String inputPath =audioCodeInfo.getInputPath();
		String outputPath =audioCodeInfo.getOutputPath();
		File ffmpegFile = new File(ffmpegPath);
		if (!ffmpegFile.exists()) {
			logger.error("FFmpegWarpper.transcodeToWav: ffmpeg转码工具缺失");
			return false;
		}
		File inputFile = new File(inputPath);
		if (!inputFile.exists()) {
			logger.error("输入音频文件不存在");
			return false;
		}
		if (inputFile.isDirectory()) {
			logger.error("输入不能是文件夹");
			return false;
		}
		Runtime runtime = Runtime.getRuntime();
		String[] commandWithArgs = new String[1024];
		String outFile = "";
		if(audioCodeInfo.getAudioType().equals("vox")){
			outFile=inputFile.getParent()+"/"+inputFile.getName().substring(0, inputFile.getName().lastIndexOf(".vox"))+"-temp"+".wav";
			V3ToWav.voxConvert(inputPath, outFile, 1, 8000, 1);
			if(audioCodeInfo.getAudioRate().equals("8k")){
				commandWithArgs = new String[]{ffmpegPath, "-y", "-i", outFile, "-f", "wav", "-acodec", "pcm_s16le", "1", "-ar", "8000", outputPath};
			}else{
				commandWithArgs = new String[]{ ffmpegPath, "-y", "-i",outFile,"-f","wav","-acodec","pcm_s16le" , "1", "-ar", "16000", outputPath };
			}
		}else if(audioCodeInfo.getAudioRate().equals("8k")&&!audioCodeInfo.getAudioType().equals("opus")){
			commandWithArgs = new String[]{ffmpegPath, "-y", "-i", inputPath, "-f", "wav", "-acodec", "pcm_s16le", "1", "-ar", "8000", outputPath};
		}else if(audioCodeInfo.getAudioRate().equals("16k")){
			commandWithArgs = new String[]{ ffmpegPath, "-y", "-i",inputPath,"-f","wav","-acodec","pcm_s16le" , "1", "-ar", "16000", outputPath };
		}else if(audioCodeInfo.getAudioRate().equals("8k")&&audioCodeInfo.getAudioType().equals("opus")){
			commandWithArgs = new String[] { ffmpegPath, "-y", "-i",inputPath,"-f","wav","-acodec","pcm_s16le" , "1", "-ar", "16000", outputPath };
		}
		logger.info("ffmpeng命令：{}", JSONUtil.toJsonStr(commandWithArgs));
		try {
			Process process = runtime.exec(commandWithArgs);
			process.waitFor();
			int exitValue = process.exitValue();
			logger.debug("exitValue: " + exitValue);
			if (exitValue != 0) {
				InputStream is = process.getErrorStream();
				byte[] err = new byte[is.available()];
				is.read(err);
				logger.debug(new String(err));
				return false;
			}else{
				File file = new File(outFile);
				if(file.exists()){
					file.delete();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}
