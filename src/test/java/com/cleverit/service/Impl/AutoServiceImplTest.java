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

import com.cleverit.entity.Car;
import com.cleverit.service.impl.AutoApiServiceImpl;

@SpringBootTest
public class AutoServiceImplTest {

	@InjectMocks
	private AutoApiServiceImpl autoApiServiceImpl;

	@Mock
	RestTemplate restTemplate;
	
	public static final Car car = new Car();
	
	@Test
	public void testCar() {
		car.setId("id");
		car.setTipoAuto("tipoAuto");
		car.setPatente("patente");
		car.setColor("color");
		car.getId();
		car.getTipoAuto();
		car.getPatente();
		car.getColor();
		car.toString();
	}
	
	@Test
	public void testList() {
		String res = "[\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"adfdkl\",\r\n" + 
				"    \"patente\": \"GYSS81\",\r\n" + 
				"    \"tipoAuto\": \"Sedan\",\r\n" + 
				"    \"color\": \"Rojo\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"mbXqx35\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"dIZXamf\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"c_BauuS\"\r\n" + 
				"  },\r\n" + 
				"  {\r\n" + 
				"    \"id\": \"L_yrccy\"\r\n" + 
				"  }\r\n" + 
				"]";
		ResponseEntity<String> response = new ResponseEntity<>(res, HttpStatus.OK);
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(),
				Mockito.eq(String.class))).thenReturn(response);
		List<Car> list = autoApiServiceImpl.list();
	}
}
