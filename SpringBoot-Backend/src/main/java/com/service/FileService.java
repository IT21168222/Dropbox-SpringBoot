package com.service;

import com.entity.upload_info;
import com.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    public FileRepository fileRepository;


    public void savepath(upload_info file){
        System.out.println("asd");
        fileRepository.save(file);
    }
    public List<upload_info> getAllFiles(String username){
        return fileRepository.findByEmail(username);
    }
}
