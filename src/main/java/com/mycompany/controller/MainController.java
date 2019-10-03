package com.mycompany.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mycompany.entity.AuthorityType;
import com.mycompany.entity.InfoUser;
import com.mycompany.entity.Order;
import com.mycompany.entity.OrderStatus;
import com.mycompany.entity.Product;
import com.mycompany.entity.Products;
import com.mycompany.jdbc.InfoUserDAO;
import com.mycompany.model.ChoiceCartList;
import com.mycompany.service.ChoiceCart;
import com.mycompany.service.ListOfDepartments;
import com.mycompany.service.OrderServiceInterface;
import com.mycompany.service.ProductServiceInterface;
import com.mycompany.service.ProductsXml;
import com.mycompany.service.ServiceCart;
import com.mycompany.service.UserServiceInterface;

@Controller
@SessionAttributes("order")
public class MainController {
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	ProductServiceInterface productService;
	
	@Autowired
	ListOfDepartments listOfDepartments;
	
	@Autowired
	ServiceCart serviceCart;
	
	@Autowired
	ChoiceCart choiceCart;
	
	@Autowired
	OrderServiceInterface orderService;
	
	@Autowired
	UserServiceInterface userService;
	
	@Autowired
	ProductsXml productsXml;
	
	@ModelAttribute("order")
	public Order order() {		
		return new Order();
	}

	//start page
	@GetMapping({"/", "/welcome"})
	public String mainPage() {			
		return "welcome";
	}
	
