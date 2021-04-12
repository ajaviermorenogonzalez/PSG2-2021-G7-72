package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/causes")
public class CauseController {
	
	@Autowired
	private CauseService causeService;
	
	@GetMapping()
	public String list(ModelMap model) {
		Collection<Cause> causes=causeService.findAll();
		model.addAttribute("causes",causes);
		return "causes/causesList";
	}
	
	@GetMapping("/{id}")
	public String showCause(@PathVariable("id") int id,ModelMap model) {
		Optional<Cause> cause=causeService.findById(id);
		model.addAttribute("cause",cause.get());
		return "causes/causesDetails";
	}
	
	@GetMapping("/new")
	public String newCause(ModelMap model) {
		Cause cause=new Cause();
		model.addAttribute("cause",cause);
		return "causes/createCause";
		
	}
	@PostMapping("/{id}")
	public String saveCause(@Valid Cause cause,BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			return "causes/createCause";
		}else {
			cause.setBudgetAchieved(0);
			causeService.save(cause);
			model.addAttribute("message","The cause has been saved");
			return list(model);
		}
	}
	@GetMapping("/{id}/delete")
	String deletCause(@PathVariable("id") int id, ModelMap model) {
		Optional<Cause> cause = causeService.findById(id);
		if(!cause.isPresent()) {
			return list(model);
		}else {
			causeService.delete(cause.get());
			model.addAttribute("message","The cause has been deleted");
			return list(model);
		}
	}

}
