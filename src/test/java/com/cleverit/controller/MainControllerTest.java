package com.cleverit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeTypeUtils;

import com.cleverit.entity.User;
import com.cleverit.service.UserApiService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserApiService userApiService;

	@Test
	public void testIndex() throws Exception {
		final ResultActions result = mockMvc.perform(get("/welcome").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void testNewUser() throws Exception {
		final ResultActions result = mockMvc.perform(get("/new").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void testCreateUser() throws Exception {
		Mockito.when(userApiService.createUser(Mockito.any(User.class))).thenReturn(true);
		final ResultActions result = mockMvc.perform(post("/create").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void testEditUser() throws Exception {
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "1");
		Mockito.when(userApiService.findUser(Mockito.any(String.class))).thenReturn(new User());
		final ResultActions result = mockMvc
				.perform(get("/edit").accept(MimeTypeUtils.APPLICATION_JSON_VALUE).params(requestParams));
		Assert.assertNotNull("Ok", result);
	}
	
	@Test
	public void testEditSave() throws Exception {
		Mockito.when(userApiService.editUser(Mockito.any(User.class))).thenReturn(true);
		final ResultActions result = mockMvc.perform(post("/editsave").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

	@Test
	public void testDeleteUser() throws Exception {
		final ResultActions result = mockMvc.perform(get("/delete").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

}
