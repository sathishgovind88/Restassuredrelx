package stepDefinitions;

import java.io.File;
import java.util.HashMap;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest{
	
	private static final String baseurl = "https://api.url.org/";

	private Response response;
	private String jsonString;
	private File jsondatafile;
	private int userid;
	
	RequestSpecification request;
	
	@Given("List of all users")
	public void getalltheusers() {
		 
		RestAssured.baseURI = baseurl;
		request = RestAssured.given();
		
		response = request.get("/user");
		 
		jsonString = response.asString();
		
	}
	
	@When("Add the users")
	public void addtheusersdetails() {
		
		jsondatafile = new File("src/test/resources/JSONData/users.json");
		
		RestAssured.baseURI = baseurl; 
		request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.body(jsondatafile).post("/user");				
		
	}
	
	@Then("Verify the user is added")
	public void verifyUser() {
		String[] arr;
		String[] idarr;
		
		 Assert.assertEquals(201, response.statusCode());
		 
		 jsonString = response.asString();
		 arr = jsonString.split(",");
		 
		 for (int i = 0;i< arr.length;i++) {
			 if (arr[i].contains("id")) {				 
				 idarr = arr[i].split(":");
				 				 
				 userid = Integer.parseInt(idarr[1].replace("}", "").trim());
				 break;				
			 }
		 }		 
			 
		 Assert.assertEquals(jsonString.contains("id"),true);
		 Assert.assertEquals(jsonString.contains("Sathish Sampath"),true);	
		 
		 System.out.println(jsonString);		 		 
	}
	
	@When("Update the user details")
	public void updateUser() {	
				
		jsondatafile = new File("src/test/resources/JSONData/update.json");
		
		RestAssured.baseURI = baseurl; 
		request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		request.body(jsondatafile).put("/user/update/" + userid);
				
	}
			
	@When("Get specific user details")
	public void getUser() {	
				
		RestAssured.baseURI = baseurl; 
		request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.get("/user/" + userid);
				
		jsonString = response.asString();
		
		System.out.println(jsonString);
		
	}
}


