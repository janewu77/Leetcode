package LeetCode;


import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * 399. Evaluate Division
 * Medium
 *
 * 6459
 *
 * 552
 *
 * Add to List
 *
 * Share
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 *
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 *
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */

/**
 * M - [time: 120
 *
 */
public class N399MEvaluateDivision {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        List<List<String>> equations;
        double[] values;
        List<List<String>> queries;
        double[] expect;

        equations = new ArrayList<>();
        equations.add(Arrays.asList("a","b"));
        equations.add(Arrays.asList("e","f"));
        equations.add(Arrays.asList("b","e"));

        values = new double[]{3.4,1.4,2.3};

        queries = new ArrayList<>();
        queries.add(Arrays.asList("b","a"));
        queries.add(Arrays.asList("a","f"));
        queries.add(Arrays.asList("f","f"));
        queries.add(Arrays.asList("e","e"));
        queries.add(Arrays.asList("c","c"));
        queries.add(Arrays.asList("a","c"));
        queries.add(Arrays.asList("f","e"));

        expect = new double[]{0.29412,10.94800,1.00000,1.00000,-1.00000,-1.00000,0.71429};
        doRun(expect, equations, values, queries);

        equations = new ArrayList<>();
        equations.add(Arrays.asList("x1","x2"));
        equations.add(Arrays.asList("x2","x3"));
        equations.add(Arrays.asList("x1","x4"));
        equations.add(Arrays.asList("x2","x5"));
        values = new double[]{3.0,0.5,3.4,5.6};
        queries = new ArrayList<>();
        queries.add(Arrays.asList("x2","x4"));
        queries.add(Arrays.asList("x1","x5"));
        queries.add(Arrays.asList("x1","x3"));
        queries.add(Arrays.asList("x5","x5"));
        queries.add(Arrays.asList("x5","x1"));
        queries.add(Arrays.asList("x3","x4"));
        queries.add(Arrays.asList("x4","x3"));
        queries.add(Arrays.asList("x6","x6"));
        queries.add(Arrays.asList("x0","x0"));

        expect = new double[]{1.13333,16.80000,1.50000,1.00000,0.05952,2.26667,0.44118,-1.00000,-1.00000};
        doRun(expect, equations, values, queries);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(double[] expect, List<List<String>> equations, double[] values, List<List<String>> queries) {
        System.out.println("==================");
        double[] res = new N399MEvaluateDivision()
                .calcEquation(equations, values, queries);
        if (expect.length != res.length){
            System.out.println("[wrong!]"+ expect.length +"|"+ res.length);
            return;
        }

        for (int i = 0; i < expect.length; i++){
            System.out.println("["+(Math.abs(expect[i] - res[i]) <= 0.001) +"]  expect:" + expect[i] + " res:" + res[i]);
        }
        System.out.println("==================");
    }

    //Runtime: 2 ms, faster than 72.51% of Java online submissions for Evaluate Division.
    //Memory Usage: 43.4 MB, less than 5.65% of Java online submissions for Evaluate Division.
    //Time: O(N + M * N); Space: O(N)
    //Time: O(M * N); Space: O(N)
    //Let N be the number of input equations and M be the number of queries.
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];

        //Space: O(2N)
        Map<String, Map<String, Double>> graph = new HashMap<>();

        //Time: O(N);
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);

            Map<String, Double> neighbour = graph.getOrDefault(equation.get(0), new HashMap<>());
            graph.put(equation.get(0), neighbour);
            neighbour.put(equation.get(1), values[i]);

            neighbour = graph.getOrDefault(equation.get(1), new HashMap<>());
            graph.put(equation.get(1), neighbour);
            neighbour.put(equation.get(0), 1 / values[i]);
        }

        //Time: O(M * N); Space: O(N)
        int idx = 0;
        for (List<String> query : queries)
            res[idx++] = isConnected_helper_dfs(graph, query.get(0), query.get(1));

        return res;
    }

    //DFS
    //Time : O(N); Space: O(N)
    private double isConnected_helper_dfs(Map<String, Map<String, Double>> graph, String node1, String node2){
        if (!graph.containsKey(node1) || !graph.containsKey(node2)) return -1;
        if (node1.equals(node2)) return 1d;

        //Space: worst case: O(N)
        Set<String> seen = new HashSet<>();

        //Space: worst case: O(N)
        Deque<Pair<String, Double>> stack = new ArrayDeque();
        stack.push(new Pair<>(node1, 1d));

        //Time : O(N)
        while (!stack.isEmpty()) {

            Pair<String, Double> pair = stack.pop();
            if (seen.contains(pair.getKey())) continue;

            Map<String, Double> neighbours = graph.get(pair.getKey());
            if (neighbours.containsKey(node2))
                return neighbours.get(node2) * pair.getValue();

            for (String key : neighbours.keySet())
                if (!seen.contains(key))
                    stack.add(new Pair<>(key, neighbours.get(key) * pair.getValue()));

                seen.add(pair.getKey());
        }
        return -1;
    }

    //https://leetcode.com/problems/evaluate-division/discuss/270993/Python-BFS-and-UF(detailed-explanation)
    //Runtime: 1 ms, faster than 99.46% of Java online submissions for Evaluate Division.
    //Memory Usage: 40.5 MB, less than 99.11% of Java online submissions for Evaluate Division.
    //Union find
    //Time: O((M+N) * log∗N); O(N)
    //let N be the number of input equations and M be the number of queries.
    public double[] calcEquation_1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] res = new double[queries.size()];

        //Time: O(N * )
        //build the union groups
        UnionFind uf = new UnionFind();
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            uf.union(equation.get(0), equation.get(1), values[i]);
        }

        //Time: O(M * )
        int idx = 0;
        for (List<String> query : queries) {

            Pair<String, Double> pair0 = uf.find(query.get(0));
            Pair<String, Double> pair1 = uf.find(query.get(1));

            if (pair0 != null && pair1 != null && pair0.getKey().equals(pair1.getKey()))
                res[idx++] = pair0.getValue() / pair1.getValue();
            else
                res[idx++] = -1d;
        }
        return res;
    }

    class UnionFind {
        Map<String, Pair<String, Double>> data = new HashMap<>();

        //Time : O(log∗N)
        public Pair<String, Double> find(String x) {
            if (!data.containsKey(x)) return null;

            Pair<String, Double> group = data.get(x);

            if (!group.getKey().equals(x)) {
                Pair<String, Double> newGroup = find(group.getKey());
                group = new Pair<>(newGroup.getKey(), group.getValue() * newGroup.getValue());
                data.put(x, group); //update group
            }
            return group;
        }

        //Time : O(log∗N)
        public void union(String x, String y, double value){
            if (!data.containsKey(x)) data.put(x, new Pair<>(x, 1d));
            if (!data.containsKey(y)) data.put(y, new Pair<>(y, 1d));

            Pair<String, Double> groupX = find(x);
            Pair<String, Double> groupY = find(y);

            if (groupX.getKey().equals(groupY.getKey())) return;

            Pair<String, Double> pair = new Pair<>(groupY.getKey(), value * groupY.getValue() / groupX.getValue());
            data.put(groupX.getKey(), pair);
        }
    }
}
