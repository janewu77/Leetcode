package LeetCode;

import ADraft.TreeDemo;

import java.util.*;

public class N429MNaryTreeLevelOrderTraversal {




    //Runtime: 2 ms, faster than 95.52% of Java online submissions for N-ary Tree Level Order Traversal.
    //Memory Usage: 43 MB, less than 98.21% of Java online submissions for N-ary Tree Level Order Traversal.
    //BFS + iteration
    //Time: O(N); Space: O(N)
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++){
                Node node = queue.poll();
                list.add(node.val);
                for (Node child : node.children) queue.add(child);
            }
            res.add(list);
        }
        return res;
    }


    //Runtime: 1 ms, faster than 99.75% of Java online submissions for N-ary Tree Level Order Traversal.
    //Memory Usage: 43.5 MB, less than 87.25% of Java online submissions for N-ary Tree Level Order Traversal.
    //dfs + recursion
    //Time: O(N); Space: O(N)
    //Map<Integer, List<Integer>> map = new HashMap<>();
    public List<List<Integer>> levelOrder_dfs(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper_dfs(root, 0, res);
        return res;
    }

    private void helper_dfs(Node node, int layer, List<List<Integer>> res){
        if (res.size() < layer + 1) res.add(new ArrayList<>());
        res.get(layer).add(node.val);
        for (Node child : node.children) helper_dfs(child, layer + 1, res);
    }



    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
