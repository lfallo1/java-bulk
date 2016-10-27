package com.catalyst.teammateria.injuryreport.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Class for body parts in the database
 * @author gPorter
 *
 */
public class BodyPart implements Serializable{   
    
    private static final long serialVersionUID = 1L;
    
    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;
    
    private int bodyPartId;
    
    private String bodyPartName;
    
    /**
     * Empty constructor for bodypart object
     */
    public BodyPart(){
        
    }
    
    public int getBodyPartId() {
        return bodyPartId;
    }

    
    public void setBodyPartId(int bodypartId) {
        this.bodyPartId = bodypartId;
    }

    
    public String getBodyPartName() {
        return bodyPartName;
    }

    
    public void setBodyPartName(String bodyPartName) {
        this.bodyPartName = bodyPartName;
    }
    
    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.bodyPartId);
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
        BodyPart rhs = (BodyPart)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.bodyPartId, rhs.getBodyPartId());
        return equalsBuilder.isEquals();
    }

    

    
}
