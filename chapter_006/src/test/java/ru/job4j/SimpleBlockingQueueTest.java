package ru.job4j;

import org.junit.Test;

/**
 * Class SimpleBlockingQueueTest | Task Solution: Implementation of Producer Consumer pattern [#1098]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 27.12.2018
 */
public class SimpleBlockingQueueTest {

    SimpleBlockingQueue<Integer> sbq = new SimpleBlockingQueue<>(2);

    Thread producer = new Thread(() -> {
        try {
            System.out.println("Producer is starting");
            System.out.println("Producer do offers");
            sbq.offer(1);
            sbq.offer(2);
            sbq.offer(3);
            sbq.offer(4);
            sbq.offer(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    Thread consumer1 = new Thread(() -> {
        try {
            System.out.println("Consumer1 is starting");
            System.out.println("Consumer1 do polls");
            sbq.poll();
            sbq.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    Thread consumer2 = new Thread(() -> {
        try {
            System.out.println("Consumer2 is starting");
            System.out.println("Consumer2 do polls");
            sbq.poll();
            sbq.poll();
            sbq.poll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    });

    @Test
    public void whenRunThreeThreadsThatAllWorksFine() throws InterruptedException {
        producer.start();
        consumer1.start();
        consumer2.start();
        producer.join();
        consumer1.join();
        consumer2.join();
    }
}