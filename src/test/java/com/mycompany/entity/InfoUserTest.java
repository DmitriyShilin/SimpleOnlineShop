package com.mycompany.entity;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class InfoUserTest {
	
	private static List<Order> orders;
	private static InfoUser user;
	
	@BeforeClass
	public static void init() {
		orders = new LinkedList<>();
		orders.add(new Order());
		user = new InfoUser(1L, "surname", "name", "patronymic", "email", "password", "phone", "status", orders, true, AuthorityType.ADMIN);
	}

	@Test
	public void testGetIdUser() {
		Long expResult = 1L;
		Long result = user.getIdUser();
		assertTrue(expResult == result);
	}

	@Test
	public void testSetIdUser() {
		InfoUser userExp = new InfoUser();
		Long expResult = 5L;
		userExp.setIdUser(expResult);
		Long result = userExp.getIdUser();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetSurname() {
		InfoUser userExp = new InfoUser();
		String expResult = "Addington";
		userExp.setSurname(expResult);
		String result = userExp.getSurname();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetSurname() {
		String expResult = "surname";
		String result = user.getSurname();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetName() {
		InfoUser userExp = new InfoUser();
		String expResult = "Bob";
		userExp.setName(expResult);
		String result = userExp.getName();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetName() {
		String expResult = "name";
		String result = user.getName();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetPatronymic() {
		InfoUser userExp = new InfoUser();
		String expResult = "William";
		userExp.setPatronymic(expResult);
		String result = userExp.getPatronymic();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetPatronymic() {
		String expResult = "patronymic";
		String result = user.getPatronymic();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetEmail() {
		InfoUser userExp = new InfoUser();
		String expResult = "email@mail.com";
		userExp.setEmail(expResult);
		String result = userExp.getEmail();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetEmail() {
		String expResult = "email";
		String result = user.getEmail();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetPassword() {
		InfoUser userExp = new InfoUser();
		String expResult = "qwerty";
		userExp.setPassword(expResult);
		String result = userExp.getPassword();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetPassword() {
		String expResult = "password";
		String result = user.getPassword();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetPhone() {
		InfoUser userExp = new InfoUser();
		String expResult = "+380501234567";
		userExp.setPhone(expResult);
		String result = userExp.getPhone();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetPhone() {
		String expResult = "phone";
		String result = user.getPhone();
		assertEquals(expResult, result);
	}

	@Test
	public void testSetStatus() {
		InfoUser userExp = new InfoUser();
		String expResult = "new";
		userExp.setStatus(expResult);
		String result = userExp.getStatus();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetStatus() {
		String expResult = "status";
		String result = user.getStatus();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testSetOrders() {
		InfoUser productExp = new InfoUser();
		List<Order> expResult = new LinkedList<>();
		expResult.add(new Order());
		productExp.setOrders(expResult);
		List<Order> result = productExp.getOrders();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}

	@Test
	public void testGetOrders() {
		List<Order> expResult = orders;
		List<Order> result = user.getOrders();
		assertSame(expResult, result);
		assertTrue(result.containsAll(expResult));
	}
	
	@Test
	public void testSetEnabled() {
		InfoUser userExp = new InfoUser();
		boolean expResult = false;
		userExp.setEnabled(expResult);
		boolean result = userExp.getEnabled();
		assertTrue(expResult == result);
	}

	@Test
	public void testGetEnabled() {
		boolean expResult = true;
		boolean result = user.getEnabled();
		assertTrue(expResult == result);
	}
	
	@Test
	public void testSetAuthority() {
		InfoUser userExp = new InfoUser();
		AuthorityType expResult = AuthorityType.USER;
		userExp.setAuthority(expResult);
		AuthorityType result = userExp.getAuthority();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetAuthority() {
		AuthorityType expResult = AuthorityType.ADMIN;
		AuthorityType result = user.getAuthority();
		assertEquals(expResult, result);
	}
}
