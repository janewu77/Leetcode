package LeetCode;


import java.util.*;

/**
 * Given an integer array nums and an integer k, return the length of the shortest non-empty
 * subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 *
 * A subarray is a contiguous part of an array.
 * 注意，这里有负数出现
 *
 *
 * Example 1:
 * Input: nums = [1], k = 1
 * Output: 1
 *
 * Example 2:
 * Input: nums = [1,2], k = 4
 * Output: -1
 *
 * Example 3:
 * Input: nums = [2,-1,2], k = 3
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -105 <= nums[i] <= 105
 * 1 <= k <= 109
 *
 *
 */
//If someone has problem in understanding the intuition behind this question, below is the intution:
//1)What is the problem that we have to solve?
//Ans - find smallest subarray with atleast sum == k.
//2) What if there were only postive numbers?
//Ans - I can solve it using two pointer approach directly
//3) What problem negative numbers have induced?
//Ans - I am not sure if reducing window will increase or decrease the sum between two pointers.
//4) How cumulative sum solved the problem?
//Ans - I was poping from last if current cumulative is lesser or equal. Because I want to know the ans including
// all the numbers and excluding numbers when I saw a negative number.
/**
 *
 * H - [Time - 240+
 *  - 解法：遍历比较sum差值。
 *  - 自己没有做出来。在看了solution后，用bruteforce解法，能推导出类似的算法。
 *  - 自我反省：
 *  - 1。没有想到用sum去互减, 一直停留在原来用"累加"的思路上。
 *  - 2。看过在看了solution后, 一直没有明白原因。 直到用bruteforce 重新解。
 *  - 3。在solution里面，看到一个非常棒，值得学习的思路。他用一步一个问题，将思路引导至deque上......
 *
 *
 *
 * 1358.Number of Substrings Containing All Three Characters
 * 1248.Count Number of Nice Subarrays
 * 1234.Replace the Substring for Balanced String
 * 1004Max Consecutive Ones III
 * 930.Binary Subarrays With Sum
 * 992.Subarrays with K Different Integers
 * 904.Fruit Into Baskets
 * 862.Shortest Subarray with Sum at Least K
 * 209.Minimum Size Subarray Sum
 */
public class N862MShortestSubarraywithSumatLeastK {

    public static void main(String[] args) {
        int[] data;

        data  = new int[]{1};
        doRun(1, data, 1);

        data  = new int[]{1,2};
        doRun(-1, data, 4);

        data  = new int[]{2, -1, 2};
        doRun(3, data, 3);

        data  = new int[]{-2, -1, -2};
        doRun(-1, data, 3);

        data  = new int[]{9,-1,4,2,-1,9,8};
        doRun(4, data, 18);

        data  = new int[]{9,-1,2};
        doRun(1, data, 8);

        data  = new int[]{80618,20594,-6181,9181,65796,-25716,66593,-6873,34062,29878,852,-4767,-36415,11783,8085,-41063,-39940,74284,66272,82385,51634,-48550,9028,-30777,86509,44623,9413,-38369,-1822,46408,35217,72635,-16560,85373,52105,39477,3852,45974,-21593,15481,47280,73889,-42981,54978,95169,-19615,93133};
        doRun(11, data, 387303);

        data  = new int[]{-2, -1, -2, 2, -1, 2};
        doRun(3, data, 3);

        data  = new int[]{84,-37,32,40,95};
        doRun(3, data, 167);

        data  = new int[]{-36,10,-28,-42,17,83,87,79,51,-26,33,53,63,61,76,34,57,68,1,-30};
        doRun(9, data, 484);
    }

    static private void doRun(int expected, int[] nums, int k) {
        int res = new N862MShortestSubarraywithSumatLeastK().shortestSubarray(nums, k);
        System.out.println("["+(expected == res)+"]expect:" + expected + " res:" + res);
    }

