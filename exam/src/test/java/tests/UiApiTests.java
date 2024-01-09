package tests;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.Data;
import models.TestResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import api.ApiSteps;
import utils.BrowserUtils;

import java.util.List;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class UiApiTests extends BaseTest {

    private static final String URL = EnvironmentConfig.getUrl();
    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private final ApiSteps apiSteps = new ApiSteps();
    private ProjectsPage projectsPage;

    @Test
    public void test() {
        BrowserUtils.navigateAndAuthorize(URL, USER, PASSWORD);
        String token = apiSteps.getToken();
        BrowserUtils.addCookie(token);
        getBrowser().refresh();

        projectsPage = new ProjectsPage();
        Assert.assertTrue(projectsPage.state().waitForDisplayed(), "Projects page not displayed");
        Assert.assertTrue(projectsPage.footerForm().getVersionText().contains(Data.VARIANT), "Variant incorrect");
        String projectId = projectsPage.getProjectId(Data.PROJECT_NAME);
        projectsPage.clickProjectLink(Data.PROJECT_NAME);

        List<TestResponse> tests = apiSteps.getTests(projectId);
    }
}
