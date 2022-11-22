package LeetCode;


import ADraft.TreeDemo.TreeNode;

/**
 * Given the root of a binary tree, construct a string consisting of parenthesis and integers
 * from a binary tree with the preorder traversal way, and return it.
 *
 * Omit all the empty parenthesis pairs that do not affect the one-to-one mapping relationship
 * between the string and the original binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4]
 * Output: "1(2(4))(3)"
 * Explanation: Originally, it needs to be "1(2(4)())(3()())", but you need to omit all
 * the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)"
 * Example 2:
 *
 *
 * Input: root = [1,2,3,null,4]
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example, except we cannot omit the first parenthesis pair
 * to break the one-to-one mapping relationship between the input and the output.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -1000 <= Node.val <= 1000
 */

/**
 * E- [time: 15-
 */
public class N606EConstructStringfromBinaryTree {

    //Runtime: 2 ms, faster than 98.77% of Java online submissions for Construct String from Binary Tree.
    //Memory Usage: 42.1 MB, less than 98.01% of Java online submissions for Construct String from Binary Tree.
    //DFS + recursion
    //Time: O(N); Space:O(N)
    public String tree2str(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        helper_dfs(root, sb);
        return sb.toString();
    }

    private void helper_dfs(TreeNode node, StringBuilder sb){
        sb.append(node.val);
        if (node.left == null && node.right == null) return;

        sb.append("(");
        if (node.left != null) helper_dfs(node.left, sb);
        sb.append(")");

        if (node.right != null) {
            sb.append("(");
            helper_dfs(node.right, sb);
            sb.append(")");
        }
    }

}
