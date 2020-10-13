package com.example.dockerdemo.service;

import com.example.dockerdemo.domain.FileModel;
import com.example.dockerdemo.mapper.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileModel saveFile(FileModel file) {
        return fileRepository.save(file);
    }

    @Override
    public void removeFile(String id) {
        fileRepository.deleteById(id);
    }

    @Override
    public Optional<FileModel> getFileById(String id) {
        return fileRepository.findById(id);
    }

    @Override
    public List<FileModel> listFilesByPage(int pageIndex, int pageSize) {
        Page<FileModel> page = null;
        List<FileModel> list = null;
        Sort sort = Sort.by(Sort.Direction.DESC,"uploadDate");
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        page = fileRepository.findAll(pageable);
        list = page.getContent();
        return list;
    }
}
