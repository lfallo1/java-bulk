package com.catalyst.teammateria.timeclock.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.dao.UserRoleDao;

/**
 * Implementation of UserRoleDao. Responsible for performing database operations
 * with UserRole objects.
 * 
 * @author aDietrich
 */
@Component
public class UserRoleDaoHibernate implements UserRoleDao {

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
    public UserRole getUserRoleById(Integer key) {
        return em.find(UserRole.class, key);
    }
}
