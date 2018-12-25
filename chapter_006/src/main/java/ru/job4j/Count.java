package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Count | Task Solution: Threadsafe Count class [#1102]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 25.12.2018
 */
@ThreadSafe
public class Count {

    @GuardedBy("this")
    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int getValue() {
        return this.value;
    }
}