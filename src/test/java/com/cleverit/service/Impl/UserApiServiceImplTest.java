package com.cleverit.service.Impl;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
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

	private static User user = new User();

	@InjectMocks
	private UserApiServiceImpl userApiServiceImpl;

	@Mock
	RestTemplate restTemplate;

	@BeforeAll
	public static void init() {
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
	}

	@Test
	public void testlist() {
		String res = "[\r\n" + "  {\r\n" + "    \"id\": \"vpPU69c\",\r\n" + "    \"nombre\": \"prueba2\",\r\n"
				+ "    \"apellido\": \"prueba2\",\r\n" + "    \"profesion\": \"aasdasdsad\",\r\n"
				+ "    \"email\": \"prueba2@s.com\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": \"pKOIDtY\",\r\n"
				+ "    \"nombre\": \"blah\",\r\n" + "    \"apellido\": \"agile\",\r\n"
				+ "    \"profesion\": \"scrum master\",\r\n" + "    \"email\": \"dev@foo.cl\"\r\n" + "  }]";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		List<User> list = userApiServiceImpl.list();
		Assert.assertNotNull("Ok", list);
	}

	@Test
	public void testCreateUser() {
		boolean create = userApiServiceImpl.createUser(user);
		Assert.assertTrue("Ok", create);
	}

	@Test
	public void testDeleteUser() {
		boolean delete = userApiServiceImpl.deleteUser(user.getId());
		Assert.assertTrue("Ok", delete);
	}

	@Test
	public void testEditUser() {
		String res = "{\r\n" + "    \"id\": \"vpPU69c\",\r\n" + "    \"nombre\": \"prueba2\",\r\n"
				+ "    \"apellido\": \"prueba2\",\r\n" + "    \"profesion\": \"aasdasdsad\",\r\n"
				+ "    \"email\": \"prueba2@s.com\"\r\n" + "  }";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.PUT), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		boolean edit = userApiServiceImpl.editUser(user);
		Assert.assertTrue("Ok", edit);
	}

	@Test
	public void testFindUser() {
		String res = "{\r\n" + "    \"id\": \"vpPU69c\",\r\n" + "    \"nombre\": \"prueba2\",\r\n"
				+ "    \"apellido\": \"prueba2\",\r\n" + "    \"profesion\": \"aasdasdsad\",\r\n"
				+ "    \"email\": \"prueba2@s.com\"\r\n" + "  }";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		User user = userApiServiceImpl.findUser("id");
		Assert.assertNotNull("Ok", user);
	}

}
