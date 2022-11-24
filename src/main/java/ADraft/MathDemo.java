package ADraft;

import java.util.ArrayList;
import java.util.List;

public class MathDemo {


    public static void main(String[] args){
        boolean res;
        res = Math.round((float)16 / 17) == 1;
        System.out.println("["+res +"]Math.round((float)16 / 17) == 1");


        res = Math.ceil(0.95) == 1;
        System.out.println("["+res +"]Math.ceil(0.95) == 1" );

        res = Math.ceil(5) == 5;
        System.out.println("["+res +"]Math.ceil(5) == 5" );

        res = Math.ceil(-7.03) == -7;
        System.out.println("["+res +"]Math.ceil(-7.03) == -7" );

        res = Math.floor(1.95) == 1;
        System.out.println("["+res +"]Math.floor(1.95) == 1" );

        res = Math.floor(5) == 5;
        System.out.println("["+res +"]Math.floor(5) == 5" );

        res = Math.floor(-7.03) == -8;
        System.out.println("["+res +"]Math.floor(-7.03) == -8" );

        primeDecompose(12);
    }


    //乘积因子分解
    static List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<Integer>();
        for (int factor = 2; factor < Math.sqrt(num); factor++) {
            if (num % factor == 0) {
                for (int i = 0; i < num / factor; i++) {
                    primeFactors.add(factor);
                    num /= factor;
                }
            }
        }
        primeFactors.add(num);
        return primeFactors;
    }
}