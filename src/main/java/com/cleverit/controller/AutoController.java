package com.cleverit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cleverit.entity.Car;
import com.cleverit.repo.IAuto;
import com.cleverit.service.AutoApiService;

@Controller
public class AutoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoController.class);

	@Autowired
	private final AutoApiService autoApiService;
	
	@Autowired
	private IAuto repo;

	@Autowired
	public AutoController(AutoApiService autoApiService) {
		this.autoApiService = autoApiService;
	}

	@GetMapping("/licenseplate")
	public String newUser(Model model) {
		try {
			List<Car> list = autoApiService.list();
			LOGGER.info(list.toString());
			//Guardar en BD POSTGRESQL
			//Datos de conexión en appication.properties
			repo.saveAll(list);
			model.addAttribute("message", "Datos Obtenidos guardados en la Base de Datos");
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
			model.addAttribute("message", "Error al realizar el proceso, intente mas tarde");
		}
		return "auto";
	}
}
