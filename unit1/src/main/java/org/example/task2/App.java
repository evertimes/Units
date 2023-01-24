package org.example.task2;

public class App {

    public static void main(String[] args) {
        calculate(0, 10, 1.1);
    }

    public static void calculate(double a, double b, double h) {
        TableBuilder tableBuilder = new TableBuilder(20);
        tableBuilder.addRowSeparator();
        tableBuilder.addRow("x", "f(x)");
        tableBuilder.addRowSeparator();
        for (double x = a; x <= b; x += h) {
            tableBuilder.addRow(x, f(x));
            tableBuilder.addRowSeparator();
        }
        System.out.println(tableBuilder.build());
    }

    public static double f(double x) {
        return Math.tan(2 * x) - 3;
    }
}
