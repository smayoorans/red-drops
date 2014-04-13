package com.yarlithub.hackathon.dao;

import com.yarlithub.hackathon.model.BloodBank_Request;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BloodBankRequestDao {
    EntityManagerFactory emf;
    EntityManager em;

    public BloodBankRequestDao() {
        this.emf = Persistence.createEntityManagerFactory("RedDropsJPAunit");
        this.em = emf.createEntityManager();
    }

    public boolean insert_donor_request_detail(BloodBank_Request r) {
        if (r == null) {
            return false;
        }
        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
        return true;
    }

}
