package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest{


    @BeforeClass(alwaysRun = true)
    public void preconditionsBeforeClass(){
        //refresh//go to main page//click btn login
        app.navigateToMainPage();
        app.getUserHelper().refresh();
        app.getUserHelper().openLoginPage();
    }

    @AfterMethod(alwaysRun = true)
    public void preconditionsBeforeMethod(){
        preconditionsForLoginAndRegTests();
    }


    //create user for test
    UserDTO userDTO = new UserDTO("krasleo@gmail.com", "Cristiano7777$!");

    UserDTOWith userDTOWith = new UserDTOWith().withEmail("krasleo@gmail.com")
            .withPassword("Cristiano7777$!");


    @Test(groups={"smoke"})
    public void positiveLoginUserDTO(){
        logger.info("Test date --> " + userDTO.toString());
        logger.info(String
                .format("in the next function: fill email input with email: %s and with the password: %s and click on button login",
                        userDTO.getEmail(), userDTO.getPassword()));

        app.getUserHelper().login(userDTO);
        logger.info("validation by assertTrue, that contacts link which displays on the menu");
        flagIsUserLogin = true;
        //to make validation ( returns true or false)
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }

    @Test
    public void positiveLoginUserDTOWith(){
        logger.info("Test date --> " + userDTOWith.toString());
        app.getUserHelper().login(userDTOWith);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }

    @Test(groups={"regression"})
    public void positiveLoginUserDTOLombok(Method method){
      long timeStart, timeStop;

        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("Cristiano7777$!")
                .build();

        timeStart = System.currentTimeMillis();
        logger.info("Start test -->  " + method.getName());
        logger.info("Test date --> " + user.toString());

        app.getUserHelper().loginUserDTOLombok(user);
        flagIsUserLogin = true;
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
        timeStop = System.currentTimeMillis();
        logger.info("Method stop --> " + method.getName() + "method duration = " + (timeStop - timeStart));

    }

    @Test(groups={"smoke"})
    public void negativeLoginTest_WrongEmail_WO_dot(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmailcom")
                .password("Cristiano7777$!")
                .build();

        logger.info("Test date --> " + user.toString());
        app.getUserHelper().loginUserDTOLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password

    }

    @Test
    public void negativeLoginTest_wrongPassword() {
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("Cristian7777$!")
                .build();

        logger.info("Test date --> " + user.toString());
        app.getUserHelper().loginUserDTOLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password

    }

    @Test
    public void negativeLoginTest_WrongEmail_WO_Letters(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("123456@7890")
                .password("Cristiano7777$!")
                .build();

        logger.info("Test date --> " + user.toString());
        app.getUserHelper().loginUserDTOLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password

    }

    @Test
    public void negativeLoginTest_WrongEmail_WO_Digits(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("werfdth@acbf")
                .password("Cristiano7777$!")
                .build();
        app.getUserHelper().loginUserDTOLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password
    }

    @Test
    public void negativeLoginTest_WrongPassword_WO_Digits(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("Cristianoreal$!")
                .build();
        app.getUserHelper().loginUserDTOLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password
    }

    @Test
    public void negativeLoginTest_WrongPassword_WO_Letters(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("12345678$!")
                .build();
        app.getUserHelper().loginUserDTOLombok(user);
        flagIsAlertPresent = true;
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password
    }


}
