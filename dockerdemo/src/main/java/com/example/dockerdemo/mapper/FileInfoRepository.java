package com.example.dockerdemo.mapper;

import com.example.dockerdemo.domain.FileInfo;
import com.example.dockerdemo.domain.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface FileInfoRepository extends MongoRepository<FileInfo,String> {
}
