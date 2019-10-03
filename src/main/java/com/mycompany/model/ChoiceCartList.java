package com.mycompany.model;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

//for /buy/choiceCart and /buy/mixCart
public class ChoiceCartList {
	
	private List<Timestamp> orderedDate;
	
	public ChoiceCartList(){
		orderedDate = new LinkedList<Timestamp>();
	}
	
	public void setOrderedDate(List<Timestamp> orderedDate) {
		this.orderedDate = orderedDate;
	}
	
	public List<Timestamp> getOrderedDate() {
		return orderedDate;
	}
}
