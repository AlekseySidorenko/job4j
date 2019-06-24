package ru.job4j.calc;

import ru.job4j.calculator.Calculator;

/**
 * Class TrigonometryOperations | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */
public class TrigonometryOperations extends Operations {

    /**
     * Constructor.
     */
    public TrigonometryOperations(Calculator calculator) {
        super(calculator);
    }

    /**
     * Fill map with available operations.
     */
    @Override
    protected void fillOperations() {
        super.fillOperations();
        operations.put("sin", this.calculator::sin);
        operations.put("cos", this.calculator::cos);
        operations.put("tg", this.calculator::tg);
        operations.put("ctg", this.calculator::ctg);
    }
}