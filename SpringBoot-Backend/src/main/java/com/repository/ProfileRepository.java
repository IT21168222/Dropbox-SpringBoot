package com.repository;


import com.entity.user_profile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileRepository extends CrudRepository<user_profile, Long> {
    List<user_profile> findByEmail(String EMAIL);
}
