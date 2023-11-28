package tests.restassured;

import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseRA{
    String emailR = randomUtils.generateEmail(7);

    UserDTOLombok userR = UserDTOLombok.builder()
            .username(emailR)
            .password("Cristiano7777$!")
            .build();
    @Test
    public void testStatusCodePositiveRegistration() {
        System.out.println(emailR);
        System.out.println(userApi.getStatusCodeResponseRegistration(userR));
        Assert.assertTrue(userApi.getStatusCodeResponseRegistration(userR) == 200);
    }

    @Test
    public void testGetTokenPositiveRegistration(){
        System.out.println(userApi.getTokenRegistrationResponse(userR));
    }

    @Test
    public void NegativeRegistrationEmailExist() { // start this test only separate from others
        UserDTOLombok userN = UserDTOLombok.builder()
                .username("zn8uybj@domain.com")
                .password("Cristiano7777$!")
                .build();
        userApi.responseRegistrationSetNull();
        int statusCode = userApi.getStatusCodeResponseRegistration(userN);
        userApi.responseRegistrationSetNull();
        Assert.assertEquals(statusCode, 409);
    }

}
