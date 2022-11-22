package LeetCode;

import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;


/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 * TimeMap() Initializes the object of the data structure.
 * void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 * String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 *
 *
 * Example 1:
 *
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 *
 * Explanation
 * TimeMap timeMap = new TimeMap();
 * timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
 * timeMap.get("foo", 1);         // return "bar"
 * timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
 * timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
 * timeMap.get("foo", 4);         // return "bar2"
 * timeMap.get("foo", 5);         // return "bar2"
 *
 *
 * Constraints:
 *
 * 1 <= key.length, value.length <= 100
 * key and value consist of lowercase English letters and digits.
 * 1 <= timestamp <= 107
 * All the timestamps timestamp of set are strictly increasing.
 * At most 2 * 105 calls will be made to set and get.
 */

/**
 * M - [Time: 20-
 */
public class N981MTimeBasedKeyValueStore {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        new N981MTimeBasedKeyValueStore().doRun();
        System.out.println(now());
        System.out.println("==================");
    }

    void doRun() {
        String res;
        TimeMap obj = new TimeMap();
        obj.set("foot","aaa1",1);
        obj.set("foot","aaa2",2);
        res = obj.get("foot",1);
        System.out.println(res);
        res = obj.get("foot",2);
        System.out.println(res);
        res = obj.get("foot",0);
        System.out.println(res);
        res = obj.get("foot",10);
        System.out.println(res);
    }


    //Runtime: 181 ms, faster than 87.71% of Java online submissions for Time Based Key-Value Store.
    //Memory Usage: 117.3 MB, less than 91.94% of Java online submissions for Time Based Key-Value Store.
    //List + binary search
    // If M is the number of set function calls
    // N is the number of get function calls
    // L is average length of key and value strings.
    class TimeMap{

        //Space: O(M * L)
        Map<String, List<Pair<Integer, String>>> map;
        public TimeMap() {
            map = new HashMap<>();
        }

        //Time: O(M * L) <<< M calls
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair<>(timestamp, value));
        }

        //Time: O(N * L * LogM) <<< N calls
        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            List<Pair<Integer, String>> list = map.get(key);
            int idx = Collections.binarySearch(list, new Pair<>(timestamp, ""), Comparator.comparingInt(Pair::getKey));
            if (idx == -1) return "";
            if (idx < 0) idx = Math.abs(idx) - 2;
            return list.get(idx).getValue();
        }
    }

    //Runtime: 158 ms, faster than 96.18% of Java online submissions for Time Based Key-Value Store.
    //Memory Usage: 117.5 MB, less than 89.86% of Java online submissions for Time Based Key-Value Store.
    //TreeMap
    // If M is the number of set function calls
    // N is the number of get function calls
    // L is average length of key and value strings.
     class TimeMap_treeMap{

        //Space: O(M * L)
        Map<String, TreeMap<Integer, String>> map;
        public TimeMap_treeMap() {
            map = new HashMap<>();
        }

        //Time: O(M * L * LogM) <<< M calls
        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k ->new TreeMap<>()).put(timestamp, value);
        }

        //Time: O(N * L * LogM) <<< N calls
        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            Map.Entry<Integer, String> entry = map.get(key).floorEntry(timestamp);
            return entry == null ? "" : entry.getValue();
        }
    }
}
