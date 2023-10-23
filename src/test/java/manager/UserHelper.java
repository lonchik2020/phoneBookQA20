package manager;

import dto.UserDTO;
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
    }    public void login(UserDTOWith userDTO) {
        clickBase(btnLoginHeaderMenu);
        typeTextBase(inputEmailLoginForm, userDTO.getEmail());
        typeTextBase(inputPasswordLoginForm, userDTO.getPassword());
        clickBase(btnLoginForm);
    }



    public boolean validateSuccessConfirmationElementAfterLogin() {
        return isTextEqual(successLoginConfirmationElement , "CONTACTS");

    }
}
