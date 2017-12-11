package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class user_activity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer USER_ID;
    private String email;

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getACTIVITY_TIME() {
        return ACTIVITY_TIME;
    }

    public void setACTIVITY_TIME(String ACTIVITY_TIME) {
        this.ACTIVITY_TIME = ACTIVITY_TIME;
    }

    public String getACTIVITY() {
        return ACTIVITY;
    }

    public void setACTIVITY(String ACTIVITY) {
        this.ACTIVITY = ACTIVITY;
    }

    private String ACTIVITY_TIME;
    private String ACTIVITY;


}
