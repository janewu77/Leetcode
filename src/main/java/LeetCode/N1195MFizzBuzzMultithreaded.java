package LeetCode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 *
 * Write a program that outputs the string representation of numbers from 1 to n, however:
 *
 * If the number is divisible by 3, output "fizz".
 * If the number is divisible by 5, output "buzz".
 * If the number is divisible by both 3 and 5, output "fizzbuzz".
 * For example, for n = 15, we output: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
 *
 * Suppose you are given the following code:
 *
 * class FizzBuzz {
 *   public FizzBuzz(int n) { ... }               // constructor
 *   public void fizz(printFizz) { ... }          // only output "fizz"
 *   public void buzz(printBuzz) { ... }          // only output "buzz"
 *   public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
 *   public void number(printNumber) { ... }      // only output the numbers
 * }
 * Implement a multithreaded version of FizzBuzz with four threads.
 * The same instance of FizzBuzz will be passed to four different threads:
 *
 * Thread A will call fizz() to check for divisibility of 3 and outputs fizz.
 * Thread B will call buzz() to check for divisibility of 5 and outputs buzz.
 * Thread C will call fizzbuzz() to check for divisibility of 3 and 5 and outputs fizzbuzz.
 * Thread D will call number() which should only output the numbers.
 *
 *
 * Jane'要Note：
 * 1。当15的倍数时，要跳过3、5；
 *
 */
public class N1195MFizzBuzzMultithreaded {

    //Runtime: 7 ms, faster than 98.24% of Java online submissions for Fizz Buzz Multithreaded.
    //Memory Usage: 39.2 MB, less than 55.36% of Java online submissions for Fizz Buzz Multithreaded.
    class FizzBuzz {
        private int n;

        private Semaphore semaphore;
        private Semaphore semaphore3;
        private Semaphore semaphore5;
        private Semaphore semaphore15;

        public FizzBuzz(int n) {
            semaphore = new Semaphore(0);
            semaphore3 = new Semaphore(0);
            semaphore5 = new Semaphore(0);
            semaphore15 = new Semaphore(0);

            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {

            //for 跳过不必要的step
            for(int i=3;i<=n;i+=3){
                if(i%15 == 0){continue;}
                semaphore3.acquire();
                printFizz.run();
                semaphore.release();
            }

        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {

            for(int i=5;i<=n;i+=5){
                if(i%15 == 0){continue;}
                semaphore5.acquire();
                printBuzz.run();
                semaphore.release();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

            for(int i=15;i<=n;i+=15){
                semaphore15.acquire();
                printFizzBuzz.run();
                semaphore.release();
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for(int i=1;i<=n;i++){

                if( i%3!=0 && i%5!=0 ){
                    printNumber.accept(i);
                }else{

                    if(i%15 == 0)
                        semaphore15.release();
                    else if(i%5==0)
                        semaphore5.release();
                    else
                        semaphore3.release();

                    semaphore.acquire();
                }
            }//end for

        }
    }
}
