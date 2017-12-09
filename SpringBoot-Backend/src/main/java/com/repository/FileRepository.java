package com.repository;

import com.entity.upload_info;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<upload_info, Long> {
    List<upload_info> findByEmail(String username);
}

