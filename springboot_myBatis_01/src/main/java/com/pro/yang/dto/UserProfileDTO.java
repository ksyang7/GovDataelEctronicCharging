package com.pro.yang.dto;

import lombok.Data;

@Data
public class UserProfileDTO {
	private String id;
	private String name;
	private String phone;
	private String address;
	
	public String toStringUserProfileToString() {
		String str = "";
		str = "id: "+ this.id + ", name: " + this.name +  ", phone: " + this.phone +  ", address: " + this.address;
		return str;
	}
	
}
