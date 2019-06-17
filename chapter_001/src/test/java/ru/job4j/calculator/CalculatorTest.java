package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 17.10.2017
 */
public class CalculatorTest {

    /** Test addition. */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(new Operands(1D, 1D));
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /** Test substruction. */
    @Test
    public void whenSubstructFiveMinusTwoThenThree() {
        Calculator calc = new Calculator();
        calc.substract(new Operands(5D, 2D));
        double result = calc.getResult();
        double expected = 3D;
        assertThat(result, is(expected));
    }

    /** Test division. */
    @Test
    public void whenDivideTwelveByThreeThenFour() {
        Calculator calc = new Calculator();
        calc.divide(new Operands(12D, 3D));
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

    /** Test multiplication. */
    @Test
    public void whenMultipleTwoTimesThreeThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(new Operands(2D, 3D));
        double result = calc.getResult();
        double expected = 6D;
        assertThat(result, is(expected));
    }
}