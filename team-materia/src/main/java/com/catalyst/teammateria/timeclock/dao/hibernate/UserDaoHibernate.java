package com.catalyst.teammateria.timeclock.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.dao.UserDao;

/**
 * Implementation of UserDao. Responsible for performing database operations
 * with User objects.
 * 
 * @author aDietrich
 */
@Component
public class UserDaoHibernate implements UserDao {

    private static final String ALL_USERS_ORDERBY_USERNAME_QRY = "SELECT u FROM User u ORDER BY u.username";	
	
    private static final String BASE_SELECT = "SELECT o FROM User o ";

    private static final String COUNT_SELECT = "SELECT COUNT(o) FROM User o ";

    private static final String AJAX_BOX_SELECT = "SELECT new User(o.firstName, o.lastName, o.userId) FROM User o ";

    private static final String ROLE_CLAUSE = "o.role = :role ";

    private static final String ACTIVE_CLAUSE = "o.active = true ";

    private static final String USERNAME_CLAUSE = "o.username = :username ";

    private static final String EMAIL_CLAUSE = "o.email = :email ";

    private static final String USER_CLAUSE = "o.userId = :userId ";

    private static final String WHERE = "WHERE ";

    private static final String AND = "AND ";

    private static final String USER_ID = "userId";

    private static final String EMAIL = "email";

    private static final String USERNAME = "username";

    private static final String ROLE = "role";

    private EntityManager em;

    /**
     * Sets the entity manager for use with hibernate
     * 
     * @param em
     */
    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.em.persist(user);
    }

    @Override
    @Transactional
    public void updateUser(User newObject) {
        this.em.merge(newObject);
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return em.createQuery(BASE_SELECT, User.class).getResultList();
    }

    @Override
    public boolean userNameAvailable(String username) {
        if (em.createQuery(COUNT_SELECT + WHERE + USERNAME_CLAUSE, Long.class)
                .setParameter(USERNAME, username).getSingleResult() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return em
                .createQuery(BASE_SELECT + WHERE + USERNAME_CLAUSE, User.class)
                .setParameter(USERNAME, username).getSingleResult();
    }

    @Override
    public boolean emailAvailable(String email) {
        if (em.createQuery(COUNT_SELECT + WHERE + EMAIL_CLAUSE, Long.class)
                .setParameter(EMAIL, email).getSingleResult() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsersByRole(UserRole role) {
        return em
                .createQuery(BASE_SELECT + COUNT_SELECT + ROLE_CLAUSE,
                        User.class).setParameter(ROLE, role).getResultList();
    }

    @Override
    public List<User> getUsersForSelect(boolean withInactive) {
        if (withInactive) {
            return em.createQuery(AJAX_BOX_SELECT, User.class).getResultList();
        } else {
            return em.createQuery(AJAX_BOX_SELECT + WHERE + ACTIVE_CLAUSE,
                    User.class).getResultList();
        }
    }

    @Override
    public User getUserById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean usernameRemainedTheSame(Integer userId, String username) {
        if (em.createQuery(
                COUNT_SELECT + WHERE + USER_CLAUSE + AND + USERNAME_CLAUSE,
                Long.class).setParameter(USER_ID, userId)
                .setParameter(USERNAME, username).getSingleResult() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean emailRemainedTheSame(Integer userId, String email) {
        if (em.createQuery(
                COUNT_SELECT + WHERE + USER_CLAUSE + AND + EMAIL_CLAUSE,
                Long.class).setParameter(USER_ID, userId)
                .setParameter(EMAIL, email).getSingleResult() == 1) {
            return true;
        }
        return false;
    }
    
	@Override
	public List<User> getAllSortByUsername() {
		return em.createQuery(ALL_USERS_ORDERBY_USERNAME_QRY, User.class).getResultList();
	}    
}
