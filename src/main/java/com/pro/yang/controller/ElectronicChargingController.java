package com.pro.yang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pro.yang.dto.ElectronicChargingDTO;
import com.pro.yang.service.ElectronicChargingService;



@RestController
public class ElectronicChargingController {
	
	private static final Logger log = LoggerFactory.getLogger(ElectronicChargingController.class);
	
	@Autowired
	private ElectronicChargingService service;
	
	@GetMapping("/elecCharg/all")
	public List<ElectronicChargingDTO> getElectronicChargingAll(){
		log.info("### getElectronicChargingAll Size : {}", service.getElectronicChargingAll() == null ? "0" : service.getElectronicChargingAll().size());
		return service.getElectronicChargingAll();
	}
	
	@GetMapping("/elecCharg/list")
	public List<ElectronicChargingDTO> getElectronicChargingList(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude){

		log.info("### getElectronicChargingList  [RequestParam] longitude= {}, latitude= {}", longitude, latitude);
		
		return service.getElectronicChargingList(longitude, latitude);
	}
	
	@PutMapping("/elecCharg/add")
	public void insertElectronicCharging(@RequestParam("page") String page, @RequestParam("perPage") String perPage) {
		
		service.insertElectronicCharging(page, perPage);
		
	}
}
