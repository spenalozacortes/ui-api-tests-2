package api;

import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    private static final String API_URI = EnvironmentConfig.getApiUri();
    protected static final String ROOT_PATH = ".";

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(API_URI);
    }
}
