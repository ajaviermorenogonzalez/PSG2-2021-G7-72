package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
	
	@Autowired
	RoomRepository roomRepo;
	
	public Collection<Room> findAll(){
		return roomRepo.findAll();
	}
	
	public Optional<Room> findById(Integer id){
    	return roomRepo.findById(id);
    }
	
	@Transactional
    public void delete(Room room) {
		roomRepo.deleteById(room.getId());
    }
    
    @Transactional
    public void save(@Valid Room room) {
    	roomRepo.save(room);
    }
	

	
	
}
