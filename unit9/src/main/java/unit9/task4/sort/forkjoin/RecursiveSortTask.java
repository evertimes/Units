package unit9.task4.sort.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecursiveSortTask<T extends Comparable<T>> extends RecursiveTask<List<T>> {

    private List<T> collection;
    private int left;
    private int right;

    @Override
    protected List<T> compute() {
        if (left < right) {
            int partitionIndex = 0;
            T pivot = collection.get(right);
            int i = (left - 1);

            for (int j = left; j < right; j++) {
                if (collection.get(j).compareTo(pivot) <= 0) {
                    i++;

                    T swapTemp = collection.get(i);
                    collection.set(i, collection.get(j));
                    collection.set(j, swapTemp);
                }
            }

            T swapTemp = collection.get(i + 1);
            collection.set(i + 1, collection.get(right));
            collection.set(right, swapTemp);

            partitionIndex = i + 1;

            int finalPartitionIndex = partitionIndex;

            var leftTask = new RecursiveSortTask<>(collection, left, finalPartitionIndex - 1);
            var rightTask = new RecursiveSortTask<>(collection, finalPartitionIndex + 1, right);

            leftTask.fork();
            rightTask.fork();

            leftTask.join();
            rightTask.join();

        }
        return null;
    }
}
