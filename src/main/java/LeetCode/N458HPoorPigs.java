package LeetCode;


import static java.time.LocalTime.now;

/**
 * There are buckets buckets of liquid, where exactly one of the buckets is poisonous.
 * To figure out which one is poisonous, you feed some number of (poor) pigs the liquid to see
 * whether they will die or not.
 * Unfortunately, you only have minutesToTest minutes to determine which bucket is poisonous.
 *
 * You can feed the pigs according to these steps:
 *
 * Choose some live pigs to feed.
 * For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets
 * simultaneously and will take no time.
 * Wait for minutesToDie minutes. You may not feed any other pigs during this time.
 * After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket will die,
 * and all others will survive.
 * Repeat this process until you run out of time.
 * Given buckets, minutesToDie, and minutesToTest, return the minimum number of pigs needed to figure out
 * which bucket is poisonous within the allotted time.
 *
 *
 *
 * Example 1:
 * Input: buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * Output: 5
 *
 *
 * Example 2:
 * Input: buckets = 4, minutesToDie = 15, minutesToTest = 15
 * Output: 2
 *
 *
 * Example 3:
 * Input: buckets = 4, minutesToDie = 15, minutesToTest = 30
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= buckets <= 1000
 * 1 <= minutesToDie <= minutesToTest <= 100
 */

/**
 * H - [time: 480+....
 *  - math 问题
 */
public class N458HPoorPigs {

    public static void main(String[] args) {

        System.out.println(now());
        doRun(2, 4, 15, 15);
        doRun(2, 4, 15, 30);

        doRun(3, 12, 15, 30);
        doRun(3, 13, 15, 30);


        doRun(5, 1000, 15, 60);

        doRun(2, 8, 15, 30);

        doRun(0, 1, 1, 1);

        System.out.println(now());
    }

    static private void doRun(int expect, int buckets, int minutesToDie, int minutesToTest) {
        int res = new N458HPoorPigs().poorPigs(buckets, minutesToDie, minutesToTest);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //这个比较好理解
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Poor Pigs.
    //Memory Usage: 40.7 MB, less than 72.80% of Java online submissions for Poor Pigs.
    //编码【就是混喝】[横向 放桶, 纵向 放pig] -- 举例：每一次测试时 ： 2只pigs可以处理4个buckets; 3只可以8个buckets... 2的N次方。
    //（这里的2是指个状态，当状态变多时。就是 x 的 N次方）往多维度扩展时（就是多次测试时），就把2扩展成x.
    // 多轮测试，就是往多维度进行扩展
    //单个pig 测试n轮， 就最多会有  n+1 种状态。
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {

        int states = minutesToTest / minutesToDie + 1;

        int pigs = 0;
        int maxBuckets = 1;

        while (maxBuckets < buckets) {
            maxBuckets *= states;
            pigs++;
        }
        return pigs;

        // 与以上逻辑 等价。
        //return (int)Math.ceil(Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1));
    }



    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Poor Pigs.
    //Memory Usage: 41 MB, less than 39.20% of Java online submissions for Poor Pigs.
    public int poorPigs_1(int buckets, int minutesToDie, int minutesToTest) {

        int states = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(states));

        //return (int)Math.ceil(Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1));
    }


}
