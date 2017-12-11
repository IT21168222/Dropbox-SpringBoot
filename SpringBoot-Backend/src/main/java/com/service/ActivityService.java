package com.service;

import com.entity.user_activity;
import com.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    public ActivityRepository activityRepository;

    public void saverec(user_activity rec){
        activityRepository.save(rec);
    }
    public List<user_activity> getactivity(String username){
        return activityRepository.findByEmail(username);
    }
}
