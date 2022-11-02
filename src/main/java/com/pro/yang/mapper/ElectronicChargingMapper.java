package com.pro.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pro.yang.dto.ElectronicChargingDTO;

@Repository
@Mapper
public interface ElectronicChargingMapper {
	
	List<ElectronicChargingDTO> getElectronicChargingAll();
	
	int insertElectronicCharging(@Param("ElectronicChargingDTO") ElectronicChargingDTO dto);
	
	List <ElectronicChargingDTO> getElectronicChargingList( double longitude, double latitude);
}
