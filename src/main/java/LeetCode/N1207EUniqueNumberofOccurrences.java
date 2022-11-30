package LeetCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.time.LocalTime.now;

public class N1207EUniqueNumberofOccurrences {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        doRun(false, new int[]{1,2});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int[] arr) {
        boolean res = new N1207EUniqueNumberofOccurrences().uniqueOccurrences(arr);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //2.map + set
    //Runtime: 4 ms, faster than 67.10% of Java online submissions for Unique Number of Occurrences.
    //Memory Usage: 42.3 MB, less than 36.12% of Java online submissions for Unique Number of Occurrences.
    //Time: O(N); Space: O(N)
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

        Set<Integer> set = new HashSet<>(map.values());
        return set.size() == map.size();
    }

    //1.map + set
    //Runtime: 1 ms, faster than 99.97% of Java online submissions for Unique Number of Occurrences.
    //Memory Usage: 40.1 MB, less than 95.86% of Java online submissions for Unique Number of Occurrences.
    //Time: O(N); Space: O(N)
    public boolean uniqueOccurrences_1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet())
            if (!set.add(entry.getValue())) return false;
        return true;
    }
}
