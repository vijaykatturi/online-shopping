package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

/**
 * @author vijay
 *
 */
/**
 * @author vijay
 *
 */
public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private static Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		
		categoryDAO = (CategoryDAO)context.getBean("categoryDAO");
	}
	
	
	/*
	 * @Test public void testAddCategory() {
	 *  category = new Category();
	 * category.setName("Laptop");
	 * category.setDescription("this is some description for laptop");
	 * category.setImageURL("CAT_145.png");
	 * 
	 * assertEquals("successfully added a category inside the table!!",true,
	 * categoryDAO.add(category));
	 * 
	 * 
	 * }
	 * 
	 */
	/*
	 * @Test public void testGetCategory() {
	 *  category = categoryDAO.get(3);
	 * assertEquals("successfully fetched  a single category from  the table!!"
	 * ,"Television",category.getName()); }
	 */
	/*
	 * @Test public void testUpdateCategory() { 
	 * category = categoryDAO.get(3);
	 * category.setName("TViii");
	 * assertEquals("successfully updated  a single category in the table!!",true,
	 * categoryDAO.update(category));
	 * 
	 * }
	 */
	 
	/*
	 * @Test public void testDeleteCategory() {
	  
	 * category = categoryDAO.get(65); // category.setName("TVi");
	 * assertEquals("successfully deleted a single category in the table!!",true,
	 * categoryDAO.delete(category));
	 * 
	 * }
	 */
	  
	/*
	 * @Test public void testListCategory() {
	 * 
	 * category = categoryDAO.get(65); // category.setName("TVi");
	 * assertEquals("successfully fetched the list categories from the table!!",3,
	 * categoryDAO.list().size());
	 * 
	 * }
	 */
	
	@Test
	public void testCRUDCategory() {
		
		/*
		 * //add operration category = new Category(); category.setName("Laptop");
		 * category.setDescription("this is some description for laptop");
		 * category.setImageURL("CAT_1.png");
		 * 
		 * assertEquals("successfully added a category inside the table!!",true,
		 * categoryDAO.add(category));
		 * 
		 * category = new Category(); category.setName("Television");
		 * category.setDescription("this is some description for Television");
		 * category.setImageURL("CAT_2.png");
		 * 
		 * assertEquals("successfully added a category inside the table!!",true,
		 * categoryDAO.add(category));
		 */
		  // fetch and updating the category
		     category = categoryDAO.get(2);
			 category.setName("TV");
			 assertEquals("successfully updated  a single category in the table!!",true,categoryDAO.update(category));
			 
			 //delete the category
		    assertEquals("successfully deleted a single category in the table!!",true,categoryDAO.delete(category));
			 
			 //fetching the list
			 assertEquals("successfully fetched the list categories from the table!!",1,categoryDAO.list().size());		 
			 
			 
		
	}
	
	 
	 
}
