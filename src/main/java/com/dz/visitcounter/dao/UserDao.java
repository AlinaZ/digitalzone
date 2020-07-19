package com.dz.visitcounter.dao;

import com.dz.visitcounter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class UserDao {

    private final EntityManager em;

    @Autowired
    public UserDao(EntityManager em) {
        this.em = em;
    }

    public User loadById(Long id) {
        return em.find(User.class, id);
    }

    public User loadByIp(String ip) throws NoResultException {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.ip='"+ip+"'", User.class);
        return query.getSingleResult();
    }

    public void save(User user) {
        em.persist(user);
    }
}