    //比较二个和，如果差值大于等于target了。就通过坐标计算他们间的长度，然后找出最小长度。
    // 具体做题时思路： 这里的比较， 相当于每次从头开始逐一比较，找到差值满足target的。
    // >>> 直接上list, 每次都遍历一遍。运行成功。但超时!!!
    // >>> 所以就想到, 缩短list, 把不用的点移掉。
    // >>> 所以改用queue.[FIFO]. 从头部，不需要的点移掉。
    // >>> 然后发现有些点，无法触及!!! 是有些"大点"拦在了前面。
    // >>> 所以，要先移走"大点". (这时，也可用双queue来做，但每次"大点"会一直保存着，在二个queue里来回倒腾)
    // >>> 所以，先移大点。（只要比自己大的，在自己加入的那一刻，从后往前移掉)
    // >>> 要同时操作last。 所以，改成Deque. 再加一个"去点"的循环。
    //Runtime: 65 ms, faster than 53.42% of Java online submissions for Shortest Subarray with Sum at Least K.
    //Memory Usage: 128.8 MB, less than 16.79% of Java online submissions for Shortest Subarray with Sum at Least K.
    //Time: O(N); Space:O(N);
    public int shortestSubarray(int[] nums, int target) {
        long[] sum = new long[nums.length + 1];//0
        for (int i = 0; i < nums.length; ++i)
            sum[i+1] = sum[i] + (long) nums[i];

        // Want smallest right-left with sum[right] - sum[left] >= target
        int res = Integer.MAX_VALUE;

        //opt(right) candidates, as indices of right
        Deque<Integer> monoq = new LinkedList();

        for (int right = 0; right < sum.length; right++) {

            // Want opt(right) = largest x with P[x] <= P[right] - K;
            //将用过的移出队列：当找到后，这个节点就可以删除了。因为下一次的长度一定比当前要大，所以不需要保留。
            while (!monoq.isEmpty()
                    && sum[right] >= sum[monoq.getFirst()] + target)
                res = Math.min(res, right - monoq.removeFirst());

            // To keep sum[right] increasing in the deque.
            // 比"我"大，说明在未来，比"我"更难满足target的。会导致下面第二个while时会无法触及"我"。
            while (!monoq.isEmpty()
                    && sum[right] <= sum[monoq.getLast()])
                monoq.removeLast();

            monoq.addLast(right);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    //two pointer slide-window
    //太过于纠结于 slide window了。可早点放弃。
    public int shortestSubarray_2(int[] nums, int k) {

        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;

        Queue<int[]> queue = new LinkedList<>();

        while (right < nums.length) {
            int n = nums[right++];
            sum += n;
            if (sum <= 0){
                sum = 0;
                left = right;
                queue.clear();
                continue;
            }
            if (n <= 0) {
                queue.offer(new int[]{right,sum});
            }

            //shrink window
            while (sum >= k) {
                minLen = Math.min(minLen, right - left);

                int leftN = nums[left++];

                Queue<int[]> queue2 = new LinkedList<>();
//                int queueSize = queue.size();
//                for(int i = 0; i < queueSize; i++){
                while (!queue.isEmpty()){
                    int[] item = queue.poll();
                    if (item[1] - leftN <= 0) {
                        queue2.clear();
                        leftN = item[1];
                        left = item[0];
                    }else{
                        item[1] -= leftN;
                        //queue.offer(item);
                        queue2.add(item);
                    }
                }
                queue = queue2;
                sum -= leftN;
            }
        }
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }


    //虽然超时，但能把思路引到解决方案上。
    //Time Limit Exceeded
    //brute force
    public int shortestSubarray_1(int[] nums, int target) {
        long[] sum = new long[nums.length + 1];//0
        for (int i = 0; i < nums.length; ++i)
            sum[i + 1] = sum[i] + (long) nums[i];

        int res = Integer.MAX_VALUE;
        for (int right = 0; right < sum.length; right++) {
            for (int i = right - 1; i >= 0; i--) {
                if (sum[right] - sum[i] >= target) res = Math.min(res, right - i);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
