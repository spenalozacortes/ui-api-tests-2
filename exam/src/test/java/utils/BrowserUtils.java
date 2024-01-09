package utils;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.Cookie;

import static aquality.selenium.browser.AqualityServices.getBrowser;

@UtilityClass
public class BrowserUtils {

    public static void addCookie(String token) {
        getBrowser().getDriver().manage().addCookie(new Cookie("token", token));
    }

    public static void navigateAndAuthorize(String url, String user, String password) {
        String protocolSeparator = "://";
        int protocolEndIndex = url.indexOf(protocolSeparator);
        String authenticatedUrl = url.substring(0, protocolEndIndex + protocolSeparator.length()) +
                user + ":" + password + "@" +
                url.substring(protocolEndIndex + protocolSeparator.length());
        getBrowser().goTo(authenticatedUrl);
    }
}
