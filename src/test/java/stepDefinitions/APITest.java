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
	
	private static String baseurl = "https://jsonplaceholder.typicode.com/";

	private static Response response;
	private static String jsonString;
		
		 		 
	@Given("A list of users making posts and comments")
	public void showalltheusers() {
		 
		 RestAssured.baseURI = baseurl;				 
		 RequestSpecification request = RestAssured.given();
		 
		 response = request.get("/users");
		 
		 String jsonString = response.asString();
		// System.out.println(jsonString);

	}
	
	@When("I Add the users")
	public void addtheusersdetails() {
		
		File jsondatafile = new File("src/test/resources/JSONData/createusers.json");
		RestAssured.baseURI = baseurl; 
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.body(jsondatafile).post("/users");		 	
		
	}
	
	@Then("Verify the user is added")
	public void verifytheuser() {	
		
		 Assert.assertEquals(201, response.statusCode());
		 
		 String jsonString = response.asString();
		 Assert.assertEquals(jsonString.contains("11"),true);
		 Assert.assertEquals(jsonString.contains("Sathish Sampath"),true);
		 Assert.assertEquals(jsonString.contains("sathish@gmail.com"),true);
		 System.out.println(jsonString);
	}
	
	@When("Post to the users")
	public void i_post_the_comments_to_users() {
		File jsondatafile = new File("src/test/resources/JSONData/posts.json");
		RestAssured.baseURI = baseurl; 
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.body(jsondatafile).post("/posts");		
	}
	
	@Then("Verify the post is added")
	public void verifytheusers() {
		Assert.assertEquals(201, response.statusCode());
		 
		 String jsonString = response.asString();
		 Assert.assertEquals(jsonString.contains("11"),true);
		 Assert.assertEquals(jsonString.contains("101"),true);
		 Assert.assertEquals(jsonString.contains("sucessfully posted"),true);		 
		 System.out.println(jsonString);
	}
		 
	@When("Comment to the posts")
	public void addthecomments(){
		File jsondatafile = new File("src/test/resources/JSONData/comments.json");
		RestAssured.baseURI = baseurl; 
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.body(jsondatafile).post("/comments");	
	}
	
	@Then("Verify the comment is added")
	public void verifythecomments(){
		Assert.assertEquals(201, response.statusCode());
		 
		 String jsonString = response.asString();
		 Assert.assertEquals(jsonString.contains("101"),true);
		 Assert.assertEquals(jsonString.contains("501"),true);
		 Assert.assertEquals(jsonString.contains("verify the comment is added successfuly"),true);
		 Assert.assertEquals(jsonString.contains("sathish@gmail.com"),true);
		 System.out.println(jsonString);
	}
	
	@When("Remove the posts")
	public void removetheposts(){
		
		RestAssured.baseURI = baseurl; 
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.delete("/posts/101");	
	}
	
	@Then("Verify the post is removed")
	public void verifytheremovepost(){
		Assert.assertEquals(200, response.statusCode());		 
		
	}
	
	@When("Remove the comments")
	public void removethecomments(){
		
		RestAssured.baseURI = baseurl; 
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");		 		 	 
				
		response = request.delete("/comments/501");	
	}
	
	@Then("Verify the comment is removed")
	public void verifytheremovecomment(){
		Assert.assertEquals(200, response.statusCode());		 
		
	}
}


