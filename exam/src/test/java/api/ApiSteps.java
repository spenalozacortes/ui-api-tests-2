package api;

import config.TestDataConfig;
import constants.Endpoints;
import constants.Parameters;
import io.restassured.http.ContentType;
import models.TestResponse;

import java.util.List;

public class ApiSteps extends BaseSteps {

    private static final String VARIANT = TestDataConfig.getVariant();

    public String getToken() {
        return getBaseReq()
                .queryParam(Parameters.VARIANT, VARIANT)
                .when()
                .post(Endpoints.GET_TOKEN)
                .asString();
    }

    public List<TestResponse> getTests(String projectId) {
        return getBaseReq()
                .accept(ContentType.JSON)
                .queryParam(Parameters.PROJECT_ID, projectId)
                .when()
                .post(Endpoints.GET_TESTS_JSON)
                .jsonPath()
                .getList(ROOT_PATH, TestResponse.class);
    }

    public String addTest(String sid, String projectName, String testName, String methodName, String env, String browser) {
        return getBaseReq()
                .queryParam(Parameters.SID, sid)
                .queryParam(Parameters.PROJECT_NAME, projectName)
                .queryParam(Parameters.TEST_NAME, testName)
                .queryParam(Parameters.METHOD_NAME, methodName)
                .queryParam(Parameters.ENV, env)
                .queryParam(Parameters.BROWSER, browser)
                .when()
                .post(Endpoints.ADD_TEST)
                .asString();
    }
}
