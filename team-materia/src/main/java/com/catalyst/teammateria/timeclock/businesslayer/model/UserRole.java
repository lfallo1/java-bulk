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
 * Class for a user role. Mapped to the user_role table in the database.
 * 
 * @author aDietrich
 */
@Entity
@Table (name = "USER_ROLE")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int  HASH_ARG_1       = 15;
    private static final int  HASH_ARG_2       = 7;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Integer           roleId;
    @Column (name = "role")
    private String            userRole;

    /**
     * Empty constructor for UserRole
     */
    public UserRole() {
    }

    /**
     * User Role constructor
     * 
     * @param roleId
     *            role identification number
     * @param userRole
     *            role description
     */
    public UserRole(Integer roleId, String userRole) {
        this.roleId = roleId;
        this.userRole = userRole;
    }

    /**
     * Get role identification number
     * 
     * @return role identification number
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * Set role identification number
     * 
     * @param roleId
     *            role identification number
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * Get role description
     * 
     * @return role description
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Set role description
     * 
     * @param userRole
     *            role description
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Override method to create a unique hashCode for a UserRole Object
     * 
     * @return hashcode Integer
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.roleId);
        return hashBuilder.toHashCode();
    }

    /**
     * Override method to determine whether an Object is the same as a UserRole
     * Object
     * 
     * @param obj
     *            Object to be compared with a UserRole Object
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
        UserRole rhs = (UserRole)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.roleId, rhs.getRoleId());
        return equalsBuilder.isEquals();
    }
}
