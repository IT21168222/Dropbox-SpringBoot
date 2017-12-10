package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class user_profile {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;


    private String NAME;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }


    public String getWORK() {
        return WORK;
    }

    public void setWORK(String WORK) {
        this.WORK = WORK;
    }

    public String getEDUCATION() {
        return EDUCATION;
    }

    public void setEDUCATION(String EDUCATION) {
        this.EDUCATION = EDUCATION;
    }

    public String getCONTACT() {
        return CONTACT;
    }

    public void setCONTACT(String CONTACT) {
        this.CONTACT = CONTACT;
    }

    public String getINTERESTS() {
        return INTERESTS;
    }

    public void setINTERESTS(String INTERESTS) {
        this.INTERESTS = INTERESTS;
    }

    public String getLANG_PREF() {
        return LANG_PREF;
    }

    public void setLANG_PREF(String LANG_PREF) {
        this.LANG_PREF = LANG_PREF;
    }

    private String WORK;
    private String EDUCATION;
    private String CONTACT;
    private String INTERESTS;
    private String LANG_PREF;

}