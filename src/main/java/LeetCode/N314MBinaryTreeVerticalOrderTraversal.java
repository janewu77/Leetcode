package LeetCode;


import javafx.util.Pair;

import java.util.*;

/**
 *
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values.
 * (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Example 2:
 *
 *
 * Input: root = [3,9,8,4,0,1,7]
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Example 3:
 *
 *
 * Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 * Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */

/**
 *
 * M - [Time: 90+
 *
 */
public class N314MBinaryTreeVerticalOrderTraversal {

    //[1,2,3,4,5,6,null,null,7,8,null,null,9,null,10,null,11,10]

    //Runtime: 2 ms, faster than 99.64% of Java online submissions for Binary Tree Vertical Order Traversal.
    //Memory Usage: 42.8 MB, less than 82.99% of Java online submissions for Binary Tree Vertical Order Traversal.
    //BFS + without sort
    //Time : O(N); Space: worst case O(N)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        final int MAX_NODE = 100;
        //Space: O(N)
        //List<Integer>[] data = new List[MAX_NODE * 2];

        //Space: worst case: O(N)
        int min_column = Integer.MAX_VALUE;
        int max_column = Integer.MIN_VALUE;
        Map<Integer, List<Integer>> data = new HashMap<>();

        //BFS
        //Space: O(lgN)
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.add(new Pair<>(MAX_NODE, root)); // column = 100

        //Time: O(N)
        while(!queue.isEmpty()){
            Pair<Integer, TreeNode> pair = queue.poll();
            int column = pair.getKey();

            //List<Integer> list = data[column] != null ? data[column] : (data[column] = new ArrayList<>());
            List<Integer> list = data.getOrDefault(column, new ArrayList<>());
            data.put(column, list);
            list.add(pair.getValue().val);

            min_column = Math.min(min_column, column);
            max_column = Math.max(max_column, column);

            TreeNode node = pair.getValue();
            if (node.left != null) queue.add(new Pair<>((column - 1), node.left));
            if (node.right != null) queue.add(new Pair<>((column + 1), node.right));
        }

        //for (List<Integer> list : data) if (list != null)  res.add(list);
        for (int i = min_column; i <= max_column; i++) res.add(data.get(i));
        return res;
    }



    //////////////////////////////////////////////////////////////////////////////////
    //Runtime: 5 ms, faster than 54.99% of Java online submissions for Binary Tree Vertical Order Traversal.
    //Memory Usage: 42.6 MB, less than 87.21% of Java online submissions for Binary Tree Vertical Order Traversal.
    //through inorder_traversal to collect necessary information(column:layer:z-index), then sort them.
    //in-order + sort
    //Time : O(NLogN); Space: O(N)
    public List<List<Integer>> verticalOrder_1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        //Space: O(N)
        //Pair: [key: column:layer:z-index]; [value : node.val]
        List<Pair<Integer, Integer>> nodeList = new LinkedList<>();
        //O(LogN)
        inorder_traversal(root, 0, 0, nodeList);

        //sort by column, layer, z-index
        //O(NLogN)
        Collections.sort(nodeList, Comparator.comparingInt(Pair::getKey));

        //output
        List<Integer> tmpList = new ArrayList<>();
        int lastColumn = -1;
        for (Pair<Integer, Integer> pair : nodeList) {
            int column = pair.getKey() / COLUMNBASE - OFFSET;

            //new column
            if (column != lastColumn && tmpList.size() > 0){
                res.add(new ArrayList<>(tmpList));
                tmpList = new ArrayList<>();
            }
            tmpList.add(pair.getValue());
            lastColumn = column;
        }
        res.add(new ArrayList<>(tmpList));
        return res;
    }

    private final int OFFSET = 200;
    private final int COLUMNBASE = 1000 * 1000;
    private int z_index = 0;
    private void inorder_traversal(TreeNode root, int layer, int column, List<Pair<Integer, Integer>> list){
        if (root == null) return;

        //child's layer is lager than parent's
        //left child's column is less than Parent's ; right child's column is larger than parent's.
        inorder_traversal(root.left, layer + 1, column - 1, list);

        //combination: column , layer, z-index
        int extraInfo = z_index++;
        extraInfo += (layer) * 1000;
        extraInfo += (column + OFFSET) * COLUMNBASE;
        list.add(new Pair(extraInfo, root.val));

        inorder_traversal(root.right ,layer + 1, column + 1, list);
    }



    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
