package tests.restassured;

import api.ContactsService;
import api.UserApi;
import dto.UserDTOLombok;
import org.testng.annotations.BeforeSuite;

public class BaseRA {
    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
    static String token = "";

    UserDTOLombok user = UserDTOLombok.builder()
            .username("krasleo@gmail.com")
            .password("Cristiano7777$!")
            .build();

    @BeforeSuite
    public void preCondApiRA(){
       token = userApi.getTokenFromLoginResponse(user);
    }
}
