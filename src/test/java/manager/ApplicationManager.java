package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigProperties;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser;

    String url = ConfigProperties.getProperty("url");
    EventFiringWebDriver driver;//object which allows to connect listener with web driver
    UserHelper userHelper; //1

    ContactHelper contactHelper;//**1

    public ApplicationManager(){//constructor with default browser
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init(){
        //driver = new EventFiringWebDriver(new ChromeDriver());

        if(browser.equals(BrowserType.CHROME)) {
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("created chrome browser");
        }else if(browser.equals(BrowserType.FIREFOX)){
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("started tests in firefox driver");
        }


        driver.navigate().to(ConfigProperties.getProperty("url"));
        //driver.navigate().to(url);
        //logger.info("navigated to: " + ConfigProperties.getProperty("url"));
        logger.info("navigated to: " + url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.register(new WDListener());

        userHelper = new UserHelper(driver);//2
        contactHelper = new ContactHelper(driver);//**2

    }

     public UserHelper getUserHelper() {//3
         return userHelper;
    }

    public ContactHelper getContactHelper(){//**3
        return contactHelper;
    }

    public void tearDown(){
        driver.quit();
        //logger.info("Stop testing --> " + LocalDateTime.now());
    }

    public void navigateToMainPage(){
        //driver.navigate().to(ConfigProperties.getProperty("url"));
        driver.navigate().to(url);
    }

    public boolean isPageUrHome(){
        String urlCurrent = driver.getCurrentUrl();
        System.out.println(urlCurrent + "================== url");
        //return urlCurrent.equals(ConfigProperties.getProperty("url"));
        return urlCurrent.equals(url);
        // google - prod
        //preprod.google - pre production
        //qa78.google.com
    }
}
