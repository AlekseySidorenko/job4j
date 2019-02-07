package ru.job4j.nonblockingcache;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;

/**
 * Class NonBlockingCacheTest | Task Solution: Non-blocking cache [#4741]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 06.01.2019
 */
public class NonBlockingCacheTest {

    @Test
    public void whenAddModelThenGetModel() {
        NonBlockingCache storage = new NonBlockingCache();
        Base model = new Base(0);
        storage.add(model);
        Assert.assertThat(model, is(storage.getModel(model)));
    }

    @Test
    public void whenDeleteModelThenModelRemoveFromStorage() {
        NonBlockingCache storage = new NonBlockingCache();
        Base model = new Base(0);
        storage.add(model);
        storage.delete(model);
        Assert.assertNull(storage.getModel(model));
    }

    @Test
    public void whenVersionsAreDifferentThenThrowOptimisticException() throws InterruptedException {

        NonBlockingCache storage = new NonBlockingCache();
        AtomicReference<Exception> atomic = new AtomicReference<>();
        storage.add(new Base(0));

        Thread first = new Thread(() -> {
                    try {
                        storage.update(new Base(0));
                    } catch (OptimisticException ex) {
                        atomic.set(ex);
                    }
                }
        );

        Thread second = new Thread(() -> {
                    try {
                        storage.update(new Base(0));
                    } catch (OptimisticException ex) {
                        atomic.set(ex);
                    }
                }
        );

        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(atomic.get().getMessage(), is("Versions are different"));
    }
}
