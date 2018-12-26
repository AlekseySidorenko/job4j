package ru.job4j;

import org.junit.Test;
import ru.job4j.list.DynamicList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class DynamicListDecoratorTest | Task Solution: Threadsafe Dynamic List [#1105]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 26.12.2018
 */
public class DynamicListDecoratorTest {

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionInsteadConcurrentModificationException() {
        DynamicListDecorator<Integer> list = new DynamicListDecorator<>(new DynamicList<>(1));
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        list.add(2);
        iterator.next();
    }
}