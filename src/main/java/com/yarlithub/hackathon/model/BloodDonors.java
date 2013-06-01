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
public class BloodDonors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int phone_number;
    private String donor_name;
    private String blood_group;
    private String donor_location;

    public BloodDonors() {
    }

    
    public BloodDonors(int phone_number, String donor_name, String blood_group, String donor_location) {
        this.phone_number = phone_number;
        this.donor_name = donor_name;
        this.blood_group = blood_group;
        this.donor_location = donor_location;
    }

    
    
    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getDonor_location() {
        return donor_location;
    }

    public void setDonor_location(String donor_location) {
        this.donor_location = donor_location;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }
    
    
    
    
    
   
}
