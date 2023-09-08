package unit9.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCounter {

    private AtomicInteger atomicInteger;

    public int getValue() {
        return atomicInteger.get();
    }

    public int increment() {
        return atomicInteger.incrementAndGet();
    }
}
