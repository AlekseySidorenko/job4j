package ru.job4j.calculator;

/**
 * Class InteractCalcTest | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.06.2019
 */
public class Operands {

    /** Left operand. */
    private final double left;

    /** Right operand. */
    private final double right;

    /**
     * Constructor.
     */
    public Operands(double left, double right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Getter.
     */
    public double getLeft() {
        return left;
    }

    /**
     * Getter.
     */
    public double getRight() {
        return right;
    }
}