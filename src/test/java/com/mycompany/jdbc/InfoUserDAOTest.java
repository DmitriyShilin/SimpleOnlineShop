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

public class InfoUserDAOTest {
	
	//InfoUser
	private static List<Order> ordersExp;
	private static List<Order> ordersUpd;
	private static List<Order> ordersDel;
	
	private static InfoUser userExp;
	private static InfoUser userUpd;
	private static InfoUser userDel;
	
	private static Long idUserExp;
	private static Long idUserUpd;
	private static Long idUserDel;
	
	//Order
	private static Order orderExp;
	private static List<LineItem> lineitemsExp;
	private static Long idOrderExp;
	
	//LineItem
    private static LineItem lineitemExp;
		
	//Product	
	private static List<String> specificationsExp;
	private static Product productExp;
	private static Long idProductExp;	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Product
		specificationsExp = new LinkedList<>();
		specificationsExp.add("specificationsExp");		
		productExp = new Product("departmentExpUser", "nameExpUser", 0.99, "currencyExpUser", "availabilityExpUser", "descriptionExpUser", specificationsExp);		
		ProductDAO productDAO = new ProductDAO();
		productDAO.insert(productExp);				
		idProductExp = productDAO.findAllByDepartment("departmentExpUser").get(0).getIdProduct();
		
		//InfoUser
		ordersExp = new LinkedList<>();
		ordersUpd = new LinkedList<>();
		ordersDel = new LinkedList<>();
		
		userExp = new InfoUser("surnameExp", "nameExp", "patronymicExp", "emailExp", "passwordExp", "phoneExp", "statusExp", ordersExp, true, AuthorityType.USER);
		userUpd = new InfoUser("surnameUpd", "nameUpd", "patronymicUpd", "emailUpd", "passwordUpd", "phoneUpd", "statusUpd", ordersUpd, true, AuthorityType.USER);
		userDel = new InfoUser("surnameDel", "nameDel", "patronymicDel", "emailDel", "passwordDel", "phoneDel", "statusDel", ordersDel, true, AuthorityType.USER);
		
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.insert(userExp);
		infoUserDAO.insert(userUpd);
		infoUserDAO.insert(userDel);
		
		idUserExp = infoUserDAO.findByEmail("emailExp").getIdUser();
		idUserUpd = infoUserDAO.findByEmail("emailUpd").getIdUser();
		idUserDel = infoUserDAO.findByEmail("emailDel").getIdUser();
		
		//Order
		lineitemsExp = new LinkedList<>();
		lineitemsExp.add(lineitemExp);
				
		Timestamp orderedDateExp = Timestamp.valueOf(LocalDateTime.now());
		Timestamp paidDateExp = Timestamp.valueOf(LocalDateTime.now());
				
		orderExp = new Order(idUserExp, orderedDateExp, paidDateExp, "statusExpUser", "customerAddressExpUser", lineitemsExp, 0.98, true);		
				
		OrderDAO orderDAO = new OrderDAO();
		orderDAO.insert(orderExp);
				
		List<Order> orderLisr = orderDAO.findAllByIdUser(idUserExp);		
		for(Order order: orderLisr) {
			if(order.getStatus().equals("statusExpUser")) idOrderExp = order.getIdOrder();
		}
				
