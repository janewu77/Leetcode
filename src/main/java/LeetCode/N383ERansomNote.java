package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * E - [time: 5-
 */
public class N383ERansomNote {

    //Runtime: 2 ms, faster than 99.48% of Java online submissions for Ransom Note.
    //Memory Usage: 41.9 MB, less than 99.72% of Java online submissions for Ransom Note.
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] counter = new int[26];
        for (int i = 0; i<magazine.length(); i++)
            counter[magazine.charAt(i) - 'a']++;

        for (int i = 0; i<ransomNote.length(); i++){
            int c = ransomNote.charAt(i);
            if (counter[c - 'a'] == 0) return false;
            counter[c - 'a']--;
        }
        return true;
    }

    //Runtime: 2 ms, faster than 99.48% of Java online submissions for Ransom Note.
    //Memory Usage: 43 MB, less than 87.14% of Java online submissions for Ransom Note.
    //new int[26];
    //Time: O(M+N); Space: O(1)
    public boolean canConstruct_2(String ransomNote, String magazine) {
        int[] counter = new int[26];
        for (char c : magazine.toCharArray())
            counter[c - 'a']++;

        for (char c : ransomNote.toCharArray()){
            if (counter[c - 'a'] == 0) return false;
            counter[c - 'a']--;
        }
        return true;
    }


    //Runtime: 19 ms, faster than 47.37% of Java online submissions for Ransom Note.
    //Memory Usage: 53.4 MB, less than 15.39% of Java online submissions for Ransom Note.
    //Hashmap
    //Time: O(N+M); Space: O(M)
    public boolean canConstruct_1(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : ransomNote.toCharArray()){
            if (!map.containsKey(c)) return false;
            int count = map.get(c) - 1;
            if (count == 0) map.remove(c);
            else map.put(c,count);
        }
        return true;
    }
}
