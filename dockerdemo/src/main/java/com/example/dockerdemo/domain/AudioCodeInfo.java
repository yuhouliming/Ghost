package com.example.dockerdemo.domain;

import lombok.Data;

/**
 * 音频转码采样信息
 */
@Data
public class AudioCodeInfo {

    //音频类型 vox raw...

    private String AudioType;

    //音频采样率 8k 16k

    private String AudioRate;

    //音频比特率 8bit 16bit
    private String AudioBit;

    //输入文件路径
    private String inputPath;

    //输入文件路径;
    private String outputPath;


}
