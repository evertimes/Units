package unit1.task4;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        solveQuadraticEquation();
    }

    private static void solveQuadraticEquation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter a: ");
        int a = scanner.nextInt();
        System.out.println("Please, enter b: ");
        int b = scanner.nextInt();
        System.out.println("Please, enter c: ");
        int c = scanner.nextInt();
        int d = b * b - 4 * a * c;
        if (d < 0) {
            System.out.println("The equation cannot be solved in real numbers");
            return;
        }
        double x1 = (double) (-b + d) / (2 * a);
        double x2 = (double) (-b - d) / (2 * a);
        System.out.printf("First root x1=%f,second root x2=%f\n", x1, x2);
    }
}
