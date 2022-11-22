package LeetCode;

import ADraft.TreeDemo.*;

import java.util.*;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 *
 *
 *
 * Example 1:
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 *
 *
 * Example 2:
 * Input: root = [1,null,3]
 * Output: [1,3]
 *
 *
 * Example 3:
 * Input: root = []
 * Output: []

 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 */

/**
 * M - 耗时 45+
 *  - 注意弄明白题意，在右边可见，可能会见到左子树哦！
 *  - 哨兵技巧
 */
public class N199MBinaryTreeRightSideView {

    public static void main(String[] args){

        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
//        TreeNode root = new ADraft.TreeDemo().buildTree(data);
//
//        List<Integer> result = new N199MBinaryTreeRightSideView().rightSideView(root);
//        result.stream().forEach(System.out::println);
    }

    //Runtime: 1 ms, faster than 95.06% of Java online submissions for Binary Tree Right Side View.
    //Memory Usage: 40.8 MB, less than 95.89% of Java online submissions for Binary Tree Right Side View.
    // 1 Deque + sentinel
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeNode sentinel = new TreeNode(200);

        Queue<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        dq.offer(sentinel);
        while (!dq.isEmpty()) {
            TreeNode node = dq.poll();

            if (node.left != null) dq.offer(node.left);
            if (node.right != null) dq.offer(node.right);

            TreeNode nextNode = dq.peek();
            if (nextNode != null && nextNode.equals(sentinel)) {
                result.add(node.val);
                dq.offer(sentinel);
                dq.poll();
            }
        }
        return result;
    }


}
