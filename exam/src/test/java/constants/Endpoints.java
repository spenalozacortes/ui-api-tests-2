package constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {
    public static final String TEST = "/test";
    public static final String GET_TOKEN = "/token/get";
    public static final String GET_TESTS_JSON = String.format("%s/get/json", TEST);
    public static final String ADD_TEST = String.format("%s/put", TEST);
    public static final String ADD_LOG_TO_TEST = String.format("%s/put/log", TEST);
}
