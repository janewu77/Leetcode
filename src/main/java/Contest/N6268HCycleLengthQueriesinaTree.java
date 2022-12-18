package Contest;

import java.io.IOException;
import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * You are given an integer n. There is a complete binary tree with 2n - 1 nodes. The root of that tree is the node with the value 1, and every node with a value val in the range [1, 2n - 1 - 1] has two children where:
 *
 * The left node has the value 2 * val, and
 * The right node has the value 2 * val + 1.
 * You are also given a 2D integer array queries of length m, where queries[i] = [ai, bi]. For each query, solve the following problem:
 *
 * Add an edge between the nodes with values ai and bi.
 * Find the length of the cycle in the graph.
 * Remove the added edge between nodes with values ai and bi.
 * Note that:
 *
 * A cycle is a path that starts and ends at the same node, and each edge in the path is visited only once.
 * The length of a cycle is the number of edges visited in the cycle.
 * There could be multiple edges between two nodes in the tree after adding the edge of the query.
 * Return an array answer of length m where answer[i] is the answer to the ith query.
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, queries = [[5,3],[4,7],[2,3]]
 * Output: [4,5,3]
 * Explanation: The diagrams above show the tree of 23 - 1 nodes. Nodes colored in red describe the nodes in the cycle after adding the edge.
 * - After adding the edge between nodes 3 and 5, the graph contains a cycle of nodes [5,2,1,3]. Thus answer to the first query is 4. We delete the added edge and process the next query.
 * - After adding the edge between nodes 4 and 7, the graph contains a cycle of nodes [4,2,1,3,7]. Thus answer to the second query is 5. We delete the added edge and process the next query.
 * - After adding the edge between nodes 2 and 3, the graph contains a cycle of nodes [2,1,3]. Thus answer to the third query is 3. We delete the added edge.
 * Example 2:
 *
 *
 * Input: n = 2, queries = [[1,2]]
 * Output: [2]
 * Explanation: The diagram above shows the tree of 22 - 1 nodes. Nodes colored in red describe the nodes in the cycle after adding the edge.
 * - After adding the edge between nodes 1 and 2, the graph contains a cycle of nodes [2,1]. Thus answer for the first query is 2. We delete the added edge.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 30
 * m == queries.length
 * 1 <= m <= 105
 * queries[i].length == 2
 * 1 <= ai, bi <= 2n - 1
 * ai != bi
 */

//2509. Cycle Length Queries in a Tree
public class N6268HCycleLengthQueriesinaTree {

    static public void main(String... args) throws IOException{
        System.out.println(now());
        System.out.println("==================");

        //System.out.println((int)(Math.log10(21) / Math.log10(2)));
        //[[17,21],[23,5],[15,7],[3,21],[31,9],[5,15],[11,2],[19,7]]

        doRun(new int[]{3}, 3, new int[][]{{4,5}});
        doRun(new int[]{7,3}, 5, new int[][]{{17,21},{23,5}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int[] expect, int n, int[][] queries) {
        int[] res = new N6268HCycleLengthQueriesinaTree().cycleLengthQueries(n, queries);
        boolean success = Arrays.equals(res, expect);
        System.out.println("["+success+"] expect:" + Arrays.toString(expect) + " res:" + Arrays.toString(res));
    }

    //Runtime: 15ms 100%; Memory: 105.7MB 100%
    //Time: O(L * logN); Space: O(1)
    //let L be the length of the input array.
    public int[] cycleLengthQueries(int n, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = 1;
            int n1 = queries[i][0], n2 = queries[i][1];
            //Time: O(log(max(n1, n2))
            while (n1 != n2) {
                if (n1 > n2) n1 = n1 >> 1;
                else n2 = n2 >> 1;
                res[i]++;
            }
        }
        return res;
    }

    //Runtime: 31ms 100%; Memory: 114.8MB 100%
    //Time: O(L * logN); Space: O(1)
    //let L be the length of the input array.
    public int[] cycleLengthQueries_1(int n, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            //Time: O(logN)
            int comm = nearestCommonAncestor(queries[i][0], queries[i][1]);
            res[i] = 1 + getLayer(queries[i][0]) + getLayer(queries[i][1]) - (getLayer(comm) << 1);
        }
        return res;
    }

    //Lowest Common Ancestor
    //Time: O(log(max(n1, n2)))
    private int nearestCommonAncestor(int n1, int n2){
        while (n1 != n2) {
            if (n1 > n2) n1 = n1 >> 1;
            else n2 = n2 >> 1;
        }
        return n1;
    }

    private int getLayer(int n){
        return (int) (Math.log10(n) / Math.log10(2));
    }
}


