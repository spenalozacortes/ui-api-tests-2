package tests;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import org.testng.annotations.Test;
import steps.ApiSteps;
import utils.BrowserUtils;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class UiApiTests extends BaseTest {

    private static final String URL = EnvironmentConfig.getUrl();
    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private final ApiSteps apiSteps = new ApiSteps();

    @Test
    public void test() {
        String token = apiSteps.getToken();
        BrowserUtils.navigateAndAuthorize(URL, USER, PASSWORD);
        BrowserUtils.addCookie(token);
        getBrowser().refresh();
    }
}
