package com.javademo.demohouseloan.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mortgage implements Serializable {
    @JsonProperty("persons")
    private List<Person> persons;
    private int amount;
    private String description;
    private boolean active;

    //public Mortgage(){}
    public Mortgage(Person[] personsList, int amount, String description){
        this.active = true;
        this.amount = amount;
        this.description = description;
        if(personsList != null)
            for (Person per: personsList
             ) {
            this.setPersons(per);
            }
    }
    

    public List<Person> getPersons(){ return this.persons;}

    public void setPersons(Person person) {
        if(this.persons == null) this.persons = new ArrayList<Person>();
        this.persons.add(person);
    }

    public int getAmount() { return  this.amount; }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description;}

    public boolean getActive() { return this.active; }
    public void setActive(boolean active) { this.active = active;}
}
