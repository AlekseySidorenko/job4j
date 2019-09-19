
package ru.job4j.calc;

import org.junit.Test;
import ru.job4j.calc.input.*;
import ru.job4j.calc.operations.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Class TrigonometryCalcTest | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */

public class InteractCalcTrigonometryTest {

    Calculator calculator = new Calculator();

    /** Test sin */
    @Test
    public void whenSinPIdivided2ThenGet1() {
        Double value = Math.PI / 2;
        String v = value.toString();
        Input input = new StubInput(new String[]{v, "sin", "exit"});
        InteractCalcTrigonometry calc = new InteractCalcTrigonometry(input, calculator);
        calc.addOperation(new Sin());
        calc.init();
        assertThat(calc.getResult(), is(1D));
    }

    /** Test cos */
    @Test
    public void whenCos0ThenGet1() {
        Input input = new StubInput(new String[]{"0", "cos", "exit"});
        InteractCalcTrigonometry calc = new InteractCalcTrigonometry(input, calculator);
        calc.addOperation(new Cos());
        calc.init();
        assertThat(calc.getResult(), is(1D));
    }
}