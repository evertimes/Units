package unit9.task3.type2;

import java.util.concurrent.CompletableFuture;

public class Leg implements Runnable {

    private final String name;
    private static final Object flag = new Object();

    public Leg(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Leg.class) {
                System.out.println(name);
            }

            synchronized (flag) {
                flag.notifyAll();
                try {
                    flag.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.allOf(
            CompletableFuture.runAsync(new Leg("left")),
            CompletableFuture.runAsync(new Leg("right"))
        ).join();
    }
}