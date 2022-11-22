package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Serialization is the process of converting a data structure or object
 * into a sequence of bits so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same
 * or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string
 * can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with
 * different approaches yourself.
 *
 *
 *
 * Example 1:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 *
 */

/**
 *  H - [time: 60+]
 *
 */
public class N297HSerializeandDeserializeBinaryTree {

    public static void main(String[] args){
//        int xx = +9;
//        String str = String.valueOf(xx);
//        System.out.println(str);

//        int a = Integer.valueOf("-9");
//        int b = Integer.valueOf("+9");
//
//        System.out.println(a);
//        System.out.println(b);

        TreeNode root = new N297HSerializeandDeserializeBinaryTree().buildTree();

        String str = new N297HSerializeandDeserializeBinaryTree().serialize(root);
        System.out.println("serialize():"+str);

        TreeNode tmp = new N297HSerializeandDeserializeBinaryTree().deserialize(str);


        String strtmp = new N297HSerializeandDeserializeBinaryTree().serialize(tmp);
        System.out.println("serialize():"+strtmp);

    }

    public TreeNode buildTree(){
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        return n1;
    }

    //Runtime: 11 ms, faster than 91.19% of Java online submissions for Serialize and Deserialize Binary Tree.
    //Memory Usage: 42.9 MB, less than 99.91% of Java online submissions for Serialize and Deserialize Binary Tree.
    //save more space. zip '#'
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (node != null) {

            sb.append(node.val).append(',');
            stack.add(node.right);
            node = node.left;

            //left sub-tree is finished.
            if (node == null && !stack.isEmpty()) {
                int n = 0;
                while (node == null && !stack.isEmpty()) {
                    node = stack.pop();
                    n++;
                }
                sb.append('#').append(n).append('#');
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;

        Stack<TreeNode> stack = new Stack<>();
        boolean rightTree = false;
        TreeNode root = new TreeNode(0); //dummy
        TreeNode p = root;

        int i = 0;
        while (i < data.length()) {

            int nextI = data.indexOf(',', i);
            if (nextI == -1) break;

            TreeNode node = new TreeNode(Integer.valueOf(data.substring(i,nextI++)));
            if (rightTree) p.right = node;
            else p.left = node;
            rightTree = false;

            p = node;
            stack.add(p);
            i = nextI;

            if (i < data.length() && data.charAt(i) == '#'){
                rightTree = true;
                nextI = data.indexOf('#', i+1);
                int k = Integer.valueOf(data.substring(i+1, nextI++));
                for (; k > 0; k--) p = stack.pop();
                i = nextI;
            }
        }
        return root.left;
    }


//    //Runtime: 12 ms, faster than 87.75% of Java online submissions for Serialize and Deserialize Binary Tree.
//    //Memory Usage: 49 MB, less than 82.84% of Java online submissions for Serialize and Deserialize Binary Tree.
//
//    // pre-order.  '#' marks that the left sub-tree is finished.
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        if (root == null) return "";
//        Stack<TreeNode> stack = new Stack<>();
//
//        StringBuilder sb = new StringBuilder();
//        TreeNode node = root;
//        while (node != null) {
//
//            sb.append(node.val).append(',');
//
//            stack.add(node.right);
//            node = node.left;
//
//            //left sub-tree is finished.
//            while (node == null && !stack.isEmpty()) {
//                sb.append("#");
//                node = stack.pop();
//            }
//        }
//        return sb.toString();
//    }
//
//    // Decodes your encoded data to tree.
//    // 123|-3|#
//    public TreeNode deserialize(String data) {
//        if (data.isEmpty()) return null;
//
//        Stack<TreeNode> stack = new Stack<>();
//        boolean rightTree = false;
//        TreeNode root = new TreeNode(0); //dummy
//        TreeNode p = root;
//
//        int i = 0;
//        while (i < data.length()) {
//
//            int nextI = data.indexOf(',', i);
//            if (nextI == -1) break;
//
//            TreeNode node = new TreeNode(Integer.valueOf(data.substring(i,nextI++)));
//            if (rightTree) p.right = node;
//            else p.left = node;
//            rightTree = false;
//
//            p = node;
//            stack.add(p);
//            i = nextI;
//
//            while (i < data.length() && data.charAt(i) == '#'){
//                rightTree = true;
//                p = stack.pop();
//                i++;
//            }
//        }
//        return root.left;
//    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
