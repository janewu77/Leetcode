package Contest;

/**
 * 6212. Remove Letter To Equalize Frequency
 * User Accepted:3727
 * User Tried:9390
 * Total Accepted:3811
 * Total Submissions:33920
 * Difficulty:Easy
 * You are given a 0-indexed string word, consisting of lowercase English letters. You need to select one index and remove the letter at that index from word so that the frequency of every letter present in word is equal.
 *
 * Return true if it is possible to remove one letter so that the frequency of all letters in word are equal, and false otherwise.
 *
 * Note:
 *
 * The frequency of a letter x is the number of times it occurs in the string.
 * You must remove exactly one letter and cannot chose to do nothing.
 *
 *
 * Example 1:
 *
 * Input: word = "abcc"
 * Output: true
 * Explanation: Select index 3 and delete it: word becomes "abc" and each character has a frequency of 1.
 * Example 2:
 *
 * Input: word = "aazz"
 * Output: false
 * Explanation: We must delete a character, so either the frequency of "a" is 1 and the frequency of "z" is 2, or vice versa. It is impossible to make all present letters have equal frequency.
 *
 *
 * Constraints:
 *
 * 2 <= word.length <= 100
 * word consists of lowercase English letters only.
 */

//2423. Remove Letter To Equalize Frequency

/**
 * E - [time: 50-
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.time.LocalTime.now;

public class N6212ERemoveLetterToEqualizeFrequency {



    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(true, "abbcc");

        doRun(true, "bb");
        doRun(true, "abb");
        doRun(false, "cbccca");
        doRun(false, "abaccb");
        doRun(false, "abbccc");
        doRun(true, "abc");
        doRun(true, "cccd");
        doRun(false, "cbccca");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, String word) {
        boolean res = new N6212ERemoveLetterToEqualizeFrequency()
                .equalFrequency(word);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Letter To Equalize Frequency.
    //Memory Usage: 42.2 MB, less than 35.13% of Java online submissions for Remove Letter To Equalize Frequency.
    //Time:O(L + 26 * 26); Space: O(26)
    public boolean equalFrequency(String word) {
        int[] data = new int[26];
        for (char c: word.toCharArray()) data[c -'a']++;
        for (int i = 0; i < 26; i++){
            data[i]--;
            if (isEqual(data)) return true;
            data[i]++;
        }
        return false;
    }
    private boolean isEqual(int[] data){
        int lastCount = 0;
        for (int count: data){
            if (count == 0) continue;
            if (lastCount != 0 && count != lastCount) return false;
            lastCount = count;
        }
        return true;
    }



    //实际上是错的。
    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Remove Letter To Equalize Frequency.
    //Memory Usage: 42.1 MB, less than 100.00% of Java online submissions for Remove Letter To Equalize Frequency.
    //two pass | hashmap
    //Time: O(N); Space:O(N)
    public boolean equalFrequencyx(String word) {
        int maxCount = 0;
        Map<Character, Integer> map = new HashMap<>(); //char: count
        for (char c: word.toCharArray()){
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
            maxCount = Math.max(maxCount, count);
        }
        if (maxCount == 1 || map.size() == 1) return true;

//        int avg = (word.length() - 1) / map.keySet().size();

        Map<Integer, Integer> countMap = new HashMap<>(); //count: count
        int minCount = Integer.MAX_VALUE;
        for (char key : map.keySet()) {
            int count = map.get(key);
            minCount = Math.min(minCount, count);

            countMap.put(count, countMap.getOrDefault(count, 0) + 1);
        }

        if (countMap.size() == 2 ){
            if ((maxCount - minCount == 1) && countMap.get(minCount) == 1 || countMap.get(maxCount) == 1)
                return true;
            return false;
        }
        if (maxCount - minCount > 1)
            return false;

//        return countMap.get(minCount) != 1;
        return true;
//        if (maxCount - minCount > 1 || countMap.size() != 2) return false;
//        return countMap.get(minCount) == 1 || countMap.get(maxCount) == 1;
    }


}
