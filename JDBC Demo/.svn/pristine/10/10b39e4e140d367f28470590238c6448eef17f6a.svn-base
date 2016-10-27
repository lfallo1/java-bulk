package com.catalyst.cycle.jdbc_demo.model;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder; 

import com.catalyst.cycle.jdbc_demo.utils.ValidationUtils;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * User class for our database table.
 * 
 * @author egover
 *
 */
 
public class User implements Serializable {
    

    private static final long serialVersionUID = 1L;

    private static final String FIRST_NAME_FIELD ="firstName";
    private static final String LAST_NAME_FIELD = "lastName";
    
     
 
    private Integer userID;

    private String firstName;
    private String lastName;

    /*
     * We use <code>@JsonIdentityInfo</code> to prevent infinite loop formed
     * from when two users are updated to be managers of each other.
     */
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userID")
    private User manager;
    
    
   

    /**
     * @return the userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = StringUtils.trim(firstName);
      
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = StringUtils.trim(lastName);
    }

    /**
     * @return the manager
     */
    public User getManager() {
        return manager;
    }

    /**
     * @param manager
     *            the manager to set
     */
    public void setManager(User manager) {
        this.manager = manager;
    }

    /**
     * 
     * @return the full name of the User.
     */
    public String getFullName() {
        return this.lastName + ", " + this.firstName;
    }

   /**
    * Checks the fields for validity. Error code are in the format of [field].[error]
    * Example: "firstName.empty"
    * 
    * @return A list of error codes in the format [field].[error]
    */
    public List<String> checkErrors(){
        //Our collection of errors that have occurred.
        List<String> errorCodes = new ArrayList<String>(); 
        
        //Check if the firstName is valid
       String firstNameError= ValidationUtils.checkValidRequiredName(this.firstName);
       if(!StringUtils.isEmpty(firstNameError)){
           errorCodes.add(FIRST_NAME_FIELD+"."+firstNameError);
       }
       
       //Check if the lastName is valid
       String lastNameError= ValidationUtils.checkValidRequiredName(this.lastName);
       if(!StringUtils.isEmpty(lastNameError)){
           errorCodes.add(LAST_NAME_FIELD+"."+lastNameError);
       }
     
       
       //We can call the validation methods of any dependencies and combine the error codes.
        
       
        return errorCodes;
        
        
        
        
        
    }
    
    @Override
    public String toString() {
        return "User [id=" + userID+"]";
    }

    @Override
    public int hashCode() { 
        HashCodeBuilder hashBuilder = new HashCodeBuilder(15, 7);
        //Since our equals builder only uses the userID field,
        //our hash builder can only use the userID.
        hashBuilder.append(this.userID);
        return hashBuilder.toHashCode();
 
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != this.getClass()) {
          return false;
        }
        User rhs = (User) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        
        //The only field we are concerned with is the userID. If the
        //userID's are equal, then the objects are considered to be the same.
        equalsBuilder.append(this.userID, rhs.getUserID());
     
        return  equalsBuilder.isEquals();
    }

}
