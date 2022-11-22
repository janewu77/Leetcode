package LeetCode;

import java.util.*;


/**
 * M - [Time: 30-
 */
public class N797MAllPathsFromSourcetoTarget {

    //2.BFS
    //Runtime: 5 ms, faster than 53.15% of Java online submissions for All Paths From Source to Target.
    //Memory Usage: 43.8 MB, less than 98.73% of Java online submissions for All Paths From Source to Target.
    //Time: O( 2^V * V); Space: O( 2^V * V);
    //V represents the number of vertices;
    // E represents the number of edges.
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(0));

        while (!queue.isEmpty()) {
            List<Integer> list = queue.poll();
            int node = list.get(list.size() - 1);
            for (int child: graph[node]) {

                List<Integer> listChild = new ArrayList<>(list);
                listChild.add(child);

                if (child == graph.length - 1) res.add(listChild);
                else queue.offer(listChild);
            }
        }
        return res;
    }


    //1.DFS
    //Runtime: 2 ms, faster than 93.42% of Java online submissions for All Paths From Source to Target.
    //Memory Usage: 43.5 MB, less than 99.82% of Java online submissions for All Paths From Source to Target.
    //DFS backtracking
    //Time: O(2^V * V); Space:O(V)
    //V represents the number of vertices.
    public List<List<Integer>> allPathsSourceTarget_1(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0);

        helper_backtracking(graph, 0,  res, list);
        return res;
    }

    private void helper_backtracking(int[][] graph, int source,
                        List<List<Integer>> res, LinkedList<Integer> list){
        if (source == graph.length - 1) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int neighbour: graph[source]) {
            list.add(neighbour);
            helper_backtracking(graph, neighbour, res, list);
            list.removeLast();
        }
    }

}
