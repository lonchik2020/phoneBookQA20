package tests;

import api.ContactsService;
import api.UserApi;
import dto.UserDTOLombok;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.RandomUtils;

import java.lang.reflect.Method;
import java.util.Arrays;


@Listeners(TestNGListener.class)
public class BaseTest {//to create here object of applicationManager

    boolean flagIsAlertPresent = false;
    boolean flagIsUserLogin = false;

    Logger logger = LoggerFactory.getLogger(BaseTest.class);

    RandomUtils randomUtils = new RandomUtils();

    static ApplicationManager app = new ApplicationManager();


    UserDTOLombok user = UserDTOLombok.builder()
            .username("krasleo@gmail.com")
            .password("Cristiano7777$!")
            .build();

    UserApi userApi = new UserApi();
    ContactsService contactsService = new ContactsService();
    String token = "";

    SoftAssert softAssert = new SoftAssert();


    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        token = userApi.getTokenFromLoginResponse(user);
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void stop(){
        app.tearDown();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeEachMethod(Method method){
        logger.info("--------------------------------------------------------------------------");
        logger.info("started method: " + method.getName());
        logger.info("started method with params: " + Arrays.toString(method.getParameters()));

    }


    @AfterMethod(alwaysRun = true)
    public void afterEachMethod(Method method) {
        logger.info("stopped method: " + method.getName());
        logger.info("stopped method with params: " + Arrays.toString(method.getParameters()));
    }


    public void preconditionsForLoginAndRegTests() {
        if (flagIsAlertPresent) {
            flagIsAlertPresent = false;
            app.getUserHelper().clickAcceptAlert();

        }
        if (flagIsUserLogin) {
            flagIsUserLogin = false;
            app.getUserHelper().logout();

        }
    }

}
