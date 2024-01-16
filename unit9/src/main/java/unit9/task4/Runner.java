package unit9.task4;

import java.util.ArrayList;
import java.util.Collections;
import unit9.task4.sort.forkjoin.ForkJoinQuickSort;
import unit9.task4.sort.recursive.RecursiveQuickSort;
import unit9.task4.sort.thread.QuickSortThread;

public class Runner {

    public static void main(String[] args) {
        var forkJoinQuickSort = new ForkJoinQuickSort<Integer>();
        var recursiveQuickSort = new RecursiveQuickSort<Integer>();
        var threadQuickSort = new QuickSortThread<Integer>();

        System.out.println("FORK-JOIN QUICKSORT");
        var list = new ArrayList<Integer>();
        Collections.addAll(list, 3, 2, 4, 1, 15, 21, 234, 0, 5, 5);
        forkJoinQuickSort.sort(list);
        System.out.println(list);

        System.out.println("RECURSIVE QUICKSORT");
        list = new ArrayList<>();
        Collections.addAll(list, 3, 2, 4, 1, 15, 21, 234, 0, 5, 5);
        recursiveQuickSort.sort(list);
        System.out.println(list);

        System.out.println("THREAD QUICKSORT");
        list = new ArrayList<>();
        Collections.addAll(list, 3, 2, 4, 1, 15, 21, 234, 0, 5, 5);
        threadQuickSort.sort(list);
        System.out.println(list);
    }
}
