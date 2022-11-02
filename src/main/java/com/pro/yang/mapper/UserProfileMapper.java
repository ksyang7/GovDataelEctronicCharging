package com.pro.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pro.yang.dto.UserProfileDTO;

@Repository
@Mapper
public interface UserProfileMapper {
	UserProfileDTO getUserProfile(@Param("id") String id);
	
	List<UserProfileDTO> getUserProfileList();
	
	int insertUserProfile(@Param("UserProfileDTO") UserProfileDTO dto);
	
	int updateUserProfile(@Param("UserProfileDTO") UserProfileDTO dto,@Param("id") String id);

	int deleteUserProfile(@Param("id") String id);
}
