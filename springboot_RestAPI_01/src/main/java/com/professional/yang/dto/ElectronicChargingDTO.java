package com.professional.yang.dto;

import lombok.Data;

@Data
public class ElectronicChargingDTO {
	
	private String charging_id  = "";
	private String charging_name  = "";
	private String charging_addr  = "";
	private String charging_addr_detail  = "";
	private String charging_longitude  = "";
	private String charging_latitude  = "";
	private String charging_usage_time  = "";

	public ElectronicChargingDTO(String id, String name, String addr, String addr_detail, String longitude, String latitude, String usage_time) {
		this.charging_id = id;
		this.charging_name = name;
		this.charging_addr = addr;
		this.charging_addr_detail = addr_detail;
		this.charging_longitude = longitude;
		this.charging_latitude = latitude;
		this.charging_usage_time = usage_time;
	}

}
