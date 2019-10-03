package com.mycompany.entity;

import java.util.LinkedList;
import java.util.List;

public class InfoUser {
	
	private Long idUser;
	private String surname;
	private String name;
	private String patronymic;
	private String email;
	private String password;
	private String phone;
	private String status; 
	private List<Order> orders;
	private boolean enabled;
	private AuthorityType authority;
	
	
	public InfoUser() {
		orders = new LinkedList<>();
	}
	
	public InfoUser(String surname, String name, String patronymic, String email, String password, String phone, String status, List<Order> orders, boolean enabled, AuthorityType authority) {
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.status = status;
		this.orders = orders;
		this.enabled = enabled;
		this.authority = authority;
	}

	public InfoUser(Long idUser, String surname, String name, String patronymic, String email, String password, String phone, String status, List<Order> orders, boolean enabled, AuthorityType authority) {
		this.idUser = idUser;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.status = status;
		this.orders = orders;
		this.enabled = enabled;
		this.authority = authority;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Long idUser) {
		 this.idUser = idUser;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	
	public void setAuthority(AuthorityType authority) {
		this.authority = authority;
	}
	
	public AuthorityType getAuthority() {
		return authority;
	}
}
