package config;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataConfig {

    private static final ISettingsFile TEST_DATA = new JsonSettingsFile("testData.json");

    public static String getVariant() {
        return TEST_DATA.getValue("/variant").toString();
    }

    public static String getProjectName() {
        return TEST_DATA.getValue("/projectName").toString();
    }

    public static String getEnv() {
        return TEST_DATA.getValue("/env").toString();
    }

    public static String getBrowser() {
        return TEST_DATA.getValue("/browser").toString();
    }
}
