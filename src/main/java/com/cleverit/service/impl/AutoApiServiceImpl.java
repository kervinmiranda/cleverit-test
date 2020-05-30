package com.cleverit.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.cleverit.entity.Car;
import com.cleverit.service.AutoApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AutoApiServiceImpl implements AutoApiService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoApiServiceImpl.class);
	
	private String url = "https://arsene.azurewebsites.net/LicensePlate/";
	
	@Autowired
	private RestTemplate restTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Override
	public List<Car> list() {
		List<Car> list = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		String res;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, null,
					String.class);
			res = response.getBody();
			try {
				list = objectMapper.readValue(res, new TypeReference<List<Car>>() {
				});
			} catch (JsonMappingException e) {
				LOGGER.error(e.getMessage());
			} catch (JsonProcessingException e) {
				LOGGER.error(e.getMessage());
			}
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}
		return list;
	}

}
