package com.service;

import com.entity.Groups;
import com.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    public GroupRepository groupRepository;

    public void creategroup(Groups grp){
        groupRepository.save(grp);
    }
    public List<Groups> getgroups(String username){
        return groupRepository.findByEmail(username);
    }
    public void remove(Groups grp){
        groupRepository.delete(grp);
    }
    public Groups find(String grp){
        return groupRepository.findBygroupname(grp);
    }
}
