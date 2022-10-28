package com.professional.yang.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.professional.yang.dto.ElectronicChargingDTO;

@Mapper
public interface ElectronicChargingMapper {
	
	@Insert("INSERT INTO ELECTRONIC_CHARGING  VALUES (#{charging_id}, #{charging_name}, #{charging_addr}, #{charging_addr_detail}, #{charging_longitude}, #{charging_latitude}, #{charging_usage_time})")
	int insertElectronicCharging(@Param("charging_id") String charging_id, @Param("charging_name") String charging_name, @Param("charging_addr") String charging_addr, @Param("charging_addr_detail") String charging_addr_detail, @Param("charging_longitude") String charging_longitude, @Param("charging_latitude") String charging_latitude, @Param("charging_usage_time") String charging_usage_time);
	
//	int insertElectronicChargingInfo(ElectronicChargingDTO electronicChargingDto) throws SQLException;
//	
//	List<ElectronicChargingDTO> getElectronicChargingList() throws SQLException;
}