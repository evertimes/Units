package unit9.task3.type1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;

public class Leg implements Runnable {

    private final String name;
    private BlockingQueue<String> in;
    private BlockingQueue<String> out;

    public Leg(String name, BlockingQueue<String> in, BlockingQueue<String> out) {
        this.name = name;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            try {
                in.take();
                System.out.println(name);
                out.put("");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> first = new LinkedBlockingDeque(1);
        BlockingQueue<String> second = new LinkedBlockingDeque(1);
        first.put("");
        CompletableFuture.allOf(
            CompletableFuture.runAsync(new Leg("left", first, second)),
            CompletableFuture.runAsync(new Leg("right", second, first))
        ).join();
    }
}