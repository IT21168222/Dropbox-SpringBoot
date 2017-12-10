package com.controller;

import com.entity.user_profile;
import com.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/user_profile") // This means URL's start with /demo (after Application path)
public class ProfileController {
    @Autowired
    private ProfileService profileService;


    @PostMapping(path="/profile",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public  ResponseEntity<?> addNewProfile (@RequestBody user_profile user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        profileService.addUser(user);
        System.out.println("Saved");
        return new ResponseEntity(null,HttpStatus.CREATED);
    }

    @GetMapping(path="/getdetails",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<user_profile> getprofile(HttpSession session) {
        // This returns a JSON with the users
        return profileService.getprofile(session.getAttribute("name").toString());
    }





}