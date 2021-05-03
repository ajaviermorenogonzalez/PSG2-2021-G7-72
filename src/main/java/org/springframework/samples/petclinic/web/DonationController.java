package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.CauseService;
import org.springframework.samples.petclinic.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donations")
public class DonationController {

	@Autowired
	private DonationService donationService;
	
	@Autowired
	private CauseService causeService;

	@GetMapping()
	public String list(ModelMap model) {
		Collection<Donation> donations = donationService.findAll();
		model.addAttribute("donations", donations);
		return "donations/listDonations";
	}

	@GetMapping("/new/{causeId}")
	public String newDonation(@PathVariable("causeId")Integer causeId,final ModelMap model) {
		Donation donation = new Donation();	
		model.addAttribute("donation", donation);
		model.addAttribute("causeId", causeId);
		return "donations/createDonation";
	}

	@PostMapping("/new")
	public String saveDonation(@Valid Donation donation, Integer causeId, BindingResult result, ModelMap model) {
		
		donation.setDate(LocalDate.now());
		
		if (result.hasErrors()) {
			model.addAttribute("donation", donation);
			model.addAttribute("message", "La donación no se ha podido crear correctamente");
			return "donations/createDonation";
		} else{
			
			Cause cause = causeService.findById(causeId).get();
			cause.setTotalBudgetAchived(cause.getTotalBudgetAchived() + donation.getAmount());
			cause.getDonations().add(donation);
			
			if(cause.getTotalBudgetAchived() >= cause.getBudgetTarget()) {
				
				cause.setIsClosed(true);
				
			}
			
			donationService.save(donation);
			causeService.save(cause);
			
			model.addAttribute("message", "The donation has been saved");
			model.addAttribute("causes", causeService.findAll());
			return "causes/listCauses";
		}
	}

}
