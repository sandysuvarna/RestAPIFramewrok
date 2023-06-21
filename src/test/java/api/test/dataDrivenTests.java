package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.dataProvider;
import io.restassured.response.Response;

public class dataDrivenTests {

  @Test(priority=1,dataProvider="Data",dataProviderClass=dataProvider.class)
  public void testPostUserByExcel(String userID,String userName,String fName,String lname,String Password,String email,String phone)
  {
	  User userPayload = new User();
	  userPayload.setId(Integer.parseInt(userID));
	  userPayload.setUsername(userName);
	  userPayload.setFirstname(fName);
	  userPayload.setLastname(lname);
	  userPayload.setEmail(email);
	  userPayload.setPhone(phone);
	  userPayload.setPassword(Password);
	  Response response = userEndPoints.createUser(userPayload);
	   response.then().log().all();
	   
	   Assert.assertEquals(response.getStatusCode(),200);
  }

  @Test(priority=2,dataProvider="Usernames",dataProviderClass=dataProvider.class)
  public void testDeleteUserByName(String userName)
  {
	  Response response = userEndPoints.deleteUser(userName);
	  Assert.assertEquals(response.getStatusCode(), 200);
  }






}
