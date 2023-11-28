package tests.restassured;

import api.ContactsService;
import api.UserApi;
import dto.NewContactDTO;
import dto.UserDTOLombok;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;
import utils.RandomUtils;

public class BaseRA {
    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
     String token = "";
    RandomUtils randomUtils = new RandomUtils();
    SoftAssert softAssert = new SoftAssert();

    UserDTOLombok user = UserDTOLombok.builder()
            .username("krasleo@gmail.com")
            .password("Cristiano7777$!")
            .build();

    @BeforeSuite
    public void preCondApiRA(){
       token = userApi.getTokenFromLoginResponse(user);
    }

    public NewContactDTO createNewContact() {
        String phoneNumber = randomUtils.generateStringDigits(13);
        NewContactDTO contact2 = NewContactDTO.builder()
                .address("Haifa")
                .description("worker")
                .email("sshdtj@mail.com")
                .id("0")
                .lastName("rsfhj")
                .name("fdfhascgj")
                .phone(phoneNumber)
                .build();
        return contact2;
    }
}
