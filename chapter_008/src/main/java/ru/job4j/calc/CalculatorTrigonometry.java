package ru.job4j.calc;

/**
 * Class CalculatorTrigonometry | Implement engineering calculator using InteractCalculator [#851]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 24.06.2019
 */
public class CalculatorTrigonometry extends Calculator {

    /**
     * Sine calculation operation.
     * @param operands Two operands for calculate
     *                 Use only one operand
     */
    public void sin(Operands operands) {
        this.result = Math.sin(operands.getFirst());
    }

    /**
     * Cosine calculation operation.
     * @param operands Two operands for calculate
     *                 Use only one operand
     */
    public void cos(Operands operands) {
        this.result = Math.cos(operands.getFirst());
    }

    /**
     * Tangent calculation operation.
     * @param operands Two operands for calculate
     *                 Use only one operand
     */
    public void tg(Operands operands) {
        this.result = Math.tan(operands.getFirst());
    }

    /**
     * Cotangent calculation operation.
     * @param operands Two operands for calculate
     *                 Use only one operand
     */
    public void ctg(Operands operands) {
        this.result = 1D / Math.tan(operands.getFirst());
    }

    /**
     * Fill map with available operations.
     */
    @Override
    protected void fillOperations() {
        super.fillOperations();
        operations.put("sin", this::sin);
        operations.put("cos", this::cos);
        operations.put("tg", this::tg);
        operations.put("ctg", this::ctg);
    }
}