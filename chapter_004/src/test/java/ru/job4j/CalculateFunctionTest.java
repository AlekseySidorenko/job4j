package ru.job4j;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class CalculateFunctionTest | Task Solution: Calculate function in range [#24251]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 22.11.2018
 */
public class CalculateFunctionTest {

    private CalculateFunction cf = new CalculateFunction();

    @Test
    public void when1To5LinearFunction() {
        List<Double> buffer = cf.linear(1, 5);
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D, 4D, 5D)));
    }

    @Test
    public void when1To5QuadraticFunction() {
        List<Double> buffer = cf.quadratic(1, 5);
        assertThat(buffer, is(Arrays.asList(1D, 4D, 9D, 16D, 25D)));
    }

    @Test
    public void when1To3LogFunction() {
        List<Double> buffer = cf.log(1, 3);
        assertThat(buffer, is(Arrays.asList(0.0, 0.6931471805599453, 1.0986122886681098)));
    }
}