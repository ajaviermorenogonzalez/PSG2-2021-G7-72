package org.springframework.samples.petclinic.repository;


import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
	
	Collection<Room> findAll();
	Optional<Room> findById(int id);
	
	@Query("SELECT room FROM Room room WHERE room.pet=:pet")
	public Collection<Room> findRoomsForPet(@Param("pet") Pet pet);
}
