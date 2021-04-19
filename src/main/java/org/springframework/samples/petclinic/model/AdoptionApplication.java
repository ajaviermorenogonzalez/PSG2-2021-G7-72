package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	@OneToOne(optional=false)
	private AdoptionRequest adoptionRequest;
	
}
