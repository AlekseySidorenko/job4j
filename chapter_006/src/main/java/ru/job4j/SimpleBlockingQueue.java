package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Class SimpleBlockingQueue | Task Solution: Implementation of Producer Consumer pattern [#1098]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 27.12.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int size;

    /**
     * Constructor.
     */
    SimpleBlockingQueue(int queueSize) {
        this.size = queueSize;
    }

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Add element to queue.
     * @param value element.
     */
    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() == this.size) {
                queue.wait();
            }
            queue.offer(value);
            queue.notify();
        }
    }

    /**
     * Get element from queue.
     * @return element.
     */
    public T poll() throws InterruptedException {
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            queue.notify();
            return queue.poll();
        }
    }
}