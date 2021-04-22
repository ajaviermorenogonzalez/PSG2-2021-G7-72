package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.State;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdoptionController {

	private final AdoptionService adoptionService;
	private final PetService petService;
	private final OwnerService ownerService;
	
	public static final String ADOPTION_LISTING ="adoptions/adoptionList";
	public static final String VIEW_ADOPTION_REQUESTS ="adoptions/petRequest";
	@Autowired
	public AdoptionController(AdoptionService adoptionService, PetService petService, OwnerService ownerService) {
		this.adoptionService = adoptionService;
		this.petService = petService;
		this.ownerService = ownerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}    
	
	@GetMapping(value = "/request/new/{petId}")
	public String createAdoptionRequest(@PathVariable("petId") int petId, Map<String, Object> model) {
	//	AdoptionRequest request = new AdoptionRequest();
		return "redirect:/owners/ownersList";
	}
	
	@GetMapping(value = "adoptions/request/{petId}")
	public String viewAdoptionRequest(@PathVariable("petId") int petId, ModelMap model) {
		Iterator<AdoptionApplication> allAdoption = adoptionService.findAll().iterator();
		List<AdoptionApplication> adoptionPet = new ArrayList<>();
		List<AdoptionApplication> adoptionPetRejected = new ArrayList<>();
		while(allAdoption.hasNext()) {
			AdoptionApplication elemento = allAdoption.next();
			if(elemento.getPet().getId().equals(petId) && elemento.getState().equals(State.revision)) {
				adoptionPet.add(elemento);
			} else if(elemento.getState().equals(State.rejected) || elemento.getState().equals(State.accepted)) {
				adoptionPetRejected.add(elemento);
			}
		}
		model.addAttribute("adoptions", adoptionPet);
		model.addAttribute("adoptionsRejected", adoptionPetRejected);
		return VIEW_ADOPTION_REQUESTS;
	}
	
	@GetMapping(value = "adoptions/request/true/{adoptionId}")
	public String adoptionRequestTrue(@PathVariable("adoptionId") int adoptionId, ModelMap model) throws DataAccessException, DuplicatedPetNameException {
		AdoptionApplication request = adoptionService.findById(adoptionId).get();
		request.setState(State.accepted);
		
		Pet pet = request.getPet();
		Owner owner = request.getOwner();
		petService.findById(pet.getId()).get().setOwner(owner);
		petService.findById(pet.getId()).get().setInAdoption(false);
		petService.savePet(pet);

		Iterator<AdoptionApplication> otherAdoption = adoptionService.findAll().iterator();
		while(otherAdoption.hasNext()) {
			AdoptionApplication elemento = otherAdoption.next();
			if(elemento.getState().equals(State.revision)) {
				elemento.setState(State.rejected);
				adoptionService.save(elemento);
			}
		}		
		return "redirect:/adoptions/";
	}
	
	@GetMapping(value = "adoptions/request/false/{adoptionId}")
	public String adoptionRequestFalse(@PathVariable("adoptionId") int adoptionId, ModelMap model) throws DataAccessException, DuplicatedPetNameException {
		AdoptionApplication request = adoptionService.findById(adoptionId).get();
		State state = State.rejected;
		request.setState(state);
		adoptionService.save(request);
		return "redirect:/adoptions/";
	}
	
	
	
}
