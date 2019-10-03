package com.mycompany.jdbc;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.entity.AuthorityType;
import com.mycompany.entity.InfoUser;
import com.mycompany.entity.LineItem;
import com.mycompany.entity.Order;
import com.mycompany.entity.Product;

public class OrderDAOTest {
	
	//InfoUser
	private static InfoUser customerExp;
	private static List<Order> ordersExp;
	private static Long idUserExp;
	
	//Order
	private static Order orderExp;
	private static Order orderUpd;
	private static Order orderDel;
	
	private static List<LineItem> lineitemsExp;
	private static List<LineItem> lineitemsUpd;
	private static List<LineItem> lineitemsDel;
	
	private static Long idOrderExp;
	private static Long idOrderUpd;
	private static Long idOrderDel;
	
	//LineItem
	private static LineItem lineitemExp;
	
	//Product	
	private static List<String> specificationsExp;
	private static Product productExp;
	private static Long idProductExp;
	

	@BeforeClass
	public static void setUpBeforeClass() {
		//Product
		specificationsExp = new LinkedList<>();
		specificationsExp.add("specificationsExp");		
		productExp = new Product("departmentExpOrder", "nameExpOrder", 0.99, "currencyExpOrder", "availabilityExpOrder", "descriptionExpOrder", specificationsExp);		
		ProductDAO productDAO = new ProductDAO();
		productDAO.insert(productExp);				
		idProductExp = productDAO.findAllByDepartment("departmentExpOrder").get(0).getIdProduct();
		
		//InfoUser
		ordersExp = new LinkedList<>();
		customerExp = new InfoUser("surnameOrder", "nameOrder", "patronymicOrder", "emailOrder", "passwordOrder", "phoneOrder", "statusOrder", ordersExp, true, AuthorityType.ADMIN);
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.insert(customerExp);
		idUserExp = infoUserDAO.findByEmail("emailOrder").getIdUser();		
		
		//Order
		lineitemsExp = new LinkedList<>();
		lineitemsExp.add(lineitemExp);		
		lineitemsUpd = new LinkedList<>();
		lineitemsUpd.add(new LineItem());
		lineitemsDel = new LinkedList<>();
		lineitemsDel.add(new LineItem());
		
		Timestamp orderedDateExp = Timestamp.valueOf(LocalDateTime.now());
		Timestamp paidDateExp = Timestamp.valueOf(LocalDateTime.now());
		Timestamp orderedDateUpd = Timestamp.valueOf(LocalDateTime.now());
		Timestamp paidDateUpd = Timestamp.valueOf(LocalDateTime.now());
		Timestamp orderedDateDel = Timestamp.valueOf(LocalDateTime.now());
		Timestamp paidDateDel = Timestamp.valueOf(LocalDateTime.now());
		
		orderExp = new Order(idUserExp, orderedDateExp, paidDateExp, "statusExp", "customerAddressExp", lineitemsExp, 0.98, true);
		orderUpd = new Order(idUserExp, orderedDateUpd, paidDateUpd, "statusUpd", "customerAddressUpd", lineitemsUpd, 0.98, true);
		orderDel = new Order(idUserExp, orderedDateDel, paidDateDel, "statusDel", "customerAddressDel", lineitemsDel, 0.98, true);
		
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insert(orderExp);
		orderDAO.insert(orderUpd);
		orderDAO.insert(orderDel);
		
		List<Order> orderLisr = orderDAO.findAllByIdUser(idUserExp);		
		for(Order order: orderLisr) {
			if(order.getStatus().equals("statusExp")) idOrderExp = order.getIdOrder();
			if(order.getStatus().equals("statusUpd")) idOrderUpd = order.getIdOrder();
			if(order.getStatus().equals("statusDel")) idOrderDel = order.getIdOrder();
		}
		
		//LineItem
		lineitemExp = new LineItem(idOrderExp, idProductExp, productExp, 5, 0.99, 0.99*5);
		LineItemDAO lineItemDAO = new LineItemDAO();
		lineItemDAO.insert(lineitemExp);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.delete(idUserExp);
		ProductDAO productDAO = new ProductDAO();
		productDAO.delete(idProductExp);
	}

