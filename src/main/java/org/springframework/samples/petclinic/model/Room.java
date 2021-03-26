package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="rooms")
public class Room extends BaseEntity {
	
	@Column(name = "first_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate firstDate;
	
	@Column(name = "last_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate lastDate;
	
	@OneToOne
	@JoinColumn(name="pet_id")
	private Pet pet;
	
	@OneToOne
	@JoinColumn(name="owner_id")
	private Owner owner;

	public LocalDate getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(LocalDate firstDate) {
		this.firstDate = firstDate;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	
	

}
