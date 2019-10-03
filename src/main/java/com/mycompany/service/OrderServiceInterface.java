package com.mycompany.service;

import java.util.List;

import com.mycompany.entity.Order;

public interface OrderServiceInterface {
	
	public Long insert(Order order);
	
	public void delete(Long id);
	
	public void update(Order order);
	
	public Order find(Long id);
	
	public List<Order> findAll();
	
	public List<Order> findAllByIdUser(Long idUser);
	
	public List<Order> findAllByIdUserStatus(Long idUser, String status);
}
