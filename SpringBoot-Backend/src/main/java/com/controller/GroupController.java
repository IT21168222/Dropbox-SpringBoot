package com.controller;

import com.entity.Groups;
import com.service.GroupService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/groups")

public class GroupController{
    @Autowired
    private GroupService groupService;

    @PostMapping(path="/Groupcreation",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody String group (@RequestBody String group, HttpSession session ) {

        JSONObject jsonObject = new JSONObject(group);
        String newgrp=jsonObject.getString("group");
        Groups newgroup=new Groups();
        newgroup.setEmail(session.getAttribute("name").toString());
        newgroup.setGroupname(newgrp);
        newgroup.setPermission(1);
        groupService.creategroup(newgroup);
        return "201";
    }
    @GetMapping(path="/listgroups",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Groups> getAllgroups(HttpSession session) {
        // This returns a JSON with the users
        return groupService.getgroups(session.getAttribute("name").toString());
    }

    @PostMapping(path="/deletegroup",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public @ResponseBody String deletegrp (@RequestBody String groupname,HttpSession session ) {
        JSONObject jsonObject = new JSONObject(groupname);
        String newgrp=jsonObject.getString("groupname");
        Groups newgroup=groupService.find(newgrp);
        groupService.remove(newgroup);

        return "201";
    }

}

