package com.cleverit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleverit.entity.User;
import com.cleverit.service.UserApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class MainController {

	@Autowired
	private final UserApiService userApiService;

	@Autowired
	public MainController(UserApiService userApiService) {
		this.userApiService = userApiService;
	}

	@GetMapping("/welcome")
	public String index(Model model) throws JsonMappingException, JsonProcessingException {
		model.addAttribute("usuarios", userApiService.list());
		return "welcome";
	}
	
	@GetMapping("/new")
	public String newUser(Model model) {
		User user = new User();
		model.addAttribute("User",user);
		return "new";
	}

	@PostMapping("/create")
	public String createUser(User user) {
		boolean save = false;
		save = userApiService.createUser(user);
		return (save) ? "redirect:/welcome" : "new";
	}
	
	@GetMapping("/edit")
	public String editUser(Model model, @RequestParam(name = "id", required = false) String id) {
		User user;
		user = userApiService.findUser(id);
		model.addAttribute("User",user);
		return "edit";
	}
	
	@PostMapping("/editsave")
	public String editUserSave(User user) {
		boolean save = false;
		save = userApiService.editUser(user);		
		return (save) ? "redirect:/welcome" : "edit";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam(name = "id", required = false) String id) {
		boolean save = false;
		save = userApiService.deleteUser(id);
		return "redirect:/welcome";
	}
	
	

}
