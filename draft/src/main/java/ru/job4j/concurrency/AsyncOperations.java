package ru.job4j.concurrency;

public class AsyncOperations implements Runnable {

    private Thread t;

    AsyncOperations() {
        t = new Thread(this, "Demo thread");
        System.out.println("Child thread created " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("-----Child thread: iteration " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child thread is interrupted");
        }
    }
}
