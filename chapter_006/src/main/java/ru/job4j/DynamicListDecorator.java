package ru.job4j;

import ru.job4j.list.DynamicList;

import java.util.Iterator;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class DynamicListDecorator | Task Solution: Threadsafe Dynamic List [#1105]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 26.12.2018
 */
@ThreadSafe
public class DynamicListDecorator<E> implements Iterable<E> {

    @GuardedBy("this")
    private DynamicList<E> container;

    /**
     * Constructor.
     */
    public DynamicListDecorator(DynamicList<E> container) {
        this.container = container;
    }

    /**
     * Add element to container.
     * @param value element.
     */
    public synchronized void add(E value) {
        container.add(value);
    }

    /**
     * Get element from container.
     * @param index position.
     * @return element.
     */
    public synchronized E get(int index) {
        return this.container.get(index);
    }

    /**
     * Get container size.
     * @return number of elements in storage.
     */
    public synchronized int getSize() {
        return this.container.getSize();
    }

    /**
     * Get container snapshot.
     * @param source Container.
     * @return Snapshot.
     */
    private DynamicList<E> copy(DynamicList<E> source) {
        DynamicList<E> snapshot = new DynamicList<>();
        for (E element : source) {
            snapshot.add(element);
        }
        return snapshot;
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return this.copy(container).iterator();
    }
}