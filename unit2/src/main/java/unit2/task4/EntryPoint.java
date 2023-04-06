package unit2.task4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import unit2.task4.office_supplies.Notepad;
import unit2.task4.office_supplies.OfficeSupply;
import unit2.task4.office_supplies.Pen;
import unit2.task4.office_supplies.Scissors;

public class EntryPoint {

    public static void main(String[] args) {
        List<OfficeSupply> beginnersKit = new ArrayList<>();
        beginnersKit.add(new Scissors("Scissors", 15.0));
        beginnersKit.add(new Pen("Pen", 5.0));
        beginnersKit.add(new Pen("Pencil", 5.0));
        beginnersKit.add(new Notepad("Notepad", 10.0));

        System.out.println("Initial collection:");
        beginnersKit.forEach(System.out::println);

        beginnersKit.sort(Comparator.comparingDouble(OfficeSupply::getCost));
        System.out.println("After price sorting");
        beginnersKit.forEach(System.out::println);

        beginnersKit.sort(Comparator.comparing(OfficeSupply::getName));
        System.out.println("After name sorting");
        beginnersKit.forEach(System.out::println);

        beginnersKit.sort(Comparator.comparingDouble(OfficeSupply::getCost).thenComparing(OfficeSupply::getName));
        System.out.println("After price and name sorting");
        beginnersKit.forEach(System.out::println);
    }
}
