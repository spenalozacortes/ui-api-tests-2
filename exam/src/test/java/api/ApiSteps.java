package api;

import constants.TestData;
import constants.Endpoints;
import constants.Parameters;
import io.restassured.http.ContentType;
import models.TestResponse;

import java.util.List;

public class ApiSteps extends BaseSteps {

    public String getToken() {
        return getBaseReq()
                .queryParam(Parameters.VARIANT, TestData.VARIANT)
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
}
