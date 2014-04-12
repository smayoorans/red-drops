/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yarlithub.hackathon.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Mayooran
 */
public class BloodBankDao {
    EntityManagerFactory emf;
    EntityManager em;

    public BloodBankDao() {
        this.emf = Persistence.createEntityManagerFactory("RedDropsJPAunit");
       
        this.em = emf.createEntityManager();
    }
    
     public java.util.List validpincode(int pin){
      Query query = em.createQuery("select pincode from BloodBank where pincode='"+pin+"'");
      return query.getResultList();
  }
    
}
