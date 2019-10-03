package com.mycompany.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mycompany.validator.PasswordMatches;

@PasswordMatches(message="{update.password.matches}")
public class UserUpdate implements UserMatchingPassword{
	
	@NotNull(message="{update.surname.null}")
	@NotEmpty(message="{update.surname.empty}")
	private String surname;
	
	@NotNull(message="{update.name.null}")
	@NotEmpty(message="{update.name.empty}")
	private String name;
	
	@NotNull(message="{update.patronymic.null}")
	@NotEmpty(message="{update.patronymic.empty}")
	private String patronymic;
	
	@Size(max = 24, min = 8, message="{update.password.size}")
	private String password;
	
	private String matchingPassword;
	
	@NotNull(message="{update.phone.null}")
	@NotEmpty(message="{update.phone.empty}")
	@Pattern(regexp="^[+]{1}\\d{12}$", message="{update.phone.invalid}")
	private String phone;
	
	public UserUpdate() {
		
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
		
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	@Override
	public String getMatchingPassword() {
		return matchingPassword;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
}
