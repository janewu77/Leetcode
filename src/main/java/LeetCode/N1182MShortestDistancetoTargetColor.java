package LeetCode;


import java.util.*;

/**
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
 * Output: [3,0,3]
 * Explanation:
 * The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away).
 * The nearest 1 from index 6 is at index 3 (3 steps away).
 * Example 2:
 *
 * Input: colors = [1,2], queries = [[0,3]]
 * Output: [-1]
 * Explanation: There is no 3 in the array.
 *
 *
 * Constraints:
 *
 * 1 <= colors.length <= 5*10^4
 * 1 <= colors[i] <= 3
 * 1 <= queries.length <= 5*10^4
 * queries[i].length == 2
 * 0 <= queries[i][0] < colors.length
 * 1 <= queries[i][1] <= 3
 */

/**
 * M - 【time：120+】
 *  - 容易在edge上出错。
 *  - 数据结构：tree set
 *
 */
public class N1182MShortestDistancetoTargetColor {

    public static void main(String[] args){

//        int[][] queries = {{12,3},{12,2},{12,1}};
//        Arrays.sort(queries, (q1, q2)-> q1[0] > q2[0] ? 1 : q1[0] == q2[0] ? q1[1] - q2[1] : -1);
//        System.out.println("ddd");

        int[] colors;
        int [][] queries;
        String expected;

        colors = new int[]{1,1,2,1,3,2,2,3,3};
        queries = new int[][]{{1,3},{2,2},{6,1}};
        expected = "[3, 0, 3]";
        doRun(colors, queries, expected);

        colors = new int[]{2,1,2,2,1};
        queries = new int[][]{{1,1},{4,3},{1,3}, {4,2}, {2,1}};
        expected = "[0, -1, -1, 1, 1]";
        doRun(colors, queries, expected);

        System.out.println(".End.");
    }


    private static int c = 0;
    private static void doRun(int[] colors, int[][] queries, String expected){
        List<Integer> result = new N1182MShortestDistancetoTargetColor().shortestDistanceColor(colors, queries);
        System.out.println("[" + (expected.equals(result.toString()) )+"]"+(c++)
                +".result: " + result + ".expected:"+expected);
    }

    //Runtime: 237 ms, faster than 8.33% of Java online submissions for Shortest Distance to Target Color.
    //Memory Usage: 120.9 MB, less than 84.83% of Java online submissions for Shortest Distance to Target Color.
    // 整理各颜色的位置列表放入hashmap中。 query时，用 tree set 找位置并计算。
    //tree set
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, TreeSet<Integer>> colorMappings = new HashMap<>();

        for (int idx = 0; idx < colors.length; idx++) {
            TreeSet<Integer> positions = colorMappings.getOrDefault(colors[idx], new TreeSet<>());
            colorMappings.put(colors[idx],positions);
            positions.add(idx);
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i< queries.length; i++)
            res.add(findMinDistance(colorMappings.get(queries[i][1]), queries[i][0]));
        return res;
    }

    private int findMinDistance(TreeSet<Integer> colorPosition, int val){
        if (colorPosition == null || colorPosition.size() <= 0) return -1;

        int distance = 60000;

        Integer ceiling = colorPosition.ceiling(val);
        Integer floor = colorPosition.floor(val);
        if (ceiling == null && floor == null) return distance;

        if (ceiling != null) distance = ceiling - val;
        if (floor != null) distance = Math.min(distance, val - floor);

        return distance;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 整理各颜色的位置列表放入hashmap中。 query时，用binarysearch 找位置并计算。
    public List<Integer> shortestDistanceColor2(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> colorMappings = new HashMap<>();

        for (int idx = 0; idx < colors.length; idx++) {
            List<Integer> positions = colorMappings.getOrDefault(colors[idx], new ArrayList());
            colorMappings.put(colors[idx],positions);
            positions.add(idx);
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i< queries.length; i++)
            res.add(findMinDistance(colorMappings.get(queries[i][1]), queries[i][0]));

        return res;
    }

    private int findMinDistance(List<Integer> colorPosition, int val){
        int distance = -1;
        if (colorPosition == null || colorPosition.size() <= 0) return distance;
        int idx = binarySearch(colorPosition, val , 0, colorPosition.size() - 1);
        if (idx != -1) {
            distance = val - colorPosition.get(idx);
            if (distance < 0) {
                distance = -distance;
                if (idx - 1 >= 0) distance = Math.min(distance, val - colorPosition.get(idx - 1));
            }
        }
        return distance;
    }

    private int binarySearch(List<Integer> colorPosition, int val, int from, int to){
        if (colorPosition.size() <= 0 || to < from) return  -1;

        if (from == to || from + 1 == to) {
            if (colorPosition.get(from) >= val) return from;
            else return to;
        }
        int k = (to - from) / 2 + from;
        if (colorPosition.get(k) == val) return k;

        if (colorPosition.get(k) > val) return binarySearch(colorPosition,val,from, k);
        else return binarySearch(colorPosition,val,k+1, to);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 26 ms, faster than 83.76% of Java online submissions for Shortest Distance to Target Color.
    //Memory Usage: 68.9 MB, less than 91.45% of Java online submissions for Shortest Distance to Target Color.
    // 从前往后&从后往前，同时计算各颜色间最短距离
    //pre-computed . compute from head and tail at same time.
    // time: O(N+Q); space:O(3N)
    public List<Integer> shortestDistanceColor1(int[] colors, int[][] queries) {
        int max = 60000;

        int[][] colorMappings = new int[colors.length][3];
        for (int i = 0; i < colorMappings.length; i++) colorMappings[i] = new int[]{max, max, max};

        //forward: 012; backward: 345
        int[] colorMap = new int[]{max, max, max, max, max, max};

        //compute both directions (forward & backward)
        for(int idx = 0; idx<colors.length; idx++){
            int tailIdx = colors.length - idx -1;

            for(int j = 0; j < colorMap.length; j++) {
                if (colors[idx] - 1 == j || colors[tailIdx] - 1 + 3 == j) colorMap[j] = 0;
                else colorMap[j] = colorMap[j] + 1;

                if (j < 3) colorMappings[idx][j] = Math.min(colorMappings[idx][j], colorMap[j]);
                else colorMappings[tailIdx][j - 3] = Math.min(colorMappings[tailIdx][j - 3], colorMap[j]);
            }
        }

        //Query
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i< queries.length; i++){
            int tmp = colorMappings[queries[i][0]][queries[i][1]-1];
            res.add(tmp >= max ? -1 : tmp);
        }
        return res;
    }

}
