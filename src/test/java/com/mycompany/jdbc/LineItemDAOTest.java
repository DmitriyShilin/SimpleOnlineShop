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

public class LineItemDAOTest {
	
	//InfoUser
	private static InfoUser customerExp;
	private static List<Order> ordersExp;
	private static Long idUserExp;
		
	//Order
	private static Order orderExp;
	private static List<LineItem> lineitemsExp;
	private static Long idOrderExp;	
		
	//LineItem
	private static LineItem lineitemExp;
	private static LineItem lineitemUpd;
	private static LineItem lineitemDel;
	private static Long idLineitemExp;
	private static Long idLineitemUpd;
	private static Long idLineitemDel;
	private static int quantityExp = 5;
	private static int quantityUpd = 6;
	private static int quantityDel = 99;
	private static double priceExp = 0.98;
		
	//Product	
	private static List<String> specificationsExp;
	private static Product productExp;
	private static Long idProductExp;

	@BeforeClass
	public static void setUpBeforeClass() {
		//Product
		specificationsExp = new LinkedList<>();
		specificationsExp.add("specificationsExp");		
		productExp = new Product("departmentExpLineItem", "nameExpLineItem", 0.99, "currencyExpLineItem", "availabilityExpLineItem", "descriptionExpLineItem", specificationsExp);		
		ProductDAO productDAO = new ProductDAO();
		productDAO.insert(productExp);				
		idProductExp = productDAO.findAllByDepartment("departmentExpLineItem").get(0).getIdProduct();
				
		//InfoUser
		ordersExp = new LinkedList<>();
		customerExp = new InfoUser("surnameLineItem", "nameLineItem", "patronymicLineItem", "emailLineItem", "passwordLineItem", "phoneLineItem", "statusLineItem", ordersExp, true, AuthorityType.ADMIN);
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.insert(customerExp);
		idUserExp = infoUserDAO.findByEmail("emailLineItem").getIdUser();
				
		//Order
		lineitemsExp = new LinkedList<>();
		lineitemsExp.add(lineitemExp);
		lineitemsExp.add(lineitemUpd);
		lineitemsExp.add(lineitemDel);				
		Timestamp orderedDateExp = Timestamp.valueOf(LocalDateTime.now());
		Timestamp paidDateExp = Timestamp.valueOf(LocalDateTime.now());		
		orderExp = new Order(idUserExp, orderedDateExp, paidDateExp, "statusExpLineItem", "customerAddressExpLineItem", lineitemsExp, 0.98, true);				
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insert(orderExp);
		List<Order> orderLisr = orderDAO.findAllByIdUser(idUserExp);		
		for(Order order: orderLisr) {
			if(order.getStatus().equals("statusExpLineItem")) idOrderExp = order.getIdOrder();		
		}
			
		//LineItem		
		lineitemExp = new LineItem(idOrderExp, idProductExp, productExp, quantityExp, priceExp, priceExp*quantityExp);
		lineitemUpd = new LineItem(idOrderExp, idProductExp, productExp, quantityUpd, priceExp, priceExp*quantityUpd);
		lineitemDel = new LineItem(idOrderExp, idProductExp, productExp, quantityDel, priceExp, priceExp*quantityDel);
		LineItemDAO lineItemDAO = new LineItemDAO();
		lineItemDAO.insert(lineitemExp);
		lineItemDAO.insert(lineitemUpd);
		lineItemDAO.insert(lineitemDel);
		List<LineItem> lineItems = lineItemDAO.findAllByIdOrder(idOrderExp);
		for(LineItem lineItem: lineItems) {			
			if(lineItem.getQuantity() == quantityExp) idLineitemExp = lineItem.getIdLineitem();			
			if(lineItem.getQuantity() == quantityUpd) idLineitemUpd = lineItem.getIdLineitem();			
			if(lineItem.getQuantity() == quantityDel) idLineitemDel = lineItem.getIdLineitem();			
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.delete(idUserExp);
		ProductDAO productDAO = new ProductDAO();
		productDAO.delete(idProductExp);	
	}
	
	@Test
	public void testDelete() {
		LineItemDAO lineItemDAO = new LineItemDAO();
		lineItemDAO.delete(idLineitemDel);
		LineItem itemResult = lineItemDAO.find(idLineitemDel);
		assertTrue(itemResult == null);
	}

	@Test
	public void testUpdate() {
		LineItem itemUpdate = new LineItem(idLineitemUpd, idOrderExp, idProductExp, productExp, 26, 0.97, 26*0.97); 		
		LineItemDAO lineItemDAO = new LineItemDAO();
		lineItemDAO.update(itemUpdate);
		LineItem itemResult = lineItemDAO.find(idLineitemUpd);
		assertEquals(itemResult.getIdLineitem(), idLineitemUpd);
		assertEquals(itemResult.getIdOrder(), itemUpdate.getIdOrder());
		assertEquals(itemResult.getIdProduct(), itemUpdate.getIdProduct());
		assertEquals(itemResult.getProduct().getName(), itemUpdate.getProduct().getName());
		assertTrue(itemResult.getQuantity() == 26);
		assertTrue(itemResult.getPrice() == 0.97);
		assertEquals(itemResult.getAmount(), 26*0.97, 0.01);		
	}

	@Test
	public void testFind() {
		LineItemDAO lineItemDAO = new LineItemDAO();
		LineItem itemResult = lineItemDAO.find(idLineitemExp);
		assertEquals(itemResult.getIdLineitem(), idLineitemExp);
		assertEquals(itemResult.getIdOrder(), lineitemExp.getIdOrder());		
		assertEquals(itemResult.getIdProduct(), lineitemExp.getIdProduct());
		assertEquals(itemResult.getProduct().getName(), lineitemExp.getProduct().getName());
		assertTrue(itemResult.getQuantity() == quantityExp);
		assertTrue(itemResult.getPrice() == priceExp);
		assertEquals(itemResult.getAmount(), priceExp*quantityExp, 0.01);
	}
	
	@Test
	public void testFindAll() {
		LineItemDAO lineItemDAO = new LineItemDAO();
		List<LineItem> lineitems = lineItemDAO.findAll();
		for(LineItem itemResult: lineitems) {
			if(itemResult.getIdLineitem() == idLineitemExp) {
				assertEquals(itemResult.getIdLineitem(), idLineitemExp);
				assertEquals(itemResult.getIdOrder(), lineitemExp.getIdOrder());
				assertEquals(itemResult.getIdProduct(), lineitemExp.getIdProduct());
				assertEquals(itemResult.getProduct().getName(), lineitemExp.getProduct().getName());
				assertTrue(itemResult.getQuantity() == quantityExp);
				assertTrue(itemResult.getPrice() == priceExp);
				assertEquals(itemResult.getAmount(), priceExp*quantityExp, 0.01);
			}
		}
	}

	@Test
	public void testFindAllByIdOrder() {
		LineItemDAO lineItemDAO = new LineItemDAO();
		List<LineItem> lineitems = lineItemDAO.findAllByIdOrder(idOrderExp);
		for(LineItem itemResult: lineitems) {
			if(itemResult.getIdLineitem() == idLineitemExp) {
				assertEquals(itemResult.getIdLineitem(), idLineitemExp);
				assertEquals(itemResult.getIdOrder(), lineitemExp.getIdOrder());
				assertEquals(itemResult.getIdProduct(), lineitemExp.getIdProduct());
				assertEquals(itemResult.getProduct().getName(), lineitemExp.getProduct().getName());
				assertTrue(itemResult.getQuantity() == quantityExp);
				assertTrue(itemResult.getPrice() == priceExp);
				assertEquals(itemResult.getAmount(), priceExp*quantityExp, 0.01);
			}
		}
	}
}
