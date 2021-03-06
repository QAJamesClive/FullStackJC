package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import code.JDBC;

public class JDBCTest {
	
	static JDBC c;
	static JSONObject TestJSon;
	static JSONObject TestUpdateJSon;
	
	@BeforeClass
	public static void PriorRequirements() {
		c = new JDBC("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		TestJSon = new JSONObject();
		TestUpdateJSon = new JSONObject();
	}
	@Test
	public void constructorTest() {
		assertNotNull(c);		
	}
	@Test
	public void getdB_URLTest() {
		assertEquals("The database url get has failed",c.getdB_URL(),"jdbc:mysql://localhost/db_ticketBook");	
	}
	@Test
	public void setdB_URLTest() {
		c.setdB_URL("url");
		assertEquals("The database url set has failed",c.getdB_URL(),"url");	
	}
	@Test
	public void getUsernameTest() {
		assertEquals("The database url get has failed",c.getUsername(),"root");
	}
	@Test
	public void setusernameTest() {
		c.setUsername("public");
		assertEquals("The database url set has failed",c.getUsername(),"public");
	}
	@Test
	public void getPasswordTest() {
		assertEquals("The database url get has failed",c.getPassword(),"password");
	}
	@Test
	public void setPasswordTest() {
		c.setPassword("password1");
		assertEquals("The database url set has failed",c.getPassword(),"password1");
	}
//	@Test
//	public void InsertTest() {
//		c.Connect();
//		c.Create("tbl_band", "bandName, bandDescription", "'A good band name','A good band description'");
//		JSONObject js = (JSONObject) c.Read("tbl_band", "bandName, bandDescription").get("2");
//		assertEquals("Reading/returning data from the database",TestJSon.get("bandName"),js.get("bandName"));	
//	}
//	@Test
//	public void UpdateTest() {
//		c.Connect();
//		c.Update("tbl_band", "bandName", "A exceptional band name", "bandIDPK", "2");
//		JSONObject js = (JSONObject) (c.Read("tbl_band", "bandName, bandDescription").get("1"));
//		System.out.print(js);
//		assertEquals("Update data in the database",TestUpdateJSon.get("bandName"),js.get("bandName"));
//	}
//	@Test
//	public void DeleteTest() {
//		c.Connect();
//		c.Delete("tbl_band", "bandIDPK", "1");
//	}

}
