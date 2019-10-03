package com.mycompany.entity;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	
	private Long idProduct;
	private String department;
	private String name;
	private double price;
	private String currency;
	private String availability;
	private String description;
	
	@XmlElementWrapper(name = "specifications")
	@XmlElement(name = "specification")
	private List<String> specifications;
	
	public Product() {
		specifications = new LinkedList<>();
	}
	
	public Product(String department, String name, double price, String currency, String availability, String description, List<String> specifications) {
		this.department = department;
		this.name = name;
		this.price = price;
		this.currency = currency;
		this.availability = availability;
		this.description = description;
		this.specifications = specifications;
	}

	public Product(Long idProduct, String department, String name, double price, String currency, String availability, String description, List<String> specifications) {
		this.idProduct = idProduct;
		this.department = department;
		this.name = name;
		this.price = price;
		this.currency = currency;
		this.availability = availability;
		this.description = description;
		this.specifications = specifications;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Long idProduct) {
		 this.idProduct = idProduct;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	public String getAvailability() {
		return availability;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setSpecifications(List<String> specifications) {
		this.specifications = specifications;
	}
	
	public List<String> getSpecifications() {
		return specifications;
	}
}
