package com.lancefallon.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity  
@DiscriminatorValue("character_villain")  
public class Villain extends Character {

	@Column(name="crimes")
	private String crimes;

	public Villain() {
		super(false);
	}

	public String getCrimes() {
		return crimes;
	}

	public void setCrimes(String crimes) {
		this.crimes = crimes;
	}

}
