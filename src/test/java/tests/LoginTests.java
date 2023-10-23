package tests;

import dto.UserDTO;
import dto.UserDTOWith;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Test
    public void positiveLoginUserDTO(){
        //create user for test
        UserDTO userDTO = new UserDTO("krasleo@gmail.com", "Cristiano7777$!");
        //to put the user inside the login method
        app.getUserHelper().login(userDTO);
        //to make validation ( returns true or false)
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }

    @Test
    public void positiveLoginUserDTOWith(){
        //create user for test
        UserDTOWith userDTOWith = new UserDTOWith().withEmail("krasleo@gmail.com")
                .withPassword("Cristiano7777$!");
        //to put the user inside the login method
        app.getUserHelper().login(userDTOWith);
        //to make validation ( returns true or false)
        Assert.assertTrue(app.getUserHelper().validateSuccessConfirmationElementAfterLogin());
    }
}
