package com.catalyst.teammateria.timeclock.businesslayer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class for mapping users to passwords in the user_password table in the
 * database.
 * 
 * @author aDietrich
 */
@Entity
@Table (name = "USER_PASSWORD")
public class UserPassword implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int  HASH_ARG_1       = 15;
    private static final int  HASH_ARG_2       = 7;
    @Id
    @Column (nullable = false)
    private Integer           userId;
    private String            userHash;

    /**
     * Empty constructor for UserPassword
     */
    public UserPassword() {
    }

    /**
     * UserPassword constructor with specified id and hash
     * 
     * @param id
     *            identification number of associated user
     * @param hash
     *            hash string for security
     */
    public UserPassword(Integer id, String hash) {
        this.userId = id;
        this.userHash = hash;
    }

    /**
     * Get userId value
     * 
     * @return associated user identification number
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Set userId value
     * 
     * @param userId
     *            identification number of associated user
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Get hash String
     * 
     * @return hash String for security
     */
    public String getUserHash() {
        return userHash;
    }

    /**
     * Set hash String
     * 
     * @param userHash
     *            hash String for security
     */
    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    /**
     * Override method to create a unique hashcode for a UserPassword object
     * 
     * @return hashcode Integer
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.userId);
        return hashBuilder.toHashCode();
    }

    /**
     * Override method to determine whether an object is the same as a
     * UserPassword Object
     * 
     * @param obj
     *            Object to be compared with a UserPassword object
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
        UserPassword rhs = (UserPassword)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.userId, rhs.getUserId());
        return equalsBuilder.isEquals();
    }
}
