package com.mycompany.entity;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class OrderTest {
	
	private static List<LineItem> lineitems;
	private static Order order;
	private static Timestamp orderedDate;
	private static Timestamp paidDate;
	private static double totalprice = 50.00;
	private static boolean paid = true;

	@BeforeClass
	public static void setUpBeforeClass() {
		lineitems = new LinkedList<>();
		lineitems.add(new LineItem());	
		orderedDate = Timestamp.valueOf(LocalDateTime.now());
		paidDate = Timestamp.valueOf(LocalDateTime.now());
		order = new Order(1L, 2L, orderedDate, paidDate, "status", "customerAddress", lineitems, totalprice, paid);
	}

	@Test
	public void testGetIdOrder() {
		Long expResult = 1L;
		Long result = order.getIdOrder();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetIdOrder() {
		Order orderExp = new Order();
		Long expResult = 5L;
		orderExp.setIdOrder(expResult);
		Long result = orderExp.getIdOrder();
		assertTrue(expResult == result);
	}
	
	@Test
	public void testGetIdUser() {
		Long expResult = 2L;
		Long result = order.getIdUser();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetIdUser() {
		Order orderExp = new Order();
		Long expResult = 20L;
		orderExp.setIdUser(expResult);
		Long result = orderExp.getIdUser();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetOrderedDate() {
		Order orderExp = new Order();
		Timestamp expResult = Timestamp.valueOf(LocalDateTime.now());
		orderExp.setOrderedDate(expResult);
		Timestamp result = orderExp.getOrderedDate();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOrderedDate() {
		Timestamp expResult = orderedDate;
		Timestamp result = order.getOrderedDate();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetPaidDate() {
		Order orderExp = new Order();
		Timestamp expResult = Timestamp.valueOf(LocalDateTime.now());
		orderExp.setPaidDate(expResult);
		Timestamp result = orderExp.getPaidDate();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetPaidDate() {
		Timestamp expResult = paidDate;
		Timestamp result = order.getPaidDate();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetStatus() {
		Order orderExp = new Order();
		String expResult = "delivered";
		orderExp.setStatus(expResult);
		String result = orderExp.getStatus();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetStatus() {
		String expResult = "status";
		String result = order.getStatus();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetCustomerAddress() {
		Order orderExp = new Order();
		String expResult = "Ukraine, Kiev, Hospitalna 12g";
		orderExp.setCustomerAddress(expResult);
		String result = orderExp.getCustomerAddress();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetCustomerAddress() {
		String expResult = "customerAddress";
		String result = order.getCustomerAddress();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetLineItem() {
		Order orderExp = new Order();
		List<LineItem> expResult = new LinkedList<>();
		expResult.add(new LineItem());
		orderExp.setLineItem(expResult);
		List<LineItem> result = orderExp.getLineItem();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}

	@Test
	public void testGetLineItem() {
		List<LineItem> expResult = lineitems;
		List<LineItem> result = order.getLineItem();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}

	@Test
	public void testSetTotalprice() {
		Order orderExp = new Order();
		double expResult = 0.8;
		orderExp.setTotalprice(expResult);
		double result = orderExp.getTotalprice();
		assertTrue(expResult == result);
	}

	@Test
	public void testGetTotalprice() {
		double expResult = totalprice;
		double result = order.getTotalprice();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetPaid() {
		Order orderExp = new Order();
		boolean expResult = paid;
		orderExp.setPaid(expResult);
		boolean result = orderExp.getPaid();
		assertTrue(expResult == result);
	}

	@Test
	public void testGetPaid() {
		boolean expResult = paid;
		boolean result = order.getPaid();
		assertTrue(expResult == result);
	}

}
