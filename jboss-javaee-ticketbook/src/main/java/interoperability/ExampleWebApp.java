package interoperability;
import java.sql.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/web")
public class ExampleWebApp {
	
	
	@GET
	@Produces("Application/json")
	@Path("/find/{id}")
	public String findPerson(@PathParam("id") int id) {
		
		return "{\"result\":\""+id +"\"}";
	}
	
	
	/*
	
	@POST
	@Path("/add")
	public void addNewEntry() {
		String DB_URL = "jdbc:mysql://localhost/mydb?useSSL=false";

		String USER = "root";
		String PASS = "root";
		String finalValue = "";
		String sql = "INSERT INTO person(pk_person_id,first_name,last_name) VALUES(?,?,?)";
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1,1);
				stmt.setString(2, "Dale");
				stmt.setString(3, "Carr");
				stmt.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	@POST
	@Path("/addFromInput")
	public void addNewCreatedEntry(Person p) {
		String DB_URL = "jdbc:mysql://localhost/mydb?useSSL=false";

		String USER = "root";
		String PASS = "root";
		String finalValue = "";
		String sql = "INSERT INTO person(pk_person_id,first_name,last_name) VALUES(?,?,?)";
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1,p.getPk_person_id());
				stmt.setString(2, p.getFirst_name());
				stmt.setString(3, p.getLast_name());
				stmt.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
	}
	
	
	@POST
	@Path("/cleanup")
	public void deleteTestData() {
		String DB_URL = "jdbc:mysql://localhost/mydb?useSSL=false";

		String USER = "root";
		String PASS = "root";
		String finalValue = "";
		String sql = "DELETE FROM Person WHERE pk_person_id = ?";
			try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1,1);
				stmt.executeUpdate();
			} catch (SQLException se) {
				se.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
	} */
}
