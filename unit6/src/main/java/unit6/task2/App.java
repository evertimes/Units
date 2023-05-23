package unit6.task2;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

public class App {
    //Результат с пояснениями находится в папке resources

    static Random random = new Random();

    public static void main(String[] args) throws IOException {
        Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void addEndArray(ExecutionPlanArray executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.add(random.nextInt()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void addMiddleArray(ExecutionPlanArray executionPlan, Blackhole blackhole) {
        executionPlan.list.add(executionPlan.list.size() / 2, random.nextInt());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void getArray(ExecutionPlanArray executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.get(random.nextInt() % executionPlan.list.size()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void containsArray(ExecutionPlanArray executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.contains(random.nextInt()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void removeValueArray(ExecutionPlanArray executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.remove((Integer) random.nextInt()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void removeIndexArray(ExecutionPlanArray executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.remove(random.nextInt() % executionPlan.list.size()));
    }

    /* Linked */

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void addEndLinked(ExecutionPlanLinked executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.add(random.nextInt()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void addMiddleLinked(ExecutionPlanLinked executionPlan, Blackhole blackhole) {
        executionPlan.list.add(executionPlan.list.size() / 2, random.nextInt());
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void getLinked(ExecutionPlanLinked executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.get(random.nextInt() % executionPlan.list.size()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void containsLinked(ExecutionPlanLinked executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.contains(random.nextInt()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void removeValueLinked(ExecutionPlanLinked executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.remove((Integer) random.nextInt()));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Fork(value = 3, warmups = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void removeIndexLinked(ExecutionPlanLinked executionPlan, Blackhole blackhole) {
        blackhole.consume(executionPlan.list.remove(random.nextInt() % executionPlan.list.size()));
    }


}
