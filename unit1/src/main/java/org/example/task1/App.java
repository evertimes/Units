package org.example.task1;

public class App {

    private static final int ARRAY_SIZE = 10;

    public static void main(String[] args) {
        if (ARRAY_SIZE <= 0) {
            System.out.println("Array size should be non-zero");
            return;
        }
        System.out.println("Unit 1 Task 1");
        System.out.println("With for loop");
        withFor(ARRAY_SIZE);
        System.out.println("\n");
        System.out.println("With while loop");
        withWhile(ARRAY_SIZE);
        System.out.println("\n");
        System.out.println("With do while loop");
        withDoWhile(ARRAY_SIZE);
    }

    public static void withFor(int size) {
        System.out.println("Creating array..");
        int[] arr = new int[size];
        StringBuilder initialArray = new StringBuilder();
        for (int i = 0; i < size; i++) {
            arr[i] = (i + 2) * 2;
            initialArray.append(arr[i]).append(" ");
        }
        System.out.println("Created array:");
        System.out.println(initialArray);
        System.out.println("Transformed array:");
        StringBuilder transformedArray = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if ((i + 1) % 2 == 0) {
                arr[i] *= arr[i - 1];
            }
            transformedArray.append(arr[i]).append(" ");
        }
        System.out.println(transformedArray);
    }

    public static void withWhile(int size) {
        System.out.println("Creating array..");
        int[] arr = new int[size];
        StringBuilder initialArray = new StringBuilder();
        int i = 0;
        while (i < size) {
            arr[i] = (i + 2) * 2;
            initialArray.append(arr[i]).append(" ");
            i++;
        }
        System.out.println("Created array:");
        System.out.println(initialArray);
        System.out.println("Transformed array:");
        StringBuilder transformedArray = new StringBuilder();
        i = 0;
        while (i < size) {
            if ((i + 1) % 2 == 0) {
                arr[i] *= arr[i - 1];
            }
            transformedArray.append(arr[i]).append(" ");
            i++;
        }
        System.out.println(transformedArray);
    }

    public static void withDoWhile(int size) {
        System.out.println("Creating array..");
        int[] arr = new int[size];
        StringBuilder initialArray = new StringBuilder();
        int i = 0;
        do {
            arr[i] = (i + 2) * 2;
            initialArray.append(arr[i]).append(" ");
            i++;
        } while (i < size);
        System.out.println("Created array:");
        System.out.println(initialArray);
        System.out.println("Transformed array:");
        StringBuilder transformedArray = new StringBuilder();
        i = 0;
        do {
            if ((i + 1) % 2 == 0) {
                arr[i] *= arr[i - 1];
            }
            transformedArray.append(arr[i]).append(" ");
            i++;
        } while (i < size);
        System.out.println(transformedArray);
    }

}
