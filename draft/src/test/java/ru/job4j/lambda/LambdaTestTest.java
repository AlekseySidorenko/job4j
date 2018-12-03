package ru.job4j.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LambdaTestTest {

    @Test
    public void whenAdd1Yntil3() {
        LambdaTest lambdaTest = new LambdaTest();
        List<Double> buffer = new ArrayList<>();
        lambdaTest.multiple(1, 3, 1,
                MathUtil::add,
                buffer::add);
        assertThat(buffer, is(Arrays.asList(2.0, 3.0, 4.0)));
    }
}
