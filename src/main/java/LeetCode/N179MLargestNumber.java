package LeetCode;

import java.util.*;

/**
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */

/**
 * M - [time: 60+]
 *  - 关键点是Comparator怎么写
 */
public class N179MLargestNumber {

    public static void main(String[] args){
       int[] nums;

       nums = new int[] {10,2};
       new N179MLargestNumber().doRun("210", nums);
       nums = new int[] {3,30,34,5,9};
       new N179MLargestNumber().doRun("9534330", nums);

        nums = new int[] {3,30,34,5,9,5,612,6,8};
        new N179MLargestNumber().doRun("9866125534330", nums);

        nums = new int[] {432, 43243};
        new N179MLargestNumber().doRun("43243432", nums);

        nums = new int[] {3,30,34,5,0,9};
        new N179MLargestNumber().doRun("95343300", nums);

        nums = new int[] {0,0};
        new N179MLargestNumber().doRun("0", nums);

        nums = new int[] {0,0,10};
        new N179MLargestNumber().doRun("1000", nums);
    }


    private void doRun(String expected, int[] nums  ){
        String res = new N179MLargestNumber().largestNumber(nums);
        System.out.println("["+(expected.equals(res)) +"] expected:"+expected+".res:"+res);
    }

    //Runtime: 5 ms, faster than 99.27% of Java online submissions for Largest Number.
    //Memory Usage: 42.2 MB, less than 86.28% of Java online submissions for Largest Number.
    //Time : O(NlgN); Space: O(N)
    //Comparator
    public String largestNumber(int[] nums) {
        if (nums.length == 1) return String.valueOf(nums[0]);

        String[] arrString = new String[nums.length];
        for (int i = 0; i < nums.length; i++) arrString[i] = String.valueOf(nums[i]);

        Arrays.sort(arrString, (s1, s2) ->(s2 + s1).compareTo(s1 + s2));

        if (arrString[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for (String str : arrString) sb.append(str);

        return sb.toString();
    }


    //Runtime: 4 ms, faster than 99.80% of Java online submissions for Largest Number.
    //Memory Usage: 42.2 MB, less than 88.04% of Java online submissions for Largest Number.
    //Time : O(NlgN); Space: O(N)
    // Comparator + heap
    public String largestNumber_2(int[] nums) {
        if (nums.length == 1) return String.valueOf(nums[0]);

        //PriorityQueue<String> maxHeap = new PriorityQueue<>((s1,s2) -> s2.charAt(0) - s1.charAt(0));
        PriorityQueue<String> maxHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                //s12 : s21
                if (s1.length() == s2.length()) return s2.compareTo(s1);
                for (int i = 0; i< s1.length() + s2.length(); i++){
                    char c12 = i < s1.length() ? s1.charAt(i) : s2.charAt(i - s1.length());
                    char c21 = i < s2.length() ? s2.charAt(i) : s1.charAt(i - s2.length());
                    if (c12 < c21) return 1;
                    if (c12 > c21) return -1;
                }
                return 0;
            }
        });

        for (int n : nums) maxHeap.add(String.valueOf(n));
        if (maxHeap.peek().equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) sb.append(maxHeap.poll());
        return sb.toString();
    }


    //Runtime: 6 ms, faster than 88.79% of Java online submissions for Largest Number.
    //Memory Usage: 41.8 MB, less than 96.34% of Java online submissions for Largest Number.
    //time : O(NlgN)
    //其实不用分组。
    public String largestNumber_1(int[] nums) {

        Map<Character, List<String>> data = new HashMap<>();
        for(int n : nums){
            String tmp = String.valueOf(n);
            List<String> list = data.getOrDefault(tmp.charAt(0), new ArrayList<>());
            list.add(tmp);
            data.put(tmp.charAt(0), list);
        }

        //PriorityQueue<String> maxHeap = new PriorityQueue<>((s1,s2) -> s2.charAt(0) - s1.charAt(0));
        PriorityQueue<String> maxHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                //s12 : s21
                for (int i = 0; i< s1.length() + s2.length(); i++){
                    char c12 = i < s1.length() ? s1.charAt(i) : s2.charAt(i - s1.length());
                    char c21 = i < s2.length() ? s2.charAt(i) : s1.charAt(i - s2.length());
                    if (c12 < c21) return 1;
                    if (c12 > c21) return -1;
                }
                return 0;
            }
        });

        Character[] chars = new Character[data.keySet().size()];
        int k = 0;
        for (char c : data.keySet()) chars[k++] = c;
        Arrays.sort(chars);

        StringBuilder sb = new StringBuilder();
        for (int i = chars.length-1; i >= 0; i--){
            List<String> list = data.get(chars[i]);
            for (String str : list) maxHeap.add(str);
            while (!maxHeap.isEmpty()) sb.append(maxHeap.poll());
        }

        while(sb.length() >1 && sb.charAt(0) == '0') sb = sb.deleteCharAt(0);
        return sb.toString();
    }

}
