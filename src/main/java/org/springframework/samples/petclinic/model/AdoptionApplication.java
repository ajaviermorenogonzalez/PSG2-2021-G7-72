package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "adoptionApplications")
public class AdoptionApplication extends BaseEntity{
	
	@Column(name = "description")
	@NotEmpty
	private String description;
	
    @Column(name="state")
    @Enumerated(value = EnumType.STRING)
    private State state;
	
	@OneToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;


/*	@OneToOne(optional=false)
	private AdoptionRequest adoptionRequest; */
	
	public Pet getPet() {
		return this.pet;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Owner getOwner() {
		return this.owner;
	}

	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
