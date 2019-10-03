package com.mycompany.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.entity.InfoUser;
import com.mycompany.entity.LineItem;
import com.mycompany.entity.Order;
import com.mycompany.entity.OrderStatus;

@Service
public class ChoiceCart {
	
	@Autowired
	LineItemServiceInterface lineItemService;
	
	@Autowired
	OrderServiceInterface orderService;	
	
	//mix two orders
	public Order mixOrder(Order order, Order orderUser) {
		
		//if one order is empty
		if(order.getLineItem().isEmpty()) {			
			return orderUser;
		}
		
		//if one order is empty
		if(orderUser.getLineItem().isEmpty()) {			
			return order;
		}
		
		//get the matched products 
		List<LineItem> lineItemMatchedUser = new LinkedList<LineItem>();
		List<LineItem> lineItemMatchedSession = new LinkedList<LineItem>();
		for(LineItem itemUser: orderUser.getLineItem()) {
			for(LineItem itemSession: order.getLineItem()) {
				if(itemUser.getIdProduct().equals(itemSession.getIdProduct())) {					
					lineItemMatchedUser.add(itemUser);
					lineItemMatchedSession.add(itemSession);
					break;
				}
			}			
		}
		//delete the matched products from order and orderUser
		orderUser.getLineItem().removeAll(lineItemMatchedUser);
		order.getLineItem().removeAll(lineItemMatchedSession);
		//mix the matched products
		for(LineItem itemUser: lineItemMatchedUser) {
			for(LineItem itemSession: lineItemMatchedSession) {
				if(itemUser.getIdProduct().equals(itemSession.getIdProduct())) {					
					itemUser.setQuantity(itemUser.getQuantity()+itemSession.getQuantity());
					break;
				}
			}			
		}
		//set the matched products
		orderUser.getLineItem().addAll(lineItemMatchedUser);
		//set the remaining products
		if(!order.getLineItem().isEmpty()) {
			for(LineItem item: order.getLineItem()) {
				item.setIdOrder(orderUser.getIdOrder());
				item.setIdLineitem(lineItemService.insert(item));
				orderUser.getLineItem().add(item);
				}
		}
		//calculate total and amount
		new ServiceOrderEntity().setTotal(orderUser);
		//update lineitems
		for(LineItem itemUser: lineItemMatchedUser){
			lineItemService.update(itemUser);
		}
		return orderUser;
	}
	
	//set a new order for user
	public void createNewOrder(Order order, InfoUser infoUser) {
		order.setIdUser(infoUser.getIdUser());		
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		order.setOrderedDate(timestamp);
		order.setPaidDate(timestamp);
		order.setStatus(OrderStatus.NEW.name());
		order.setCustomerAddress("");		
		Long idOrder = orderService.insert(order);
		order.setIdOrder(idOrder);
		if(!order.getLineItem().isEmpty()) for(LineItem item: order.getLineItem()) {
			item.setIdOrder(idOrder);
			item.setIdLineitem(lineItemService.insert(item));
		}
		infoUser.getOrders().add(order);
	}
}
