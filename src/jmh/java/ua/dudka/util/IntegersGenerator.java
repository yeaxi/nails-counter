package ua.dudka.util;

import java.util.stream.IntStream;

public class IntegersGenerator {

    public static int[] generateNails(int size) {
        return IntStream.rangeClosed(1, size)
                .flatMap(e -> IntStream.generate(() -> e).limit(e))
                .toArray();
    }
}
