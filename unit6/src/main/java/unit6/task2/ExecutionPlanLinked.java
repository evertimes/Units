package unit6.task2;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class ExecutionPlanLinked {

    @Param({"50000", "500000", "1000000"})
    public int iterations;

    List<Integer> list;

    @Setup(Level.Invocation)
    public void setUp() {
        list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            list.add(random.nextInt());
        }
    }
}