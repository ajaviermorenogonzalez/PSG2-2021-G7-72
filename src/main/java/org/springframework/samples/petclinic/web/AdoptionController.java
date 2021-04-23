package org.springframework.samples.petclinic.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.State;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AdoptionService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdoptionController {

	private final AdoptionService adoptionService;
	private final PetService petService;
	private final OwnerService ownerService;
	private final UserService userService;
	
	public static final String ADOPTION_LISTING ="adoptions/adoptionList";
	public static final String VIEW_ADOPTION_REQUESTS ="adoptions/petRequest";
	@Autowired
	public AdoptionController(AdoptionService adoptionService, PetService petService, OwnerService ownerService, UserService userService) {
		this.adoptionService = adoptionService;
		this.petService = petService;
		this.ownerService = ownerService;
		this.userService = userService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}    
	
	@GetMapping(value = "adoptions/request/owner/{ownerId}/pet/{petId}/new")
	public String createAdoptionRequest(@PathVariable("petId") int petId,@PathVariable("ownerId") int ownerId, Map<String, Object> model) throws DataAccessException, DuplicatedPetNameException {
		Pet pet = petService.findPetById(petId);
		pet.setInAdoption(true);
		this.petService.savePet(pet);
		return "redirect:/owners/"+ownerId;
	}
	
	@GetMapping(value = "/adoptions/pets")
	public String findPets(Map<String, Object> model) {
		Collection<Pet> results = petService.findAllInAdoption();
		model.put("selections", results);
		return "adoptions/petsForAdoption";
	}
	
	@GetMapping(value = "adoptions/application/pet/{petId}/new")
	public String initCreationForm(@PathVariable("petId") int petId, ModelMap model) {
		//Principal principal = request.getUserPrincipal();
		//String username =  principal.getName();
		//User  user = userService.findByUsername(username);
		//Owner owner = ownerService.findByUser(user);
		AdoptionApplication application = new AdoptionApplication("Hola", null, new Pet(), new Owner());
		//Owner owner = ownerService.findById(1).get();
		//Pet pet = petService.findById(petId).get();
		//application.setOwner(owner);
		//application.setPet(pet);
		application.setState(State.revision);
		//application.setOwner(owner);
		model.addAttribute("application",application);
		model.addAttribute("pet",petId);
		
		return "adoptions/createApplicationForm";
	}
	
	@PostMapping(value = "adoptions/application/pet/{petId}/new")
	public String processCreationForm(HttpServletRequest request, @PathVariable("petId") int petId, @ModelAttribute(name="application") AdoptionApplication application, @ModelAttribute(name="description") String description, BindingResult result) {
		Principal principal = request.getUserPrincipal();
		String username =  principal.getName();
		User  user = userService.findByUsername(username);
		Owner owner = ownerService.findByUser(user).get(0);
		if (result.hasErrors()) {
			return "adoptions/createApplicationForm";
		}
		else {
			//creating application
			//int ownerId = ownerService.getOwnerId();
			//Owner owner = ownerService.findById(ownerId).get();
			Pet pet = petService.findById(petId).get();
			application.setOwner(owner);
			application.setPet(pet);
			application.setDescription(description);
			this.adoptionService.save(application);
			
			return "redirect:/adoptions/pets";
		}
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
