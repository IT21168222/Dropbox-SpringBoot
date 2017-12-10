package com.service;

import com.entity.user_profile;
import com.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public void addUser(user_profile user){
        profileRepository.save(user);
    }
    public List<user_profile> getprofile(String username){
        return profileRepository.findByEmail(username);
    }
}
