package com.mycompany.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class LineItemTest {
	
	private Product product = new Product();
	private int quantity = 10;
	private double price = 19.99;
	private double amount = quantity*price;
	private LineItem lineitem = new LineItem(1L, 2L, 3L, product, quantity, price, amount);

	@Test
	public void testGetIdLineitem() {
		Long expResult = 1L;
		Long result = lineitem.getIdLineitem();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetIdLineitem() {
		LineItem lineitemExp = new LineItem();
		Long expResult = 5L;
		lineitemExp.setIdLineitem(expResult);
		Long result = lineitemExp.getIdLineitem();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetIdOrder() {
		Long expResult = 2L;
		Long result = lineitem.getIdOrder();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetIdOrder() {
		LineItem lineitemExp = new LineItem();
		Long expResult = 56L;
		lineitemExp.setIdOrder(expResult);
		Long result = lineitemExp.getIdOrder();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testGetIdProduct() {
		Long expResult = 3L;
		Long result = lineitem.getIdProduct();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetIdProduct() {
		LineItem lineitemExp = new LineItem();
		Long expResult = 66L;
		lineitemExp.setIdProduct(expResult);
		Long result = lineitemExp.getIdProduct();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetProduct() {
		Product expResult = product;
		Product result = lineitem.getProduct();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetProduct() {
		LineItem lineitemExp = new LineItem();
		Product expResult = new Product();
		lineitemExp.setProduct(expResult);
		Product result = lineitemExp.getProduct();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetQuantity() {
		int expResult = quantity;
		int result = lineitem.getQuantity();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetQuantity() {
		LineItem lineitemExp = new LineItem();
		int expResult = 99;
		lineitemExp.setQuantity(expResult);
		int result = lineitemExp.getQuantity();
		assertTrue(expResult == result);
	}

	@Test
	public void testGetPrice() {
		double expResult = price;
		double result = lineitem.getPrice();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetPrice() {
		LineItem lineitemExp = new LineItem();
		double expResult = 99.99;
		lineitemExp.setPrice(expResult);
		double result = lineitemExp.getPrice();
		assertTrue(expResult == result);
	}

	@Test
	public void testGetAmount() {
		double expResult = amount;
		double result = lineitem.getAmount();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetAmount() {
		LineItem lineitemExp = new LineItem();
		double expResult = 0.99;
		lineitemExp.setAmount(expResult);
		double result = lineitemExp.getAmount();
		assertTrue(expResult == result);
	}
}
