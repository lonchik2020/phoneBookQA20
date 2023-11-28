package tests.restassured;

import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRA extends BaseRA {
    @Test
    public void LoginStatusCodeTest() {
        //System.out.println("token from base" + token);
        Assert.assertEquals(userApi.getStatusCodeResponseLogin(user), 200,
                "status code not 200 for login test with correct data");
    }

    @Test
    public void LoginStatusCodeTest2() {
        Assert.assertEquals(userApi.getStatusCodeResponseLogin(user), 200,
                "status code not 200 for login test with correct data");
    }


    @Test
    public void testToken(){
        System.out.println("token" + userApi.getTokenFromLoginResponse(user));
    }

    @Test
    public void negativeLogin(){
        userApi.setResponseLoginNull();
        UserDTOLombok user1 = UserDTOLombok.builder()//user for local test
                .username("krasleo@gmail.com")
                .password("Cristiano777777")
                .build();
        int statusCode = userApi.getStatusCodeResponseLogin(user1);
        userApi.setResponseLoginNull();
        Assert.assertEquals(statusCode, 401);
    }

}