	@GetMapping("/setProduct")
	public String setProduct() {
		Products products = productsXml.getProductsFromXml();	
		Logger logger = Logger.getLogger(MainController.class.getName());
		for(Product product: products.getProducts()) {			
			Long id = productService.insert(product);
			String source_file = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images" + File.separator + product.getIdProduct().toString() + "." + "jpg";
			Path path_source = Paths.get(source_file);
			String destination_file = servletContext.getRealPath(File.separator) + File.separator + "WEB-INF" + File.separator + "images" + File.separator + id.toString() + "." + "jpg";
			Path path_destination = Paths.get(destination_file);
			if(Files.exists(path_source)) {
				try {
					Files.copy(path_source, path_destination, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					logger.log(Level.SEVERE, null, e);
				}
			}
		}
		return "redirect:/welcome";
	}
	
	@GetMapping("/setAdmin")
	public String setAdmin() {
		if(userService.userAlreadyExist("2718@i.ua")) {
			return "redirect:/welcome";
		}
		InfoUser infoUser = new InfoUser();
		infoUser.setName("Dmitriy");		
		infoUser.setEmail("2718@i.ua");		
		infoUser.setPassword(new BCryptPasswordEncoder().encode("12345678"));
		infoUser.setEnabled(true);
		infoUser.setAuthority(AuthorityType.ADMIN);
		new InfoUserDAO().save(infoUser);
		return "redirect:/welcome";
	}
	
	//show products
	@GetMapping("/buy/{department}")
	public String buyProduct(@PathVariable("department") String department, @RequestParam(name = "page", defaultValue = "1", required = false) String page, Model model) {
		//convert String to int, if issue then to return -1 
		int pageNow = (int) serviceCart.getId(page);
		if (pageNow == -1) return "redirect:/welcome";		
		for(String dep: listOfDepartments.getDepartments()) {
			if(department.equalsIgnoreCase(dep)) {
				//get a list products form the database
				List<Product> products = productService.findAllByDepartment(department);
				products.sort((Product prod1, Product prod2)-> prod1.getName().compareToIgnoreCase(prod2.getName()));
				//8 products on page
				int productOnPage = 8;
				//the number of pages with 8 products on page
				int pages = getPages(products.size(), productOnPage);
				//if current page <=0 then current page = 1
				if(pageNow<=0) pageNow = 1;
				//if current page > max page then current page = max page
				if(pageNow>pages) pageNow = pages; 
				model.addAttribute("products", productList(products, pageNow, productOnPage));
				model.addAttribute("department", department);
				model.addAttribute("pages", pages);
				model.addAttribute("page", pageNow);
				return "buyPage";
			}
		}		
		return "redirect:/welcome";
	}
	
	//product's details
	@GetMapping("/buy/details/{id}")
	public String buyDetailsProduct(@PathVariable("id") String id, Model model) {
		//convert String to Long, if issue then to return -1L 		
		Product product = getProduct(serviceCart.getId(id));
		//if not find then to return null
		if(product == null) return "redirect:/welcome";
		model.addAttribute("product", product);	
		return "detailsProduct";
	}
	
	//form for checkout
	@GetMapping("/buy/checkout")
	public String checkout() {
		return "checkout";
	}
	
	//checkout
	@PostMapping("/buy/complete")
	public String complete(@ModelAttribute("order") @Valid Order order,BindingResult result , HttpServletRequest request, Principal principal, Model model) {
		
		if(result.hasErrors()) {		
			return "checkout";
		}
		
		//if a unregistered user
		if(order.getIdOrder() == null) {
			model.addAttribute("order", new Order());
			return "redirect:/welcome";
		}
		
		//if a registered user
		order.setPaidDate(Timestamp.valueOf(LocalDateTime.now()));
		order.setStatus(OrderStatus.DELIVERED.name());
		order.setPaid(true);
		//complete order
		orderService.update(order);
		
		InfoUser user =  userService.findByEmail(principal.getName());	
		//set a empty order
		Order orderNew = new Order();		
		choiceCart.createNewOrder(orderNew, user);
		
		model.addAttribute("order", orderNew);
		request.getSession().setAttribute("currentUser", user);
		
		return "redirect:/welcome";
	}
	
	//form for to choose a current order or mix a current order with user's orders
	@GetMapping("/user/choiceCart")
	public String choiceCart(@SessionAttribute("currentUser") InfoUser user, Model model) {
		//select user's orders with status NEW
		List<Order> orderUserNew = orderService.findAllByIdUserStatus(user.getIdUser(), OrderStatus.NEW.name());
		//delete current order
		orderUserNew.remove(orderUserNew.size()-1);	
		model.addAttribute("orderUserNew", orderUserNew);
		//list of orders to be selected
		model.addAttribute("choiceCartList", new ChoiceCartList());
		return "choiceCart";
	}
	
	//mix a current order with user's orders
	@PostMapping("/user/mixCart")
	public String mixCart(@ModelAttribute("choiceCartList") ChoiceCartList choiceCartList, HttpServletRequest request, Principal principal, Model model) {
		
		//if no orders are selected
		if(choiceCartList.getOrderedDate().isEmpty()) return "redirect:/welcome";
		
		InfoUser user =  userService.findByEmail(principal.getName());
		//select user's orders with status NEW
		List<Order> orderUserNew = orderService.findAllByIdUserStatus(user.getIdUser(), OrderStatus.NEW.name());
		//current order
		Order orderUserNewLast = orderUserNew.get(orderUserNew.size()-1);
		//delete current order
		orderUserNew.remove(orderUserNewLast);		
		//mix orders
		for(Timestamp orderedDate: choiceCartList.getOrderedDate()) {			
			for(Order orderUser: orderUserNew) {
				if(orderUser.getOrderedDate().equals(orderedDate)) {
					orderService.delete(orderUserNewLast.getIdOrder());
					orderUserNewLast = choiceCart.mixOrder(orderUserNewLast, orderUser);
					orderService.update(orderUserNewLast);
					break;
				}
			}
		}
		//update model attribute for order and currentUser
		model.addAttribute("order", orderUserNewLast);
		request.getSession().setAttribute("currentUser", userService.findByEmail(principal.getName()));
		
		return "redirect:/welcome";
	}
	
	//show cart
	@GetMapping("/buy/cart")
	public String viewCart() {
		return "cart";
	}
	
	//add a product to order
	@GetMapping("/buy/addToCart")
	public String addToCart(@RequestParam(name = "idProduct") String id, HttpServletRequest request) {
		//convert String to Long, if issue then to return -1L
		Product product = getProduct(serviceCart.getId(id));
		//if not find then to return null
		if(product == null) return "redirect:/welcome";
		//add a product to order
		serviceCart.setItemsToCart(product, request);
		return "redirect:/buy/cart";
	}
	
	//change quantity of items
	@GetMapping("/buy/quantity/{change}")
	public String minusItem(@PathVariable("change") String change, @RequestParam(name = "idProduct") String id, HttpServletRequest request) {	
		//convert String to Long, if issue then to return -1L
		Product product = getProduct(serviceCart.getId(id));
		//if not find then to return null
		if(product == null) return "redirect:/welcome";	
		//change quantity of items
		serviceCart.changeQuantityOfItems(product, request, change.equals("plus")? 1 : change.equals("minus")? -1 : 0);
		return "redirect:/buy/cart";
	}
	
	//find a product
	private Product getProduct(long id) {
		Product product = null;
		if(id > 0)  product = productService.find(id);
		return product;
	}
	
	//get a list of products for one page
	private List<Product> productList(List<Product> products, int page, int productOnPage){
		//the number of pages with 8 products on page
		int pages = getPages(products.size(), productOnPage);	
		//if products <= 8 items
		if(pages==1 || pages<page && products.size()<=productOnPage) {
			return products;
		}
		//for a last page
		else if(pages==page || pages<page) {
			return products.subList(productOnPage*(pages-1), products.size());
		}
		//return 8 products from all products 
		return products.subList(productOnPage*(page-1), productOnPage*page);
	}
	
	//calculate the number of pages
	private int getPages(int size, int productOnPage) {
		double productOnPageDouble = (double) productOnPage;
		return (int) Math.ceil(size/productOnPageDouble);
	}
}
