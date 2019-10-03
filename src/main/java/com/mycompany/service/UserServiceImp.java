package com.mycompany.service;

import org.springframework.stereotype.Service;

import com.mycompany.entity.AuthorityType;
import com.mycompany.entity.InfoUser;
import com.mycompany.jdbc.InfoUserDAO;
import com.mycompany.model.UserRegistration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImp implements UserServiceInterface {

	@Override
	public boolean userAlreadyExist(String email) {
		InfoUserDAO dao = new InfoUserDAO();
		InfoUser infoUser = dao.findByEmail(email);
		if(infoUser == null) return false;
		return true;
	}

	@Override
	public void save(UserRegistration user) {
		InfoUserDAO dao = new InfoUserDAO();
		InfoUser infoUser = new InfoUser();
		infoUser.setName(user.getName());
		infoUser.setEmail(user.getEmail());		
		infoUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		infoUser.setEnabled(true);
		infoUser.setAuthority(AuthorityType.USER);
		dao.save(infoUser);
	}

	@Override
	public InfoUser findByEmail(String email) {
		InfoUserDAO dao = new InfoUserDAO();		
		return dao.findByEmail(email);
	}

	@Override
	public void update(InfoUser user) {
		InfoUserDAO dao = new InfoUserDAO();
		dao.update(user);
	}
}
