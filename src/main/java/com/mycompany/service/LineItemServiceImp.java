package com.mycompany.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycompany.entity.LineItem;
import com.mycompany.jdbc.LineItemDAO;

@Service
public class LineItemServiceImp implements LineItemServiceInterface{

	@Override
	public Long insert(LineItem lineitem) {
		LineItemDAO dao = new LineItemDAO();		
		return dao.insert(lineitem);
	}

	@Override
	public void delete(Long id) {
		LineItemDAO dao = new LineItemDAO();		
		dao.delete(id);
	}

	@Override
	public void update(LineItem lineitem) {
		LineItemDAO dao = new LineItemDAO();		
		dao.update(lineitem);
	}

	@Override
	public LineItem find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LineItem> findAllByIdOrder(Long idOrder) {
		// TODO Auto-generated method stub
		return null;
	}

}
