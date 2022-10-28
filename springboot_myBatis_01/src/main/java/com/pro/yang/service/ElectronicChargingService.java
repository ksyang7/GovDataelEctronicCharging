package com.pro.yang.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pro.yang.dto.ElectronicChargingDTO;
import com.pro.yang.gov.CallGovDataAPI;
import com.pro.yang.mapper.ElectronicChargingMapper;

@Service
@Transactional
public class ElectronicChargingService {
	
	private static final Logger log = LoggerFactory.getLogger(ElectronicChargingService.class);
	
	@Autowired
	private ElectronicChargingMapper mapper;
	
	public List<ElectronicChargingDTO> getElectronicChargingAll(){
		
		return mapper.getElectronicChargingAll();
	}
	
	public List<ElectronicChargingDTO> getElectronicChargingList(double longitude, double latitude){
		return mapper.getElectronicChargingList(longitude+0.2, latitude+0.2);
	}

	public void  insertElectronicCharging(String page, String perPage) {
		
		CallGovDataAPI callGovData = new CallGovDataAPI();
		
		List<ElectronicChargingDTO> govDataList =  callGovData.callElectronicChargingApi(page, perPage);
		
		for (int i = 0; i < govDataList.size(); i++) {
			mapper.insertElectronicCharging(govDataList.get(i));
			
		   log.info("insertElectronicCharging {} : {}", i, govDataList.get(i) );
		}
	}
	
}
