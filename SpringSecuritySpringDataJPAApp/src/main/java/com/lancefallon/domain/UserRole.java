package com.lancefallon.domain;

import javax.persistence.*;

@Entity
@Table(name="user_roles")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_roles_id_seq")
    @SequenceGenerator(name="user_roles_id_seq", sequenceName="user_roles_id_seq", allocationSize=1)
	private Long userroleid;
	
	@Column(name="userid")
	private Long userid;
	
	@Column(name="role")
	private String role;	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}	
	
}
