package com.mycompany.entity;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {
	
	@XmlElement(name = "product")
	private List<Product> products;
	
	public Products() {
		products = new LinkedList<>();
	}
	
	public Products(List<Product> products) {
		this.products = products;
	}
	
	public void setProducts(LinkedList<Product> products) {
		this.products = products;
	}
	
	public List<Product> getProducts(){
		return products;
	}
}
