package com.mycompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.entity.Order;
import com.mycompany.jdbc.OrderDAO;

@Service
public class OrderServiceImp implements OrderServiceInterface{

	@Override
	public Long insert(Order order) {
		OrderDAO dao = new OrderDAO();
		return dao.insert(order);
	}

	@Override
	public void delete(Long id) {
		OrderDAO dao = new OrderDAO();
		dao.delete(id);
	}

	@Override
	public void update(Order order) {
		OrderDAO dao = new OrderDAO();
		dao.update(order);
	}

	@Override
	public Order find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAllByIdUser(Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAllByIdUserStatus(Long idUser, String status) {
		OrderDAO dao = new OrderDAO();
		return dao.findAllByIdUserStatus(idUser, status);
	}
}
