package com.catalyst.teammateria.timeclock.businesslayer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Class for UserTime. Mapped to the user_time table in the database. Contains
 * information for the userId, clockIn, clockOut, and clockDate fields.
 * 
 * @author aDietrich
 */
@Entity
@Table (name = "USER_TIME")
public class UserTime {

    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "userId")
    private User user;

    @DateTimeFormat (pattern = "hh:mma")
    @Type (type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime clockIn;

    @DateTimeFormat (pattern = "hh:mma")
    @Type (type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    private LocalTime clockOut;

    @DateTimeFormat (pattern = "MM-dd-yyyy")
    @Type (type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate clockDate;

    public UserTime() {
    }

    /**
     * Get UserTime object identification number
     * 
     * @return UserTime object identification number
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set UserTime object identification number
     * 
     * @param id
     *            UserTime object identification number
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get userId associated with UserTime object
     * 
     * @return userId associated with UserTime object
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets unique identification Integer for specified UserTime Object
     * 
     * @param userId
     *            unique identification Integer
     */
    public void setUserId(User user) {
        this.user = user;
    }

    /**
     * Gets LocalTime clockIn value associated with specified UserTime Object
     * 
     * @return LocalTime clockIn value
     */
    public LocalTime getClockIn() {
        return clockIn;
    }

    /**
     * Sets LocalTime clockIn value associated with specified UserTime Object
     * 
     * @param clockIn
     *            LocalTime clockIn value
     */
    public void setClockIn(LocalTime clockIn) {
        this.clockIn = clockIn;
    }

    /**
     * Gets LocalTime clockOut value associated with specified UserTime Object
     * 
     * @return LocalTime clockOut value
     */
    public LocalTime getClockOut() {
        return clockOut;
    }

    /**
     * Sets LocalTime clockOut value associated with specified UserTime Object
     * 
     * @param clockOut
     *            LocalTime clockOut value
     */
    public void setClockOut(LocalTime clockOut) {
        this.clockOut = clockOut;
    }

    /**
     * Gets LocalDate value associated with specified UserTime Object
     * 
     * @return LocalDate value
     */
    public LocalDate getClockDate() {
        return clockDate;
    }

    /**
     * Sets LocalDate value associated with specified UserTime Object
     * 
     * @param clockDate
     *            LocalDate value
     */
    public void setClockDate(LocalDate clockDate) {
        this.clockDate = clockDate;
    }

    /**
     * Override method to create a unique hashCode for a UserTime Object
     * 
     * @return hashCode Integer
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hashBuilder = new HashCodeBuilder(HASH_ARG_1,
                HASH_ARG_2);
        hashBuilder.append(this.id);
        return hashBuilder.toHashCode();
    }

    /**
     * Override method to determine whether an Object is the same as a UserTime
     * Object
     * 
     * @param obj
     *            Object to be compared with a UserTime Object
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
        UserTime rhs = (UserTime)obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder();
        equalsBuilder.append(this.id, rhs.getId());
        return equalsBuilder.isEquals();
    }
}
