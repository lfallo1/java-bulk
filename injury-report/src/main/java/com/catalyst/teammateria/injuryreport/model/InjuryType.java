package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Injury Type model - maps to "injurytype" table
 * 
 * @author dGrimes
 */
public class InjuryType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    /** type_id */
    private int typeId;

    /** type_name */
    private String typeName;

    public InjuryType() {
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1, HASH_ARG_2);
        hashBuilder.append(this.typeId);
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
        InjuryType rhs = (InjuryType)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.typeId, rhs.getTypeId());
        return equalsBuilder.isEquals();
    }
}
