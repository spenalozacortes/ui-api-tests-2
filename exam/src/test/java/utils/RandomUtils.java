package utils;

import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class RandomUtils {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";

    public static int getRandomInt(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = CHARACTERS.charAt(getRandomInt(CHARACTERS.length()));
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    public static String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}
