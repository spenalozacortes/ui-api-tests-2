package tests;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import constants.TestData;
import models.TestResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddProjectPage;
import pages.ProjectsPage;
import api.ApiSteps;
import pages.ProjectPage;
import utils.BrowserUtils;
import utils.RandomUtils;
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
    private AddProjectPage addProjectPage;

    @Test
    public void test() {
        BrowserUtils.navigateAndAuthorize(URL, USER, PASSWORD);
        String token = apiSteps.getToken();
        BrowserUtils.addCookie(token);
        getBrowser().refresh();

        projectsPage = new ProjectsPage();
        Assert.assertTrue(projectsPage.state().waitForDisplayed(), "Projects page not displayed");
        Assert.assertTrue(projectsPage.footerForm().getVersionText().contains(TestData.VARIANT), "Variant incorrect");
        String projectId = projectsPage.getProjectId(TestData.PROJECT_NAME);
        projectsPage.clickProjectLink(TestData.PROJECT_NAME);

        projectPage = new ProjectPage();
        projectPage.state().waitForDisplayed();
        List<String> testDates = projectPage.getColumnData(TestData.START_TIME_COLUMN);
        Assert.assertTrue(TestUtils.areTestsSortedDesc(testDates), "Tests on page are not sorted in descending order by date");
        List<String> testNames = projectPage.getColumnData(TestData.NAME_COLUMN);
        List<TestResponse> tests = apiSteps.getTests(projectId);
        Assert.assertTrue(TestUtils.areTestsContainedInResponse(testNames, tests), "Tests on page don't correspond to API response");

        getBrowser().goBack();
        projectsPage.state().waitForDisplayed();
        projectsPage.clickAddBtn();
        getBrowser().tabs().switchToLastTab();
        addProjectPage = new AddProjectPage();
        addProjectPage.state().waitForDisplayed();
        String randomProjectName = RandomUtils.generateRandomString(TestData.PROJECT_NAME_LENGTH);
        addProjectPage.setProjectName(randomProjectName);
        addProjectPage.clickSaveProjectBtn();
        Assert.assertTrue(addProjectPage.isSuccessAlertDisplayed(), "Saved project success alert not displayed");
        getBrowser().tabs().closeTab();
        getBrowser().tabs().switchToLastTab();
        projectsPage.state().waitForDisplayed();
        getBrowser().refresh();
        Assert.assertEquals(projectsPage.getProjectText(randomProjectName), randomProjectName, "New project not displayed");
    }
}
