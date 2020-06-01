package com.cleverit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MimeTypeUtils;

import com.cleverit.entity.Car;
import com.cleverit.repo.IAuto;
import com.cleverit.service.AutoApiService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(AutoController.class)
public class AutoControllerTest {

	private static List<Car> cars;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AutoApiService autoApiService;

	@MockBean
	private IAuto repo;

	@BeforeAll
	public static void init() {
		cars = new ArrayList<Car>();
		Car car = new Car();
		car.setId("id");
		car.setTipoAuto("tipoAuto");
		car.setPatente("patente");
		car.setColor("color");
		cars.add(car);
	}

	@Test
	public void testNewUser() throws Exception {
		Mockito.when(autoApiService.list()).thenReturn(cars);
		Mockito.when(repo.saveAll(Mockito.anyList())).thenReturn(cars);
		final ResultActions result = mockMvc.perform(get("/licenseplate").accept(MimeTypeUtils.APPLICATION_JSON_VALUE));
		Assert.assertNotNull("Ok", result);
	}

}
