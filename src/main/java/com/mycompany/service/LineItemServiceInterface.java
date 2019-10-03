package com.mycompany.service;

import java.util.List;

import com.mycompany.entity.LineItem;

public interface LineItemServiceInterface {
	
	public Long insert(LineItem lineitem);
	
	public void delete(Long id);
	
	public void update(LineItem lineitem);
	
	public LineItem find(Long id);
	
	public List<LineItem> findAll();
	
	public List<LineItem> findAllByIdOrder(Long idOrder);	
}
