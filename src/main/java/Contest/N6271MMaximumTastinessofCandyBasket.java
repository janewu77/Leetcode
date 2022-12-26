package Contest;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;


/**
 * You are given an array of positive integers price where price[i] denotes the price of the ith candy and a positive integer k.
 *
 * The store sells baskets of k distinct candies. The tastiness of a candy basket is the smallest absolute difference of the prices of any two candies in the basket.
 *
 * Return the maximum tastiness of a candy basket.
 *
 *
 *
 * Example 1:
 *
 * Input: price = [13,5,1,8,21,2], k = 3
 * Output: 8
 * Explanation: Choose the candies with the prices [13,5,21].
 * The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
 * It can be proven that 8 is the maximum tastiness that can be achieved.
 * Example 2:
 *
 * Input: price = [1,3,1], k = 2
 * Output: 2
 * Explanation: Choose the candies with the prices [1,3].
 * The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
 * It can be proven that 2 is the maximum tastiness that can be achieved.
 * Example 3:
 *
 * Input: price = [7,7,7,7], k = 2
 * Output: 0
 * Explanation: Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
 *
 *
 * Constraints:
 *
 * 1 <= price.length <= 105
 * 1 <= price[i] <= 109
 * 2 <= k <= price.length
 */
//2517. Maximum Tastiness of Candy Basket
public class N6271MMaximumTastinessofCandyBasket {



    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");
        doRun(19, new int[]{34,116,83,15,150,56,69,42,26}, 6);
        doRun(8, new int[]{13,5,1,8,21,2}, 3);
        doRun(2, new int[]{1,3,1}, 2);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] price, int k) {
        int res = new N6271MMaximumTastinessofCandyBasket().maximumTastiness(price, k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1.Binary search
    //Runtime: 55ms 100%; Memory: 52MB 100%
    //Time: O(N * logN); Space: O(1)
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int low = 1, high = price[price.length - 1] - price[0];
        while (low <= high) {
            int mid = (high + low) >> 1;

            if (isPossilbe(price, k, mid)){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    //Time: O(N)
    private boolean isPossilbe(int[] price, int k, int mid){
        int value = price[0] + mid;
        k--;
        for (int i = 1; i < price.length && k > 0; i++) {
            if (price[i] >= value){
                value = price[i] + mid;
                k--;
            }
        }
        return k == 0;
    }

}


