package unit9.task4.sort;

import java.util.List;

public interface QuickSort<T extends Comparable<T>> {
    void sort(List<T> collection);
}
