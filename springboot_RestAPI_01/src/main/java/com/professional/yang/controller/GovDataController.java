package com.professional.yang.controller;

import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.professional.yang.mapper.ElectronicChargingMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class GovDataController {
	
	private static final Logger log = LoggerFactory.getLogger(GovDataController.class) ;
	
//	private ElectronicChargingMapper electronicChargingMapper;
//	
//	private GovDataController (ElectronicChargingMapper electronicChargingMapper) {
//		this.electronicChargingMapper = electronicChargingMapper;		
//	}
	@Autowired
	private ElectronicChargingMapper electronicChargingMapper;
		
	@GetMapping("/xmlapi")
	public String callApiWithXml() {
		StringBuffer result = new StringBuffer();
		try {
			String apiUrl = "http://openapi.kepco.co.kr/service/EvInfoServiceV2/getEvSearchList?addr=%EC%A0%84%EB%A0%A5%EB%A1%9C"
									+ "&pageNo=1"
									+ "&numOfRows=10"
									+ "&ServiceKey=hAgRdVx3F/ARiludHT2n8xMIqZIcNKII3IPOgPyMAtd4Ret/aHMxE7gyZp7yXmdtH/j7wYe8R8tt2P0Nu2EqSA==";
			
			URL url = new URL(apiUrl);
			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			
			String returnLine;
			result.append("<xmp>");
			
			while((returnLine = bufferedReader.readLine()) != null) {
				result.append(returnLine + "\n");
			}
			urlConnection.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result + "</xmp>";
	}

	
	@GetMapping("/electronicChargingApi")
	public void callElectronicChargingApi() {
		try {
	        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15039545/v1/uddi:e87a2022-4e6d-4caa-a750-a9127d82be67_202004141324"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2BfsBVJiHj1vOdKK1jPFCwf%2FZjNuB3hwY4AmZc9XIjsP1JtPjz3M%2BwDAtmOCD%2F2SaZ5efIDrflw2klKOguc52nQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	       urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("4000", "UTF-8")); /*검색건수*/
	        
	        URL url = new URL(urlBuilder.toString());
	       
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        BufferedReader br;
	        String result;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        } else {
	            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        result = br.readLine();
	        
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
	        
	        JSONArray  ElecChargingArr = (JSONArray)jsonObject.get("data");
	        
	        Long totalCount = (Long)jsonObject.get("totalCount");
	         
	        for(int i=0; i< totalCount; i++) {
	        	JSONObject tmp = (JSONObject)ElecChargingArr.get(i);
	        	log.info("DATA : {}", tmp.toString());
	        	//ElectronicChargingInfo  infoObject = new ElectronicChargingInfo((String)tmp.get("충전소ID"), ( String)tmp.get("충전소명"), (String)tmp.get("충전소주소"),(String)tmp.get("상세주소"),(String)tmp.get("위도"), (String)tmp.get("경도"), (String)tmp.get("이용가능시간"));
	       
	        	electronicChargingMapper.insertElectronicCharging((String)tmp.get("충전소ID"), ( String)tmp.get("충전소명"), (String)tmp.get("충전소주소"), (String)tmp.get("상세주소"), (String)tmp.get("경도"), (String)tmp.get("위도"), (String)tmp.get("이용가능시간"));
	        
	        }
        
	        br.close();
	        conn.disconnect();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@GetMapping("/api")
	public void callApiExprorer() {
		try {
	        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15039545/v1/uddi:e87a2022-4e6d-4caa-a750-a9127d82be67_202004141324"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2BfsBVJiHj1vOdKK1jPFCwf%2FZjNuB3hwY4AmZc9XIjsP1JtPjz3M%2BwDAtmOCD%2F2SaZ5efIDrflw2klKOguc52nQ%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*검색건수*/
	        
	        URL url = new URL(urlBuilder.toString());
	       
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        
	        log.info("DATA : {}", sb.toString());
		        
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
