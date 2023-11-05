package tests;


import dto.UserDTOLombok;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RandomUtils;

public class RegistrationTests extends BaseTest{
    @Test
    public void positiveRegistration(){
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("Cristiano7777$!")
                .build();
        app.getUserHelper().registrationUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }

    @Test
    public void negativeRegistration_WrongPassword_WO_Symbol(){
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);

        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("Cristiano77777")
                .build();
        app.getUserHelper().registrationUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertCorrectEmailWrongPasswordReg());


    }

    @Test
    public void negativeRegistration_WrongPassword_WO_Letters(){
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("112233477#")
                .build();
        app.getUserHelper().registrationUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertCorrectEmailWrongPasswordReg());

    }

    @Test
    public void negativeRegistration_WrongPassword_WO_Digits(){
        RandomUtils randomUtils = new RandomUtils();
        String email = randomUtils.generateEmail(7);
        UserDTOLombok user = UserDTOLombok.builder()
                .email(email)
                .password("abcdefghijk#")
                .build();
        app.getUserHelper().registrationUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertCorrectEmailWrongPasswordReg());

    }

}
