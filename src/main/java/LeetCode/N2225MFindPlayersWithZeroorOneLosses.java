package LeetCode;

import java.util.*;

public class N2225MFindPlayersWithZeroorOneLosses {

    //3.Array
    //Runtime: 84 ms, faster than 95.56% of Java online submissions for Find Players With Zero or One Losses.
    //Memory Usage: 156.4 MB, less than 36.22% of Java online submissions for Find Players With Zero or One Losses.
    //Time: O(100_000 + N); Space: O(100_000)
    public List<List<Integer>> findWinners(int[][] matches) {
        int[] counter = new int[100_001];
        Arrays.fill(counter, -1);

        for(int[] match : matches) {
            if (counter[match[0]] == -1) counter[match[0]] = 0;
            if (counter[match[1]] == -1) counter[match[1]] = 0;
            counter[match[1]]++;
        }

        List<List<Integer>> res = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == 0) res.get(0).add(i);
            if (counter[i] == 1) res.get(1).add(i);
        }
        return res;
    }

    //1.HashSet
    //Runtime: 113 ms, faster than 84.27% of Java online submissions for Find Players With Zero or One Losses.
    //Memory Usage: 93.2 MB, less than 92.50% of Java online submissions for Find Players With Zero or One Losses.
    //Time: O(N * logN); Space: O(N)
    public List<List<Integer>> findWinners_2(int[][] matches) {
        //Space: O(N)
        Set<Integer> set0 = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> setOther = new HashSet<>();
        //Time: O(N); Space: O(N)
        for(int[] match : matches) {
            if (!setOther.contains(match[0]) && !set0.contains(match[0]) && !set1.contains(match[0]))
                set0.add(match[0]);

            if (!setOther.contains(match[1])) {
                if (set0.contains(match[1])) {
                    set0.remove(match[1]);
                    set1.add(match[1]);
                }else if(!set1.add(match[1])){
                    set1.remove(match[1]);
                    setOther.add(match[1]);
                }
            }
        }

        //Time: O(N * logN); Space: O(logN)
        List<List<Integer>> res = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        res.get(0).addAll(set0);
        res.get(1).addAll(set1);

        Collections.sort(res.get(0));
        Collections.sort(res.get(1));
        return res;
    }

    //1.map
    //Runtime: 180 ms, faster than 70.85% of Java online submissions for Find Players With Zero or One Losses.
    //Memory Usage: 168.4 MB, less than 23.65% of Java online submissions for Find Players With Zero or One Losses.
    //Time: O(N * logN); Space: O(N)
    public List<List<Integer>> findWinners_1(int[][] matches) {
        //Time: O(N); Space: O(N)
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] match : matches) {
            map.put(match[1], map.getOrDefault(match[1],0) + 1);
            if (!map.containsKey(match[0])) map.put(match[0], 0);
        }

        //Time: O(N);
        List<List<Integer>> res = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() == 0) res.get(0).add(entry.getKey());
            else if (entry.getValue() == 1) res.get(1).add(entry.getKey());
        }

        //Time: O(N * logN); Space: O(logN)
        Collections.sort(res.get(0));
        Collections.sort(res.get(1));
        return res;
    }
}
