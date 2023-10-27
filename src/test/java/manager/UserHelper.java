package manager;

import dto.UserDTO;
import dto.UserDTOLombok;
import dto.UserDTOWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper{

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By btnLoginHeaderMenu = By.xpath("//a[contains(@href, '/login')]");

    By inputEmailLoginForm = By.xpath("//input[@name ='email']");

    By inputPasswordLoginForm = By.xpath("//input[@name ='password']");

    By btnLoginForm = By.xpath("//button[@name ='login']");

    By successLoginConfirmationElement = By.xpath("//a[@href='/contacts']");

    public void login(UserDTO userDTO) {
        clickBase(btnLoginHeaderMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnLoginForm);
    }    public void login(UserDTOWith userDTOWith) {
        clickBase(btnLoginHeaderMenu);
        typeTextBase(inputEmailLoginForm, userDTOWith.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTOWith.getPassword());
        clickBase(btnLoginForm);
    }



    public boolean validateSuccessConfirmationElementAfterLogin() {
        return isTextEqual(successLoginConfirmationElement , "CONTACTS");

    }

    public void loginUserDTOLombok(UserDTOLombok user) {
        clickBase(btnLoginHeaderMenu);
        typeTextBase(inputEmailLoginForm, user.getEmail());
        typeTextBase(inputPasswordLoginForm, user.getPassword());
        clickBase(btnLoginForm);
    }
}
