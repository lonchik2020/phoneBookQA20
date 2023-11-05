package tests;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest{

    private long timeStart, timeStop;

//    @BeforeClass
//    public void preconditionsBeforeClass(){
//
//    }

    @Test
    public void positiveLoginUserDTO(){
        //create user for test
        UserDTO userDTO = new UserDTO("krasleo@gmail.com", "Cristiano7777$!");
        logger.info(String
                .format("in the next function: open login page, fill email input with email: %s and with the password: %s and click on button login",
                        userDTO.getEmail(), userDTO.getPassword()));
        logger.info("Test date --> " + userDTO.toString());
        //to put the user inside the login method
        app.getUserHelper().login(userDTO);
        //to make validation ( returns true or false)
        logger.info("validation by assertTrue, that contacts link which displays on the menu");
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
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password

    }

    @Test
    public void negativeLoginTest_WrongEmail_WO_Digits(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("werfdth@acbf")
                .password("Cristiano7777$!")
                .build();
        app.getUserHelper().loginUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password
    }

    @Test
    public void negativeLoginTest_WrongPassword_WO_Digits(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("Cristianoreal$!")
                .build();
        app.getUserHelper().loginUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password
    }

    @Test
    public void negativeLoginTest_WrongPassword_WO_Letters(){
        UserDTOLombok user = UserDTOLombok.builder()
                .email("krasleo@gmail.com")
                .password("12345678$!")
                .build();
        app.getUserHelper().loginUserDTOLombok(user);
        Assert.assertTrue(app.getUserHelper().validateMessageAlertWrongEmailCorrectPassword());//Wrong email or password
    }


}
