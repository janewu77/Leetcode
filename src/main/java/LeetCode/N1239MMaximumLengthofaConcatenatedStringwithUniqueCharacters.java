package LeetCode;

import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
 *
 * Return the maximum possible length of s.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All the valid concatenations are:
 * - ""
 * - "un"
 * - "iq"
 * - "ue"
 * - "uniq" ("un" + "iq")
 * - "ique" ("iq" + "ue")
 * Maximum length is 4.
 * Example 2:
 *
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
 * Example 3:
 *
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 * Explanation: The only string in arr has all 26 characters.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 16
 * 1 <= arr[i].length <= 26
 * arr[i] contains only lowercase English letters.
 */

/**
 * M - [time: 60
 */
public class N1239MMaximumLengthofaConcatenatedStringwithUniqueCharacters {
    public static void main(String[] args) {

        System.out.println(now());
        List<String> list;

//        System.out.println(1 << 26);
//        System.out.println(2 << 26);

        list = Arrays.asList("a","b","dx","ey","am","bn","abc","def");
        doRun(8, list);

        list = Arrays.asList("a","b","c");
        doRun(3, list);

        //list = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p");
        list = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p");
        doRun(16, list);

        list = Arrays.asList("a", "abc", "d", "de", "def");
        doRun(6, list);

        list = Arrays.asList("abcdefghijklmnopqrstuvwxyz");
        doRun(26, list);

        list = Arrays.asList("yxa","svub","jtrx","ltrhqeyukanpfdbz");
        doRun(16, list);

        list = Arrays.asList("un","iq","ue");
        doRun(4, list);

        list = Arrays.asList("aa","bb");
        doRun(0, list);
        list = Arrays.asList("yy","bkhwmpbiisbldzknpm");
        doRun(0, list);

        list = Arrays.asList("cha","r","act","ers");
        doRun(6, list);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, List<String> arr) {
        int res = new N1239MMaximumLengthofaConcatenatedStringwithUniqueCharacters().maxLength(arr);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //4.DFS + bit manipulation
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    //Memory Usage: 39.6 MB, less than 99.30% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    //Time: O(2^N); Space: O(N)
    public int maxLength(List<String> arr) {
        List<Integer> arr2 = new ArrayList<>();
        for(String word: arr) {
            int bitSet = str2BitInt4(word);
            if (bitSet > 0) arr2.add(str2BitInt4(word));
        }
        int[] data = new int[arr2.size()];
        for (int i = 0; i < data.length; i++)
            data[i] = arr2.get(i);

        return helper_dfs(data, 0, 0);
    }
    private int helper_dfs(int[] data, int begin, int bitSet) {
        int maxLen = Integer.bitCount(bitSet);
        for (int i = begin; i < data.length; i++) {
            if ((data[i] | bitSet) == (data[i] ^ bitSet))
                maxLen = Math.max(maxLen, helper_dfs(data, i + 1, bitSet | data[i]));
        }
        return maxLen;
    }

    private int str2BitInt4(String word){
        int x = 0;
        for(char c : word.toCharArray()) {
            if ((x >> (c - 'a') & 1) == 1) return -1;
            x |= 1 << c - 'a';
        }
        return x;
    }

    //3.Bit manipulation
    //Runtime: 9 ms, faster than 91.52% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    //Memory Usage: 42.3 MB, less than 84.45% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    public int maxLength_3(List<String> arr) {
        int mask = (1<<26) - 1;
        List<Integer> result = new ArrayList<>();
        result.add(0);
        int maxLen = 0;
        for (String word : arr) {
            int x = str2BitInt3(word);
            if (x < 1) continue;

            int resultSize = result.size();
            //Time: O((2 ^ N) * K)
            for (int i = 0; i < resultSize; i++) {
                int currX = result.get(i) | x;
                if (currX == (result.get(i) ^ x)) {
                    //int currLen = Integer.bitCount(currX);
                    int currLen = (result.get(i) >> 26) + word.length();
                    maxLen = Math.max(maxLen, currLen);
                    currX = currX & mask | (currLen << 26);
                    result.add(currX);
                }
            }
        }
        return maxLen;
    }

    //Time:O(K)
    private int str2BitInt3(String word){
        int x = 0;
        for(char c : word.toCharArray()) {
            if ((x >> (c - 'a') & 1) == 1) return -1;
            x |= 1 << c - 'a';
        }
        return x;
    }

