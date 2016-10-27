package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class for Employees in the database
 * 
 * @author dGrimes
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    private Integer employeeId;

    private String firstName;

    private String lastName;

    private Position position;

    public Employee() {
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeID) {
        this.employeeId = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.employeeId);
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
        Employee rhs = (Employee)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.employeeId, rhs.getEmployeeId());
        return equalsBuilder.isEquals();
    }
}
