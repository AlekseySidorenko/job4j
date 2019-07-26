package ru.job4j.calc;

/**
 * Class InteractCalcTrigonometry | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */
public class InteractCalcTrigonometry extends InteractCalc {

    /**
     * Constructor.
     * @param calculator Calculator type.
     * @param input Input type.
     */
    public InteractCalcTrigonometry(Input input, Calculator calculator) {
        super(input, calculator);
    }

    /**
     * Do calculation.
     * @return Calculation result.
     */
    @Override
    protected double doCalculation() {
        double operand;
        String operation;
        operand = getter.getOperand(input.ask("Type operand"));
        operation = getter.getOperation(input.ask("Type operation"));
        if (!"false".equalsIgnoreCase(operation)) {
            this.calculator.getOperations().get(operation).accept(new Operands(operand, 0));
            System.out.println("Result: " + this.getResult());
        }
        return this.getResult();
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalcTrigonometry(new ConsoleInput(), new CalculatorTrigonometry());
        calc.init();
    }
}