package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an integer array of unique positive integers nums. Consider the following graph:
 *
 * There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
 * There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [4,6,15,35]
 * Output: 4
 * Example 2:
 *
 *
 * Input: nums = [20,50,9,63]
 * Output: 2
 * Example 3:
 *
 *
 * Input: nums = [2,3,6,7,4,12,21,39]
 * Output: 8
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 105
 * All the values of nums are unique.
 */

/**
 * H - [time: 45-
 */
public class N952HLargestComponentSizebyCommonFactor {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(6, new int[]{1,2,3,4,5,6,7,8,9});
        doRun(2, new int[]{20,50,9,63});

        doRun(4, new int[]{4,6,15,35});

        doRun(2, new int[]{20,50,9,63});

        doRun(8, new int[]{2,3,6,7,4,12,21,39});
        doRun(7, new int[]{98,39,14,86,56,89,26,59,63});


        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums) {
        int res = new N952HLargestComponentSizebyCommonFactor()
                .largestComponentSize(nums);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 149 ms, faster than 94.03% of Java online submissions for Largest Component Size by Common Factor.
    //Memory Usage: 49.9 MB, less than 86.57% of Java online submissions for Largest Component Size by Common Factor.
    //Union-Find
    public int largestComponentSize(int[] nums) {

        int maxNum = 0;
        for (int num: nums) maxNum = Math.max(maxNum, num);

        //Space: O(M)
        UnionFind uf = new UnionFind(maxNum + 1);

        //Time: O(N * (sqrt(M)  + lgM * logM ))
        for (int num : nums){
            Set<Integer> factors = primeDecompose(num);
            for (int factor : factors) uf.union(num, factor);
        }

        int res = 1;
        //Space: O(N)
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int groupID = uf.find(num);
            int count = map.getOrDefault(groupID, 0) + 1;
            map.put(groupID, count);
            res = Math.max(res, count);
        }
        return res;
    }

    //Time: O(sqrt(num))
    public Set<Integer> primeDecompose(int num) {
        Set<Integer> primeFactors = new HashSet<>();
        int factor = 2;

        while (factor * factor <= num) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                num = num / factor;
            } else {
                factor += 1;
            }
        }
        primeFactors.add(num);
        return primeFactors;
    }

    class UnionFind {
        int[] data;
        int[] rank;
        //int[] weight;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            //weight = new int[n];
            for (int i = 0; i <n; i++){
                data[i] = i; rank[i] = 1; //weight[i] = 1;
            }
        }
        public int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                //int count = weight[rootX] + weight[rootY];

                if (rank[rootX] < rank[rootY]){
                    data[rootX] = rootY;
                    rootX = rootY;
                }else{
                    data[rootY] = rootX;
                    if (rank[rootX] == rank[rootY]) rank[rootX]++;
                }
                //weight[rootX] = count;
            }
        }

    }



//        int res = 1;
//        int maxFactor =  maxNum / 2;
//        for (int i = 2; i < maxFactor; i++){
//            if (i != 2 && (i & 1) == 0) i++;
//            int factor = i;
//            int firstOne = 0;
//
//            while (factor <= maxNum) {
//                if (set.contains(factor)) {
//                    if (firstOne != 0) {
//                        res = Math.max(res, uf.union(firstOne, factor));
//                        if (res == nums.length) return res;
//                    }
//                    firstOne = factor;
//                }
//                factor += i;
//            }
//        }

}
