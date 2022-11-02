package com.pro.yang.gov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.pro.yang.dto.ElectronicChargingDTO;

@Service
@Transactional
public class CallGovDataAPI {
	
	private static final Logger log = LoggerFactory.getLogger(CallGovDataAPI.class);
	
	public List<ElectronicChargingDTO> callElectronicChargingApi(String page, String perPage) {
		
			List<ElectronicChargingDTO> list = new ArrayList<ElectronicChargingDTO>();
			
			try {
			       StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15039545/v1/uddi:e87a2022-4e6d-4caa-a750-a9127d82be67_202004141324"); /*URL*/
			       urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2BfsBVJiHj1vOdKK1jPFCwf%2FZjNuB3hwY4AmZc9XIjsP1JtPjz3M%2BwDAtmOCD%2F2SaZ5efIDrflw2klKOguc52nQ%3D%3D"); /*Service Key*/
			       urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode(page, "UTF-8")); /*페이지번호*/
			       urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode(perPage, "UTF-8")); /*검색건수*/
			        
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
			        
			         
			        for(int i=0; i < totalCount ; i++) {
			        	
			        	JSONObject jsonData = (JSONObject)ElecChargingArr.get(i);
			        	log.info("jsonData {}  / {} : {}", i,totalCount, jsonData.toString());
			        	
			            if(jsonData.get("충전소ID") != "" && jsonData.get("충전소명") != "" && jsonData.get("충전소주소") != "" ) {

			            	ElectronicChargingDTO dto = new ElectronicChargingDTO();
			            	
			            	dto.setCharging_id((String)jsonData.get("충전소ID"));
				            dto.setCharging_name((String)jsonData.get("충전소명"));
				            dto.setCharging_addr((String)jsonData.get("충전소주소"));
				            
				           if (jsonData.get("상세주소") == "") { 
				        	   dto.setCharging_addr("");
				           } else {
				        	   dto.setCharging_addr((String)jsonData.get("상세주소"));
				           }
				           
				            if(jsonData.get("경도") == "") {
				            	 dto.setCharging_longitude("");
				            } else {
				            	 dto.setCharging_longitude((String)jsonData.get("경도"));
				            }
				            
				            if(jsonData.get("위도") == "") {
				            	dto.setCharging_longitude("");
				            } else {
				            	dto.setCharging_longitude((String)jsonData.get("위도"));
				            }
				            
				            if(jsonData.get("이용가능시간") == "") {
				            	dto.setCharging_usage_time("");
				            } else {
				            	dto.setCharging_usage_time((String)jsonData.get("이용가능시간"));
				            }
				            
				            list.add(dto);
			            }
		        	}
		        
			        br.close();
			        conn.disconnect();
			               
			} catch (Exception e) {
				
				e.printStackTrace();
			}	       
			 return list;
	}
	
}
