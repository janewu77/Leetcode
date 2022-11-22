package LeetCode;


import javafx.util.Pair;
import utils.comm;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**Given an array of strings words and an integer k, return the k most frequent strings.

 Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



 Example 1:

 Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 Output: ["i","love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.
 Example 2:

 Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 Output: ["the","is","sunny","day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


 Constraints:

 1 <= words.length <= 500
 1 <= words[i].length <= 10
 words[i] consists of lowercase English letters.
 k is in the range [1, The number of unique words[i]]


 Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */

/**
 * M - [time: 5-
 */
public class N692MTopKFrequentWords {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("i,love", new String[]{"i","j","j","j","love","love","love","love","i","i","i"}, 2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String[] words, int k) {
        List<String> res1 = new N692MTopKFrequentWords().topKFrequent(words, k);
        String res = res1.stream().collect(Collectors.joining(","));
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.HashMap + min Heap
    //Runtime: 6 ms, faster than 96.26% of Java online submissions for Top K Frequent Words.
    //Memory Usage: 42.6 MB, less than 89.23% of Java online submissions for Top K Frequent Words.
    //Time: O(N + N * LogK + 2 * K * LogK); Space: O(N + k)
    //Time: O(N * LogK); Space: O(N)
    public List<String> topKFrequent(String[] words, int k) {
        //Time: O(N); Space: O(N)
        Map<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        //Space: O(K)
        Queue<Pair<String, Integer>> heap = new PriorityQueue<>((o1, o2) ->
                (o1.getValue() - o2.getValue() == 0) ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue());
        //Time: O(N * logK);
        for (String key : map.keySet()){
            heap.add(new Pair<>(key, map.get(key)));
            if (heap.size() > k) heap.poll();//logK
        }

        //K * LogK
        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) list.add(heap.poll().getKey());
        //K * LogK
        Collections.reverse(list);
        return list;
    }


    //1.HashMap + PriorityQueue (max heap)
    //Runtime: 5 ms, faster than 99.86% of Java online submissions for Top K Frequent Words.
    //Memory Usage: 42.8 MB, less than 87.21% of Java online submissions for Top K Frequent Words.
    //Time: O(N + N + K * LogNd); Space: O(N + N)
    //Time: O(N + K * LogN); Space: O(N)
    public List<String> topKFrequent_1(String[] words, int k) {

        Map<String, Integer> map = new HashMap<>();
        for (String word : words)
            map.put(word, map.getOrDefault(word, 0) + 1);

        Queue<Pair<String, Integer>> heap = new PriorityQueue<>((o1, o2) ->
                (o2.getValue() - o1.getValue() == 0) ? o1.getKey().compareTo(o2.getKey()) : o2.getValue() - o1.getValue());
        for (String key : map.keySet())
            heap.add(new Pair<>(key, map.get(key)));

        List<String> list = new ArrayList<>();
        for (int i = 0; i < k; i++) list.add(heap.poll().getKey());
        return list;
    }
}
