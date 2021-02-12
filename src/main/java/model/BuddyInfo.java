package model;

/**
 * @author Simon Krol
 * SYSC4806 Lab 4 - Feb 4th 2021
 */

import javax.persistence.*;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    private String name, phoneNumber;

    public BuddyInfo() {}

    //Creates a model.BuddyInfo with a name and phone number
    public BuddyInfo(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //returns the Buddy's name
    public String getName(){
        return name;
    }

    //returns the Buddy's phone number
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String toString()
    {
        return "Name: " + name + "    PhoneNumber: " + phoneNumber;
    }

}
