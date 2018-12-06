package lars.advent2018;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class Day2 {

    @Test
    void no_letter_twice() {
        assertThat(howOften(2L, "abcdef")).isEqualTo(0);
    }

    @Test
    void no_letter_triple() {
        assertThat(howOften(3L, "abcdef")).isEqualTo(0);
    }

    @Test
    void one_letter_twice() {
        assertThat(howOften(2L, "bababc")).isEqualTo(1);
    }

    @Test
    void one_letter_triple() {
        assertThat(howOften(3L, "bababc")).isEqualTo(1);
    }

    @Test
    void checksum_calculation() {
        assertThat(checksum("abcdef")).isEqualTo(0);
        assertThat(checksum("abcdef\nbababc")).isEqualTo(1);
    }

    private int checksum(String input) {
        List<String> lines = input.lines().collect(Collectors.toList());
        var twos = 0;
        for (String l : lines) {
            int i = howOften(2L, l);
            if (i == 1) {
                twos++;
            }
        }
        var threes = 0;
        for (String l : lines) {
            int i = howOften(3L, l);
            if (i == 1) {
                threes++;
            }
        }
        return twos * threes;
    }

    private int howOften(long target, String input) {
        Map<String, Long> map = input.chars()
                .mapToObj(i -> String.valueOf((char) (i)))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return map.values().contains(target) ? 1 : 0;
    }
}