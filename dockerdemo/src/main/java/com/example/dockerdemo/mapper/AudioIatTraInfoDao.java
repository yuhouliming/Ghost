package com.example.dockerdemo.mapper;

import com.example.dockerdemo.domain.AudioIatTraInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * AudioIatTraInfoDao继承基类
 */
@Mapper
public interface AudioIatTraInfoDao extends MyBatisBaseDao<AudioIatTraInfo, Integer> {

    /**
     * 根据uid查询识别翻译表数据
     * @param uniqueID
     * @return
     */
    AudioIatTraInfo queryIatTraInfoByUid(@Param("uniqueID") String uniqueID);
}