package com.mycompany.service;

import com.mycompany.entity.LineItem;
import com.mycompany.entity.Order;

public class ServiceOrderEntity {
	
	//calculate order.total and lineitem.amount
	public void setTotal(Order order) {
		double totalprice = 0.0;
		for(LineItem item: order.getLineItem()) {
			item.setAmount(round(item.getQuantity()*item.getPrice()));	
			totalprice += item.getAmount();
		}
		order.setTotalprice(round(totalprice));
		if(order.getIdOrder() != null) {
			new OrderServiceImp().update(order);
		}
	}
	
	private double round(double number){
		return Math.round(number*100)/100.0;
	}
}
