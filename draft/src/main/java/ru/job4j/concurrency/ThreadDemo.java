package ru.job4j.concurrency;

public class ThreadDemo {

    public static void main(String[] args) {
        new AsyncOperations();

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main thread: iteration " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread is interrupted");
        }

        System.out.println("Main thread is finished");
    }

}
