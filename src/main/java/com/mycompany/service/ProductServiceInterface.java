package com.mycompany.service;

import java.util.List;

import com.mycompany.entity.Product;

public interface ProductServiceInterface {
	
	public abstract Long insert(Product product);
	
	public abstract void delete(Long id);
	
	public abstract void update(Product product);
	
	public abstract Product find(Long id);
	
	public abstract List<Product> findAll();
	
	public abstract List<Product> findAllByDepartment(String department);

}
