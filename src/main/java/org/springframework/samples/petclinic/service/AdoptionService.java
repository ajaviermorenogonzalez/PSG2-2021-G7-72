package org.springframework.samples.petclinic.service;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.AdoptionApplication;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.model.State;
import org.springframework.samples.petclinic.repository.AdoptionRepository;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {
	
	private AdoptionRepository adoptionRepository;
	
	@Autowired
	public AdoptionService(AdoptionRepository adoptionRepository) {
		this.adoptionRepository = adoptionRepository;
	}
	
	@Transactional
	public Iterable<AdoptionApplication> findAll() {
		return adoptionRepository.findAll();
	}
	
	public Optional<AdoptionApplication> findById(Integer id) {
		return adoptionRepository.findById(id);
	}
   
	@Transactional
    public void save(@Valid AdoptionApplication adoption) {
		adoption.setState(State.revision);
		adoptionRepository.save(adoption);
    }

}
