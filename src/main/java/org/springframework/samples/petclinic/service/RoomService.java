package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.repository.RoomRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedRoomBookingException;
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
	
    @Transactional(rollbackOn = DuplicatedRoomBookingException.class)
    public void validateAndSave(@Valid Room room) throws DataAccessException, DuplicatedRoomBookingException {
    	Pet rp = room.getPet();
    	Optional<Integer> p= roomRepo.findPetInRoom(rp);
    	if(p.isPresent()) {
    		throw new DuplicatedRoomBookingException();
    		
    	}else {
    		roomRepo.save(room);
    	}
    }
	

	
	
}
