package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 *
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 *
 * You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every
 * tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 *
 *
 * Example 1:
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.
 *
 *
 * Example 2:
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].
 *
 *
 * Example 3:
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 *
 *
 * Constraints:
 *
 * 1 <= fruits.length <= 105
 * 0 <= fruits[i] < fruits.length
 *
 */

/**
 * M - [time: 20-
 *  - slide window。 （two pointers slide-window & maxsize slide-window)
 *  - 代码如何写得更简洁、优雅？
 */
public class N904MFruitIntoBaskets {


    public static void main(String[] args) {

        int[] data  = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        doRun(5, data);

        data  = new int[]{1,0,3,4,3};
        doRun(3, data);

    }

    static private void doRun(int expect, int[] nums) {
        int res = new N904MFruitIntoBaskets().totalFruit(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //网上看来的。同样是写死。 但写得更简洁、精炼
    public int totalFruit(int[] fruits) {
        int res = 0;
        int count = 0, count_second = 0;
        int first = 0, second = 0;
        for (int fruit: fruits){
            count = fruit == first || fruit == second ? count + 1 : count_second + 1;
            count_second = fruit == second ? count_second + 1 : 1;
            if (second != fruit) {
                first = second;
                second = fruit;
            }
            res = Math.max(res, count);
        }
        return res;
    }


    //Runtime: 11 ms, faster than 83.99% of Java online submissions for Fruit Into Baskets.
    //Memory Usage: 77.6 MB, less than 65.36% of Java online submissions for Fruit Into Baskets.
    //纯为了性能. 写死了K=2. 扩展性并不好。
    public int totalFruit_3(int[] fruits) {
        int res = 0;
        int left = 0, right = 0;

        int[] firstBasket = new int[]{-1, 0};
        int[] secondBasket = new int[]{-1, 0};

        int[] tmp;
        for (right = 0; right < fruits.length; right++) {
            //picked
            if (fruits[right] == firstBasket[0] || fruits[right] == secondBasket[0]) {
                tmp = fruits[right] == firstBasket[0] ? firstBasket : secondBasket;
                tmp[1]++;
                res = Math.max(res, firstBasket[1] + secondBasket[1]);
                continue;
            }

            while (firstBasket[0] >= 0 && secondBasket[0] >= 0) {
                //a new type fruit
                tmp = (fruits[left] == firstBasket[0]) ? firstBasket : secondBasket;
                tmp[1]--;
                if (tmp[1] == 0) tmp[0] = -1;
                left++;
            }

            //have an empty baskets
            tmp = firstBasket[0] < 0 ? firstBasket : secondBasket;
            tmp[0] = fruits[right];
            tmp[1]++;
            res = Math.max(res, firstBasket[1] + secondBasket[1]);

        }
        return res;
    }

    //Runtime: 58 ms, faster than 66.21% of Java online submissions for Fruit Into Baskets.
    //Memory Usage: 51.5 MB, less than 94.19% of Java online submissions for Fruit Into Baskets.
    //maxsize window
    public int totalFruit_2(int[] fruits) {
        int left = 0, right = 0;
        int kSize = 2;

        Map<Integer, Integer> map = new HashMap<>();

        for (right = 0; right < fruits.length; right++){
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            //这里不用while的话，窗口将一直保持最大值。直到有新的最大值出现。最后只要 right - left 就能得到结果。
            if (map.size() > kSize) {
                map.put(fruits[left], map.get(fruits[left]) - 1); //这里进行减， 相当于进一个减一个。这样就让window一直保持当前最大size
                map.remove(fruits[left++], 0); //这个写法很简洁。
            }
        }

        return right - left; //用窗口size即可。
    }


    //性能不怎么好，如何改？
    //Runtime: 70 ms, faster than 58.76% of Java online submissions for Fruit Into Baskets.
    //Memory Usage: 102.8 MB, less than 53.29% of Java online submissions for Fruit Into Baskets.
    //two pointer slide-window
    //Time: O(N); Space:O(2)
    public int totalFruit_1(int[] fruits) {
        int res = 0, count = 0;
        int left = 0, right = 0;
        int kSize = 2;

        Map<Integer, Integer> map = new HashMap<>();

        for (right = 0; right < fruits.length; right++){

            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            count++;

            while (map.size() > kSize && left < right) {

                int tmp =  map.get(fruits[left]) - 1;
                if (tmp <= 0) map.remove(fruits[left]);
                else map.put(fruits[left], tmp);

                count--;
                left++;
            }
            res = Math.max(res, count);
        }

        return res;
    }
}
