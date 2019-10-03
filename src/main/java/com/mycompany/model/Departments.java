package com.mycompany.model;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//get list of department from department.xml
@XmlRootElement(name = "departments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Departments {
	
	@XmlElement(name = "department")
	private List<String> departments;

	public Departments() {
		departments= new LinkedList<String>();
	}
	
	public List<String> getDepartments() {
		return departments;
	}
	
	public void setDepartments(List<String> departments) {
		 this.departments = departments;
	}
}
