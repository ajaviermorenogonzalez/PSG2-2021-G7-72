/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {
	
	private static final String VIEWS_VET_CREATE_OR_UPDATE_FORM = "vets/createOrUpdateVetForm";

	private final VetService vetService;

	@Autowired
	public VetController(VetService clinicService) {
		this.vetService = clinicService;
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	
	@GetMapping(value = "/vets/new")
	public String initCreationForm(Map<String, Object> model) {
		Vet vet = new Vet();
		model.put("vet", vet);
		return VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/new")
	public String processCreationForm(@Valid Vet vet, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.vetService.saveVet(vet);
			
			return "redirect:/vets/" + vet.getId();
		}
	}
	
	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateVetForm(@PathVariable("vetId") int vetId, Model model) {
		Vet vet = this.vetService.findVetById(vetId);
		model.addAttribute(vet);
		return VIEWS_VET_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateVetForm(@Valid Vet vet, BindingResult result,
			@PathVariable("vetId") int vetId) {
		if (result.hasErrors()) {
			return VIEWS_VET_CREATE_OR_UPDATE_FORM;
		}
		else {
			vet.setId(vetId);
			this.vetService.saveVet(vet);
			return "redirect:/vets/{vetId}";
		}
	}
	
	@GetMapping(value="/vets/{vetId}/delete")
	public String deleteVet(@PathVariable("vetId") int vetId, ModelMap model) {
		Optional<Vet> vet = vetService.findById(vetId);
		if(vet.isPresent()) {
			vetService.delete(vet.get());
			model.addAttribute("message", "The vet was deleted successfully.");
			return "redirect:/vets";
		}else {
			model.addAttribute("message", "We could not find the vet you are trying to delete.");
			return "redirect:/vets";
		}
	}
	
	/**
	 * Custom handler for displaying a vet.
	 * @param vetId the ID of the vet to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/vets/{vetId}")
	public ModelAndView showVet(@PathVariable("vetId") int vetId) {
		ModelAndView mav = new ModelAndView("vets/vetDetails");
		mav.addObject(this.vetService.findVetById(vetId));
		return mav;
	}

}
