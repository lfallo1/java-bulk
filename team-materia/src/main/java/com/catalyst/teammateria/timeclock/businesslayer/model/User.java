package com.catalyst.teammateria.timeclock.businesslayer.model;

import java.io.Serializable;

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
import org.joda.time.LocalDateTime;

/**
 * Class for a user. Mapped to the user table in the database. Contains
 * information for the userId, username , password, firstName, lastName, email,
 * role, active, created, and updated fields.
 * 
 * @author aDietrich
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final int HASH_ARG_1 = 15;

    private static final int HASH_ARG_2 = 7;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Integer userId;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    @ManyToOne
    @JoinColumn (name = "role")
    private UserRole role;

    private Boolean active;

    @Type (type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime created;

    @Type (type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate updated;
	/**
	 * Empty constructor for User Object
	 */
	public User() {
	}

    /**
     * Special constructor for use with the UserDaoHibernate.getUsersForSelect()
     * method only these three items should be returned, and added to the
     * object.
     * 
     * @param firstName
     * @param lastName
     * @param userId
     */
    public User(String firstName, String lastName, int userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    /**
     * Get identification number
     * 
     * @return unique user ID number
     */
    public Integer getUserId() {
        return userId;
    }

	/**
	 * Set identification number
	 * 
	 * @param userId
	 *            unique user ID number
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Get username String
	 * 
	 * @return user-chosen handle
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set username String
	 * 
	 * @param username
	 *            user-chosen handle
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get firstName String
	 * 
	 * @return user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set firstName String
	 * 
	 * @param firstName
	 *            user first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get lastName String
	 * 
	 * @return user last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set lastName String
	 * 
	 * @param lastName
	 *            user last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get email String
	 * 
	 * @return user email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set email String
	 * 
	 * @param email
	 *            user email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get role Role
	 * 
	 * @return user role (user/admin)
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * Set role Role
	 * 
	 * @param role
	 *            user role(user/admin)
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * Get user's active status
	 * 
	 * @return user active/inactive
	 */
	public Boolean getActive() {
		return active;
	}

	/**
	 * Set user's active status
	 * 
	 * @param active
	 *            user active/inactive
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * Get user's created time
	 * 
	 * @return time of user creation
	 */
	public LocalDateTime getCreated() {
		return created;
	}

	/**
	 * Set user's created time
	 * 
	 * @param created
	 *            time of user's creation
	 */
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	/**
	 * Get user's date of update
	 * 
	 * @return date of user update
	 */
	public LocalDate getUpdated() {
		return updated;
	}

	/**
	 * Set user's date of update
	 * 
	 * @param updated
	 *            date of user update
	 */
	public void setUpdated(LocalDate updated) {
		this.updated = updated;
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
		User rhs = (User) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(this.userId, rhs.getUserId());
		return equalsBuilder.isEquals();
	}
}
