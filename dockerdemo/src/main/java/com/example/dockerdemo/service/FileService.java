package com.example.dockerdemo.service;

import com.example.dockerdemo.domain.FileModel;

import java.util.List;
import java.util.Optional;

public interface FileService {
    /**
     * 保存文件
     */
    FileModel saveFile(FileModel file);

    /**
     * 删除文件
     */
    void removeFile(String id);

    /**
     * 根据id获取文件
     */
    Optional<FileModel> getFileById(String id);

    /**
     * 分页查询，按上传时间降序
     * @return
     */
    List<FileModel> listFilesByPage(int pageIndex, int pageSize);
}