		//LineItem
		lineitemExp = new LineItem(idOrderExp, idProductExp, productExp, 5, 0.99, 0.99*5);
		LineItemDAO lineItemDAO = new LineItemDAO();
		lineItemDAO.insert(lineitemExp);
	}

	@AfterClass
	public static void tearDownAfterClass(){
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.delete(idUserExp);
		infoUserDAO.delete(idUserUpd);
		ProductDAO productDAO = new ProductDAO();
		productDAO.delete(idProductExp);
	}

	@Test
	public void testDelete() {
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.delete(idUserDel);
		InfoUser userResult = infoUserDAO.find(idUserDel);
		assertTrue(userResult == null);
	}

	@Test
	public void testUpdate() {
		List<Order> ordersUpdate = new LinkedList<>();
		InfoUser userUpdate = new InfoUser(idUserUpd, "surnameUpdate", "nameUpdate", "patronymicUpdate", "emailUpdate", "passwordUpdate", "phoneUpdate", "statusUpdate", ordersUpdate, false, AuthorityType.ADMIN);
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		infoUserDAO.update(userUpdate);
		InfoUser userResult = infoUserDAO.find(idUserUpd);
		assertEquals(userResult.getIdUser(), idUserUpd);
		assertEquals(userResult.getSurname(), userUpdate.getSurname());
		assertEquals(userResult.getName(), userUpdate.getName());
		assertEquals(userResult.getPatronymic(), userUpdate.getPatronymic());
		assertEquals(userResult.getEmail(), userUpdate.getEmail());
		assertEquals(userResult.getPassword(), userUpdate.getPassword());
		assertEquals(userResult.getPhone(), userUpdate.getPhone());
		assertEquals(userResult.getStatus(), userUpdate.getStatus());
		assertTrue(userResult.getOrders().containsAll(userUpdate.getOrders()));
		assertTrue(userResult.getEnabled() == userUpdate.getEnabled());
		assertEquals(userResult.getAuthority(), userUpdate.getAuthority());
	}

	@Test
	public void testFind() {
		InfoUserDAO infoUserDAO = new InfoUserDAO();		
		InfoUser userResult = infoUserDAO.find(idUserExp);
		assertEquals(userResult.getIdUser(), idUserExp);
		assertEquals(userResult.getSurname(), userExp.getSurname());
		assertEquals(userResult.getName(), userExp.getName());
		assertEquals(userResult.getPatronymic(), userExp.getPatronymic());
		assertEquals(userResult.getEmail(), userExp.getEmail());
		assertEquals(userResult.getPassword(), userExp.getPassword());
		assertEquals(userResult.getPhone(), userExp.getPhone());
		assertEquals(userResult.getStatus(), userExp.getStatus());
		assertTrue(userResult.getOrders().containsAll(userExp.getOrders()));
		assertTrue(userResult.getEnabled() == userExp.getEnabled());
		assertEquals(userResult.getAuthority(), userExp.getAuthority());
	}

	@Test
	public void testFindByEmail() {
		InfoUserDAO infoUserDAO = new InfoUserDAO();		
		InfoUser userResult = infoUserDAO.findByEmail(userExp.getEmail());
		assertEquals(userResult.getIdUser(), idUserExp);
		assertEquals(userResult.getSurname(), userExp.getSurname());
		assertEquals(userResult.getName(), userExp.getName());
		assertEquals(userResult.getPatronymic(), userExp.getPatronymic());
		assertEquals(userResult.getEmail(), userExp.getEmail());
		assertEquals(userResult.getPassword(), userExp.getPassword());
		assertEquals(userResult.getPhone(), userExp.getPhone());
		assertEquals(userResult.getStatus(), userExp.getStatus());
		assertTrue(userResult.getOrders().containsAll(userExp.getOrders()));
		assertTrue(userResult.getEnabled() == userExp.getEnabled());
		assertEquals(userResult.getAuthority(), userExp.getAuthority());
	}

	@Test
	public void testFindByPhone() {
		InfoUserDAO infoUserDAO = new InfoUserDAO();		
		InfoUser userResult = infoUserDAO.findByPhone(userExp.getPhone());
		assertEquals(userResult.getIdUser(), idUserExp);
		assertEquals(userResult.getSurname(), userExp.getSurname());
		assertEquals(userResult.getName(), userExp.getName());
		assertEquals(userResult.getPatronymic(), userExp.getPatronymic());
		assertEquals(userResult.getEmail(), userExp.getEmail());
		assertEquals(userResult.getPassword(), userExp.getPassword());
		assertEquals(userResult.getPhone(), userExp.getPhone());
		assertEquals(userResult.getStatus(), userExp.getStatus());
		assertTrue(userResult.getOrders().containsAll(userExp.getOrders()));
		assertTrue(userResult.getEnabled() == userExp.getEnabled());
		assertEquals(userResult.getAuthority(), userExp.getAuthority());
	}

	@Test
	public void testFindAll() {
		InfoUserDAO infoUserDAO = new InfoUserDAO();
		List<InfoUser> users = infoUserDAO.findAll();
		for(InfoUser userResult: users) {
			if(userResult.getIdUser() == idUserExp) {
				assertEquals(userResult.getIdUser(), idUserExp);
				assertEquals(userResult.getSurname(), userExp.getSurname());
				assertEquals(userResult.getName(), userExp.getName());
				assertEquals(userResult.getPatronymic(), userExp.getPatronymic());
				assertEquals(userResult.getEmail(), userExp.getEmail());
				assertEquals(userResult.getPassword(), userExp.getPassword());
				assertEquals(userResult.getPhone(), userExp.getPhone());
				assertEquals(userResult.getStatus(), userExp.getStatus());
				assertTrue(userResult.getOrders().containsAll(userExp.getOrders()));
				assertTrue(userResult.getEnabled() == userExp.getEnabled());
				assertEquals(userResult.getAuthority(), userExp.getAuthority());
			}
		}
	}
}
