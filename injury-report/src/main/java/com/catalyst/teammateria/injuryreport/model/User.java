package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * User class for User table in the database
 * 
 * @author gPorter
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    private int userId;

    private String userName;

    private String userPass;

    private Role role;

    private Employee employee;

    private boolean active;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.userId);
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
        User rhs = (User)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.userId, rhs.getUserId());
        return equalsBuilder.isEquals();
    }
}
