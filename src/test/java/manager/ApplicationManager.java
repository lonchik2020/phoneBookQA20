package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    WebDriver driver;
    UserHelper userHelper; //1

    public void init(){
        driver = new ChromeDriver();
        driver.navigate().to("https://telranedu.web.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        userHelper = new UserHelper(driver);//2
        logger.info("navigated to the url: https://telranedu.web.app/home Start testing --> " + LocalDateTime.now());
    }

     public UserHelper getUserHelper() {//3
         return userHelper;
    }

    public void tearDown(){driver.quit();
        logger.info("Stop testing --> " + LocalDateTime.now());
    }
}
