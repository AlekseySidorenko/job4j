package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class MatrixIterator Task Solution: Make prime numbers iterator [#151]
 * @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 02.05.2018
 */
public class PrimeIterator implements Iterator {

    private final int[] numbers;
    private int index = 0;
    private final static int MAX_NUMBER_OF_DIVIDERS = 2;

    /**
     * Constructor.
     */
    public PrimeIterator(int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Check next element presence in array.
     * @return presence is true, not presence is false.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        int i = index;
        while (i < numbers.length) {
            int numberOfDividers = 0;
            for (int j = 1; j <= numbers[i]; j++) {
                if ((numbers[i] % j) == 0) {
                    numberOfDividers++;
                }
            }
            if (numberOfDividers == MAX_NUMBER_OF_DIVIDERS) {
                result = true;
                break;
            }
            i++;
        }
        return result;
    }

    /**
     * Get next element in array.
     * @return next element in array.
     * @throws NoSuchElementException
     */
    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        while ((this.hasNext())) {
            int numberOfDividers = 0;
            for (int i = 1; i <= numbers[index]; i++) {
                if ((numbers[index] % i) == 0) {
                    numberOfDividers++;
                }
            }
            if (numberOfDividers == MAX_NUMBER_OF_DIVIDERS) {
                break;
            }
            index++;
        }
        return numbers[index++];
    }
}