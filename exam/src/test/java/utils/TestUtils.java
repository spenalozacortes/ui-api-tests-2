package utils;

import lombok.experimental.UtilityClass;

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
}
