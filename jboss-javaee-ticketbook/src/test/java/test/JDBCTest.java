package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import code.JDBC;

public class JDBCTest {
	
	static JDBC c;
	static JSONObject TestJSon;
	static JSONObject TestUpdateJSon;
	
	@Before
	public static void PriorRequirements() {
		c = new JDBC("jdbc:mysql://localhost/db_ticketBook", "root", "password");
		TestJSon = new JSONObject();
		TestJSon.put("bandName", "A good band name");
		TestJSon.put("bandDescription", "A good band description");
		TestUpdateJSon = new JSONObject();
		TestUpdateJSon.put("bandName", "A exceptional band name");
		TestUpdateJSon.put("bandDescription", "A good band description");
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
	@Test
	public void InsertTest() {
		c.Connect();
		c.Create("tbl_band", "bandName, bandDescription", "'A good band name','A good band description'");
		JSONObject js = new JSONObject(c.Read("tbl_band", "bandName, bandDescription"));
		assertEquals("Reading/returning data from the database",TestJSon.get("bandName"),js.get("bandName"));
		c.Disconnect();
	}
	@Test
	public void UpdateTest() {
		c.Connect();
		c.Update("tbl_band", "bandName", "A exceptional band name", "bandIDPK", "2");
		JSONObject js = new JSONObject(c.Read("tbl_band", "bandName, bandDescription"));
		assertEquals("Update data in the database",TestUpdateJSon.get("bandName"),js.get("bandName"));
		c.Disconnect();
	}
	@Test
	public void DeleteTest() {
		c.Connect();
		c.Delete("tbl_band", "bandIDPK", "1");
		c.Disconnect();
	}

}
