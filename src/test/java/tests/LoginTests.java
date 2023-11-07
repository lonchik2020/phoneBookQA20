package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest{

    private long timeStart, timeStop;

    boolean flagIsAlertPresent = false;
    boolean flagIsUserLogin = false;

    @BeforeClass
    public void preconditionsBeforeClass(){
        //refresh//go to main page//click btn login
        app.navigateToMainPage();
        app.getUserHelper().refresh();
        app.getUserHelper().openLoginPage();
    }

    @BeforeMethod
    public void preconditionsBeforeMethod(){
        if(flagIsAlertPresent){
            //app.getUserHelper().refresh();
            flagIsAlertPresent = false;
            app.getUserHelper().clickAcceptAlert();

        }
        if(flagIsUserLogin){
            flagIsUserLogin = false;
            app.getUserHelper().logout();

        }
        app.getUserHelper().refresh();
        //login
        //sign out
        //alert
    }


    @Test
    public void positiveLoginUserDTO(){
        //create user for test
        UserDTO userDTO = new UserDTO("krasleo@gmail.com", "Cristiano7777$!");
        logger.info(String
                .format("in the next function: fill email input with email: %s and with the password: %s and click on button login",
                        userDTO.getEmail(), userDTO.getPassword()));
        //logger.info("Test date --> " + userDTO.toString());
        //to put the user inside the login method
        app.getUserHelper().login(userDTO);
        logger.info("validation by assertTrue, that contacts link which displays on the menu");

        flagIsUserLogin = true;
        //to make validation ( returns true or false)
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }

    @Test
    public void positiveLoginUserDTOWith(){
        //create user for test
        UserDTOWith userDTOWith = new UserDTOWith().withEmail("krasleo@gmail.com")
                .withPassword("Cristiano7777$!");

        logger.info("Test date --> " + userDTOWith.toString());
        //to put the user inside the login method
        app.getUserHelper().login(userDTOWith);

        flagIsUserLogin = true;
        //to make validation ( returns true or false)
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }

    @Test
    public void positiveLoginUserDTOWLombok(Method method){
        //create user for test
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("Cristiano7777$!")
                .build();

        //timeStart = System.currentTimeMillis();
        //logger.info("Start test -->  " + method.getName());
        logger.info("Test date --> " + user.toString());


        //to put the user inside the login method
        app.getUserHelper().loginUserDTOLombok(user);

        flagIsUserLogin = true;
        //to make validation ( returns true or false)
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
        //timeStop = System.currentTimeMillis();
        //logger.info("Method stop --> " + method.getName() + "method duration = " + (timeStop - timeStart));

    }

    @Test
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
