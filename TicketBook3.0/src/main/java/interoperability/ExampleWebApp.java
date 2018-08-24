package interoperability;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import code.JDBC;
import code.User;


@Path("/web")
public class ExampleWebApp {
	
	
	
	@GET
	@Produces("Application/json")
	@Path("/find/{search}/{searchType}")
	public String findThing(@PathParam("search") String search,@PathParam("searchType") String searchType) {
		
		JSONArray tempJson = new JSONArray();
		JSONArray tempBandJson = new JSONArray();
		JSONArray tempVenueJson = new JSONArray();
		JSONArray tempEventJson = new JSONArray();
		String tempString;
		
		JDBC JavaDataBaseConnection = new JDBC("jdbc:mysql://localhost/db_ticketbook", "root", "password");
		JavaDataBaseConnection.Connect();
		
		if(searchType.equals("Bands")) {
			tempJson = JavaDataBaseConnection.Read("tbl_band", "bandName");
			JavaDataBaseConnection.Disconnect();
			return tempJson.toString();
		}
		else if(searchType.equals("Venues")) {
			tempJson = JavaDataBaseConnection.Read("tbl_venue", "venueName");		
			JavaDataBaseConnection.Disconnect();
			return tempJson.toString();
		}
		else if(searchType.equals("Events")) {
			tempJson = JavaDataBaseConnection.Read("tbl_event", "eventName");
			JavaDataBaseConnection.Disconnect();
			return tempJson.toString();
		}
		else{
			tempBandJson = JavaDataBaseConnection.Read("tbl_band", "bandName");
			tempVenueJson = JavaDataBaseConnection.Read("tbl_venue", "venueName");
			tempEventJson = JavaDataBaseConnection.Read("tbl_event", "eventName");
			
			tempString = "["+tempBandJson.toString()+","+tempVenueJson.toString()+","+tempEventJson.toString()+"]";
			JavaDataBaseConnection.Disconnect();
			return tempString;
		}
		


}
	
	@POST
	@Produces("Application/text")
	@Path("/login")
	public String login(User u) {
				
		JSONArray tempJson;
		
		JDBC JavaDataBaseConnection = new JDBC("jdbc:mysql://localhost/db_ticketbook", "root", "password");
		JavaDataBaseConnection.Connect();
		
		tempJson = JavaDataBaseConnection.Read("tbl_person", "personUserName, personPassword");
		
		JavaDataBaseConnection.Disconnect();
		System.out.println(tempJson);
		for(Object user : tempJson) {
			System.out.println(user.toString());
			JSONObject userJson = new JSONObject();
			userJson = (JSONObject) user;
			System.out.println(userJson.toString());
			if(u.getUsername().equals(userJson.getString("personUsername"))) {
				System.out.println("True");
				if(u.getPassword().equals(userJson.get("personPassword"))) {
					System.out.println("True");
					return "True";
					
				}
			}
			
		}
		return "False";
		

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