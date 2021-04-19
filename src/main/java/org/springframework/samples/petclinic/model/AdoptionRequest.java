package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "adoptionRequests")
public class AdoptionRequest extends BaseEntity{
	
	@OneToOne(optional=false)
	private Pet pet;
	
	@OneToOne(mappedBy = "adoptionRequest")
	private AdoptionApplication adoptionApplication;

	public AdoptionRequest() {
		super();
	}

}
