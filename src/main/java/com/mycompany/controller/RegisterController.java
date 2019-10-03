package com.mycompany.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.model.UserRegistration;
import com.mycompany.service.UserServiceInterface;

@Controller
public class RegisterController {
	
	@Autowired
	UserServiceInterface userService;
	
	//form for registration
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new UserRegistration());
		return "register";
	}
	
	//registration
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") @Valid UserRegistration user, BindingResult result, Model model) {
		if(result.hasErrors()) {			
			return "register";
		}
		//check 
		if(userService.userAlreadyExist(user.getEmail())) {
			model.addAttribute("message", "User already exist!");
			return "register";
		} 
		else {	
			//save a new user
			userService.save(user);
			return "redirect:/registered";
		}
	}
	
	//registration congratulations
	@GetMapping("/registered")
	public String registeredUser() {
		return "registered";
	}
}
