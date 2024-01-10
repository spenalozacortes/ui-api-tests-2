package tests;

import config.CredentialsConfig;
import config.EnvironmentConfig;
import config.TestDataConfig;
import constants.CommonConstants;
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
    private static final String VARIANT = TestDataConfig.getVariant();
    private static final String PROJECT_NAME = TestDataConfig.getProjectName();
    public static final String START_TIME_COLUMN = "4";
    public static final String TEST_NAME_COLUMN = "1";
    public static final int PROJECT_NAME_LENGTH = CommonConstants.RANDOM_STRING_LENGTH;
    public static final int TEST_NAME_LENGTH = CommonConstants.RANDOM_STRING_LENGTH;
    public static final int METHOD_NAME_LENGTH = CommonConstants.RANDOM_STRING_LENGTH;
    public static final String ENV = TestDataConfig.getEnv();
    public static final String BROWSER = TestDataConfig.getBrowser();
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
        Assert.assertTrue(projectsPage.footerForm().getVersionText().contains(VARIANT), "Variant incorrect");
        String projectId = projectsPage.getProjectId(PROJECT_NAME);
        projectsPage.clickProjectLink(PROJECT_NAME);

        projectPage = new ProjectPage();
        projectPage.state().waitForDisplayed();
        List<String> testDates = projectPage.getColumnData(START_TIME_COLUMN);
        Assert.assertTrue(TestUtils.areTestsSortedDesc(testDates), "Tests on page are not sorted in descending order by date");
        List<String> testNames = projectPage.getColumnData(TEST_NAME_COLUMN);
        List<TestResponse> tests = apiSteps.getTests(projectId);
        Assert.assertTrue(TestUtils.areTestsContainedInResponse(testNames, tests), "Tests on page don't correspond to API response");

        getBrowser().goBack();
        projectsPage.state().waitForDisplayed();
        projectsPage.clickAddBtn();
        getBrowser().tabs().switchToLastTab();
        addProjectPage = new AddProjectPage();
        addProjectPage.state().waitForDisplayed();
        String randomProjectName = RandomUtils.generateRandomString(PROJECT_NAME_LENGTH);
        addProjectPage.setProjectName(randomProjectName);
        addProjectPage.clickSaveProjectBtn();
        Assert.assertTrue(addProjectPage.isSuccessAlertDisplayed(), "Saved project success alert not displayed");
        getBrowser().tabs().closeTab();
        getBrowser().tabs().switchToLastTab();
        projectsPage.state().waitForDisplayed();
        getBrowser().refresh();
        Assert.assertEquals(projectsPage.getProjectText(randomProjectName), randomProjectName, "New project not displayed");

        projectsPage.clickProjectLink(randomProjectName);
        projectPage.state().waitForDisplayed();
        String sid = RandomUtils.generateSessionId();
        String randomTestName = RandomUtils.generateRandomString(TEST_NAME_LENGTH);
        String randomMethodName = RandomUtils.generateRandomString(METHOD_NAME_LENGTH);
        String testId = apiSteps.addTest(sid, randomProjectName, randomTestName, randomMethodName, ENV, BROWSER);
    }
}
