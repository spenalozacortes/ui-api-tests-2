package steps;

import constants.Data;
import constants.Endpoints;
import constants.Parameters;

public class ApiSteps extends BaseSteps {

    public String getToken() {
        return getBaseReq()
                .queryParam(Parameters.VARIANT, Data.VARIANT)
                .when()
                .post(Endpoints.GET_TOKEN)
                .asString();
    }
}
