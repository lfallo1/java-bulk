package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class for user role table in database
 * 
 * @author gPorter
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int  HASH_ARG_1       = 15;
    private static final int  HASH_ARG_2       = 7;
    private int               roleId;
    private String            roleName;

    /**
     * Empty contructor for role object
     */
    public Role() {
    }

    /**
     * Single argument constructor
     * 
     * @param roleId
     *            Unique integer to determine role
     */
    public Role(int roleId) {
        if (roleId == 1) {
            this.roleId = roleId;
            roleName = "ROLE_ADMIN";
        } else {
            this.roleId = 2;
            roleName = "ROLE_USER";
        }
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.roleId);
        return hashBuilder.toHashCode();
    }

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
        Role rhs = (Role)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.roleId, rhs.getRoleId());
        return equalsBuilder.isEquals();
    }
}
