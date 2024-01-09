package tests;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.Data;
import models.TestResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProjectsPage;
import api.ApiSteps;
import pages.ProjectPage;
import utils.BrowserUtils;
import utils.TestUtils;

import java.util.List;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class UiApiTests extends BaseTest {

    private static final String URL = EnvironmentConfig.getUrl();
    private static final String USER = CredentialsConfig.getUser();
    private static final String PASSWORD = CredentialsConfig.getPassword();
    private final ApiSteps apiSteps = new ApiSteps();
    private ProjectsPage projectsPage;
    private ProjectPage projectPage;

    @Test
    public void test() {
        BrowserUtils.navigateAndAuthorize(URL, USER, PASSWORD);
        String token = apiSteps.getToken();
        BrowserUtils.addCookie(token);
        getBrowser().refresh();

        projectsPage = new ProjectsPage();
        Assert.assertTrue(projectsPage.state().waitForDisplayed(), "All projects page not displayed");
        Assert.assertTrue(projectsPage.footerForm().getVersionText().contains(Data.VARIANT), "Variant incorrect");
        String projectId = projectsPage.getProjectId(Data.PROJECT_NAME);
        projectsPage.clickProjectLink(Data.PROJECT_NAME);

        projectPage = new ProjectPage();
        Assert.assertTrue(projectPage.state().waitForDisplayed(), "Project page not displayed");
        List<String> testDates = projectPage.getColumnData(Data.START_TIME_COLUMN);
        Assert.assertTrue(TestUtils.areTestsSortedDesc(testDates), "Tests on page are not sorted in descending order by date");
        List<String> testNames = projectPage.getColumnData(Data.NAME_COLUMN);
        List<TestResponse> tests = apiSteps.getTests(projectId);
        Assert.assertTrue(TestUtils.areTestsContainedInResponse(testNames, tests), "Tests on page don't correspond to API response");
    }
}
