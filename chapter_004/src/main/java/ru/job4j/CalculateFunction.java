package ru.job4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class CalculateFunction | Task Solution: Calculate function in range [#24251]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 22.11.2018
 */
class CalculateFunction {

    /**
     * Calculate function in range.
     * @param start Range start.
     * @param end Range end.
     * @param func Function.
     * @return List of values.
     */
    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}