package com.mycompany.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mycompany.entity.InfoUser;
import com.mycompany.entity.OrderStatus;
import com.mycompany.model.UserUpdate;
import com.mycompany.service.OrderServiceInterface;
import com.mycompany.service.UserServiceInterface;

@Controller
public class UserController {
	
	@Autowired
	UserServiceInterface userService;
	
	@Autowired
	OrderServiceInterface orderService;
	
	//show all orders
	@GetMapping("/user/orderList")
	public String orderInfo() {
		return "orderList";
	}

	//show menu for user (all orders, orders with status NEW, user info). For admin manage products (add/update/delete).
	@GetMapping("/user/accountInfo")
	public String accountInfo(@SessionAttribute("currentUser") InfoUser user, Model model) {
		model.addAttribute("orderNewSize", orderService.findAllByIdUserStatus(user.getIdUser(), OrderStatus.NEW.name()).size()-1);
		return "accountinfo";
	}
	
	//show a user info
	@GetMapping("/user/profile")
	public String profile() {		
		return "profile";
	}
	
	//form for update a user info
	@GetMapping("/user/profileUpdate")
	public String profileUpdate(Model model) {		
		model.addAttribute("userUpdate", new UserUpdate());
		return "profileUpdate";
	}
	
	//update a user info
	@PostMapping("/user/updateUser")
	public String saveUser(@ModelAttribute("userUpdate") @Valid UserUpdate user, BindingResult result, HttpServletRequest request) {		
		if(result.hasErrors()) {			
			return "profileUpdate";
		}
		InfoUser userInfo = (InfoUser) request.getSession().getAttribute("currentUser");
		userInfo.setSurname(user.getSurname());
		userInfo.setName(user.getName());
		userInfo.setPatronymic(user.getPatronymic());
		userInfo.setPhone(user.getPhone());
		userInfo.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userService.update(userInfo);
		return "redirect:/user/profile";
	}
}
