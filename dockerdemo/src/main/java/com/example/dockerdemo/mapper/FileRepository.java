package com.example.dockerdemo.mapper;

import com.example.dockerdemo.domain.FileModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileModel,String> {

}
