package ru.job4j.nonblockingcache;

/**
 * Class OptimisticException | Task Solution: Non-blocking cache [#4741]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.01.2019
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}