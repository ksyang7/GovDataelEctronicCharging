package com.pro.yang.dto;

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
	
	public String toStringElectronicCharging() {
		String str = "";
		str = "id: "+ this.charging_id + ", name: " + this.charging_name +  ", addr: " + this.charging_addr +  ", addr_detail: " + this.charging_addr_detail  +  ", longitude: " + this.charging_longitude  +  ", latitude: " + this.charging_latitude  +  ", usage_time: " + this.charging_usage_time;
		return str;
	}
}
