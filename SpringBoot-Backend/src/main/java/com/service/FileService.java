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
    public void save(upload_info file){
        fileRepository.save(file);
    }
    public void remove(upload_info file){
        fileRepository.delete(file);
    }
    public void savepath(upload_info file){
        fileRepository.save(file);
    }
    public List<upload_info> getAllFiles(String username){
        return fileRepository.findByEmail(username);
    }
    public Iterable<upload_info> starfile(String email){
        Iterable<upload_info> getallfiles=fileRepository.findByEmail(email);
        return fileRepository.findByEmail(email);
    }
}
