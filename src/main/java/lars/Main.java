package lars;

import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        IntStream.range(1, 10)
                .mapToDouble(i -> Math.pow(Double.valueOf(i), 3.0))
                .forEach(d -> System.out.println(d));
    }
}