/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarlithub.hackathon.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mayooran
 */
@Entity
public class BloodBank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int pincode;
    private String bloodbank_name;
    private String bloodbank_location;
    private String  sector;

    
    public BloodBank() {
    }

    
    public BloodBank(int pincode, String bloodbank_name, String bloodbank_location, String sector) {
        this.pincode = pincode;
        this.bloodbank_name = bloodbank_name;
        this.bloodbank_location = bloodbank_location;
        this.sector = sector;
    }

    
    public String getBloodbank_location() {
        return bloodbank_location;
    }

    public void setBloodbank_location(String bloodbank_location) {
        this.bloodbank_location = bloodbank_location;
    }

    public String getBloodbank_name() {
        return bloodbank_name;
    }

    public void setBloodbank_name(String bloodbank_name) {
        this.bloodbank_name = bloodbank_name;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    
    

    
    
}
