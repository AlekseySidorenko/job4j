package ru.job4j.calc;

/**
 * Class InteractCalcTest | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 10.06.2019
 */
public class Operands {

    /** First operand. */
    private final double first;
    /** Second operand. */
    private final double second;

    /**
     * Constructor.
     * @param first First operand.
     * @param second Second operand.
     */
    public Operands(double first, double second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Getter.
     */
    public double getFirst() {
        return first;
    }

    /**
     * Getter.
     */
    public double getSecond() {
        return second;
    }
}