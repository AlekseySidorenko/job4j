package ru.job4j.calc;

import org.junit.Test;
import ru.job4j.calculator.Calculator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Class TrigonometryCalcTest | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */
public class TrigonometryCalcTest {

    Calculator calculator = new Calculator();

    /** Test sin */
    @Test
    public void whenSinPIdivided2ThenGet1() {
        Double value = Math.PI / 2;
        String v = value.toString();
        Input input = new StubInput(new String[]{v, "sin", "exit"});
        TrigonometryCalc calc = new TrigonometryCalc(input, calculator, new TrigonometryOperations(calculator));
        calc.init();
        assertThat(calc.getResult(), is(1D));
    }

    /** Test cos */
    @Test
    public void whenCos0ThenGet1() {
        Input input = new StubInput(new String[]{"0", "cos", "exit"});
        TrigonometryCalc calc = new TrigonometryCalc(input, calculator, new TrigonometryOperations(calculator));
        calc.init();
        assertThat(calc.getResult(), is(1D));
    }

    /** Test tg */
    @Test
    public void whenTgPIdivided4ThenGet1() {
        Double value = Math.PI / 4;
        String v = value.toString();
        Input input = new StubInput(new String[]{v, "tg", "exit"});
        TrigonometryCalc calc = new TrigonometryCalc(input, calculator, new TrigonometryOperations(calculator));
        calc.init();
        assertTrue(calc.getResult() > 0.9999 & calc.getResult() <= 1.0001);
    }

    /** Test ctg */
    @Test
    public void whenCotPIdivided4ThenGet1() {
        Double value = Math.PI / 4;
        String v = value.toString();
        Input input = new StubInput(new String[]{v, "ctg", "exit"});
        TrigonometryCalc calc = new TrigonometryCalc(input, calculator, new TrigonometryOperations(calculator));
        calc.init();
        assertTrue(calc.getResult() > 0.9999 & calc.getResult() <= 1.0001);
    }
}
