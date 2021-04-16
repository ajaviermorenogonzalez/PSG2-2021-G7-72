package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class AdoptionController {

	private final AdoptionService adoptionService;
	
	@Autowired
	public AdoptionController(AdoptionService adoptionService) {
		this.adoptionService = adoptionService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
}
