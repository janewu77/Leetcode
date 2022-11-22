package LeetCode;


import ADraft.TreeDemo.*;
import javafx.util.Pair;

import java.util.*;


/**
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions
 * (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings
 * for each column index starting from the leftmost column and ending on the rightmost column.
 * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 * Example 2:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 * Example 3:
 *
 *
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location
 * and should be ordered by their values.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 */

/**
 * H - [time: 45-
 */

public class N987HVerticalOrderTraversalofaBinaryTree {

    //Runtime: 4 ms, faster than 83.66% of Java online submissions for Vertical Order Traversal of a Binary Tree.
    //Memory Usage: 42.3 MB, less than 94.80% of Java online submissions for Vertical Order Traversal of a Binary Tree.
    //BFS + Collections.sort
    //Time: O(NlogN + N);  Space: O(N)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        //Space: O(N)
        Map<Integer, List<Integer>> map = new HashMap<>(); //column :

        //Space: O(N/2)
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0)); //node, column

        //BFS
        int minColumn = 0, maxColumn = 0;
        int row = 1;
        //Time: O(N)
        while (!queue.isEmpty()){
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Pair<TreeNode, Integer> pair = queue.poll();
                TreeNode node = pair.getKey();
                int column = pair.getValue();

                minColumn = Math.min(minColumn, column);
                maxColumn = Math.max(maxColumn, column);

                List<Integer> list = map.getOrDefault(column, new ArrayList<>());
                map.put(column, list);
                list.add(row * 10_000 + node.val); //row ,val

                if (node.left != null) queue.add(new Pair<>(node.left, column - 1));
                if (node.right != null) queue.add(new Pair<>(node.right, column + 1));
            }
            row++;
        }

        //result
        List<List<Integer>> res = new ArrayList<>();
        for (int i = minColumn; i <= maxColumn; i++){
            //sort
            List<Integer> orderedList = map.get(i);
            orderedList.sort(Comparator.comparingInt(o -> o));

            List<Integer> list = new ArrayList<>();
            for (int data : orderedList) list.add(data % 10_000);
            //map.get(i).stream().sorted().forEach(v -> list.add(v % 10_000));
            res.add(list);
        }
        return res;
    }

}
