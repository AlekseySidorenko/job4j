package ru.job4j.calc;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Class InteractCalcTest | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.06.2019
 */
public class InteractCalcTest {

    Calculator calculator = new Calculator();

    /** Test addition */
    @Test
    public void whenOnePlusTwoThenGet3() {
        Input input = new StubInput(new String[]{"1", "2", "+", "exit"});
        InteractCalc calc = new InteractCalc(input, calculator);
        calc.init();
        assertThat(calc.getResult(), is(3D));
    }

    /** Test subtraction */
    @Test
    public void whenFiveMinusTwoThenGet3() {
        Input input = new StubInput(new String[]{"5", "2", "-", "exit"});
        InteractCalc calc = new InteractCalc(input, calculator);
        calc.init();
        assertThat(calc.getResult(), is(3D));
    }

    /** Test multiplication */
    @Test
    public void whenFiveMultipleTwoThenGet10() {
        Input input = new StubInput(new String[]{"5", "2", "*", "exit"});
        InteractCalc calc = new InteractCalc(input, calculator);
        calc.init();
        assertThat(calc.getResult(), is(10D));
    }

    /** Test division */
    @Test
    public void whenSixDivideTwoThenGet3() {
        Input input = new StubInput(new String[]{"6", "3", "/", "exit"});
        InteractCalc calc = new InteractCalc(input, calculator);
        calc.init();
        assertThat(calc.getResult(), is(2D));
    }

    /** Test last calculation result using */
    @Test
    public void whenNextCalculationGetLastResultThenResultIsUsed() {
        Input input = new StubInput(new String[]{"1", "2", "+", "next", "last", "5", "+", "exit"});
        InteractCalc calc = new InteractCalc(input, calculator);
        calc.init();
        assertThat(calc.getResult(), is(8D));
    }

    /** Test operand's validation */
    @Test
    public void whenSetNotValidOperandThenOperandSetsByDefault() {
        Input input = new StubInput(new String[]{"1", "wrong", "+", "exit"});
        InteractCalc calc = new InteractCalc(input, calculator);
        calc.init();
        assertThat(calc.getResult(), is(1D));
    }
}