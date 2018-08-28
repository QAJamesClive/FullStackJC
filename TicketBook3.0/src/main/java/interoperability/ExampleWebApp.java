package interoperability;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;

import code.Event;
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
		JSONArray returnBandJson = new JSONArray();
				
		JSONArray tempVenueJson = new JSONArray();
		JSONArray returnVenueJson = new JSONArray();
		
		JSONArray tempEventJson = new JSONArray();
		JSONArray returnEventJson = new JSONArray();
				
		String tempString;
		
		JDBC JavaDataBaseConnection = new JDBC("jdbc:mysql://localhost/db_ticketbook", "root", "password");
		JavaDataBaseConnection.Connect();
		
		if(searchType.equals("Bands")) {
			tempJson = JavaDataBaseConnection.Read("tbl_band", "bandName, bandDescription,bandIDPK");
			JavaDataBaseConnection.Disconnect();
			for(Object band :tempJson) {
				JSONObject bandJson = new JSONObject();
				bandJson = (JSONObject) band;
				
				if(bandJson.get("bandName").toString().toUpperCase().contains(search.toUpperCase())) {
					JSONArray returnedEventJSonArray = new JSONArray();
					returnBandJson.put(bandJson);
					
					
					JSONArray tempEventList = new JSONArray();
					JavaDataBaseConnection.Connect();
					tempEventList = JavaDataBaseConnection.Read("tbl_eventlist", "bandIDFK, eventIDFK");
					JavaDataBaseConnection.Disconnect();
					for(Object eventList : tempEventList) {
						JSONObject eventListJson = new JSONObject();
						eventListJson = (JSONObject) eventList;
						
						
						JSONArray tempEvent = new JSONArray();
						if(bandJson.get("bandIDPK").equals(eventListJson.get("bandIDFK"))) {
							JavaDataBaseConnection.Connect();
							tempEvent = JavaDataBaseConnection.Read("tbl_event", "eventName, eventIDPK");
							JavaDataBaseConnection.Disconnect();
							for(Object event : tempEvent) {
								JSONObject eventJson = new JSONObject();
								eventJson = (JSONObject) event;						
								
								if(eventJson.get("eventIDPK").equals(eventListJson.get("eventIDFK"))) {
									System.out.println(eventJson);
									returnedEventJSonArray.put(eventJson);
									
								}
								
							}
						}
						
						
						
					}
					bandJson.put("Event", returnedEventJSonArray);
				}
				
			}
			
			return returnBandJson.toString();
		}
		else if(searchType.equals("Venues")) {
			JavaDataBaseConnection.Connect();
			tempJson = JavaDataBaseConnection.Read("tbl_venue", "venueName, venueIDPK");
			JavaDataBaseConnection.Disconnect();
			for(Object venue : tempJson) {
				JSONObject venueJson = new JSONObject();
				venueJson = (JSONObject) venue;
				if(venueJson.get("venueName").toString().toUpperCase().contains(search.toUpperCase())) {
					returnVenueJson.put(venue);
					
					JSONArray returnedEventJSonArray = new JSONArray();
					JavaDataBaseConnection.Connect();
					tempJson = JavaDataBaseConnection.Read("tbl_event", "eventName, eventDescription, eventIDPK, venueIDFK");
					JavaDataBaseConnection.Disconnect();
					for(Object event : tempJson) {
						JSONObject eventJson = new JSONObject();
						eventJson = (JSONObject) event;
						if(eventJson.get("venueIDFK").equals(venueJson.get("venueIDPK"))) {
							returnedEventJSonArray.put(eventJson);
						}
					}
					venueJson.put("Event", returnedEventJSonArray);
				}
			}
			JavaDataBaseConnection.Disconnect();
			return returnVenueJson.toString();
		}
		else if(searchType.equals("Events")) {
			JavaDataBaseConnection.Connect();
			tempJson = JavaDataBaseConnection.Read("tbl_event", "eventName, eventDescription");
			for(Object event : tempJson) {
				JSONObject eventJson = new JSONObject();
				eventJson = (JSONObject) event;
				if(eventJson.get("eventName").toString().toUpperCase().contains(search.toUpperCase())) {
					returnEventJson.put(event);
				}
			}
			JavaDataBaseConnection.Disconnect();
			return returnEventJson.toString();
		}
		else {
			JavaDataBaseConnection.Connect();
			tempBandJson = JavaDataBaseConnection.Read("tbl_band", "bandName, bandDescription, bandIDPK");
			for(Object band :tempBandJson) {
				JSONObject bandJson = new JSONObject();
				bandJson = (JSONObject) band;
				if(bandJson.get("bandName").toString().toUpperCase().contains(search.toUpperCase())) {
					returnBandJson.put(band);					
					
					JSONArray returnedEventJSonArray = new JSONArray();
					JSONArray tempEventList = new JSONArray();
					JavaDataBaseConnection.Connect();
					tempEventList = JavaDataBaseConnection.Read("tbl_eventlist", "bandIDFK, eventIDFK");
					JavaDataBaseConnection.Disconnect();
					for(Object eventList : tempEventList) {
						JSONObject eventListJson = new JSONObject();
						eventListJson = (JSONObject) eventList;
						
						
						JSONArray tempEvent = new JSONArray();
						if(bandJson.get("bandIDPK").equals(eventListJson.get("bandIDFK"))) {
							JavaDataBaseConnection.Connect();
							tempEvent = JavaDataBaseConnection.Read("tbl_event", "eventName, eventIDPK,eventDescription");
							JavaDataBaseConnection.Disconnect();
							for(Object event : tempEvent) {
								JSONObject eventJson = new JSONObject();
								eventJson = (JSONObject) event;						
								
								if(eventJson.get("eventIDPK").equals(eventListJson.get("eventIDFK"))) {
									System.out.println(eventJson);
									returnedEventJSonArray.put(eventJson);
									
								}
								
							}
						}
						
						
						
					}
					bandJson.put("Event", returnedEventJSonArray);
					
					
				}
				
			}
			JavaDataBaseConnection.Connect();
			tempVenueJson = JavaDataBaseConnection.Read("tbl_venue", "venueName,venueIDPK");
			JavaDataBaseConnection.Disconnect();
			for(Object venue : tempVenueJson) {
				JSONObject venueJson = new JSONObject();
				venueJson = (JSONObject) venue;
				if(venueJson.get("venueName").toString().toUpperCase().contains(search.toUpperCase())) {
					returnVenueJson.put(venue);
					
					JSONArray returnedEventJSonArray = new JSONArray();
					JavaDataBaseConnection.Connect();
					tempJson = JavaDataBaseConnection.Read("tbl_event", "eventName, eventDescription, eventIDPK, venueIDFK");
					JavaDataBaseConnection.Disconnect();
					for(Object event : tempJson) {
						JSONObject eventJson = new JSONObject();
						eventJson = (JSONObject) event;
						if(eventJson.get("venueIDFK").equals(venueJson.get("venueIDPK"))) {
							returnedEventJSonArray.put(eventJson);
						}
					}
					venueJson.put("Event", returnedEventJSonArray);
				}
				
			}
			JavaDataBaseConnection.Connect();
			tempEventJson = JavaDataBaseConnection.Read("tbl_event", "eventName, eventDescription");
			for(Object event : tempEventJson) {
				JSONObject eventJson = new JSONObject();
				eventJson = (JSONObject) event;
				if(eventJson.get("eventName").toString().toUpperCase().contains(search.toUpperCase())) {
					returnEventJson.put(event);
				}
			}
			
			tempString = "["+returnBandJson.toString()+","+returnVenueJson.toString()+","+returnEventJson.toString()+"]";
			JavaDataBaseConnection.Disconnect();
			return tempString.toString();
		}


}
	
	@POST
	@Produces("Application/text")
	@Path("/login")
	public String login(User u) {
				
		JSONArray tempJson;
		
		JDBC JavaDataBaseConnection = new JDBC("jdbc:mysql://localhost/db_ticketbook", "root", "password");
		JavaDataBaseConnection.Connect();
		
		tempJson = JavaDataBaseConnection.Read("tbl_person", "personUsernamePK, personPassword");
		
		JavaDataBaseConnection.Disconnect();
		for(Object user : tempJson) {
			JSONObject userJson = new JSONObject();
			userJson = (JSONObject) user;
			if(u.getUsername().equals(userJson.getString("personUsername"))) {
				if(u.getPassword().equals(userJson.get("personPassword"))) {
					return "True";
					
				}
			}
			
		}
		return "False";
		

}
	
	@POST
	@Produces("Application/text")
	@Path("/usernameCheck")
	public String usernameCheck(User u) {
				
		JSONArray tempJson;
		
		JDBC JavaDataBaseConnection = new JDBC("jdbc:mysql://localhost/db_ticketbook", "root", "password");
		JavaDataBaseConnection.Connect();
		
		tempJson = JavaDataBaseConnection.Read("tbl_person", "personUsernamePK");
		
		JavaDataBaseConnection.Disconnect();
		System.out.println(tempJson);
		for(Object user : tempJson) {
			JSONObject userJson = new JSONObject();
			userJson = (JSONObject) user;
			if(u.getUsername().equals(userJson.getString("personUsernamePK"))) {
				return "True";
			}
			
		}
		return "False";

}
	
	@POST
	@Produces("Application/text")
	@Path("/addUser")
	public String addUser(User u) {
		JSONArray tempJson;
		
		JDBC JavaDataBaseConnection = new JDBC("jdbc:mysql://localhost/db_ticketbook", "root", "password");
		JavaDataBaseConnection.Connect();
		
		JavaDataBaseConnection.Create("tbl_person", "personFirstName,personLastName,personAdmin,personUsernamePK,personPassword", "'"+u.getFirstName()+"','"+u.getLastName()+"',false,'"+u.getUsername()+"','"+u.getPassword()+"'");
		
		tempJson = JavaDataBaseConnection.Read("tbl_person", "personUsernamePK");
		
		JavaDataBaseConnection.Disconnect();
		for(Object user : tempJson) {
			JSONObject userJson = new JSONObject();
			userJson = (JSONObject) user;
			if(u.getUsername().equals(userJson.getString("personUsernamePK"))) {
				return "True";
			}
			
		}
		return "False";

	}
		

}
