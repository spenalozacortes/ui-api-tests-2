package utils;

import lombok.experimental.UtilityClass;
import models.TestResponse;

import java.util.Comparator;
import java.util.List;

@UtilityClass
public class TestUtils {

    public static boolean areTestsSortedDesc(List<String> list) {
        List<String> sortedList = list.stream()
                .sorted(Comparator.reverseOrder())
                .toList();
        return sortedList.equals(list);
    }

    public static boolean areTestsContainedInResponse(List<String> testNames, List<TestResponse> tests) {
        List<String> responseTestNames = tests.stream()
                .map(test -> test.getName())
                .toList();
        return responseTestNames.containsAll(testNames);
    }
}
