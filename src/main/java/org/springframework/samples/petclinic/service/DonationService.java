package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {
	
	@Autowired
	private DonationRepository donationRepository;
	
	@Transactional(readOnly=true)
	public Collection<Donation> findAll(){
		return (Collection<Donation>) donationRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Donation> findById(int id){
		return donationRepository.findById(id);
	}
	
	@Transactional 
	public void save(Donation donation) {
		donationRepository.save(donation);
	}
	
	@Transactional
	public void delete(Donation donation) {
		donationRepository.delete(donation);
	}
}
