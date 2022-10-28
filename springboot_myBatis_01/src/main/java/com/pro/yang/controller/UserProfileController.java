package com.pro.yang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pro.yang.dto.UserProfileDTO;
import com.pro.yang.service.UserProfileService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserProfileController {
	
	private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	private UserProfileService service;
	
	@GetMapping("/user/{id}")
	public UserProfileDTO getUserProfile (@PathVariable("id") String id){
		log.info("### getUserProfile ID: , {}", id);
		return  service.getUserProfile(id);
	}

	@GetMapping("/user/all")
	public List<UserProfileDTO> getUserProfileList(){
		log.info("### getUserProfileList count : {}", service.getUserProfileList() == null ? "0": service.getUserProfileList().size());
		return service.getUserProfileList();
	}
	
	@PutMapping("/user/{id}")
	public int insertUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfileDTO dto = new UserProfileDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setPhone(phone);
		dto.setAddress(address);
		
		log.info("### insertUserProfile : {}", dto.toStringUserProfileToString());
		
		return service.insertUserProfile(dto);
	}
	
	@PostMapping("/user/{id}")
	public int updateUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
		UserProfileDTO dto = new UserProfileDTO();
		
		//dto.setId(id);
		dto.setName(name);
		dto.setPhone(phone);
		dto.setAddress(address);
		
		log.info("### updateUserProfile ID : {}, param : {}", id, dto.toStringUserProfileToString());
		
		return service.updateUserProfile(dto, id);
	}
	
	@DeleteMapping("/user/{id}")
	public int deleteUserProfile(@PathVariable("id") String id) {
		
		log.info("### deleteUserProfile ID : {}", id);
		
		return service.deleteUserProfile(id);
	}
}
