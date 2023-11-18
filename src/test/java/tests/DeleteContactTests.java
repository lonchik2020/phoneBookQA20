package tests;

import dto.NewContactDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteContactTests extends  BaseTest{

    @BeforeClass(alwaysRun = true)//to make login for user ( before the class)
    public void preconditionsBeforeClass(){
        if(app.isPageUrHome()){
            app.getUserHelper().openLoginPage();
        }
        app.getUserHelper().loginUserDTOLombok(user);
    }

    @AfterClass(alwaysRun = true)
    public  void postConditions()throws InterruptedException{
        app.getUserHelper().logout();
        Thread.sleep(500);
    }

    @Test
    public void deleteCreatedContactPositive(){
        String phone = randomUtils.generateStringDigits(12);
        System.out.println("phone for the new contact " + phone);
        logger.info("phone for the new contact" + phone);
        NewContactDTO newContactDTO = NewContactDTO.builder()
                .address("tukoty")
                .description("regege")
                .email("wefre@mail.ru")
                .lastName("serfe")
                .name("yuki")
                .phone(phone)
                .build();
        app.getContactHelper().addNewContact(newContactDTO);
        app.getContactHelper().openContactInfoByPhone(phone);
        app.getContactHelper().removeActiveContact();
        app.getContactHelper().navigateToContactPage();

        Assert.assertFalse(app.getContactHelper().validateContactCreated(phone));//comparing by phone!!
    }
}
