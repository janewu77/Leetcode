package LeetCode;


import static java.time.LocalTime.now;

/***
 *
 * For an integer array nums, an inverse pair is a pair of integers [i, j]
 * where 0 <= i < j < nums.length and nums[i] > nums[j].
 *
 * Given two integers n and k, return the number of different arrays consist
 * of numbers from 1 to n such that there are exactly k inverse pairs.
 * Since the answer can be huge, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: Only the array [1,2,3] which consists of numbers
 * from 1 to 3 has exactly 0 inverse pairs.
 * Example 2:
 *
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 */
public class N629HKInversePairsArray {

    public static void main(String[] args){

        doRun(1,4,0);
        doRun(1,3,0);

        doRun(0,1,1);

        doRun(1,2,0);
        doRun(1,2,1);
        doRun(0,2,2);

        doRun(2,3,1);
        doRun(2,3,2);
        doRun(1,3,3);
        doRun(0,3,4);
////
        doRun(3,4,1);
        doRun(5,4,2);

        doRun(6,4,3);
        doRun(0,3,4);
        doRun(5,4,4);
//
        doRun(4,5,1);
        doRun(9,5,2);
        doRun(15,5,3);
        doRun(20,5,4);
//
        doRun(22,5,5);
        doRun(0,5,15);

////
        System.out.println(now());
        doRun(5545,9,9);
        doRun(132932700,30,14);
        doRun(106447125,20,20);
        doRun(41237402,40,40);

        doRun(547544970,90,90);

        doRun(663677020,1000,1000);
        doRun(334048938,500,500);
        System.out.println(now());

    }

    private static int c = 1;
    private static void doRun(int expected, int n, int k){
        int res = new N629HKInversePairsArray().kInversePairs(n,k);
        System.out.println("[" + (expected ==res) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);

//        int res2 = new N629HKInversePairsArray().kInversePairs2(n,k);
//        System.out.println("[" + (expected ==res2) +"] "+(c++)+ ".result2: "+ res2 + ".expected:"+expected);
    }

    //Runtime: 18 ms, faster than 100.00% of Java online submissions for K Inverse Pairs Array.
    //Memory Usage: 41.8 MB, less than 97.59% of Java online submissions for K Inverse Pairs Array.
    //Array: 2 * 1D
    public int kInversePairs(int n, int k) {
        if (n == 0) return 0;
        if (k == 0) return 1;

        int M = 1000000007;

        int[] dp = new int[k+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int[] tmp = new int[k+1];
            tmp[0] = 1;
            for (int j = 1; j <= k ;j++) {
                int redundantVal = j >= i ? dp[j - i] : 0;
                int val = (dp[j] + (M - redundantVal)) % M; //减掉上一轮已经累加上去的值。
                tmp[j] = (tmp[j - 1] + val) % M;
            }
            dp = tmp;
        }
        return dp[k];
    }

    //Runtime: 18 ms, faster than 100.00% of Java online submissions for K Inverse Pairs Array.
    //Memory Usage: 43 MB, less than 95.18% of Java online submissions for K Inverse Pairs Array.
    //time:O(nk)O(nk). space:O(nk).
    //在kInversePairs_2基本上优化累加
    public int kInversePairs_3(int n, int k) {
        if (n == 0) return 0;
        if (k == 0) return 1;
        int[][] dp = new int[n+1][k+1];
        int M = 1000000007;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k ;j++) {
                int redundantVal = j >= i ? dp[i - 1][j - i] : 0;
                int val = (dp[i - 1][j] + (M - redundantVal)) % M;
                dp[i][j] = (dp[i][j - 1] + val) % M;
            }
        }
        return (dp[n][k] + M - dp[n][k - 1] ) % M;
    }

    //Time Limit Exceeded
    //time: O(nkmin(n,k)). space: O(n∗k).
    // 递归转化成 双for
    // 2D-array
    public int kInversePairs_2(int n, int k) {
        if (n == 0) return 0;
        if (k == 0) return 1;
        int[][] dp = new int[n+1][k+1];

        //int sum = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= k ;j++) {
                int sum = 0;
                for (int p = 0; p <= Math.min(j, i - 1); p++) {
                    sum = (sum + dp[i - 1][j - p]) % M;
                }
                dp[i][j] = sum;
            }
        }
        return dp[n][k];
    }

    //Time Limit Exceeded
    //time : O(nkmin(n,k)) ; space:O(nk)
    //递归 + 缓存 （超时）
    int M = 1000000007;
    Integer[][] array_memo = new Integer[1001][1001];
    public int kInversePairs_1(int n, int k) {
        if (n == 0) return 0;
        if (k == 0) return 1;
        if (array_memo[n][k] != null) return array_memo[n][k];

        int sum = 0;
//        for (int j = k; j >= Math.max(0, k - (n - 1)); j--)
//            sum = (sum + kInversePairs_cursive(n - 1, j)) % M;

        for (int j = 0; j <= Math.min(k, (n - 1)); j++)
            sum = (sum + kInversePairs_1(n - 1, k-j)) % M;

        return array_memo[n][k]= sum;
    }


}
