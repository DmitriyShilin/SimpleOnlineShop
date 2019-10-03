package com.mycompany.service;

import com.mycompany.entity.InfoUser;
import com.mycompany.model.UserRegistration;

public interface UserServiceInterface {
	
	public boolean userAlreadyExist(String email);
	
	public void save(UserRegistration user);
	
	public InfoUser findByEmail(String email);
	
	public abstract void update(InfoUser user);
}
