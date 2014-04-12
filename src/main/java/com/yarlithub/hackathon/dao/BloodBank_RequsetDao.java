/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarlithub.hackathon.dao;

import com.yarlithub.hackathon.model.BloodBank_Request;
import com.yarlithub.hackathon.model.BloodDonors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mayooran
 */
public class BloodBank_RequsetDao {
     EntityManagerFactory emf;
      EntityManager em;

    public BloodBank_RequsetDao() {
        this.emf = Persistence.createEntityManagerFactory("RedDropsJPAunit");
        this.em = emf.createEntityManager();
    }
    
    public boolean insert_donor_request_detail(BloodBank_Request r)
    {
        if (r == null) {
            return false;
        }
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        return true;
    }
    
    
    
    
    
    
}
