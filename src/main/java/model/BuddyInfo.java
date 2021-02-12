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
    private String name, phoneNumber, address;

    public BuddyInfo() {}

    //Creates a model.BuddyInfo with a name and phone number
    public BuddyInfo(String name, String phoneNumber, String address){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    //returns the Buddy's phone number
    public String getAddress(){
        return address;
    }

    public String toString()
    {
        return "Name: " + name + "    PhoneNumber: " + phoneNumber + "     Address: " + address;
    }

}
