package com.catalyst.teammateria.timeclock.businesslayer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Object to handle operations between the page and the database regarding login
 * attempts and locking users out based upon failures while attempting
 * 
 * @author dGrimes
 */
@Entity
@Table (name = "USER_INVALID_LOGIN_ATTEMPTS")
public class UserInvalidLoginAttempts implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int  HASH_ARG_1       = 15;
    private static final int  HASH_ARG_2       = 7;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer           attemptId;
    @Column (name = "user_id")
    private Integer           userId;
    private Integer           attempts;

    /**
     * Returns unique identification integer for specified invalid login attempt
     * 
     * @return unique identification integer
     */
    public Integer getAttemptId() {
        return attemptId;
    }

    /**
     * Sets unique identification integer for specified invalid login attempt
     * 
     * @param attemptId
     *            unique identification integer
     */
    public void setAttemptId(Integer attemptId) {
        this.attemptId = attemptId;
    }

    /**
     * Gets unique identification integer for user associated with invalid login
     * attempt
     * 
     * @return unique user identification integer
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets unique identification integer for user associated with invalid login
     * attempt
     * 
     * @param userId
     *            unique user identification integer
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets number of unsuccessful login attempts currently held by a specified
     * user
     * 
     * @return number of unsuccessful login attempts
     */
    public Integer getAttempts() {
        return attempts;
    }

    /**
     * Sets number of unsuccessful login attempts currently held by a specified
     * user
     * 
     * @param attempts
     *            number of unsuccessful login attempts
     */
    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    /**
     * Override method to create a unique hashCode for a
     * UserInvalidLoginAttempts Object
     * 
     * @return hashcode integer
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.attemptId);
        return hashBuilder.toHashCode();
    }

    /**
     * Override method to determine whether an Object is the same as a
     * UserInvalidLoginAttempts Object
     * 
     * @param obj
     *            Object to be compared with a UserInvalidLoginAttempts Object
     * @return whether objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        UserInvalidLoginAttempts rhs = (UserInvalidLoginAttempts)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.attemptId, rhs.getAttemptId());
        return equalsBuilder.isEquals();
    }
}
