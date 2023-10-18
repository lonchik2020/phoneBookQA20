package startTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTests {

    WebDriver driver;

    @BeforeClass
    public void preConditions(){
        driver = new ChromeDriver();
        driver.navigate().to("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void loginPositive() throws InterruptedException {

        WebElement btnLoginHeader = driver.findElement(By.xpath("//a[contains(@href, '/login')]"));
        btnLoginHeader.click();


         WebElement inputEmail = driver.findElement(By.xpath("//input[@name ='email']"));
         inputEmail.click();
         inputEmail.clear();
         inputEmail.sendKeys("krasleo@gmail.com");


         WebElement inputPassword = driver.findElement(By.xpath("//input[@name ='password']"));
         inputPassword.click();
         inputPassword.clear();
         inputPassword.sendKeys("Cristiano7777$!");


         WebElement btnLogin = driver.findElement(By.xpath("//button[@name ='login']"));
         btnLogin.click();

        Thread.sleep(15000);


        WebElement confirmationElement= driver.findElement(By.xpath("//a[@href='/contacts']"));
        String actualResult = confirmationElement.getText().trim().toUpperCase();
        String expectedResult = "CONTACTS".toUpperCase();

        Assert.assertEquals(actualResult,expectedResult);


    }

    @AfterClass
    public void postConditions(){
        driver.quit();
    }


}