	@Test
	public void testDelete() {
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.delete(idOrderDel);
		Order order = orderDAO.find(idOrderDel);
		assertTrue(order == null);
	}
	
	@Test
	public void testUpdateOrder() {
		List<LineItem> lineitemsUpdate = new LinkedList<>();
		Timestamp orderedDateUpdate = Timestamp.valueOf(LocalDateTime.now());
		Timestamp paidDateUdate = Timestamp.valueOf(LocalDateTime.now());
		Order orderUpdate = new Order(idOrderUpd, idUserExp, orderedDateUpdate, paidDateUdate, "statusUpdate", "customerAddressUpdate", lineitemsUpdate, 2.99, false);
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.update(orderUpdate);
		Order orderResult = orderDAO.find(idOrderUpd);
		assertEquals(orderResult.getIdOrder(), orderUpdate.getIdOrder());
		assertEquals(orderResult.getIdUser(), orderUpdate.getIdUser());
		assertEquals(orderResult.getOrderedDate(), orderUpdate.getOrderedDate());
		assertEquals(orderResult.getPaidDate(), orderUpdate.getPaidDate());
		assertEquals(orderResult.getStatus(), orderUpdate.getStatus());
		assertEquals(orderResult.getCustomerAddress(), orderUpdate.getCustomerAddress());
		assertTrue(orderResult.getLineItem().containsAll(orderUpdate.getLineItem()));
		assertTrue(orderResult.getTotalprice() == orderUpdate.getTotalprice());
		assertTrue(orderResult.getPaid() == orderUpdate.getPaid());
	}
	
	@Test
	public void testFind() {
		OrderDAO orderDAO = new OrderDAO();
		Order orderResult = orderDAO.find(idOrderExp);		
		assertEquals(orderResult.getIdOrder(), idOrderExp);		
		assertEquals(orderResult.getIdUser(), orderExp.getIdUser());
		assertEquals(orderResult.getOrderedDate(), orderExp.getOrderedDate());
		assertEquals(orderResult.getPaidDate(), orderExp.getPaidDate());
		assertEquals(orderResult.getStatus(), orderExp.getStatus());
		assertEquals(orderResult.getCustomerAddress(), orderExp.getCustomerAddress());
		assertTrue(orderResult.getTotalprice() == orderExp.getTotalprice());
		assertTrue(orderResult.getPaid() == orderExp.getPaid());
	}

	@Test
	public void testFindAll() {
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.findAll();
		for(Order order: orders) {
			if(order.getIdOrder() == idOrderExp) {
				assertTrue(order.getIdOrder() == idOrderExp);
				assertEquals(order.getIdUser(), orderExp.getIdUser());
				assertEquals(order.getOrderedDate(), orderExp.getOrderedDate());
				assertEquals(order.getPaidDate(), orderExp.getPaidDate());
				assertEquals(order.getStatus(), orderExp.getStatus());
				assertEquals(order.getCustomerAddress(), orderExp.getCustomerAddress());
				assertTrue(order.getTotalprice() == orderExp.getTotalprice());
				assertTrue(order.getPaid() == orderExp.getPaid());
			}
		}
	}

	@Test
	public void testFindAllByIdUser() {
		OrderDAO orderDAO = new OrderDAO();
		List<Order> orders = orderDAO.findAllByIdUser(idUserExp);
		for(Order order: orders) {
			if(order.getIdOrder() == idOrderExp) {
				assertTrue(order.getIdOrder() == idOrderExp);
				assertEquals(order.getIdUser(), orderExp.getIdUser());
				assertEquals(order.getOrderedDate(), orderExp.getOrderedDate());
				assertEquals(order.getPaidDate(), orderExp.getPaidDate());
				assertEquals(order.getStatus(), orderExp.getStatus());
				assertEquals(order.getCustomerAddress(), orderExp.getCustomerAddress());
				assertTrue(order.getTotalprice() == orderExp.getTotalprice());
				assertTrue(order.getPaid() == orderExp.getPaid());
			}
		}
	}
}
