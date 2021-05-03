package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Room;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.samples.petclinic.service.RoomService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedRoomBookingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
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
			if(room.getFirstDate().isBefore(LocalDate.now())||room.getLastDate().isBefore(LocalDate.now())||room.getFirstDate().isAfter(room.getLastDate())) {
				model.addAttribute("message","The Date must be after than today");
				model.put("room", room);
	    		model.put("pet",petService.findAll());
	    		model.put("owner",ownerService.findAll());
			 return ROOMS_FORM;
			}
			try{
				
				this.roomService.validateAndSave(room);
				model.addAttribute("message", "The room was created successfully.");
            }catch(DuplicatedRoomBookingException ex){
                result.rejectValue("pet", "duplicate", "already exists");
                model.put("room", room);
    			model.put("pet",petService.findAll());
    			model.put("owner",ownerService.findAll());
    			model.addAttribute("message", "La mascota que intenta registrar ya tiene una habitación reservada para esas fechas.");
                return ROOMS_FORM;
            }
			return roomsListing(model);
            
			
		}
	}

	
}
