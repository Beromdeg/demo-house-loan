package com.javademo.demohouseloan.api.model;

import java.io.Serializable;

public class Person implements Serializable {
    private String fnr;
    private String firstname;
    private String lastname;

    public Person(String fnr, String firstname, String lastname){
        this.fnr = fnr;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getFnr() { return  this.fnr; }
    public void setFnr(String fnr) {
        this.fnr = fnr;
    }
    public String getFirstname() {return this.firstname;}
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() { return  this.lastname;}
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getName() { return  this.firstname + " "+ this.lastname;}
}
