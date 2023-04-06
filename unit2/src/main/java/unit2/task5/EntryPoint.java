package unit2.task5;

import unit2.task5.functional.NumberToBoolean;
import unit2.task5.functional.ThreeDoublesToDouble;
import unit2.task5.functional.TwoNumbersToSum;

public class EntryPoint {

    public static void main(String[] args) {
        NumberToBoolean numberToBoolean = (a) -> a % 13 == 0;
        System.out.println(numberToBoolean.execute(39)); // should return true
        System.out.println(numberToBoolean.execute(14)); // should return false
        ThreeDoublesToDouble threeDoublesToDouble = (a, b, c) -> b*b - 4*a*c;
        System.out.println(threeDoublesToDouble.execute(2,4,1));
        TwoNumbersToSum twoIntToSum = (a,b) -> ((Integer) a) + ((Integer) b);
        TwoNumbersToSum twoFloatsToSum = (a,b) -> ((Float) a) + ((Float) b);
        TwoNumbersToSum twoDoublesToSum = (a,b) -> ((Double) a) + ((Double) b);
        System.out.println(twoIntToSum.execute(1,2));
        System.out.println(twoFloatsToSum.execute(1.1f,2.6f));
        System.out.println(twoDoublesToSum.execute(1.1,2.6));
    }
}
