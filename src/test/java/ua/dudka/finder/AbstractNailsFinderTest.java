package ua.dudka.finder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

abstract class AbstractNailsFinderTest {

    private NailsFinder finder;

    @BeforeEach
    void setUp() {
        finder = getFinder();
    }

    protected abstract NailsFinder getFinder();

    @ParameterizedTest
    @MethodSource("data")
    void test(int[] a, int y, int result) {
        int solution = finder.find(a, y);

        Assertions.assertEquals(solution, result);
    }

    static Stream<Arguments> data() {
        int[] integers = generateIntegers(1_000);
        return Stream.of(
                Arguments.of((Object) null, 2, 0),
                Arguments.of((Object) new int[]{}, 2, 0),
                Arguments.of((Object) new int[]{}, 0, 0),
                Arguments.of((Object) new int[]{1}, 2, 1),
                Arguments.of((Object) new int[]{1, 1}, 2, 2),
                Arguments.of((Object) new int[]{1, 2, 2}, 2, 3),
                Arguments.of((Object) new int[]{1, 2, 3, 4, 4, 4, 5, 6, 7}, 0, 3),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 2, 5),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 5, 8),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 6, 8),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 7, 9),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 8, 10),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 9, 10),
                Arguments.of((Object) new int[]{1, 1, 3, 3, 3, 4, 5, 5, 5, 5}, 10, 10),
                Arguments.of((Object) integers, 100, 1099),
                Arguments.of((Object) integers, 1000, 1999),
                Arguments.of((Object) integers, integers.length - 1, integers.length)
        );
    }

    private static int[] generateIntegers(int size) {
        return IntStream.rangeClosed(1, size)
                .flatMap(e -> IntStream.generate(() -> e).limit(e))
                .toArray();
    }
}