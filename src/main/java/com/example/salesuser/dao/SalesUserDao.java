package com.example.salesuser.dao;

import com.example.salesuser.entity.SalesUser;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SalesUserDao {

    @PersistenceContext(unitName = "SalesUserPU")
    private EntityManager em;

    public SalesUser create(SalesUser user) {
        em.persist(user);
        return user;
    }

    public SalesUser update(SalesUser user) {
        return em.merge(user);
    }

    public void delete(Long userId) {
        SalesUser user = em.find(SalesUser.class, userId);
        if (user != null) {
            em.remove(user);
        }
    }

    public SalesUser findById(Long userId) {
        return em.find(SalesUser.class, userId);
    }

    public List<SalesUser> findAll() {
        TypedQuery<SalesUser> query = em.createNamedQuery("SalesUser.findAll", SalesUser.class);
        return query.getResultList();
    }

    public SalesUser findByUserName(String userName) {
        try {
            TypedQuery<SalesUser> query = em.createQuery(
                "SELECT u FROM SalesUser u WHERE u.userName = :userName", SalesUser.class);
            query.setParameter("userName", userName);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}