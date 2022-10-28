package com.pro.yang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.yang.dto.UserProfileDTO;
import com.pro.yang.mapper.UserProfileMapper;

@Service
@Transactional
public class UserProfileService {
	
	@Autowired
	private UserProfileMapper mapper;
	
	public UserProfileDTO getUserProfile(String id) {
		return mapper.getUserProfile(id);
	}
	
	public List<UserProfileDTO> getUserProfileList(){
		return mapper.getUserProfileList();
	}
	
	public int insertUserProfile(UserProfileDTO dto) {
		return mapper.insertUserProfile(dto);
	}
	
	public int updateUserProfile(UserProfileDTO dto, String id) {
		return mapper.updateUserProfile(dto, id);
	}

	public int deleteUserProfile(String id) {
		return mapper.deleteUserProfile(id);
	}
}
