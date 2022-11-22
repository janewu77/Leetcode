package LeetCode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

import static java.lang.Thread.sleep;


/**
 *
 * Suppose you are given the following code:
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer.
 * Modify the given program to output the series 010203040506... where the length of the series must be 2n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: "0102"
 * Explanation: There are three threads being fired asynchronously.
 * One of them calls zero(), the other calls even(),
 * and the last one calls odd(). "0102" is the correct output.
 *
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: "0102030405"
 *
 */

/**
 *  * M
 *  * 多线程概念：
 *  *  - Semaphore
 *   - 用volatile也可以，但似乎Semaphore性能更好一些
 */
public class N1116MPrintZeroEvenOdd {

    class ZeroEvenOdd_volatile {
        private int n;

        volatile private byte mark = 0;

        public ZeroEvenOdd_volatile(int n) {
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                while (mark != 0) sleep(0);
                printNumber.accept(0);
                mark =  (0 == i%2) ? (byte)1 : (byte)2;
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i+=2) {
                while(mark != 2) sleep(0);
                printNumber.accept(i);
                mark = 0;
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i+=2) {
                while(mark != 1) sleep(0);
                printNumber.accept(i);
                mark = 0;
            }
        }

    }

    //7 ms
    //38.4 MB
    class ZeroEvenOdd {
        private int n;

        private Semaphore zeroSemaphore;

        private Semaphore evenSemaphore;
        private Semaphore oddSemaphore;

        // private BlockingQueue<Byte> zeroQueue = new ArrayBlockingQueue(1);
        // volatile private byte markEven = 0;
        // volatile private byte markOdd = 0;

        public ZeroEvenOdd(int n) {
            zeroSemaphore = new Semaphore(1);
            evenSemaphore = new Semaphore(0);
            oddSemaphore = new Semaphore(0);

            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                zeroSemaphore.acquire();
                printNumber.accept(0);
//                n = 0 == i%2 ? 2:1;
                (( 0 == i%2 ) ? oddSemaphore : evenSemaphore).release();
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i+=2) {
                evenSemaphore.acquire();
                printNumber.accept(i);
                zeroSemaphore.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i+=2) {
                oddSemaphore.acquire();
                printNumber.accept(i);
                zeroSemaphore.release();
            }
        }

    }
}
