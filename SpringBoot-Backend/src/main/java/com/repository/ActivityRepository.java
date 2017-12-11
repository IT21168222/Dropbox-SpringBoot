package com.repository;

import com.entity.user_activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<user_activity, Long> {
    List<user_activity> findByEmail(String username);
}