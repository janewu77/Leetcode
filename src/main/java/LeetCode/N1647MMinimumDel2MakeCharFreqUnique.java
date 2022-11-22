package LeetCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * A string s is called good if there are no two different characters in s that have the same frequency.
 *
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 *
 * The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 *
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 *
 *
 * Example 2:
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 *
 *
 *
 * Example 3:
 * Input: s = "ceabaacb"
 * Output: 2
 * Explanation: You can delete both 'c's resulting in the good string "eabaab".
 * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s contains only lowercase English letters.
 *
 */

/**
 * M: 耗时60+
 *  - 统计字频花了很多时间，主要是没想清楚，到底要什么样格式的统计结果。
 *  - 利用 26个 字母，这个特性，能提高性能。
 *  - set 当加入重复值时，返回false
 */
public class N1647MMinimumDel2MakeCharFreqUnique {

    public static void main(String[] args){
//        int result = new N1647MMinimumDel2MakeCharFreqUnique().minDeletions("accdcdadddbaadbc");
//        System.out.println("expect:1; result:"+result);

        int result = 0;
        String str;
//        str = "abcabc";
//        result = new N1647MMinimumDel2MakeCharFreqUnique().minDeletions(str);
//        System.out.println("expect:3; result:"+result);

        str = "fblkeacljlekiiddgbdibbhfafgmigghfiejilnjbclejlcabgkocbbbhnalmikjojildlfhjdgbgdhooiddbniedaabmk";
        result = new N1647MMinimumDel2MakeCharFreqUnique().minDeletions(str);
        System.out.println("expect:27; result:"+result);
    }

    /**
     * Runtime: 9 ms, faster than 97.68% of Java online submissions for Minimum Deletions to Make Character Frequencies Unique.
     * Memory Usage: 43.3 MB, less than 82.09% of Java online submissions for Minimum Deletions to Make Character Frequencies Unique.
     * @param s
     * @return
     */
    public int minDeletions(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int result = 0;
        Set<Integer> set = new HashSet<>();

        for(int f : freq)
            while (f > 0 && !set.add(f--))
                result++;

        return result;
    }
    /**
     * Runtime: 377 ms, faster than 5.06% of Java online submissions for Minimum Deletions to Make Character Frequencies Unique.
     * Memory Usage: 54.6 MB, less than 46.96% of Java online submissions for Minimum Deletions to Make Character Frequencies Unique.
     * @param s
     * @return
     */
    public int minDeletions_stream(String s) {
        if (s == null || s.isEmpty() || s.length() <= 1) return 0;

        //统计字母频次
//        Map<String,Long> charCount = Arrays.stream(s.split("")).sorted()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            String str = String.valueOf(c);
            charCount.put(str, charCount.getOrDefault(String.valueOf(str), 0L) + 1);
        }

        System.out.println(" charCount:"+charCount);

        //频次 个数
        Map<Integer,Long> resFreq = charCount.values().stream()
                .map(l -> Integer.valueOf(l.intValue())).sorted()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()
        ));
        System.out.println(" resFreq:"+resFreq);

        List<Integer> ordered = resFreq.keySet().stream()
                .filter(k -> resFreq.get(k) > 1).collect(Collectors.toList());
        System.out.println(" ordered:"+ordered);

        int newFreq = 0;
        if (ordered.size()>0)
            newFreq = ordered.get(ordered.size() - 1) - 1; //最大数量
//
        int result = 0;
        for (int i=ordered.size()-1; i>=0; i--){
            Integer freq = ordered.get(i);
            newFreq = freq < newFreq? freq - 1 : newFreq;
            int cont = resFreq.get(freq).intValue();
            while (cont > 1) {
                // 降低自身频率
                while (resFreq.containsKey(newFreq)) newFreq--;
                // 新增频
                if (newFreq != 0)  resFreq.put(newFreq, 1L);
                result = freq - newFreq + result;
                cont--;
            }
            //resFreq.put(freq, cont);
        }
        return result;
    }

    public int minDeletions_2(String s) {
        if (s == null || s.isEmpty() || s.length() <= 1) return 0;

        //统计字母频次
        Map<Character, Integer> resCount = new HashMap<>();
        for (char c : s.toCharArray())
            resCount.put(c, resCount.getOrDefault(c,0)+1);

        //重组
        Set<Integer> dupKey = new HashSet<>();
        Map<Integer, Integer> resFreq = new HashMap<>();
        for (Character c: resCount.keySet()){
            int count = resCount.get(c);
            if (resFreq.containsKey(count)){
                dupKey.add(count);
                resFreq.put(count , resFreq.get(count)+1);
            }else{
                resFreq.put(count , 1);
            }
        }
        //resCount.clear();

        Object[] ordered = dupKey.toArray();
        Arrays.sort(ordered);

        int newFreq = 0;
        if (ordered.length>0)
            newFreq = (Integer) ordered[ordered.length - 1] - 1; //最大数量

        int result = 0;
        for (int i=ordered.length-1; i>=0; i--){
            Integer freq = (Integer)ordered[i];
            newFreq = freq < newFreq? freq - 1 : newFreq;
            int cont = resFreq.get(freq);
            while (cont > 1) {
                // 降低自身频率
                while (resFreq.containsKey(newFreq)) newFreq--;
                // 新增频
                if (newFreq != 0)  resFreq.put(newFreq, 1);
                result = freq - newFreq + result;
                cont--;
            }
            //resFreq.put(freq, cont);
        }
        return result;
    }

    /**
     * Runtime: 36 ms, faster than 31.02% of Java online submissions for Minimum Deletions to Make Character Frequencies Unique.
     * Memory Usage: 67.6 MB, less than 21.53% of Java online submissions for Minimum Deletions to Make Character Frequencies Unique.
     * @param s
     * @return
     */
    public int minDeletions1(String s) {
        if (s == null || s.isEmpty() || s.length() <= 1) return 0;

        char[] sortedChars = s.toCharArray();
        Arrays.sort(sortedChars);
        Map<Integer, Integer> resFreq = new HashMap<>(); // 频次：同频次字母数
        Map<Character, Integer> resChar = new HashMap<>();// 字母：频次
        int i = 0;
        while (i < sortedChars.length){
            int j = i;
            while( j<sortedChars.length && sortedChars[j] == sortedChars[i] ) j++;
            int count = j - i;
            resChar.put(sortedChars[i], count);
            resFreq.put(count, resFreq.getOrDefault(count, 0)+1);
            i = j;
        }

        int result = 0;
        for (char c: resChar.keySet()){

            // 检查是否存在同频
            int contFreq = resFreq.get(resChar.get(c));
            if (contFreq > 1){
                // 减去自身，以避免重复计算
                resFreq.put(resChar.get(c), contFreq - 1);
                // 降低自身频率
                int delCount = resChar.get(c);
                while (resFreq.containsKey(delCount)) delCount--;
                // 新增频
                if (delCount != 0)  resFreq.put(delCount, 1);
                result = result + (resChar.get(c) - delCount);
            }
        }

        return result;
    }


    private Map<Character, Integer> countStringByChar(String s){
        // 统计单个字母的个数 {a=2, b=3, c=7, d=2}
        Map<Character, Integer> resultCount = new HashMap<>();
        for (char c : s.toCharArray()){
            resultCount.put(c, resultCount.getOrDefault(c,0)+1);
        }
        System.out.println(resultCount);
        return resultCount;
    }
}