    //2.Iteration
    //Runtime: 30 ms, faster than 66.54% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    //Memory Usage: 46.4 MB, less than 67.87% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    public int maxLength_2(List<String> arr) {
        int maxLen = 0;
        List<String> result = new ArrayList<>();
        result.add("");
        for (String word : arr) {
            int resultSize = result.size();
            for (int i = 0; i < resultSize; i++) {
                //Time: O(K); Space: O(K)
                String word2 = result.get(i) + word;
                //Time: O(K)
                if (isSelfUnique2(word2)){
                    result.add(word2);
                    maxLen = Math.max(maxLen, word2.length());
                }
            }
        }
        return maxLen;
    }
    //Time: O(K)
    private boolean isSelfUnique2(String str){
        Set<Character> strSet = new HashSet<>();
        for(char c : str.toCharArray())
            if (!strSet.add(c)) return false;
        return true;
    }

    //1.array + backtracking word
    //Runtime: 179 ms, faster than 16.50% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
    //Memory Usage: 117.1 MB, less than 11.78% of Java online submissions for Maximum Length of a Concatenated String with Unique
    public int maxLength_1(List<String> arr) {
        List<String> arr2 = new ArrayList<>();
        for (String str : arr)
            if (isSelfUnique(str)) arr2.add(str);
        return helper_backtracking1(arr2, 0, new int[26]);
    }

    private int helper_backtracking1(List<String> arr, int begin, int[] counter) {
        int count = 0;

        if (begin >= arr.size()) {
            for (int n : counter) if (n != 0) count++;
            return count;
        }


        Set<Integer> toBeRemoveSet = new HashSet<>();
        if (isContainAny(counter, arr.get(begin))){
            count = Math.max(count, helper_backtracking1(arr, begin + 1, counter));

            //remove prev
            for(char c : arr.get(begin).toCharArray()) {
                if (counter[c - 'a'] != 0) {
                    int idx = counter[c - 'a'] - 1;
                    if (toBeRemoveSet.add(idx))
                        removeFromSet(counter, arr.get(idx));
                }
            }
        }

        addToSet(counter, arr.get(begin), begin);
        count = Math.max(count, helper_backtracking1(arr, begin + 1, counter));
        removeFromSet(counter, arr.get(begin));

        for (int toBeRemoveIdx: toBeRemoveSet)
            addToSet(counter,arr.get(toBeRemoveIdx), toBeRemoveIdx);

        return count;
    }

    private boolean isSelfUnique(String str){
        Set<Character> strSet = new HashSet<>();
        for(char c : str.toCharArray())
            if (!strSet.add(c)) return false;
        return true;
    }

    private boolean isContainAny(int[] data, String str){
        for(char c : str.toCharArray())
            if (data[c - 'a'] != 0) return true;
        return false;
    }

    private void addToSet(int[] data, String str, int i){
        i++;
        for(char c : str.toCharArray()) data[c - 'a'] = i;
    }

    private void removeFromSet(int[] data, String str){
        for(char c : str.toCharArray()) data[c - 'a'] = 0;
    }


//    //1.set
//    //Runtime: 1374 ms, faster than 5.02% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
//    //Memory Usage: 117 MB, less than 11.78% of Java online submissions for Maximum Length of a Concatenated String with Unique Characters.
//    public int maxLength_1(List<String> arr) {
//        return helper_1(arr, 0, new HashSet<>(), new HashSet<>());
//    }
//
//    private int helper_1(List<String> arr, int begin, Set<Character> set, Set<String> seen) {
//        while (begin < arr.size() && !isSelfUnique(arr.get(begin))) begin++;
//        if (begin >= arr.size()) return set.size();
//
//        int count = 0;
//        List<String> toBeRemoveList = new ArrayList<>();
//        if (isContainAny(set, arr.get(begin))){
//            count = Math.max(count, helper_1(arr, begin + 1, set, seen));
//
//            //remove prev
//            for(String strSeen : seen) {
//                if (!isDinstinct(strSeen, arr.get(begin)))
//                    toBeRemoveList.add(strSeen);
//            }
//            for (String toBeRemove: toBeRemoveList) {
//                seen.remove(toBeRemove);
//                removeFromSet(set, toBeRemove);
//            }
//        }
//
//        seen.add(arr.get(begin));
//        addToSet(set, arr.get(begin));
//        count = Math.max(count, helper_1(arr, begin + 1, set, seen));
//
//        seen.remove(arr.get(begin));
//        removeFromSet(set, arr.get(begin));
//        for (String toBeRemove: toBeRemoveList) {
//            seen.add(toBeRemove);
//            addToSet(set, toBeRemove);
//        }
//        return count;
//    }
//
//    private boolean isDinstinct(String str, String str2){
//        Set<Character> strSet = new HashSet<>();
//        for(char c : str.toCharArray()) strSet.add(c);
//        for(char c : str2.toCharArray())
//            if (strSet.contains(c)) return false;
//        return true;
//    }
//    private boolean isSelfUnique(String str){
//        Set<Character> strSet = new HashSet<>();
//        for(char c : str.toCharArray())
//            if (!strSet.add(c)) return false;
//        return true;
//    }
//
//    private boolean isContainAny(Set<Character> set, String str){
//        for(char c : str.toCharArray())
//            if (set.contains(c)) return true;
//        return false;
//    }
//
//    private void addToSet(Set<Character> set, String str){
//        for(char c : str.toCharArray()) set.add(c);
//    }
//
//    private void removeFromSet(Set<Character> set, String str){
//        for(char c : str.toCharArray()) set.remove(c);
//    }

}
