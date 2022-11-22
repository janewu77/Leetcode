package Contest;

import javafx.util.Pair;
import utils.comm;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * You are given two string arrays creators and ids, and an integer array views, all of length n. The ith video on a platform was created by creator[i], has an id of ids[i], and has views[i] views.
 *
 * The popularity of a creator is the sum of the number of views on all of the creator's videos. Find the creator with the highest popularity and the id of their most viewed video.
 *
 * If multiple creators have the highest popularity, find all of them.
 * If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
 * Return a 2D array of strings answer where answer[i] = [creatori, idi] means that creatori has the highest popularity and idi is the id of their most popular video. The answer can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
 * Output: [["alice","one"],["bob","two"]]
 * Explanation:
 * The popularity of alice is 5 + 5 = 10.
 * The popularity of bob is 10.
 * The popularity of chris is 4.
 * alice and bob are the most popular creators.
 * For bob, the video with the highest view count is "two".
 * For alice, the videos with the highest view count are "one" and "three". Since "one" is lexicographically smaller than "three", it is included in the answer.
 * Example 2:
 *
 * Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
 * Output: [["alice","b"]]
 * Explanation:
 * The videos with id "b" and "c" have the highest view count.
 * Since "b" is lexicographically smaller than "c", it is included in the answer.
 *
 *
 * Constraints:
 *
 * n == creators.length == ids.length == views.length
 * 1 <= n <= 105
 * 1 <= creators[i].length, ids[i].length <= 5
 * creators[i] and ids[i] consist only of lowercase English letters.
 * 0 <= views[i] <= 105
 */

/**
 * M - [120+
 * 注意： 题意不清。 见test case 1
 */
//2456. Most Popular Video Creator
public class N6221MMostPopularVideoCreator {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("[[alice, one],[bob, two]]",
                new String[]{"alice","bob","alice","chris"},
                new String[]{"one","two","three","four"},
                new int[]{5,10,5,4});

        //test case 1
        //注意这个例子，其中bd的view 是 3， 1。但排名时，只用3计算。
        doRun("[[a, ba]]",
                new String[]{"a","a","a","a"},
                new String[]{"za","ba","bd","db"},
                new int[]{3,3,3,1});

        doRun("[[a, 1]]",
                new String[]{"d","a","b","c","a","a"},
                new String[]{"4","1","2","3","2","1"},
                new int[]{2,3,3,1,8,10});


        doRun("[[a, 1],[b, 2]]", new String[]{"d","a","b","c"}, new String[]{"4","1","2","3"}, new int[]{2,3,3,1});
        doRun("[[a, a]]", new String[]{"a"}, new String[]{"a"}, new int[]{0});
        doRun("[[alice, b]]", new String[]{"alice","alice","alice"}, new String[]{"a","b","c"}, new int[]{1,2,2});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String[] creators, String[] ids, int[] views) {
        List<List<String>> res = new N6221MMostPopularVideoCreator().mostPopularCreator(creators,  ids, views);
        comm.printListListString(expect, res);
        //System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //2. without sort
    //Runtime: 153 ms, faster than 83.33% of Java online submissions for Most Popular Video Creator.
    //Memory Usage: 258.2 MB, less than 16.67% of Java online submissions for Most Popular Video Creator.
    //Time: O(C); Space: O(C + V)
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        //Space: O(C + V)
        Map<String, Integer> mapPopVideo = new HashMap<>();   //creator : pop video's index
        Map<String, Integer> mapPopularity = new HashMap<>(); //creator: views
        List<int[]> popList = new ArrayList<>(); //creator: current view sum
        popList.add(new int[]{0,-1});

        //Time: O(C)
        for (int i = 0; i < creators.length; i++) {
            mapPopularity.put(creators[i], mapPopularity.getOrDefault(creators[i],0) + views[i]);

            //current creator's the most pop video
            //If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
            //don't sum the views!!!!!
            int videoIdx = mapPopVideo.getOrDefault(creators[i], i);
            if (views[i] > views[videoIdx] ||
                    (views[i] == views[videoIdx] && ids[i].compareTo(ids[videoIdx]) < 0)) {
                videoIdx = i;
            }
            mapPopVideo.put(creators[i], videoIdx);

            //keep top 1
            if (mapPopularity.get(creators[i]) - popList.get(popList.size() - 1)[1] >= 0) {
                popList.add(new int[]{i, mapPopularity.get(creators[i])});
            }
        }

        //Time: O(C);
        List<List<String>> res = new ArrayList<>();
        int mostCount = popList.get(popList.size() - 1)[1];
        for (int idx = popList.size() - 1; idx >= 0 && popList.get(idx)[1] == mostCount; --idx) {
            String creator = creators[popList.get(idx)[0]];
            res.add(Arrays.asList(creator, ids[mapPopVideo.get(creator)]));
        }
        return res;
    }

    //1.sort
    //Runtime: 254 ms, faster than 50.00% of Java online submissions for Most Popular Video Creator.
    //Memory Usage: 271.7 MB, less than 16.67% of Java online submissions for Most Popular Video Creator.
    //Time: O(C * log(C)); Space: O(C)
    //let C be the number of creators ; let V be the length of the views
    public List<List<String>> mostPopularCreator_1(String[] creators, String[] ids, int[] views) {
        //Space: O(C)
        Map<String, Integer> mapPopularity = new HashMap<>(); //creator: views
        Map<String, Integer> mapPopVideo = new HashMap<>();   //creator : pop video's index
        //Time: O(C)
        for (int i = 0; i < creators.length; i++) {
            mapPopularity.put(creators[i], mapPopularity.getOrDefault(creators[i],0) + views[i]);

            //If multiple videos have the highest view count for a creator, find the lexicographically smallest id.
            //don't sum the views!!!!!
            int videoIdx = mapPopVideo.getOrDefault(creators[i], i);
            if (views[videoIdx] < views[i] ||
                    (views[videoIdx] == views[i] && ids[i].compareTo(ids[videoIdx]) < 0)) {
                videoIdx = i;
            }
            mapPopVideo.put(creators[i], videoIdx);
        }

        //find the top 1 creator
        //Space: O(C)
        List<Pair<String, Integer>> list = new ArrayList<>();
        for (String creator : mapPopularity.keySet())
            list.add(new Pair<>(creator, mapPopularity.get(creator)));
        //Time: O(C * log(C)); Space: O(log(C))
        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());

        //Time: O(C);
        List<List<String>> res = new ArrayList<>();
        int mostCount = list.get(0).getValue();
        for (int idx = 0; idx < list.size() && list.get(idx).getValue() == mostCount; idx++){
            String creator = list.get(idx).getKey();
            res.add(Arrays.asList(creator, ids[mapPopVideo.get(creator)]));
        }
        return res;
    }


}
