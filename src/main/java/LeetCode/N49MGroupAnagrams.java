package LeetCode;


import java.util.*;

/**
 * Given an array of strings strs, group the anagrams together.
 * You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 *
 * Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */

/*
* M -
*   - 用了stream, 性能不是很好
*   - key的生成有二种方式：直接sort; 用计数sort
*
* */
public class N49MGroupAnagrams {

    public static void main(String[] args){

        System.out.println("------------------");
        String[] strs = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>>  result = new N49MGroupAnagrams().groupAnagrams(strs);
        result.stream().forEach(System.out::println);

        System.out.println("------------------");
        String[] strs2 = new String[]{""};
        List<List<String>>  result2 = new N49MGroupAnagrams().groupAnagrams(strs2);
        result2.stream().forEach(System.out::println);

        System.out.println("------------------");
        String[] strs3 = new String[]{"a"};
        List<List<String>>  result3 = new N49MGroupAnagrams().groupAnagrams(strs3);
        result3.stream().forEach(System.out::println);

        System.out.println("------------------");
        String[] strs4 = new String[]{"tin","ram","zip","cry","pus","jon","zip","pyx"};
        List<List<String>>  result4 = new N49MGroupAnagrams().groupAnagrams(strs4);
        result4.stream().forEach(System.out::println);

    }

    //2.count
    //Runtime: 23 ms, faster than 29.21% of Java online submissions for Group Anagrams.
    //Memory Usage: 56.9 MB, less than 30.44% of Java online submissions for Group Anagrams.
    //Time: O(N * K); Space: O(N * K + K)
    //Time: O(N * K); Space: O(N * K)
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList();
        Map<String, List<String>> data = new HashMap<>();

        for(String str: strs){
            String key = getKey(str);
            List<String> values =  data.getOrDefault(key, new ArrayList<>());
            values.add(str);
            data.put(key, values);
        }
        return new ArrayList(data.values());
    }
    // Time: O(K); Space:O(K)
    //let K be the length of the input string
    private String getKey(String str){
        int[] counts = new int[26];
        for(char c : str.toCharArray()) counts[c - 'a']++;

        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < 26; i++){
            if(counts[i] > 0)
                sb.append((char) (i + 'a')).append(counts[i]);
        }
        return sb.toString();
    }

    //1.sort
    // non-stream
    //Runtime: 6 ms, faster than 99.59% of Java online submissions for Group Anagrams.
    //Memory Usage: 45.1 MB, less than 96.21% of Java online submissions for Group Anagrams.
    // stream
    //Runtime: 44 ms, faster than 15.94% of Java online submissions for Group Anagrams.
    //Memory Usage: 46.1 MB, less than 86.93% of Java online submissions for Group Anagrams.
    //Time: O(N * K*log(K)); Space: O(N * K)
    public List<List<String>> groupAnagrams_sorted(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList();
        Map<String, List<String>> data = new HashMap<>();

        String key = "";
        char[] chars;
        for(String str: strs){
            //key = Arrays.stream(str.split("")).sorted().collect(Collectors.joining());
            chars = str.toCharArray();
            //Time: O(K*log(K)); Space:O(log(K))
            Arrays.sort(chars);
            key = String.valueOf(chars);

            data.putIfAbsent(key, new ArrayList<>());
            data.get(key).add(str);
        }

        //return data.values().stream().collect(Collectors.toList());
        return new ArrayList(data.values());
    }
}
