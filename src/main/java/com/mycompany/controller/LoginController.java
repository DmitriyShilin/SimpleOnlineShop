package com.mycompany.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.entity.InfoUser;
import com.mycompany.entity.Order;
import com.mycompany.entity.OrderStatus;
import com.mycompany.service.ChoiceCart;
import com.mycompany.service.LineItemServiceInterface;
import com.mycompany.service.OrderServiceInterface;
import com.mycompany.service.UserServiceInterface;

@Controller
@SessionAttributes("currentUser")
public class LoginController {
	
	@Autowired
	UserServiceInterface userService;
	
	@Autowired
	LineItemServiceInterface lineItemService;
	
	@Autowired
	OrderServiceInterface orderService;
	
	@Autowired
	ChoiceCart choiceCart;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/loginFailed")
	public String loginFailed(Model model) {
		model.addAttribute("error", "true");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, SessionStatus sessionStatus) {
		Order order = (Order) request.getSession().getAttribute("order");	
		//delete a empty order from the database
		if(order.getLineItem().isEmpty()) {			
			orderService.delete(order.getIdOrder());
		}
		//set a new empty order
		request.getSession().setAttribute("order", new Order());
		SecurityContextHolder.getContext().setAuthentication(null);
		sessionStatus.setComplete();
		return "redirect:/";
	}
	
	@PostMapping("/postLogin")
	public String postLogin(HttpServletRequest request, Model model) {	
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//get info about a user from the database
		InfoUser infoUser = userService.findByEmail(principal.getUsername());
		//get a current order
		Order order = (Order) request.getSession().getAttribute("order");
		//get user's orders where status is NEW from the database (sorting by date)
		List<Order> orderUserNew = orderService.findAllByIdUserStatus(infoUser.getIdUser(), OrderStatus.NEW.name());
		
		//delete a last (according to date) user's order if it is empty 
		if(!orderUserNew.isEmpty()) {
			Order orderUserNewLast = orderUserNew.get(orderUserNew.size()-1);			
			if(orderUserNewLast.getLineItem().isEmpty()) {
				orderUserNew.remove(orderUserNewLast);
				infoUser.getOrders().remove(orderUserNewLast);
				orderService.delete(orderUserNewLast.getIdOrder());
			}
		}
		
		//if user doesn't have a order with status is NEW, current order to assign to the user
		if(orderUserNew.isEmpty()) {				
			choiceCart.createNewOrder(order, infoUser);
			model.addAttribute("currentUser", infoUser);
			return "redirect:/welcome";
		}
		
		//if current order is empty and user has one order with status is NEW, user's order makes a current order
		if(order.getLineItem().isEmpty() && orderUserNew.size() == 1) {
			request.getSession().setAttribute("order", orderUserNew.get(0));
			model.addAttribute("currentUser", infoUser);
			return "redirect:/welcome";
		}
		
		//if current order isn't empty and user has one or more orders with status is NEW, go to a choice cart page 
		choiceCart.createNewOrder(order, infoUser);
		model.addAttribute("currentUser", infoUser);
		return "redirect:/user/choiceCart";
	}
}