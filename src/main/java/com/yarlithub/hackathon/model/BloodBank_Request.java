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
public class BloodBank_Request implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int request_id;
    private int pincode;
    private String blood_group;
    private String neededtype;

    public BloodBank_Request() {
    }

    
    public BloodBank_Request(int request_id, int pincode, String blood_group, String neededtype) {
        this.request_id = request_id;
        this.pincode = pincode;
        this.blood_group = blood_group;
        this.neededtype = neededtype;
    }

    
    
    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getNeededtype() {
        return neededtype;
    }

    public void setNeededtype(String neededtype) {
        this.neededtype = neededtype;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }
    
    

    
    
}
