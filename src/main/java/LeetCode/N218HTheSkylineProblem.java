package LeetCode;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in
 * that city when viewed from a distance. Given the locations and heights of all the buildings,
 * return the skyline formed by these buildings collectively.
 *
 * The geometric information of each building is given in the array buildings
 * where buildings[i] = [lefti, righti, heighti]:
 *
 * lefti is the x coordinate of the left edge of the ith building.
 * righti is the x coordinate of the right edge of the ith building.
 * heighti is the height of the ith building.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * The skyline should be represented as a list of "key points" sorted
 * by their x-coordinate in the form [[x1,y1],[x2,y2],...].
 * Each key point is the left endpoint of some horizontal segment in the skyline
 * except the last point in the list, which always has a y-coordinate 0 and is used to
 * mark the skyline's termination where the rightmost building ends.
 * Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
 *
 * Note: There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines
 * of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
 *
 * Example 1:
 * Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
 * Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
 * Explanation:
 * Figure A shows the buildings of the input.
 * Figure B shows the skyline formed by those buildings.
 * The red points in figure B represent the key points in the output list.
 *
 *
 * Example 2:
 * Input: buildings = [[0,2,3],[2,5,3]]
 * Output: [[0,3],[5,0]]
 *
 * Constraints:
 *
 * 1 <= buildings.length <= 104
 * 0 <= lefti < righti <= 231 - 1
 * 1 <= heighti <= 231 - 1
 * buildings is sorted by lefti in non-decreasing order.
 *
 */

/**
 * H - [time: 120+]
 *  - 遇到问题不要慌，冷静！
 *  - 如果花了很多时间，想想是否可以分解问题。
 *  - 分解问题。
 */

public class N218HTheSkylineProblem {

    public static void main(String[] args) {
        int[][] data;

        data = new int[][]{{1,5,1}, {2,3,8}};
        doRun(data,  "[[1, 1], [2, 8], [3, 1], [5, 0]]");

        data = new int[][]{{1,2,1}, {1,2,2}, {1,2,3}};
        doRun(data,  "[[1, 3], [2, 0]]");

        data = new int[][]{{2,9,10}, {3,7,15}}; //, {15,20,10}, {19,24,8}};
        doRun(data,  "[[2, 10], [3, 15], [7, 10], [9, 0]]");

        data = new int[][]{{0, 2, 3}};
        doRun(data, "[[0, 3], [2, 0]]");

        data = new int[][]{{0, 2, 3}, {3, 5, 8}};
        doRun(data, "[[0, 3], [2, 0], [3, 8], [5, 0]]");

        data = new int[][]{{0, 2, 3}, {2, 5, 3}};
        doRun(data, "[[0, 3], [5, 0]]");

        data = new int[][]{{0, 2, 3}, {2, 5, 5}};
        doRun(data, "[[0, 3], [2, 5], [5, 0]]");

        data = new int[][]{{0, 2, 3}, {2, 5, 1}};
        doRun(data, "[[0, 3], [2, 1], [5, 0]]");



        data = new int[][]{{2,9,10}, {3,7,15}, {5,12,12}}; //, {15,20,10}, {19,24,8}};
        doRun(data,  "[[2, 10], [3, 15], [7, 12], [12, 0]]");

        data = new int[][]{{2,9,10}, {3,7,15}, {5,12,12}, {15,20,10}, {19,24,8}};
        doRun(data,  "[[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]");



        data = new int[][]{{4,9,10},{4,9,15},{4,9,12},{10,12,10},{10,12,8}};
        doRun(data,  "[[4, 15], [9, 0], [10, 10], [12, 0]]");

        data = new int[][]{{1,38,219},{2,19,228},{2,64,106},{3,80,65},{3,84,8},{4,12,8},{4,25,14},{4,46,225},{4,67,187},{5,36,118},{5,48,211},{5,55,97},{6,42,92},{6,56,188},{7,37,42},{7,49,78},{7,84,163},{8,44,212},{9,42,125},{9,85,200},{9,100,74},{10,13,58},{11,30,179},{12,32,215},{12,33,161},{12,61,198},{13,38,48},{13,65,222},{14,22,1},{15,70,222},{16,19,196},{16,24,142},{16,25,176},{16,57,114},{18,45,1},{19,79,149},{20,33,53},{21,29,41},{23,77,43},{24,41,75},{24,94,20},{27,63,2},{31,69,58},{31,88,123},{31,88,146},{33,61,27},{35,62,190},{35,81,116},{37,97,81},{38,78,99},{39,51,125},{39,98,144},{40,95,4},{45,89,229},{47,49,10},{47,99,152},{48,67,69},{48,72,1},{49,73,204},{49,77,117},{50,61,174},{50,76,147},{52,64,4},{52,89,84},{54,70,201},{57,76,47},{58,61,215},{58,98,57},{61,95,190},{66,71,34},{66,99,53},{67,74,9},{68,97,175},{70,88,131},{74,77,155},{74,99,145},{76,88,26},{82,87,40},{83,84,132},{88,99,99}};
        doRun(data,  "[[1, 219], [2, 228], [19, 225], [45, 229], [89, 190], [95, 175], [97, 152], [99, 74], [100, 0]]");
    }

