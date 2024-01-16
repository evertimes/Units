package unit9.task2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    public static void main(String[] args) {
        var firstAccount = new Account(100, "acc1");
        var secondAccount = new Account(100, "acc2");

        AccountThreadV2 thread = new AccountThreadV2(firstAccount, secondAccount, 1);
        AccountThreadV2 thread1 = new AccountThreadV2(secondAccount, firstAccount, 1);

        CompletableFuture.allOf(
            CompletableFuture.runAsync(thread),
            CompletableFuture.runAsync(thread1)
        ).join();
    }
}
