package com.lancefallon.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity  
@DiscriminatorValue("character_superhero")  
public class Superhero extends Character {

	@Column(name="suit_description")
	private String suitDescription;
	
	@Column(name="summary")
	private String summary;

	public Superhero() {
		super(true);
	}

	public String getSuitDescription() {
		return suitDescription;
	}

	public void setSuitDescription(String suitDescription) {
		this.suitDescription = suitDescription;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
