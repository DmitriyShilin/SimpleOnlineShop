package com.mycompany.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.entity.LineItem;
import com.mycompany.entity.Order;
import com.mycompany.entity.Product;

@Service
public class ServiceCart {
	
	@Autowired
	LineItemServiceInterface lineItemService;
	
	Logger logger = Logger.getLogger(ServiceCart.class.getName());
	
	//convert String to Long, if issue then to return -1L 
	public long getId(String id) {
		long idProduct;
		try{
			idProduct = Long.valueOf(id);
		}
		catch (NumberFormatException e) {
			logger.log(Level.SEVERE, null, e);
			return -1L;
		}
		return idProduct;
	}
	
	//add a product to order
	public void setItemsToCart(Product product, HttpServletRequest request) {
		boolean flag = true;
		Order order = (Order) request.getSession().getAttribute("order");
		//check a product
		for(LineItem item: order.getLineItem()) {
			//if order contained the product
			if(item.getIdProduct().equals(product.getIdProduct())) {
				//change quantity of product
				setQuantityOfItems(order, item, 1);
				flag = false;
				break;
			}
		}		
		if(flag) {
			//if a new product
			setNewItem(order, product);
		}
	}
	
	//change quantity of product
	public void changeQuantityOfItems(Product product, HttpServletRequest request, int quantity) {
		Order order = (Order) request.getSession().getAttribute("order");		
		for(LineItem item: order.getLineItem()) {
			if(item.getIdProduct().equals(product.getIdProduct())) {
				//if quantity = 0 delete a product from order 
				if(item.getQuantity() + quantity == 0) {
					if(order.getIdOrder() != null) lineItemService.delete(item.getIdLineitem());
					order.getLineItem().remove(item);					
					new ServiceOrderEntity().setTotal(order);
					break;
				}
				setQuantityOfItems(order, item, quantity);
			}
		}
	}
	
	//set quantity of product
	private void setQuantityOfItems(Order order, LineItem item, int quantity) {
		item.setQuantity(item.getQuantity()+quantity);
		new ServiceOrderEntity().setTotal(order);
		if(order.getIdOrder() != null) lineItemService.update(item);		
	}
	
	//set a new product
	private void setNewItem(Order order, Product product) {
		LineItem newItem = new LineItem();		
		newItem.setIdProduct(product.getIdProduct());
		newItem.setProduct(product);
		newItem.setQuantity(1);
		newItem.setPrice(product.getPrice());
		order.getLineItem().add(newItem);
		new ServiceOrderEntity().setTotal(order);
		if(order.getIdOrder() != null) {
			newItem.setIdOrder(order.getIdOrder());
			newItem.setIdLineitem(lineItemService.insert(newItem));
		}
	}
}
