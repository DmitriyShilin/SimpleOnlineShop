package com.mycompany.entity;

public class LineItem {
	
	private Long idLineitem;
	private Long idOrder;
	private Long idProduct;
	private Product product;
	private int quantity;
	private double price;
	private double amount;
	
	public LineItem() {
		
	}
	
	public LineItem(Long idOrder, Long idProduct, Product product, int quantity, double price, double amount) {
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
	
	public LineItem(Long idLineitem, Long idOrder, Long idProduct, Product product, int quantity, double price, double amount) {
		this.idLineitem = idLineitem;
		this.idOrder = idOrder;
		this.idProduct = idProduct;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
	}
	
	public Long getIdLineitem() {
		return idLineitem;
	}
	
	public void setIdLineitem(Long idLineitem) {
		 this.idLineitem = idLineitem;
	}
	
	public Long getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(Long idOrder) {
		 this.idOrder = idOrder;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Long idProduct) {
		 this.idProduct = idProduct;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		 this.product = product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		 this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		 this.price = price;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		 this.amount = amount;
	}
}
