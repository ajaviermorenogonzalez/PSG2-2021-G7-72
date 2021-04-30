package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cause;

public interface CausesRepository extends Repository<Cause,Integer>{
	
	void delete(Cause cause) throws DataAccessException;
	
	Collection<Cause> findAll();
	
	Optional<Cause> findById(int id) throws DataAccessException;
	
	void save(Cause cause) throws DataAccessException;
	

}
