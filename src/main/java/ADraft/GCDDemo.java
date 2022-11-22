package ADraft;

public class GCDDemo {

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
        //return gcd(b, Math.abs(a - b));
    }

    public int gcd1(int a, int b) {
        // stores minimum(a, b)
        int i;
        if (a < b) i = a;
        else i = b;

        // take a loop iterating through smaller number to 1
        for (i = i; i > 1; i--) {

            // check if the current value of i divides both
            // numbers with remainder 0 if yes, then i is
            // the GCD of a and b
            if (a % i == 0 && b % i == 0)
                return i;
        }

        // if there are no common factors for a and b other
        // than 1, then GCD of a and b is 1
        return 1;
    }

    //取两个数中最小，循环递减，都满足整除则为最大公倍数
    public static int gcd_1(int a, int b) {
        int min = a < b ? a : b;
        int gcd = 1;
        for (int i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
                break;
            }
        }
        return gcd;
    }

    //辗转相除法- 递归实现
    public static int gcd_recursion(int a, int b) {
        if (b == 0) return a;
        else return gcd_recursion(b, a % b);
    }

    //辗转相除法 - while循环实现
    public static int gcd_iteration(int a, int b) {
        int c;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

//    //更相减损术 - 递归实现
//    public static int gcd_recursion_2(int a, int b) {
//        if (a == b) return a;
//        else if (a > b) a = a - b;
//        else b = b - a;
//        return gcd_recursion_2(a, b);
//    }
//
//    //更相减损术 - while循环实现
//    public static int gcd_iteration_2(int a, int b) {
//        while (a != b) {
//            if (a > b) a = a - b;
//            else  b = b - a ;
//        }
//        return a;
//    }

}
