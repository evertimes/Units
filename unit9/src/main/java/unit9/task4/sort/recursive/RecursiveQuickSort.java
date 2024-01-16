package unit9.task4.sort.recursive;

import java.util.List;
import unit9.task4.sort.QuickSort;

public class RecursiveQuickSort<T extends Comparable<T>> implements QuickSort<T> {

    private void quickSort(List<T> collection, int left, int right) {
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

            quickSort(collection, left, partitionIndex - 1);
            quickSort(collection, partitionIndex + 1, right);
        }
    }

    @Override
    public void sort(List<T> collection) {
        quickSort(collection, 0, collection.size() - 1);
    }
}
