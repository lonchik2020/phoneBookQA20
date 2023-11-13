package manager;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper {


    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By btnLoginHeaderMenu = By.xpath("//a[contains(@href, '/login')]");

    By inputEmailLoginForm = By.xpath("//input[@name ='email']");

    By inputPasswordLoginForm = By.xpath("//input[@name ='password']");

    By btnLoginForm = By.xpath("//button[@name ='login']");

    String btnRegistration = "document.querySelector('[name=\"registration\"]').click();";

    By successLoginConfirmationElement = By.xpath("//a[@href='/contacts']");


    By btnLogOut = By.xpath("//button[text()='Sign Out']");


    public void openLoginPage(){
        clickBase(btnLoginHeaderMenu);
    }


    public void login(UserDTO userDTO) {
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnLoginForm);
    }

    public void login(UserDTOWith userDTOWith) {
        typeTextBase(inputEmailLoginForm, userDTOWith.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTOWith.getPassword());
        clickBase(btnLoginForm);
    }


    public boolean validateSuccessConfirmationElementAfterLogin() {
        return isTextEqual(successLoginConfirmationElement, "CONTACTS");

    }

    public void loginUserDTOLombok(UserDTOLombok user) {
        typeTextBase(inputEmailLoginForm, user.getEmail());
        typeTextBase(inputPasswordLoginForm, user.getPassword());
        clickBase(btnLoginForm);
    }

    public void registrationUserDTOLombok(UserDTOLombok user) {
        clickBase(btnLoginHeaderMenu);
        typeTextBase(inputEmailLoginForm, user.getEmail());
        typeTextBase(inputPasswordLoginForm, user.getPassword());
        jsClickBase(btnRegistration);
    }


    public boolean validateMessageAlertWrongEmailCorrectPassword() {
        String expectedResult = "Wrong email or password".toUpperCase();
        String actualResult = getTextAlert();
        return isTextContainsGetTwoStrings(expectedResult, actualResult);

    }

    public boolean validateMessageAlertCorrectEmailWrongPasswordReg() {
        String expectedResult = "WRONG EMAIL OR PASSWORD FORMAT";
        String actualResult = getTextAlert();
        if(actualResult.contains(expectedResult)){
        return true; // isTextEqualGetTwoStrings(expectedResult, actualResult);
    }else{
            System.out.println("expected result: " + expectedResult + "actual result: " + actualResult);
            return false;
        }
    }
    
    public void logout() {
        clickBase(btnLogOut);
    }
}

