package config;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CredentialsConfig {

    private static final ISettingsFile CREDENTIALS = new JsonSettingsFile("credentials.json");

    public static String getUser() {
        return CREDENTIALS.getValue("/user").toString();
    }

    public static String getPassword() {
        return CREDENTIALS.getValue("/password").toString();
    }
}
