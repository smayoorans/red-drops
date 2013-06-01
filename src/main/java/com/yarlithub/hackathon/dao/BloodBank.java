/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarlithub.hackathon.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mayooran
 */
public class BloodBank {
    EntityManagerFactory emf;
    EntityManager em;

    public BloodBank() {
        this.emf = Persistence.createEntityManagerFactory("RedDropsJPAunit");
        this.em = emf.createEntityManager();
    }
    
    
    
}
