package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class userTest {

  Faker fake;
  User userPayload;
  public Logger logger;
	@BeforeClass
	public void setupData()
  {
	  fake = new Faker();
	  userPayload = new User();
	  
	  userPayload.setId(fake.idNumber().hashCode());
	  userPayload.setUsername(fake.name().username());
	  userPayload.setFirstname(fake.name().firstName());
	  userPayload.setLastname(fake.name().lastName());
	  userPayload.setEmail(fake.internet().safeEmailAddress());
	  userPayload.setPassword(fake.internet().password(5, 10));
	  userPayload.setPhone(fake.phoneNumber().cellPhone());
	  
	  //logs
	  logger = LogManager.getLogger(this.getClass());
	  
  }

   @Test(priority=1)
	public void testPostUser()
   {
	   logger.info("*********Creating User***********");
	   Response response = userEndPoints.createUser(userPayload);
	   response.then().log().all();
	   
	   Assert.assertEquals(response.getStatusCode(),200);
	   
   }
   
   @Test(priority=2)
	public void testGetUserByName()
  {
	   logger.info("*******Getting User info*************");
	   Response response = userEndPoints.getUser(this.userPayload.getUsername());
	   response.then().log().all();
	   //response.statusCode(200);
	   Assert.assertEquals(response.getStatusCode(),200);
	   
  }
   @Test(priority=3)
	public void testUpdateUserByName()
  {
	   //Update the details
	   userPayload.setFirstname(fake.name().firstName());
	   userPayload.setLastname(fake.name().lastName());
	   userPayload.setEmail(fake.internet().safeEmailAddress());
	   
	   Response response = userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
	   response.then().log().body().statusCode(200);//Chai Assertion
	   
	   //Assert.assertEquals(response.getStatusCode(),200);
	   //Checking data after update
	   Response responseAfterUpdate = userEndPoints.getUser(this.userPayload.getUsername());
	   
	   //response.statusCode(200);
	   Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	   
  }
   @Test(priority=4)
	public void testDeleteUserByName()
 {
	   Response response = userEndPoints.deleteUser(this.userPayload.getUsername());
	   
	   //response.statusCode(200);
	   Assert.assertEquals(response.getStatusCode(),200);
	   
 }

}
