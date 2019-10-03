package com.mycompany.jdbc;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mycompany.entity.Product;

public class ProductDAOTest {
	
	private static List<String> specificationsExp;
	private static List<String> specificationsUpd;
	private static List<String> specificationsDel;
	
	private static Product productExp;
	private static Product productUpd;
	private static Product productDel;
	
	private static Long idProductExp;
	private static Long idProductUpd;
	private static Long idProductDel;
			
	@BeforeClass
	public static void setUpBeforeClass(){		
		specificationsExp = new LinkedList<>();
		specificationsExp.add("specificationsExp");
		specificationsUpd = new LinkedList<>();
		specificationsUpd.add("specificationsUpd");
		specificationsDel = new LinkedList<>();
		specificationsDel.add("specificationsDel");
		
		productExp = new Product("departmentExp", "nameExp", 0.99, "currencyExp", "availabilityExp", "descriptionExp", specificationsExp);
		productUpd = new Product("departmentUpd", "nameUpd", 0.99, "currencyUpd", "availabilityUpd", "descriptionUpd", specificationsUpd);
		productDel = new Product("departmentDel", "nameDel", 0.99, "currencyDel", "availabilityDel", "descriptionDel", specificationsExp);
		
		ProductDAO dao = new ProductDAO();
		dao.insert(productExp);
		dao.insert(productUpd);
		dao.insert(productDel);		
		idProductExp = dao.findAllByDepartment("departmentExp").get(0).getIdProduct();
		idProductUpd = dao.findAllByDepartment("departmentUpd").get(0).getIdProduct();
		idProductDel = dao.findAllByDepartment("departmentDel").get(0).getIdProduct();
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		ProductDAO dao = new ProductDAO();
		dao.delete(idProductExp);
		dao.delete(idProductUpd);
	}
	
	@Test
	public void testDelete() {
		ProductDAO dao = new ProductDAO();
		dao.delete(idProductDel);
		Product product = dao.find(idProductDel);
		assertTrue(product == null);
	}

	@Test
	public void testUpdate() {
		List<String> specificationsUpdate = new LinkedList<>();
		specificationsUpdate.add("specificationsUpdate");
		Product productUpdate = new Product(idProductUpd, "departmentUpdate", "nameUpdate", 1.99, "currencyUpdate", "availabilityUpdate", "descriptionUpdate", specificationsUpdate);
		ProductDAO dao = new ProductDAO();
		dao.update(productUpdate);
		Product productResult = dao.find(idProductUpd);
		assertEquals(productResult.getIdProduct(), idProductUpd);
		assertEquals(productResult.getDepartment(), productUpdate.getDepartment());
		assertEquals(productResult.getName(), productUpdate.getName());
		assertTrue(productResult.getPrice() == productUpdate.getPrice());
		assertEquals(productResult.getCurrency(), productUpdate.getCurrency());
		assertEquals(productResult.getAvailability(), productUpdate.getAvailability());
		assertEquals(productResult.getDescription(), productUpdate.getDescription());
		assertTrue(productResult.getSpecifications().containsAll(productUpdate.getSpecifications()));
	}

	@Test
	public void testFind() {
		ProductDAO dao = new ProductDAO();
		Product productResult = dao.find(idProductExp);
		assertEquals(productResult.getIdProduct(), idProductExp);
		assertEquals(productResult.getDepartment(), productExp.getDepartment());
		assertEquals(productResult.getName(), productExp.getName());
		assertTrue(productResult.getPrice() == productExp.getPrice());
		assertEquals(productResult.getCurrency(), productExp.getCurrency());
		assertEquals(productResult.getAvailability(), productExp.getAvailability());
		assertEquals(productResult.getDescription(), productExp.getDescription());
		assertTrue(productResult.getSpecifications().containsAll(productExp.getSpecifications()));
	}

	@Test
	public void testFindAll() {
		ProductDAO dao = new ProductDAO();
		List<Product> products = dao.findAll();
		assertTrue(!products.isEmpty());
		for(Product productResult: products) {
			if(productResult.getIdProduct() == idProductExp) {
				assertEquals(productResult.getIdProduct(), idProductExp);
				assertEquals(productResult.getDepartment(), productExp.getDepartment());
				assertEquals(productResult.getName(), productExp.getName());
				assertTrue(productResult.getPrice() == productExp.getPrice());
				assertEquals(productResult.getCurrency(), productExp.getCurrency());
				assertEquals(productResult.getAvailability(), productExp.getAvailability());
				assertEquals(productResult.getDescription(), productExp.getDescription());
				assertTrue(productResult.getSpecifications().containsAll(productExp.getSpecifications()));
			}
		}
	}

	@Test
	public void testFindAllByDepartment() {
		ProductDAO dao = new ProductDAO();
		List<Product> products = dao.findAllByDepartment(productExp.getDepartment());
		assertTrue(!products.isEmpty());
		for(Product productResult: products) {
			if(productResult.getIdProduct() == idProductExp) {
				assertEquals(productResult.getIdProduct(), idProductExp);
				assertEquals(productResult.getDepartment(), productExp.getDepartment());
				assertEquals(productResult.getName(), productExp.getName());
				assertTrue(productResult.getPrice() == productExp.getPrice());
				assertEquals(productResult.getCurrency(), productExp.getCurrency());
				assertEquals(productResult.getAvailability(), productExp.getAvailability());
				assertEquals(productResult.getDescription(), productExp.getDescription());
				assertTrue(productResult.getSpecifications().containsAll(productExp.getSpecifications()));
			}
		}
	}
}
