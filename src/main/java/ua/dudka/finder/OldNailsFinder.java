package ua.dudka.finder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OldNailsFinder implements NailsFinder {

    @Override
    public int solution(int[] A, int Y) {
        if (A == null || A.length == 0) {
            return 0;
        }
        Map<Integer, Long> nailsCountByLength = Arrays.stream(A)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Integer maxLength = A[A.length - 1]; //only diff
        int secondMaxLength = getSecondMaxLength(nailsCountByLength, maxLength);

        return getMaxNailsCount(A, Y, secondMaxLength);
    }

    private int getSecondMaxLength(Map<Integer, Long> nailsCountByLength, Integer maxLength) {
        return nailsCountByLength.entrySet().stream()
                .filter(e -> !e.getKey().equals(maxLength))
                .map(Map.Entry::getValue)
                .max(Comparator.naturalOrder())
                .orElse(0L)
                .intValue();
    }

    private int getMaxNailsCount(int[] A, int Y, int secondMaxLength) {
        int count = secondMaxLength;

        int nailsLeft = Y;
        for (int i = A.length - 1; i >= 0 && nailsLeft > 0; i--, nailsLeft--) {
            if (A[i] != secondMaxLength) {
                count++;
            }
        }
        return count;
    }
}
