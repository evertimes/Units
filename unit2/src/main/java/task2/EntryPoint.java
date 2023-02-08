package task2;

import java.util.ArrayList;
import java.util.List;
import task2.office_supplies.Notepad;
import task2.office_supplies.OfficeSupply;
import task2.office_supplies.Pen;
import task2.office_supplies.Scissors;

public class EntryPoint {

    public static void main(String[] args) {
        List<OfficeSupply> beginnersKit = new ArrayList<>();
        beginnersKit.add(new Scissors("Scissors",15.0));
        beginnersKit.add(new Notepad("Notepad", 10.0));
        beginnersKit.add(new Pen("Pen", 5.0));

        beginnersKit.forEach(System.out::println);
    }
}
