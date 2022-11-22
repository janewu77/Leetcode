package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Given two sparse vectors, compute their dot product.
 *
 * Implement class SparseVector:
 *
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values,
 * you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 *
 * Follow up: What if only one of the vectors is sparse?
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * Example 2:
 *
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * Example 3:
 *
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */

/**
 * M : [Time: 5+
 *  - list | hashmap
 */
public class N1570MDotProductofTwoSparseVectors {


    public static void main(String[] args) {
        int[] data1, data2;

        System.out.println(now());

        data1 = new int[]{0,1,0,0,2,0,0};
        data2 = new int[]{1,0,0,0,3,0,4};
        doRun(6, data1, data2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] nums1, int[] nums2) {

        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);
        int res = v1.dotProduct(v2);

//        List<List<String>> res1 = new N126HWordLadderII()
//                .findLadders(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("[" + (expect.equals(res)) + "]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }
}


    //Runtime: 10 ms, faster than 71.22% of Java online submissions for Dot Product of Two Sparse Vectors.
    //Memory Usage: 51.2 MB, less than 99.37% of Java online submissions for Dot Product of Two Sparse Vectors.
    //List
    class SparseVector {

        //Space: O(L), L is the number of non-zero elements.
        private List<int[]> data;

        //Time: O(N)
        SparseVector(int[] nums) {
            data = new ArrayList<>();
            for (int i = 0; i < nums.length; i++)
                if (nums[i] != 0) data.add(new int[]{i, nums[i]});
        }

        //Time: O(min(L1,L2)) , L is the number of non-zero elements.
        public int dotProduct(SparseVector vec) {
            int i = 0, j = 0;
            int sum = 0;
            while (i < data.size() && j < vec.data.size()){
                if (data.get(i)[0] == vec.data.get(j)[0]) {
                    sum += (data.get(i)[1]) * (vec.data.get(j)[1]);
                    i++; j++;
                }else if (data.get(i)[0] > vec.data.get(j)[0]) j++;
                else i++;
            }
            return sum;
        }
    }


    //Runtime: 14 ms, faster than 51.37% of Java online submissions for Dot Product of Two Sparse Vectors.
    //Memory Usage: 123.5 MB, less than 8.81% of Java online submissions for Dot Product of Two Sparse Vectors.
    //Map
//    class SparseVector {
//
//        //Space: O(L), L is the number of non-zero elements.
//        Map<Integer, Integer> data;
//
//        //Time: O(N)
//        SparseVector(int[] nums) {
//            data = new HashMap<>();
//            for (int i = 0; i < nums.length; i++)
//                if (nums[i] != 0) data.put(i, nums[i]);
//        }
//
//        //Time: O(L)
//        public int dotProduct(SparseVector vec) {
//            int sum = 0;
//            for(Integer i : data.keySet())
//                sum += vec.get(i) * data.get(i);
//            return sum;
//        }
//
//        //Time: O(1)
//        public int get(int i){
//            return data.getOrDefault(i, 0);
//        }
//    }



