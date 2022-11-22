package LeetCode;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence,
 * called the Fibonacci sequence, such that each number is the sum
 * of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 *
 *
 * Example 2:
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 *
 *
 * Example 3:
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Constraints:
 * 0 <= n <= 30
 */
public class N509EFibonacciNumber {

    public static void main(String[] args) {

        doRun(0, 0);
        doRun(1, 1);
        doRun(1, 2);
        doRun(2, 3);
        doRun(3, 4);
    }


    static private void doRun(int expect, int n) {
        int res = new N509EFibonacciNumber().fib(n);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
    //Memory Usage: 39.1 MB, less than 90.78% of Java online submissions for Fibonacci Number.
    //Fibonacci formula | Binet's forumula
    //Time: O(logN); Space: O(1)
    public int fib(int N) {
        double sqrt5 = Math.sqrt(5);
        double goldenRatio = (1 + sqrt5) / 2;
        return (int) Math.round(Math.pow(goldenRatio, N) / sqrt5);
    }

    public int fib_Binet2(int n) {
        if(n <= 1) return n;
        double sqrt5 = Math.sqrt(5);
        double phi = (1 + sqrt5) / 2;
        double psi = (1 - sqrt5) / 2;
        //pow method takes nlogn time.
        return (int) ((Math.pow(phi, n) - Math.pow(psi, n)) / sqrt5);
    }

    //Matrix Exponentiation
    // Time: O(logN); Space:O(logN);
    int fib_ME(int N) {
        if (N <= 1) return N;

        int[][] A = new int[][]{{1, 1}, {1, 0}};
        matrixPower(A, N - 1);
        return A[0][0];
    }

    void matrixPower(int[][] A, int N) {
        if (N <= 1) return;

        matrixPower(A, N / 2);
        multiply(A, A);

        int[][] B = new int[][]{{1, 1}, {1, 0}};
        if (N % 2 != 0) multiply(A, B);
    }

    void multiply(int[][] A, int[][] B) {
        int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];

        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
    //Memory Usage: 41.3 MB, less than 22.07% of Java online submissions for Fibonacci Number.
    //iteration
    //Time:O(N); Space: O(1)
    public int fib_iteration(int n) {
        if(n <= 1) return n;
        int f0 = 1, f1 = 1;
        for (int i = 2; i <= n; i++)  f0 = (f1 += f0) - f0;
//        for (int i = 2; i <= n; i++) {
//            r = f0 + f1;
//            f0 = f1; f1 = r;
//        }
        return f0;
    }

    //Runtime: 11 ms, faster than 28.01% of Java online submissions for Fibonacci Number.
    //Memory Usage: 40.6 MB, less than 67.01% of Java online submissions for Fibonacci Number.
    //recursion
    //Time:O(2^N); Space: O(N)
    public int fib_recursion(int n) {
        if(n <= 1) return n;
        return fib_recursion(n - 1) + fib_recursion(n - 2);
    }
}

