package tests;

import manager.ApplicationManager;
import manager.TestNGListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;


@Listeners(TestNGListener.class)
public class BaseTest {//to create here object of applicationManager

    Logger logger = LoggerFactory.getLogger(BaseTest.class);

    static ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void stop(){
        app.tearDown();
    }



}
