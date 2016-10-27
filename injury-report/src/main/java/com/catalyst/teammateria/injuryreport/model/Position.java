package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Position object that maps to the position table
 * 
 * @author dGrimes
 */
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    private Integer positionId;

    private String positionName;

    public Position() {
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionID) {
        this.positionId = positionID;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.positionId);
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
        Position rhs = (Position)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.positionId, rhs.getPositionId());
        return equalsBuilder.isEquals();
    }
}
