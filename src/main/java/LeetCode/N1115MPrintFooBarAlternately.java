package LeetCode;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 *
 *
 * The same instance of FooBar will be passed to two different threads:
 *
 * thread A will call foo(), while
 * thread B will call bar().
 * Modify the given program to output "foobar" n times.
 *
 *
 *
 * Example 1:
 * Input: n = 1
 * Output: "foobar"
 * Explanation: There are two threads being fired asynchronously.
 * One of them calls foo(), while the other calls bar().
 * "foobar" is being output 1 time.
 *
 *
 * Example 2:
 * Input: n = 2
 * Output: "foobarfoobar"
 * Explanation: "foobar" is being output 2 times.
 *
 *
 * Constraints:
 * 1 <= n <= 1000
 *
 */

/**
 * M
 * 多线程概念：
 *  - concurrent、ArrayBlockingQueue、
 *  - Semaphore
 *  - 注意 sleep(0)
 */

public class N1115MPrintFooBarAlternately {

    //Runtime: 17 ms, faster than 99.58% of Java online submissions for Print FooBar Alternately.
    //Memory Usage: 39.3 MB, less than 49.32% of Java online submissions for Print FooBar Alternately.
    class FooBar {
        private int n;

        //这里的permits可简单理解已经有permits张令牌。
        private Semaphore fooSemaphore = new Semaphore(1,true);
        private Semaphore barSemaphore = new Semaphore(0,true);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                fooSemaphore.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barSemaphore.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                barSemaphore.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooSemaphore.release();

            }
        }
    }

    //Runtime: 17 ms, faster than 99.58% of Java online submissions for Print FooBar Alternately.
    //Memory Usage: 39 MB, less than 83.14% of Java online submissions for Print FooBar Alternately.
    class FooBar2 {
        private int n;

        //capacity为1仅表示容量。当add后，才会有真正的内容；take是取走内容。
        private BlockingQueue<Byte> fooQueue = new ArrayBlockingQueue(1);
        private BlockingQueue<Byte> barQueue = new ArrayBlockingQueue(1);

        final static private byte mark = 0;

        public FooBar2(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                barQueue.add(mark);
                fooQueue.take();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                barQueue.take();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooQueue.add(mark);

            }
        }
    }

    /**
     * Runtime: 17 ms
     * Memory Usage: 39.3 MB
     */
    class FooBar1 {
        private int n;

        private volatile byte mark = 0;

        public FooBar1(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while(mark != 0){sleep(0);}
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                mark = 1;
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                //这里一定要sleep(0)
                while(mark != 1){sleep(0);}
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                mark = 0;
            }
        }
    }
}
