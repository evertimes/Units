package org.example.task3;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        printMinMaxArray(5, 8);
    }

    public static void printMinMaxArray(int n, int m) {
        int[][] randomArray = new int[n][m];
        Random random = new Random();
        System.out.println("Initial generated array: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                randomArray[i][j] = random.nextInt(100);
                System.out.print(randomArray[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        int[][] minMaxArray = new int[5][2];
        System.out.println("MinMax array: ");
        for (int i = 0; i < n; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (randomArray[i][j] > max) {
                    max = randomArray[i][j];
                }
                if (randomArray[i][j] < min) {
                    min = randomArray[i][j];
                }
            }
            minMaxArray[i][0] = min;
            minMaxArray[i][1] = max;
            System.out.printf("%d\t%d\n", minMaxArray[i][0], minMaxArray[i][1]);
        }
    }

}
