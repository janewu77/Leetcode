package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 * Example 2:
 *
 *
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 *
 *
 * Constraints:
 *
 * n == parent.length == s.length
 * 1 <= n <= 105
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 */
public class N2246HLongestPathWithDifferentAdjacentCharacters {

    //
    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        int[] parent;
        doRun(2, new int[]{-1,0,1}, "aab");
        doRun(3, new int[]{-1,0,0,1,1,2}, "abacbe");
        doRun(3, new int[]{-1,0,0,0}, "aabc");

        parent = new int[]{-1,137,65,60,73,138,81,17,45,163,145,99,29,162,19,20,132,132,13,60,21,18,155,65,13,163,125,102,96,60,50,101,100,86,162,42,162,94,21,56,45,56,13,23,101,76,57,89,4,161,16,139,29,60,44,127,19,68,71,55,13,36,148,129,75,41,107,91,52,42,93,85,125,89,132,13,141,21,152,21,79,160,130,103,46,65,71,33,129,0,19,148,65,125,41,38,104,115,130,164,138,108,65,31,13,60,29,116,26,58,118,10,138,14,28,91,60,47,2,149,99,28,154,71,96,60,106,79,129,83,42,102,34,41,55,31,154,26,34,127,42,133,113,125,113,13,54,132,13,56,13,42,102,135,130,75,25,80,159,39,29,41,89,85,19};
        doRun(17, parent, "ajunvefrdrpgxltugqqrwisyfwwtldxjgaxsbbkhvuqeoigqssefoyngykgtthpzvsxgxrqedntvsjcpdnupvqtroxmbpsdwoswxfarnixkvcimzgvrevxnxtkkovwxcjmtgqrrsqyshxbfxptuvqrytctujnzzydhpal");

        System.out.println(now());
        System.out.println("==================");
    }

    //92
    static private void doRun(int expect, int[] parent, String s) {
        int res = new N2246HLongestPathWithDifferentAdjacentCharacters().longestPath(parent, s);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.DFS
    //Runtime: 88 ms 92%; Memory: 84.1MB 93%
    //Time: O(N); Space: O(N)
    public int longestPath(int[] parent, String s) {
        List<Integer>[] graph = new List[parent.length];
        for(int i = 0; i < parent.length; i++)
            graph[i] = new ArrayList<>();

        for(int i = 1; i < parent.length; i++)
            graph[parent[i]].add(i);

        helper_DFS(graph, 0, -1, s.toCharArray());
        return longestPath;
    }
    private int longestPath = 1;
    private int helper_DFS(List<Integer>[] graph, int node, int parent, char[] s){
        int currMax = 0;
        for (int neighbour : graph[node]) {
            if (neighbour == parent) continue;

            int subChain = helper_DFS(graph, neighbour, node, s) + 1;
            if (s[node] == s[neighbour]) continue;

            longestPath = Math.max(longestPath, 1 + currMax + subChain);
            currMax = Math.max(currMax, subChain);
            longestPath = Math.max(longestPath, currMax);
        }
        return currMax;
    }

    //1.topological sort
    //Runtime: 35ms 98%; Memory: 54.6MB 99%
    //Time: O(N); Space: O(N)
    public int longestPath_1(int[] parent, String s) {
        int[] indegree = new int[parent.length];
        for (int i = 1; i < parent.length; i++)
            indegree[parent[i]]++;

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < indegree.length ; i++) {
            if (indegree[i] != 0) continue;
            //leaf node
            queue.add(i);
        }

        int res = 1;
        //node's the longest chain in one sub-tree
        int [] counter = new int[parent.length];

        while (!queue.isEmpty()){
            int i = queue.poll();
            int p = parent[i];

            if (p != -1 && (--indegree[p]) == 0)
                queue.add(p);

            if (p == -1 || s.charAt(p) == s.charAt(i)) continue;

            int currCount = 1 + counter[i];
            int pathLen = 1 + counter[p] + currCount;//path : include two chains
            res = Math.max(res, pathLen);

            counter[p] = Math.max(counter[p], currCount);
            res = Math.max(res, counter[p]);
        }
        return res;
    }
}
