package com.controller;

import com.entity.user_activity;
import com.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/user_activity") // This means URL's start with /demo (after Application path)
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping(path="/showActivity",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<user_activity> getactivity(HttpSession session) {
        // This returns a JSON with the users
        return activityService.getactivity(session.getAttribute("name").toString());
    }





}