    static int c = 0;
    private static void doRun(int[][] data, String expected){
        List<List<Integer>> result = new N218HTheSkylineProblem().getSkyline(data);
        System.out.println("[" + (expected.equals(result.toString()) )+"]"+(c++)+"expect: "+ expected +".result: " + result);
    }

    //from leetcode @lyfelmer
    //Runtime: 252 ms, faster than 37.87% of Java online submissions for The Skyline Problem.
    //Memory Usage: 45.7 MB, less than 90.95% of Java online submissions for The Skyline Problem.
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();     // height list to store all buildings' heights
        for (int[] b : buildings) {
            height.add(new int[]{b[0], - b[2]});    // start of a building, height stored as negtive
            height.add(new int[]{b[1], b[2]});      // end of a building, height stored as positive
        }
        Collections.sort(height, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));     // sort the height list

        // a pq that stores all the encountered buildings' heights in descending order
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int preMax = 0;

        for (int[] h : height) {
            if (h[1] < 0) {     // h[1] < 0, that means it meets a new building, so add it to pq
                pq.offer(-h[1]);
            } else {            // h[1] >=0, that means it has reached the end of the building, so remove it from pq
                pq.remove(h[1]);
            }

            // the current max height in all encountered buildings
            int curMax = pq.peek();
            // if the max height is different from the previous one, that means a critical point is met, add to result list
            if (curMax != preMax) {
                res.add(Arrays.asList(h[0], curMax));
                preMax = curMax;
            }
        }
        return res;
    }

    //Runtime: 10 ms, faster than 96.46% of Java online submissions for The Skyline Problem.
    //Memory Usage: 45.9 MB, less than 88.50% of Java online submissions for The Skyline Problem.
    //Union find
    //Time : O(N*logN); Space: O(N)
    public List<List<Integer>> getSkyline_4(int[][] buildings){

        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> edgeSet  = new HashSet<>();
        for (int i = 0; i < buildings.length; i++){
            edgeSet.add(buildings[i][0]);
            edgeSet.add(buildings[i][1]);
        }
        Integer[] edges = edgeSet.toArray(new Integer[edgeSet.size()]);

        //Time: O(N*logN); Space: O(logN)
        Arrays.sort(edges);

        int idx = 0;
        for (int x : edges) map.put(x, idx++);

        //Time: O(N*logN); Space: O(logN)
        Arrays.sort(buildings, (Comparator.comparingInt(o -> -o[2]))); //从高到低处理

        int[] heights = new int[edges.length];
        UnionFind uf = new UnionFind(edges.length);

        for (int[] building : buildings) {
            int leftIdx = map.get(building[0]);
            int rightIdx = map.get(building[1]);
            int height = building[2];

            while (leftIdx < rightIdx) {
                leftIdx = uf.find(leftIdx);
                if (leftIdx < rightIdx) {
                    uf.union(leftIdx, rightIdx);
                    heights[leftIdx++] = height;
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < heights.length; i++)
            if (i == 0 || heights[i] != heights[i-1])
                res.add(Arrays.asList(edges[i], heights[i]));
        return  res;
    }

    class UnionFind {
        int[] group;
        public UnionFind(int n){
            group = new int[n];
            for (int i = 0; i < n; i++) group[i] = i;
        }

        public int find(int x){
            return (group[x] == x) ? x : (group[x] = find(group[x]));
        }

        public void union(int x, int y){
            group[x] = group[y];
        }
    }

    //Runtime: 37 ms, faster than 63.49% of Java online submissions for The Skyline Problem.
    //Memory Usage: 53.1 MB, less than 33.30% of Java online submissions for The Skyline Problem.
    //Sweep Line + Two Priority Queued
    //Time: O(N*logN); Space: (N);
    public List<List<Integer>> getSkyline_3(int[][] buildings){
        //Time: O(N); Space: O(2N)
        List<List<Integer>> res = new ArrayList<>();
        int[][] edges = new int[buildings.length << 1][3];
        for (int i = 0; i < buildings.length; i++){
            edges[i + i] = new int[]{buildings[i][0], i};
            edges[i + i + 1] = new int[]{buildings[i][1],  i};
        }
        //Time: O(2N*log2N); Space: O(log2N)
        Arrays.sort(edges, Comparator.comparingInt(a -> a[0])); //order by x axis

        //Space: worst case: O(N)
        PriorityQueue<Integer> liveHeap = new PriorityQueue<>(Comparator.comparingInt(a -> -a)); //order by height
        PriorityQueue<Integer> passHeap = new PriorityQueue<>(Comparator.comparingInt(a -> -a)); //order by height

        //Time: O(2N * logN)
        int idx = 0;
        while (idx < edges.length){
            int currX = edges[idx][0];

            //duplicated edges
            while (idx < edges.length && edges[idx][0] == currX){
                int[] building  = buildings[edges[idx][1]];
                if (currX == building[0]) liveHeap.add(building[2]);
                else passHeap.add(building[2]);
                idx++;
            }

            //the tallest live building has been passed, so remove it from 'live' heap
            while (!passHeap.isEmpty() && liveHeap.peek().equals(passHeap.peek())) {
                liveHeap.remove();  passHeap.remove();
            }

            int currHeight = liveHeap.isEmpty() ? 0 : liveHeap.peek();
            if (res.size() == 0 || currHeight != res.get(res.size()-1).get(1))
                res.add(Arrays.asList(currX, currHeight));
        }
        return res;
    }

    //从左往右扫边。
    //Runtime: 54 ms, faster than 52.43% of Java online submissions for The Skyline Problem.
    //Memory Usage: 52.3 MB, less than 51.83% of Java online submissions for The Skyline Problem.
    //Sweep Line + Priority Queue
    //Time: O(N*logN); Space: (N);
    public List<List<Integer>> getSkyline_2(int[][] buildings){
        //Time: O(N); Space: O(2N)
        List<List<Integer>> res = new ArrayList<>();
        int[][] edges = new int[buildings.length << 1][3];
        for (int i = 0; i < buildings.length; i++){
            edges[i + i] = new int[]{buildings[i][0], i};
            edges[i + i + 1] = new int[]{buildings[i][1],  i};
        }
        //Time: O(2N*log2N); Space: O(log2N)
        Arrays.sort(edges, Comparator.comparingInt(a -> a[0])); //order by x axis

        //Space: worst case: O(N)
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> -a[1])); //order by height

        //Time: O(2N * logN)
        int idx = 0;
        while (idx < edges.length){
            int currX = edges[idx][0];

            //duplicated edges
            while (idx < edges.length && edges[idx][0] == currX){
                int[] building  = buildings[edges[idx][1]];
                //add building to 'live' heap
                if(currX == building[0])
                    heap.add(new int[]{building[1], building[2]}); //right, height
                idx++;
            }

            //the tallest live building has been passed, so remove it from 'live' heap
            while (!heap.isEmpty() && heap.peek()[0] <= currX) heap.poll();
            int currHeight = heap.isEmpty() ? 0 : heap.peek()[1];
            if (res.size() == 0 || currHeight != res.get(res.size()-1).get(1))
                res.add(Arrays.asList(currX, currHeight));
        }
        return res;
    }

    //2022.7.15
    //Runtime: 9 ms, faster than 98.14% of Java online submissions for The Skyline Problem.
    //Memory Usage: 45.1 MB, less than 97.97% of Java online submissions for The Skyline Problem.
    //Divide-and-Conquer
    // Time：O(N * logN); Space: O(N)
    public List<List<Integer>> getSkyline_1(int[][] buildings){
        return getSkyline_helper(buildings, 0, buildings.length-1);
    }

    private List<List<Integer>> getSkyline_helper(int[][] buildings, int i, int j){
        if (i > j) return new ArrayList<>();
        if (j == i) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Arrays.asList(buildings[i][0], buildings[i][2]));
            res.add(Arrays.asList(buildings[i][1], 0));
            return res;
        }

        //(j-i+1)/2-1 + i
        int n = (i+j-1)/2;
        List<List<Integer>> left = getSkyline_helper(buildings, i, n);
        List<List<Integer>> right = getSkyline_helper(buildings,n + 1, j);
        return mergeSkyLine(left,right);
    }

    private List<List<Integer>> mergeSkyLine(List<List<Integer>> left, List<List<Integer>> right){
        if (left == null || left.size() < 1) return right;
        if (right == null || right.size() < 1) return left;

        List<List<Integer>> res = new ArrayList<>();
        int leftSize = left.size(), rightSize = right.size();
        int leftIdx = 0, rightIdx = 0;
        int lastY = 0, leftY = 0, rightY = 0;
        int x, maxY;

        while (leftIdx < leftSize && rightIdx < rightSize){
            List<Integer> pointLeft = left.get(leftIdx);
            List<Integer> pointRight = right.get(rightIdx);

            if (pointLeft.equals(pointRight) && leftIdx + 1 == leftSize && rightIdx + 1 == rightSize){
                res.add(Arrays.asList(pointLeft.get(0), pointLeft.get(1)));
                leftIdx++; rightIdx++;
                continue;
            }

            if (pointLeft.get(0) == pointRight.get(0)) {
                x = pointLeft.get(0);
                leftY = pointLeft.get(1); leftIdx++;
                rightY = pointRight.get(1); rightIdx++;
            }else if (pointLeft.get(0) < pointRight.get(0)){
                x = pointLeft.get(0);
                leftY = pointLeft.get(1);
                leftIdx++;
            }else{
                x = pointRight.get(0);
                rightY = pointRight.get(1);
                rightIdx++;
            }

            maxY = Math.max(leftY,rightY);
            if (maxY != lastY){
                res.add(Arrays.asList(x, maxY));
                lastY = maxY;
            }
        }

        while (leftIdx < leftSize) res.add(left.get(leftIdx++));
        while (rightIdx < rightSize) res.add(right.get(rightIdx++));
        return res;
    }

}
