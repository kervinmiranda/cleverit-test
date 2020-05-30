package com.cleverit.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.cleverit.entity.User;
import com.cleverit.service.UserApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserApiServiceImpl implements UserApiService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserApiServiceImpl.class);
	
	private String url = "http://arsene.azurewebsites.net/User/";

	@Autowired
	private RestTemplate restTemplate;

	private HttpHeaders headers = new HttpHeaders();

	@Override
	public List<User> list() {
		List<User> list = null;
		ObjectMapper objectMapper = new ObjectMapper();
		String res;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET, null,
					String.class);
			res = response.getBody();
			try {
				list = objectMapper.readValue(res, new TypeReference<List<User>>() {
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

	@Override
	public boolean createUser(User user) {
		boolean res = false;
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<>(user, headers);
		ResponseEntity<String> response = null;		
		try {
			response = restTemplate.exchange(url, HttpMethod.POST, entity,
					String.class);
			res = true;
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}		
		return res;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean res = false;
		String urldel = url + id; 
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;		
		try {
			response = restTemplate.exchange(urldel, HttpMethod.DELETE, null,
					String.class);
			res = true;
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}		
		return res;
	}

	@Override
	public boolean editUser(User user) {
		boolean res = false;
		String urledit = url + user.getId(); 
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<User> entity = new HttpEntity<>(user, headers);
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(urledit, HttpMethod.PUT, entity,
					String.class);
			res = true;
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}		
		return res;
	}

	@Override
	public User findUser(String id) {
		User user = new User();
		String res;
		ObjectMapper objectMapper = new ObjectMapper();		
		String urledit = url + id; 
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(urledit, HttpMethod.GET, null,
					String.class);
			res = response.getBody();
			try {
				user = objectMapper.readValue(res, new TypeReference<User>() {
				});
			} catch (JsonMappingException e) {
				LOGGER.error(e.getMessage());
			} catch (JsonProcessingException e) {
				LOGGER.error(e.getMessage());
			}
		} catch (HttpStatusCodeException e) {
			LOGGER.error(e.getMessage());
		}		
		return user;
	}

}
