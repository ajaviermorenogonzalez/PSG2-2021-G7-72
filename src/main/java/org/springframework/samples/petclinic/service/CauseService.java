package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.repository.CausesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {
	
	@Autowired
	private CausesRepository cauRepository;
	
	@Transactional(readOnly=true)
	public Collection<Cause> findAll(){
		return cauRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Cause> findById(int id){
		return cauRepository.findById(id);
	}
	
	@Transactional 
	public void save(Cause cause) {
		cauRepository.save(cause);
	}
	
	@Transactional
	public void delete(Cause cause) {
		cauRepository.delete(cause);
	}
}
