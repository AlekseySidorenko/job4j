package ru.job4j.calculator;

/**
 * Class Calculator | Elementary calculator [#185]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 17.10.2017
 */
public class Calculator {

    /** Calculate result. */
    protected double result;

    /**
     * Addition operation.
     * @param operands Two operands for calculate
     */
    public void add(Operands operands) {
        this.result = operands.getLeft() + operands.getRight();
    }

    /**
     * Subtraction operation.
     * @param operands Two operands for calculate
     */
    public void substract(Operands operands) {
        this.result = operands.getLeft() - operands.getRight();
    }

    /**
     * Division operation.
     * @param operands Two operands for calculate
     */
    public void divide(Operands operands) {
        this.result = operands.getLeft() / operands.getRight();
    }

    /**
     * Multiplication operation.
     * @param operands Two operands for calculate
     */
    public void multiple(Operands operands) {
        this.result = operands.getLeft() * operands.getRight();
    }

    /**
     * Getter.
     * @return Result.
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Sine calculation operation.
     * @param operands Two operands for calculate
     *                 Use only first operand
     */
    public void sin(Operands operands) {
        this.result = Math.sin(operands.getLeft());
    }

    /**
     * Cosine calculation operation.
     * @param operands Two operands for calculate
     *                 Use only first operand
     */
    public void cos(Operands operands) {
        this.result = Math.cos(operands.getLeft());
    }

    /**
     * Tangent calculation operation.
     * @param operands Two operands for calculate
     *                 Use only first operand
     */
    public void tg(Operands operands) {
        this.result = Math.tan(operands.getLeft());
    }

    /**
     * Cotangent calculation operation.
     * @param operands Two operands for calculate
     *                 Use only first operand
     */
    public void ctg(Operands operands) {
        this.result = 1D / Math.tan(operands.getLeft());
    }
}
