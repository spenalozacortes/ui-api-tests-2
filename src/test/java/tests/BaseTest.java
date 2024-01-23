package tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.isBrowserStarted;

public abstract class BaseTest {

    @BeforeTest
    public void setup() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @AfterMethod
    public void tearDown() {
        if (isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
