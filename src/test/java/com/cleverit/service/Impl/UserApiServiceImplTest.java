package com.cleverit.service.Impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cleverit.entity.User;
import com.cleverit.service.impl.UserApiServiceImpl;

@SpringBootTest
public class UserApiServiceImplTest {

	@InjectMocks
	private UserApiServiceImpl userApiServiceImpl;

	@Mock
	RestTemplate restTemplate;

	public static final User user = new User();

	@Test
	public void testlist() {
		String res = "[\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"vpPU69c\",\r\n" + 
				"    \"nombre\": \"prueba2\",\r\n" + 
				"    \"apellido\": \"prueba2\",\r\n" + 
				"    \"profesion\": \"aasdasdsad\",\r\n" + 
				"    \"email\": \"prueba2@s.com\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"pKOIDtY\",\r\n" + 
				"    \"nombre\": \"blah\",\r\n" + 
				"    \"apellido\": \"agile\",\r\n" + 
				"    \"profesion\": \"scrum master\",\r\n" + 
				"    \"email\": \"dev@foo.cl\"\r\n" + 
				"  }]";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		List<User> list = userApiServiceImpl.list();
	}

	@Test
	public void testCreateUser() {
		user.setId("id");
		user.setNombre("nombre");
		user.setApellido("apellido");
		user.setProfesion("profesion");
		user.setEmail("email");
		user.getId();
		user.getNombre();
		user.getApellido();
		user.getProfesion();
		user.getEmail();
		user.toString();		
		boolean res = userApiServiceImpl.createUser(user);
	}

	@Test
	public void testDeleteUser() {
		boolean res = userApiServiceImpl.deleteUser("id");
	}

	@Test
	public void testEditUser() {
		user.setId("id");
		user.setNombre("nombre");
		user.setApellido("apellido");
		user.setProfesion("profesion");
		user.setEmail("email");
		boolean res = userApiServiceImpl.editUser(user);
	}

	@Test
	public void testFindUser() {
		String res = "{\r\n" + 
				"    \"id\": \"vpPU69c\",\r\n" + 
				"    \"nombre\": \"prueba2\",\r\n" + 
				"    \"apellido\": \"prueba2\",\r\n" + 
				"    \"profesion\": \"aasdasdsad\",\r\n" + 
				"    \"email\": \"prueba2@s.com\"\r\n" + 
				"  }";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		User user = userApiServiceImpl.findUser("id");
	}

}
