package tests;

import org.testng.annotations.Test;
import steps.ApiSteps;

public class UiApiTests extends BaseTest {

    private final ApiSteps apiSteps = new ApiSteps();

    @Test
    public void test() {
        String token = apiSteps.getToken();
        System.out.println(token);
    }
}
