package LeetCode;


import java.util.*;

/**
 * You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 *
 * Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 *
 * When a process is killed, all of its children processes will also be killed.
 *
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 * Example 2:
 *
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * n == pid.length
 * n == ppid.length
 * 1 <= n <= 5 * 104
 * 1 <= pid[i] <= 5 * 104
 * 0 <= ppid[i] <= 5 * 104
 * Only one process has no parent.
 * All the values of pid are unique.
 * kill is guaranteed to be in pid.
 */

/**
 *
 * M - [time: 10-
 */
public class N582MKillProcess {


    //Runtime: 80 ms, faster than 16.27% of Java online submissions for Kill Process.
    //Memory Usage: 79.8 MB, less than 21.98% of Java online submissions for Kill Process.
    //Tree + DFS
    //Time: O(N + N); Space: O(N + N)
    //Time: O(N); Space: O(N)
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {

        Map<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            if (!map.containsKey(pid.get(i))) map.put(pid.get(i), new TreeNode(pid.get(i)));
            TreeNode child = map.get(pid.get(i));

            TreeNode parent = map.getOrDefault(ppid.get(i), new TreeNode(ppid.get(i)));
            map.put(ppid.get(i), parent);
            parent.children.add(child);
        }

        List<Integer> res = new ArrayList<>();
        helper_dfs2(map.get(kill), res);
        return res;
    }
    class TreeNode {
        int val;
        List<TreeNode> children = new ArrayList<>();
        TreeNode(int val) {
            this.val = val;
        }
    }
    private void helper_dfs2(TreeNode killNode, List<Integer> res){
        res.add(killNode.val);
        for (TreeNode child : killNode.children)
            helper_dfs2(child, res);
    }

    //Runtime: 75 ms, faster than 20.13% of Java online submissions for Kill Process.
    //Memory Usage: 68.7 MB, less than 80.20% of Java online submissions for Kill Process.
    //DFS + recursion
    //Time: O(N + N); Space: O(N + N)
    //Time: O(N); Space: O(N)
    public List<Integer> killProcess_2(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            List<Integer> list = map.getOrDefault(ppid.get(i), new ArrayList<>());
            map.put(ppid.get(i), list);
            list.add(pid.get(i));
        }

        helper_dfs(map, kill, res);
        return res;
    }

    private void helper_dfs(Map<Integer, List<Integer>> map, int kill, List<Integer> res){
        res.add(kill);
        if (map.containsKey(kill)){
            List<Integer> list = map.get(kill);
            for (int child: list) helper_dfs(map, child, res);
        }
    }

    //Runtime: 72 ms, faster than 23.99% of Java online submissions for Kill Process.
    //Memory Usage: 69.3 MB, less than 77.35% of Java online submissions for Kill Process.
    //DFS + iteration
    //Time: O(N + N); Space: O(N + N)
    //Time: O(N); Space: O(N)
    public List<Integer> killProcess_1(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> res = new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            List<Integer> list = map.getOrDefault(ppid.get(i), new ArrayList<>());
            map.put(ppid.get(i), list);
            list.add(pid.get(i));
        }

        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(kill);
        while (!stack.isEmpty()){
            int p = stack.pop();
            res.add(p);
            if (map.containsKey(p)){
                List<Integer> list = map.get(p);
                for (int child: list) stack.add(child);
            }
        }
        return res;
    }

}
