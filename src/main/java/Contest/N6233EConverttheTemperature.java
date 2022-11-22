package Contest;

import java.io.IOException;

import static java.time.LocalTime.now;

/**
 * You are given a non-negative floating point number rounded to two decimal places celsius, that denotes the temperature in Celsius.
 *
 * You should convert Celsius into Kelvin and Fahrenheit and return it as an array ans = [kelvin, fahrenheit].
 *
 * Return the array ans. Answers within 10-5 of the actual answer will be accepted.
 *
 * Note that:
 *
 * Kelvin = Celsius + 273.15
 * Fahrenheit = Celsius * 1.80 + 32.00
 *
 *
 * Example 1:
 *
 * Input: celsius = 36.50
 * Output: [309.65000,97.70000]
 * Explanation: Temperature at 36.50 Celsius converted in Kelvin is 309.65 and converted in Fahrenheit is 97.70.
 * Example 2:
 *
 * Input: celsius = 122.11
 * Output: [395.26000,251.79800]
 * Explanation: Temperature at 122.11 Celsius converted in Kelvin is 395.26 and converted in Fahrenheit is 251.798.
 *
 *
 * Constraints:
 *
 * 0 <= celsius <= 1000
 */


//2469. Convert the Temperature
public class N6233EConverttheTemperature {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");
//
//        System.out.println(a);
//        System.out.println(A);
//        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int n) {
//        long res = new C0806().n1(n);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert the Temperature.
    //Memory Usage: 42.8 MB, less than 33.33% of Java online submissions for Convert the Temperature.
    public double[] convertTemperature(double celsius) {
        return new double[]{celsius +273.15, celsius * 1.80 + 32.00};
    }

}


