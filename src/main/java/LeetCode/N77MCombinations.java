package LeetCode;

import java.util.*;

import utils.comm;
/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */

/**
 * M - [time: 120+
 *  - 第一种解ELT， 第二种采用了 bit-manipulation;
 *  - 第三种 binary ordered(solution里看来的)
 *  - 第四种 backtracking (recursion) (solution里看来的)
 */
public class N77MCombinations {


    public static void main(String[] args) {

//        int n = 12;
//        while(n > 0) {
//            n = n - (n & (-n)) ;
//            System.out.println(n);
//        }

        doRun("",5,3);

        doRun("[[1]]",1,1);
        doRun("[[1, 2, 3]]",3,3);

        doRun("[[2, 3],[1, 3],[1, 2]]",3,2);
        doRun("[[3],[2],[1]]",3,1);

        //doRun("",6,4);


    }

    static private void doRun(String expect,  int n, int k) {
        List<List<Integer>> res1 = new N77MCombinations().combine(n, k);
        //JPrint.print(res);
        String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    ////////////////////////////////////////////////////////////////////
    //Runtime: 3 ms, faster than 96.40% of Java online submissions for Combinations.
    //Memory Usage: 54.3 MB, less than 81.50% of Java online submissions for Combinations.
    //backtracking
    // Time: O(k * C(k,N)) ; Space:  C(k,N)
    // C(k,N) = N!/((N-K)!*k!)
    public List<List<Integer>> combine_backtracking(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        helper_backtrack(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    public void helper_backtrack(int n, int k, int begin,
                                       List<Integer> list, List<List<Integer>> res) {
        if (k == 0) {
            res.add(new ArrayList(list));
            return;
        }

        for (int i = begin; i <= n - k + 1; i++) {
            list.add(i);
            helper_backtrack(n, k - 1, i + 1, list, res);
            list.remove(list.size()-1);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //按solution里的算法，写出来的。 Approach 2: Lexicographic (binary sorted)
    //Runtime: 5 ms, faster than 89.56% of Java online submissions for Combinations.
    //Memory Usage: 43.4 MB, less than 95.88% of Java online submissions for Combinations.
    //Lexicographic (binary sorted) combinations
    //time: O(k C(k,N))  C(k,N) = n! /((n-k)!*k!); Space:  O(K)
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= k; ++i) nums.add(i);
        nums.add(n + 1); //哨兵 n+1一个不存在的数

        int j = 0;
        //Time: ( C(k,N) * K)
        while (j < k){
            res.add(new ArrayList<>(nums.subList(0, k)));

            j = 0;
            //Time:O(K)
            while (j < k && nums.get(j) + 1 == nums.get(j + 1)) {
                nums.set(j, 1 + j); //保证一直是increase的
                j++;
            }
            nums.set(j, nums.get(j) + 1);
        }
        return res;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //2022.9.5优化之后由30ms 提升到5ms
    //思路：
    //把n个数，看成是一个n位的二进制。 当1的个数为K时，把1位上的数加到结果集list里。
    //Runtime: 5 ms, faster than 90.45% of Java online submissions for Combinations.
    //Memory Usage: 54.5 MB, less than 72.31% of Java online submissions for Combinations.
    //Time: O(N*N*N） Space: O(1)
    //Bit Operations / bit manipulation
    public List<List<Integer>> combine_bit(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        //if n= 5, k =3 , then  bitKMin： 00111; bitKMax 11100
        int bitKMin = (1 << k) - 1;
        int bitKMax = bitKMin;
        bitKMax <<= n - k;

        //Time：O(N*N*N)
        while (bitKMax >= bitKMin) {
            int bitMask = bitKMin;
            //Time：O(N)
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if ((bitMask & 1) == 1) list.add(i);
                bitMask >>= 1;
            }//end for
            res.add(list);
            bitKMin = nextMask(bitKMin);
        }
        return res;
    }

    int nextMask(int n) {
        int c = (n & -n), r = n + c;
        return (((r ^ n) >> 2) / c) | r;
    }


    public List<List<Integer>> combine_2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        //5个中间取3个。则 bitKMax 11100；  bitKMin： 00111；
        int bitKMin = (1 << k) - 1;
        int bitKMax = bitKMin;
        bitKMax <<= n - k;

        while (bitKMax >= bitKMin) {
            //1的个数是否为k.
//            if (Integer.bitCount(bitKMax--) != k) continue;
            if (!countKOnes(bitKMax--, k)) continue;

            //把1位对应的值，放入list内。
            int x = bitKMax + 1;
            //Time：O(N)
            List<Integer> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if ((x & 1) == 1) list.add(i);
                x >>= 1;
            }//end for
            res.add(list);
        }
        return res;
    }

    //if (!countKOnes(bitKMax--, k)) continue;
//    if (Integer.bitCount(bitKMax--) != k) continue;
    private boolean countKOnes(int n, int k){
        int c = 0;
        while (n > 0) {
            c += (n & 1);
            if (c > k) return false;
            n >>= 1;
        }
        return c == k;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //top-down往下分裂
    //Time Limit Exceeded
    //Time : O(K* (N*N*N)
    public List<List<Integer>> combine_1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        res.add(list);

        //put all
        for (int i = 1; i <=n; i++) list.add(i);
        if (k == n) return res;

        int x = n;
        while (x > k){
            List<List<Integer>> res2 = new ArrayList<>();
            for (List<Integer> oldList: res){

                for (int i = 0; i < oldList.size(); i++){
                    List<Integer> list2 = new ArrayList<>();
                    for (int j = 0; j < oldList.size(); j++){
                        if (i==j) continue;
                        list2.add(oldList.get(j));
                    }
                    if (!res2.contains(list2))
                        res2.add(list2);
                }
                res = res2;
            }
            x--;
        }
        return res;
    }
}
