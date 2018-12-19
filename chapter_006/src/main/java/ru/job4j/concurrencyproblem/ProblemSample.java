package ru.job4j.concurrencyproblem;

/**
 * Class ProblemSample | Task Solution: Illustrate concurrency problems [#1096]
 * @author @author Aleksey Sidorenko (mailto:sidorenko.aleksey@gmail.com)
 * @since 19.12.2018
 */
public class ProblemSample implements Runnable {

    private final String name;

    /**
     * Constructor.
     */
    public ProblemSample(final String name) {
        this.name = name;
    }

    @Override
    public void run() {
        method();
    }

    /**
     * Increment shared object field.
    */
    private void method() {
        for (int i = 0; i < 10000; i++) {
            Shared.count++;
        }
    }

    public static void main(String[] args) {
        new Thread(new ProblemSample("Thread 1")).start();
        new Thread(new ProblemSample("Thread 2")).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Shared.count);
    }
}