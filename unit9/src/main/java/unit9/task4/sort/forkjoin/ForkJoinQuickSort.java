package unit9.task4.sort.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import lombok.AllArgsConstructor;
import unit9.task4.sort.QuickSort;

@AllArgsConstructor
public class ForkJoinQuickSort<T extends Comparable<T>> implements QuickSort<T> {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    @Override
    public void sort(List<T> collection) {
        forkJoinPool.invoke(new RecursiveSortAction<>(collection, 0, collection.size() - 1));
    }
}
