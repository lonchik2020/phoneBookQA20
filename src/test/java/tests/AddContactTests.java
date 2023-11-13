package tests;

import dto.NewContactDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddContactTests extends BaseTest{

    @BeforeClass(alwaysRun = true)//to make login for user ( before the class)
    public void preconditionsBeforeClass(){
        if(app.isPageUrHome()){
            app.getUserHelper().openLoginPage();
            app.getUserHelper().loginUserDTOLombok(user);
        }
    }

    @AfterClass(alwaysRun = true)
    public  void postConditions(){
        app.getUserHelper().logout();
    }

    @Test
    public void addContactPositive(){
        String phone = randomUtils.generateStringDigits(12);
        NewContactDTO newContactDTO = NewContactDTO.builder()
                .address("tukoty")
                .description("regege")
                .email("wefre@mail.ru")
                .lastName("serfe")
                .name("yuki")
                .phone(phone)
                .build();

        app.getContactHelper().addNewContact(NewContactDTO);
        Assert.assertTrue(app.getContactHelper().validateContactCreated(phone));//comparing by phone!!
    }
}
