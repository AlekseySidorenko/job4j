package ru.job4j.calc;

import ru.job4j.calculator.Calculator;
import ru.job4j.calculator.Operands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Class Operations | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public class Operations {
    private final Calculator calculator;

    /**
     * Key = operaion type, value = operation implementation.
     */
    public final Map<String, Consumer<Operands>> operations;

    /**
     * Constructor.
     */
    public Operations(final Calculator calculator) {
        this.calculator = calculator;
        this.operations = new HashMap<>();
        this.fillOperations();
    }

    /**
     * Fill map with available operations.
     */
    private void fillOperations() {
        operations.put("+", this.calculator::add);
        operations.put("-", this.calculator::substract);
        operations.put("/", this.calculator::divide);
        operations.put("*", this.calculator::multiple);
    }

    /**
     * Getter.
     */
    public Map<String, Consumer<Operands>> getOperations() {
        return operations;
    }
}