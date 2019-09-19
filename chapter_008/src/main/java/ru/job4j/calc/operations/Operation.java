package ru.job4j.calc.operations;

/**
 * Interface Operation | Implement interactive calculator using Calculator [#850]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 19.09.2019
 */
public interface Operation {

    /**
     * Getting operation name e.g. +, -, sin and so on.
     * @return Operation name.
     */
    String getOperationName();

    /**
     * Calculate.
     * @return Calculation result.
     */
    Double calculate(Double first, Double second);

}