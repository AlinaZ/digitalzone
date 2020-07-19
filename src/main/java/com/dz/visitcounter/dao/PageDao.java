package com.dz.visitcounter.dao;

import com.dz.visitcounter.model.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class PageDao {
    private final EntityManager em;

    @Autowired
    public PageDao(EntityManager em) {
        this.em = em;
    }
    public Page loadById(Long id) {
        return em.find(Page.class, id);
    }

    public void save(Page page) {
        em.persist(page);
    }
}
