package com.mycompany.entity;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProductTest {	
	
	private static List<String> specifications;	
	private static Product product;
			
	@BeforeClass
	public static void init() {
		specifications = new LinkedList<>();
		specifications.add("specifications");
		product = new Product(1L, "department", "name", 50.00, "currency", "availability", "description", specifications);
	}

	@Test
	public void testGetIdProduct() {
		Long expResult = 1L;
		Long result = product.getIdProduct();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetIdProduct() {
		Product productExp = new Product();
		Long expResult = 5L;
		productExp.setIdProduct(expResult);
		Long result = productExp.getIdProduct();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetDepartment() {
		Product productExp = new Product();
		String expResult = "vegetables";
		productExp.setDepartment(expResult);
		String result = productExp.getDepartment();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetDepartment() {
		String expResult = "department";
		String result = product.getDepartment();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetName() {
		Product productExp = new Product();
		String expResult = "cucumber";
		productExp.setName(expResult);
		String result = productExp.getName();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetName() {
		String expResult = "name";
		String result = product.getName();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetPrice() {
		Product productExp = new Product();
		double expResult = 0.89;
		productExp.setPrice(expResult);
		double result = productExp.getPrice();
		assertTrue(expResult == result);
	}

	@Test
	public void testGetPrice() {
		double expResult = 50.00;
		double result = product.getPrice();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetCurrency() {
		Product productExp = new Product();
		String expResult = "$";
		productExp.setCurrency(expResult);
		String result = productExp.getCurrency();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetCurrency() {
		String expResult = "currency";
		String result = product.getCurrency();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetAvailability() {
		Product productExp = new Product();
		String expResult = "in stock";
		productExp.setAvailability(expResult);
		String result = productExp.getAvailability();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetAvailability() {
		String expResult = "availability";
		String result = product.getAvailability();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetDescription() {
		Product productExp = new Product();
		String expResult = "The Lebanese cucumber is green skinned.";
		productExp.setDescription(expResult);
		String result = productExp.getDescription();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetDescription() {
		String expResult = "description";
		String result = product.getDescription();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetSpecifications() {
		Product productExp = new Product();
		List<String> expResult = new LinkedList<>();
		expResult.add("green");
		expResult.add("juicy");		
		productExp.setSpecifications(expResult);
		List<String> result = productExp.getSpecifications();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}

	@Test
	public void testGetSpecifications() {
		List<String> expResult = specifications;
		List<String> result = product.getSpecifications();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}
}
