package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "adoptionApplications")
public class AdoptionApplication extends BaseEntity{
	
	@Column(name = "description")
	private String description;
	
    @Column(name="state")
    @Enumerated(value = EnumType.STRING)
    private State state;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;


	
	public Pet getPet() {
		return this.pet;
	}
	
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	
	public AdoptionApplication() {
		super();
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public AdoptionApplication(@NotEmpty String description, State state, Pet pet, Owner owner) {
		super();
		this.description = description;
		this.state = state;
		this.pet = pet;
		this.owner = owner;
	}

	public Owner getOwner() {
		return this.owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "AdoptionApplication [description=" + description + ", state=" + state + ", pet=" + pet + ", owner="
				+ owner + "]";
	}
	
}
