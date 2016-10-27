package com.lancefallon.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "character")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "character")
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "character_id_seq")
	@SequenceGenerator(name = "character_id_seq", sequenceName = "character_id_seq", allocationSize = 1)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "alias")
	private String alias;

	@Column(name = "is_good")
	private Boolean isGood;

	public Character() {
	}

	public Character(Boolean isGood) {
		this.isGood = isGood;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Boolean getIsGood() {
		return isGood;
	}

	public void setIsGood(Boolean isGood) {
		this.isGood = isGood;
	}

}
