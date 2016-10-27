package com.catalyst.teammateria.timeclock.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;

/**
 * Implementation of UserPasswordDao. Responsible for performing database
 * operations with UserPassword objects.
 * 
 * @author aDietrich
 */
@Component
public class UserPasswordDaoHibernate implements UserPasswordDao {

    private EntityManager em;

    /**
     * Setter for hibernate's required entity manager
     * 
     * @param em
     */
    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addUserPassword(UserPassword userPassword) {
        this.em.persist(userPassword);
    }

    @Override
    @Transactional
    public void updateUserPassword(UserPassword userPassword) {
        this.em.merge(userPassword);
    }

    @Override
    public boolean userPasswordMatch(Integer userId, String userHash) {
        return null != em
                .createQuery(
                        "SELECT u.userHash FROM UserPassword u WHERE u.userId =:userId AND u.userHash =:userHash",
                        String.class).setParameter("userId", userId)
                .setParameter("userHash", userHash).getSingleResult();
    }
}
