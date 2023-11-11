package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser;
    EventFiringWebDriver driver;//object which allows to connect listener with web driver
    UserHelper userHelper; //1

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

        driver.navigate().to("https://telranedu.web.app/home");
        logger.info("navigated to the url: https://telranedu.web.app/home Start testing --> " + LocalDateTime.now());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.register(new WDListener());

        userHelper = new UserHelper(driver);//2

    }

     public UserHelper getUserHelper() {//3
         return userHelper;
    }

    public void tearDown(){
        driver.quit();
        logger.info("Stop testing --> " + LocalDateTime.now());
    }

    public void navigateToMainPage(){
        driver.navigate().to("https://telranedu.web.app/home");
    }

    public boolean isPageUrHome(){
        String url = driver.getCurrentUrl();
        System.out.println(url + "================== url");
        return url.equals("https://telranedu.web.app/home Start testing");
        // google - prod
        //preprod.google - pre production
        //qa78.google.com
    }
}
