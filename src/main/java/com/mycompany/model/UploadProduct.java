package com.mycompany.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class UploadProduct {	
	
	private Long idProduct;
	
	@Pattern(regexp="^[a-zA-Z]+$", message="{upload.department.invalid}")
	private String department;
	
	@NotNull(message="{upload.name.null}")
	@NotEmpty(message="{upload.name.empty}")
	private String name;	
	
	@Digits(fraction = 2, integer = 10, message="{upload.price.digits}")
	private double price;
	
	@NotNull(message="{upload.currency.null}")
	private String currency;
	
	@NotNull(message="{upload.availability.null}")
	private String availability;
	
	@NotNull(message="{upload.description.null}")
	@NotEmpty(message="{upload.description.empty}")
	private String description;
	
	@NotNull(message="{upload.specification.null}")
	@NotEmpty(message="{upload.specification.empty}")
	private String specification1;
	
	@NotNull(message="{upload.specification.null}")
	@NotEmpty(message="{upload.specification.empty}")
	private String specification2;
	
	@NotNull(message="{upload.specification.null}")
	@NotEmpty(message="{upload.specification.empty}")
	private String specification3;	
	
	private String specification4;
	private String specification5;	
	
	@NotNull(message="{upload.image.null}")
	private MultipartFile image;
	
	public UploadProduct() {
		
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

	public void setSpecification1(String specification1) {
		this.specification1 = specification1;
	}
	
	public String getSpecification1() {
		return specification1;
	}
	
	public void setSpecification2(String specification2) {
		this.specification2 = specification2;
	}
	
	public String getSpecification2() {
		return specification2;
	}
	
	public void setSpecification3(String specification3) {
		this.specification3 = specification3;
	}
	
	public String getSpecification3() {
		return specification3;
	}
	
	public void setSpecification4(String specification4) {
		this.specification4 = specification4;
	}
	
	public String getSpecification4() {
		return specification4;
	}
	
	public void setSpecification5(String specification5) {
		this.specification5 = specification5;
	}
	
	public String getSpecification5() {
		return specification5;
	}
	
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public MultipartFile getImage() {
		return image;
	}	
}
