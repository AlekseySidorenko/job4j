package ru.job4j.calc.input;

import ru.job4j.calc.Calculator;

/**
 * Class InputValidator | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.07.2019
 */
public class InputValidator {

    Calculator calculator;

    /**
     * Constructor.
     *
     * @param calculator Calculator type.
     */
    public InputValidator(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Validate operand value in user input.
     *
     * @param input Operand value in string format.
     * @return true if input contains double value.
     */
    public boolean validateInputOperand(final String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Validate operation type in user input.
     *
     * @param input Operation type.
     * @return True if input contains valid operation.
     */
    public boolean validateInputOperation(final String input) {
        boolean result = false;
        if (this.calculator.getOperations().get(input) != null) {
            result = true;
        }
        return result;
    }
}