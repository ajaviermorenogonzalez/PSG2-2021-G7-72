package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RoomService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedRoomBookingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("/room/{roomId}")
public class RoomController {
	
	private static final String ROOMS_FORM = "rooms/createOrUpdateRoomForm";
	public static final String ROOMS_LISTING ="rooms/RoomsListing";
	
	@Autowired
	private final RoomService roomService;
	@Autowired
    private final OwnerService ownerService;
	@Autowired
    private final PetService petService;
	
    public RoomController(RoomService roomService, OwnerService ownerService, PetService petService) {
		super();
		this.roomService = roomService;
		this.ownerService = ownerService;
		this.petService = petService;
	}
    
    @GetMapping(value = "/rooms")
	public String roomsListing(ModelMap model) {
		model.addAttribute("rooms", roomService.findAll());
		return ROOMS_LISTING;
	}
    
    @GetMapping(value = "/rooms/new") //Select owner
	public String initCreationForm(ModelMap model) {
		Room room = new Room();
		model.addAttribute("room", room);
		model.addAttribute("pet", petService.findAll());
		model.addAttribute("owner", ownerService.findAll());
		return ROOMS_FORM;
	}

	@PostMapping(value = "/rooms/new")
	public String processCreationForm(@Valid Room room, BindingResult result, ModelMap model) throws DataAccessException, DuplicatedRoomBookingException {
		if (result.hasErrors()) {
			model.put("room", room);
			model.put("pet",petService.findAll());
			model.put("owner",ownerService.findAll());
			return ROOMS_FORM;
		}
		else {
			try{
				this.roomService.validateAndSave(room);
				model.addAttribute("message", "The room was created successfully.");
            }catch(DuplicatedRoomBookingException ex){
                result.rejectValue("pet", "duplicate", "already exists");
                model.put("room", room);
    			model.put("pet",petService.findAll());
    			model.put("owner",ownerService.findAll());
    			model.addAttribute("message", "La mascota que intenta registrar ya tiene una habitación reservada.");
                return ROOMS_FORM;
            }
			return roomsListing(model);
            
			
		}
	}
	
	@GetMapping("/rooms/{id}/edit")
	public String editRoom(@PathVariable("id") int id,ModelMap model) {
		Optional<Room> room = roomService.findById(id);
		if(room.isPresent()) {
			model.addAttribute("room", room.get());
			model.put("pet",petService.findAll());
			model.put("owner",ownerService.findAll());
			return ROOMS_FORM;
		}else {
			model.addAttribute("message", "We could not find the room you are trying to edit.");
			return ROOMS_LISTING;
		}
	}
	
	
	@PostMapping("/rooms/{id}/edit")
	public String editRoom(@PathVariable("id") int id,@Valid Room modifiedRoom, BindingResult binding,ModelMap model) throws DataAccessException, DuplicatedPetNameException {
		Optional<Room> room = roomService.findById(id);
		if(binding.hasErrors()) {
			return ROOMS_FORM;
		}else {
			BeanUtils.copyProperties(modifiedRoom, room.get(),"id");
			this.roomService.save(modifiedRoom);
			model.addAttribute("message", "The room was updated successfully.");
			return roomsListing(model);
		}
	}
	
	@GetMapping("/rooms/{id}/delete")
	public String deleteRoom(@PathVariable("id") int id, ModelMap model) {
		Optional<Room> room = roomService.findById(id);
		if(room.isPresent()) {
			roomService.delete(room.get());
			model.addAttribute("message", "The room was deleted successfully.");
			return roomsListing(model);
		}else {
			model.addAttribute("message", "We could not find the room you are trying to delete.");
			return roomsListing(model);
		}
	}
	
}
