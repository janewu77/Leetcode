package LeetCode;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;


/**
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
 *
 * In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 1 and 2.
 * In the second semester, you can take course 3.
 * Example 2:
 *
 *
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: No course can be studied because they are prerequisites of each other.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 * 1 <= relations.length <= 5000
 * relations[i].length == 2
 * 1 <= prevCoursei, nextCoursei <= n
 * prevCoursei != nextCoursei
 * All the pairs [prevCoursei, nextCoursei] are unique.
 */

/**
 * M - [time: 40-
 */
public class N1136MParallelCourses {


    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        int[][] data = new int[][]{{5,10},{11,14},{21,22},{16,19},{21,25},{6,18},{1,9},{4,7},{10,23},{5,14},{9,18},{18,21},{11,22},{1,15},{1,2},{5,18},{7,20},{2,23},{12,13},{9,14},{10,16},{11,21},{5,12},{2,24},{8,17},{15,17},{10,13},{11,16},{20,22},{7,11},{9,15},{16,22},{18,20},{19,22},{10,18},{3,20},{16,25},{10,15},{1,23},{13,16},{23,25},{1,8},{4,10},{19,24},{11,20},{3,18},{6,25},{11,13},{13,15},{22,24},{6,24},{17,20},{2,25},{15,24},{8,21},{14,16},{5,16},{19,23},{1,5},{4,22},{19,20},{12,15},{16,18},{9,13},{13,22},{14,22},{2,8},{3,13},{9,23},{14,15},{14,17},{8,20},{9,17},{3,19},{8,25},{2,12},{7,24},{19,25},{1,13},{6,11},{14,21},{7,15},{3,14},{15,23},{10,17},{4,20},{6,14},{10,21},{2,13},{3,21},{8,11},{5,21},{6,23},{17,25},{16,21},{12,22},{1,16},{6,19},{7,25},{3,23},{11,25},{3,10},{6,7},{2,3},{5,25},{1,6},{4,17},{2,16},{13,17},{17,22},{6,13},{5,6},{4,11},{4,23},{4,8},{12,23},{7,21},{5,20},{3,24},{2,10},{13,14},{11,24},{1,3},{2,7},{7,23},{6,17},{5,17},{16,17},{8,15},{8,23},{7,17},{14,18},{16,23},{23,24},{4,12},{17,19},{5,9},{10,11},{5,23},{2,9},{1,19},{2,19},{12,20},{2,14},{11,12},{1,12},{13,23},{4,9},{7,13},{15,20},{21,24},{8,18},{9,11},{8,19},{6,22},{16,20},{22,25},{20,21},{6,16},{3,17},{1,22},{9,22},{20,24},{2,6},{9,16},{2,4},{2,20},{20,25},{9,10},{3,11},{15,18},{1,20},{3,6},{8,14},{10,22},{12,21},{7,8},{8,16},{9,20},{3,8},{15,21},{17,21},{11,18},{13,24},{17,24},{6,20},{4,15},{6,15},{3,22},{13,21},{2,22},{13,25},{9,12},{4,19},{1,24},{12,19},{5,8},{1,7},{3,16},{3,5},{12,24},{3,12},{2,17},{18,22},{4,25},{8,24},{15,19},{18,23},{1,4},{1,21},{10,24},{20,23},{4,14},{16,24},{10,20},{18,24},{1,14},{12,14},{10,12},{4,16},{5,19},{4,5},{19,21},{15,25},{1,18},{2,21},{4,24},{7,14},{4,6},{15,16},{3,7},{21,23},{1,17},{12,16},{13,18},{5,7},{9,19},{2,15},{22,23},{7,19},{17,23},{8,22},{11,17},{7,16},{8,9},{6,21},{4,21},{4,13},{14,24},{3,4},{7,18},{11,15},{5,11},{12,17},{6,9},{1,25},{12,18},{6,12},{8,10},{6,8},{11,23},{7,10},{14,25},{14,23},{12,25},{5,24},{10,19},{3,25},{7,9},{8,12},{5,22},{24,25},{13,19},{3,15},{5,15},{15,22},{10,14},{3,9},{13,20},{1,10},{9,21},{10,25},{9,24},{14,20},{9,25},{8,13},{7,12},{5,13},{6,10},{2,5},{2,18},{14,19},{1,11},{7,22},{18,25},{11,19},{18,19},{4,18},{17,18},{2,11}};
        doRun(25, 25, data);


        doRun(2, 3, new int[][]{{1,3}, {2,3}});


        doRun(-1, 4, new int[][]{{1,2}, {2,3}, {3,1}, {4,1}});
        doRun(-1, 3, new int[][]{{1,2}, {2,3}, {3,1}});



        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int n, int[][] relations) {
        int res = new N1136MParallelCourses().minimumSemesters(n, relations);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.DFS
    //Runtime: 5 ms, faster than 99.63% of Java online submissions for Parallel Courses.
    //Memory Usage: 43.1 MB, less than 97.06% of Java online submissions for Parallel Courses.
    //Time: O(V + E); Space: O(V + E)
    //let V be the number of courses; E be the length of relations
    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] relation : relations) graph.get(relation[0]).add(relation[1]);

        int[] color = new int[n + 1];
        int res = 1;
        for (int i = 1; i <= n; i++) {
            int currHeight = helper_DFS(graph, i, color);
            if (currHeight == -1) return -1;
            res = Math.max(res, currHeight);
        }
        return res;
    }

    private int helper_DFS(List<List<Integer>> graph, int course, int[] visited){
        if (visited[course] != 0) return visited[course];
        visited[course] = -1;
        int res = 1;
        for (int nextCourse: graph.get(course)) {
            int currHeight = helper_DFS(graph, nextCourse, visited);
            if (currHeight == -1) return -1;
            res = Math.max(res, 1 + currHeight);
        }
        return visited[course] = res;
    }


    //1.Kahn's algorithm
    //Runtime: 7 ms, faster than 93.87% of Java online submissions for Parallel Courses.
    //Memory Usage: 44.6 MB, less than 86.77% of Java online submissions for Parallel Courses.
    //Time: O(V + E); Space: O(V + E)
    //let V be the number of courses; E be the length of relations
    public int minimumSemesters_1(int n, int[][] relations) {
        //Time: O(V); Space: O(V + E)
        int[] indegree = new int[n + 1];
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new HashSet();

        //Time: O(E)
        for (int[] relation : relations) {
            indegree[relation[1]]++;
            graph[relation[0]].add(relation[1]);
        }

        //Time: O(V); Space: O(V);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++)
            if (indegree[i] == 0) queue.add(i);

        int res = 0, count = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int course = queue.poll();
                count++;
                for (int nextCourse : graph[course]) {
                    indegree[nextCourse]--;
                    if (indegree[nextCourse] == 0) queue.add(nextCourse);
                }
            }
            res++;
        }
        return  (count != n) ? -1 :res;
    }
}
