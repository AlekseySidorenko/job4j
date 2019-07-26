package ru.job4j.calc;

/**
 * Class InteractCalc | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 13.06.2019
 */
public class InteractCalc {

    protected final Calculator calculator;
    protected final InputGetter getter;
    protected final Input input;

    /**
     * Constructor.
     * @param calculator Calculator type.
     * @param input Input type.
     */
    public InteractCalc(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
        this.getter = new InputGetter(calculator, input);
    }

    /**
     * Getter.
     */
    public double getResult() {
        return calculator.getResult();
    }

    /**
     * Do calculation.
     * @return Calculation result.
     */
    protected double doCalculation() {
        double first;
        double second;
        String operation;
        first = getter.getOperand(input.ask("Type left operand"));
        second = getter.getOperand(input.ask("Type right operand"));
        operation = getter.getOperation(input.ask("Type operation"));
        if (!"false".equalsIgnoreCase(operation)) {
            this.calculator.getOperations().get(operation).accept(new Operands(first, second));
            System.out.println("Result: " + this.getResult());
        }
        return this.getResult();
    }

    /**
     * Init calculation program.
     */
    public void init() {
        boolean nextCalculation = true;
        while (nextCalculation) {
            if (calculator.isHasPreviousResult()) {
                System.out.println("Type \"last\" to get previous operation result");
            }
            doCalculation();
            if ("exit".equalsIgnoreCase(input.ask("Type \"exit\" to exit program or any key to next calculation"))) {
                nextCalculation = false;
            }
        }
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(new ConsoleInput(), new Calculator());
        calc.init();
    }
}