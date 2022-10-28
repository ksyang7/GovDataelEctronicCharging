package com.professional.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.professional.yang.model.UserProfile;

@Mapper
public interface UserProfileMapper {
	
	@Select("SELECT * FROM UserProfile WHERE id = #{id}")
	UserProfile getUserProfile(@Param("id") String id) ;
	
	@Select ("SELECT * FROM UserProfile")
	List<UserProfile> getUserProfileList();
	
	@Insert("INSERT INTO UserProfile VALUES (#{id}, #{name}, #{phone}, #{address})")
	int insertUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);
	
	@Update("UPDATE UserProfile set name = #{name}, phone = #{phone}, address = #{address} WHERE id = #{id}")
	int updateUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);

	@Delete("DELETE FROM UserProfile where id = #{id}")
	int deleteUserProfile(@Param("id") String id);
}
