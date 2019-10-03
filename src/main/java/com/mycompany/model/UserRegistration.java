package com.mycompany.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mycompany.validator.PasswordMatches;

@PasswordMatches(message="{register.password.matches}")
public class UserRegistration implements UserMatchingPassword {
	
	@NotNull(message="{register.name.null}")
	@NotEmpty(message="{register.name.empty}")
	private String name;
	
	@NotNull(message="{register.email.null}")
	@NotEmpty(message="{register.email.empty}")
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="{register.email.invalid}")	
	private String email;
	
	@Size(max = 24, min = 8, message="{register.password.size}")
	private String password;
	
	private String matchingPassword;
	
	public UserRegistration() {
		
	}	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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
}
