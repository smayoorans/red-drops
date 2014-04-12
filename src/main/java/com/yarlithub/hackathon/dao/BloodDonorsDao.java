/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarlithub.hackathon.dao;

import com.yarlithub.hackathon.model.BloodDonors;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mayooran
 */
public class BloodDonorsDao {
     EntityManagerFactory emf;
    EntityManager em;

    public BloodDonorsDao() {
        this.emf = Persistence.createEntityManagerFactory("RedDropsJPAunit");
        this.em = emf.createEntityManager();
    }
    
    public boolean insert_donor_detail(BloodDonors d)
    {
        if (d == null) {
            return false;
        }
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        return true;
    }
    
  
    
    
    
    
    
}
