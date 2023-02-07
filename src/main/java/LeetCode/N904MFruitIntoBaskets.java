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
        int[] data;
        data  = new int[]{1,2,3,2,2};
        doRun(4, data);


        data  = new int[]{3,3,3,1,2,1,1,2,3,3,4};
        doRun(5, data);

        data  = new int[]{1,0,3,4,3};
        doRun(3, data);

    }

    static private void doRun(int expect, int[] nums) {
        int res = new N904MFruitIntoBaskets().totalFruit(nums);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4。
    public int totalFruit(int[] fruits) {
        int res = 0;
        int count = 0, count_second = 0;
        int first = 0, second = 0;
        for (int fruit : fruits) {
            count = fruit == first || fruit == second ? count + 1 : count_second + 1;

            if (fruit == second) {
                count_second = count_second + 1;
            } else {
                first = second;
                second = fruit;
                count_second = 1;
            }
            res = Math.max(res, count);
        }
        return res;
    }


    //3. array
    //Runtime: 9 ms, 89%; Memory: 51.9MB 44%
    //Time: O(N); Space: O(1)
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



    //2.slide window : maxsize window
    //Runtime: 58 ms, 49%; Memory: 51.4MB 72%
    //Time: O(N); Space:O(1)
    public int totalFruit_2(int[] fruits) {
        int left = 0, right = 0;
        int kSize = 2;
        Map<Integer, Integer> map = new HashMap<>();

        for (; right < fruits.length; right++){
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            if (map.size() > kSize) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                map.remove(fruits[left++], 0);
            }
        }
        return right - left;
    }


    //1.slide window : two pointer
    //Runtime: 58 ms, 49%; Memory: 52MB 44%
    //Time: O(N); Space:O(1)
    public int totalFruit_1(int[] fruits) {
        int res = 0, count = 0;
        int kSize = 2;
        Map<Integer, Integer> map = new HashMap<>();

        for (int left = 0, right = 0; right < fruits.length; right++){
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            count++;

            while (left < right && map.size() > kSize) {
                int c =  map.get(fruits[left]) - 1;
                map.put(fruits[left], c);
                map.remove(fruits[left], 0);
//                if (c <= 0) map.remove(fruits[left]);
//                else map.put(fruits[left], c);
                count--;
                left++;
            }
            res = Math.max(res, count);
        }
        return res;
    }


}
