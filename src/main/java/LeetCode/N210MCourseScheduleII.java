package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 */

public class N210MCourseScheduleII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("", 2, new int[][]{{1,0}, {0,1}});
        doRun("", 3, new int[][]{{0,2}, {1,2}, {2,0}});
        doRun("0,1,2,3", 4, new int[][]{{1,0},{2,0},{3,1},{3,2}});
        doRun("0,1", 2, new int[][]{{1,0}});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, int numCourses, int[][] prerequisites) {
        int[] res1 = new N210MCourseScheduleII().findOrder(numCourses, prerequisites);
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //1.DFS + gray
    //Runtime: 3 ms, faster than 99.26% of Java online submissions for Course Schedule II.
    //Memory Usage: 43.3 MB, less than 92.56% of Java online submissions for Course Schedule II.
    //Time: O(V + E + V); Space:O(V + E + V + V )
    ///Time: O(V + E); Space: O(V + E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        //Time: O(V + E); Space:O(V + E)
        List<Integer>[] graph = new List[numCourses]; //E
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] edge : prerequisites) graph[edge[0]].add(edge[1]);

        //Space:O(V)
        idx = 0;
        int[] res = new int[numCourses];
        int[] status = new int[numCourses];//0 white; 1 gray; 2 black
        //Time: O(V); Space: O(V)
        for (int i = 0; i < numCourses; i++) {
            if (status[i] == 0)
                helper_dfs(graph, status, i, res);
            else if (status[i] == 1) break;  //cycle
        }
        return idx == numCourses ? res : new int[0];
    }

    private int idx = 0;
    //Time: O(V); Space: O(V)
    private void helper_dfs(List<Integer>[] graph, int[] status, int node, int[] res)  {
        status[node] = 1;
        for (int v: graph[node]) {
            if (status[v] == 0) helper_dfs(graph, status, v, res);
            else if (status[v] == 1) return; //cycle
        }
        status[node] = 2;
        res[idx++] = node;
    }

    //1.indegree
    //Runtime: 11 ms, faster than 67.19% of Java online submissions for Course Schedule II.
    //Memory Usage: 50.4 MB, less than 25.75% of Java online submissions for Course Schedule II.
    //Time: O(3V + E); Space: O(2V + E)
    //Time: O(V + E); Space: O(V + E)
    public int[] findOrder_1(int numCourses, int[][] prerequisites) {
        //Time: O(V); Space:O(V + E)
        int[] indegreeList = new int[numCourses];
        List<Integer>[] graph = new List[numCourses]; //E
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();

        //Time:O(E)
        for (int[] edge : prerequisites) {
            graph[edge[0]].add(edge[1]);
            indegreeList[edge[1]]++;
        }

        //Time: O(V); Space: O(V)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegreeList[i] == 0) queue.add(i);

        //Time: O(V)
        int idx = numCourses - 1;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()){
            int node = queue.poll();
            res[idx--] = node;
            for (int v : graph[node])
                if ( --indegreeList[v] == 0) queue.add(v);
        }
        return idx == -1 ? res : new int[0];
    }
}
