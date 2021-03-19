package org.springframework.samples.petclinic.repository;


import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
	
	Collection<Room> findAll();
	Optional<Room> findById(int id);
}
