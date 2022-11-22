package LeetCode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition for a binary tree node.

 */

/**
 * m: (耗时60+）
 *  - 压缩val: 4位16进制
 */

/**
 *
 *
 * Runtime: 5 ms, faster than 95.85% of Java online submissions for Serialize and Deserialize BST.
 * Memory Usage: 42.6 MB, less than 98.80% of Java online submissions for Serialize and Deserialize BST.
 */
public class N449MSerializeandDeserializeBST {

    public static void main(String[] args){
        new N449MSerializeandDeserializeBST().demoCall1();
    }

    private String to4ByteHexString(int n){
        String s1 = Integer.toHexString(n);
        if (s1.length() < 4){
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<4-s1.length();i++) sb.append(" ");
            sb.append(s1);
            s1 = sb.toString();
        }
        return s1;
    }

    private int parseHex2Int(String str){
        return Integer.parseInt(str,16);
    }

    private void demoCall1(){

        Codec demo = new Codec();
//        System.out.println(demo.intToString(33));
//
//        String s1 = to4ByteHexString(200);
//        System.out.println(s1);
//        System.out.println(parseHex2Int(s1.trim()));
//        System.out.println(to4ByteHexString(10000));

        List<Integer> data = Arrays.asList(8, 4 ,2 ,10 , 9 ,12 ,14);

        TreeNode root = demo.buildBSTree(data);

        System.out.println("========================================");
        Codec ser = new Codec();
        Codec deser = new Codec();
        String tree = ser.serialize(root);
        System.out.println("serialize:"+tree);
        TreeNode ans = deser.deserialize(tree);
        System.out.println("deserialize:"+ans.toString());
    }

     class Codec{
        public Codec(){}

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            inorderVisit(root);
            return sbVisitResults.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) return null;

            List<Integer> dataList = new ArrayList<>();
            for(int i=0; i <= data.length()-4; i=i+4){
                int strVal = Integer.parseInt(data.substring(i, i+4).trim(), 16);
                dataList.add(strVal);
            }
            TreeNode resultTree = buildBSTree(dataList);
            return resultTree;
        }

        private StringBuilder sbVisitResults = new StringBuilder();
        public void inorderVisit(TreeNode root){
            sbVisitResults.append(to4ByteHexString(root.val));
            if (root.left != null) inorderVisit(root.left);
            if (root.right != null) inorderVisit(root.right);
        }

        //将数字转成4位的16进制
         private String to4ByteHexString(int n){
//             String s1 = ;

             String s1 = String.format("%4s", Integer.toHexString(n)); //25为int型
//             if (s1.length() < 4){
//                 StringBuilder sb = new StringBuilder();
//                 for (int i=0; i<4-s1.length();i++) sb.append(" ");
//                 sb.append(s1);
//                 s1 = sb.toString();
//             }
             return s1;
         }

         public TreeNode buildBSTree(List<Integer> data){
             TreeNode root = new TreeNode(data.get(0));
             for (int i : data)
                 searchAndInsertBSTree(root, new TreeNode(i));
             return root;
         }

         private void searchAndInsertBSTree(TreeNode tree, TreeNode newNode){
             if (newNode.val == tree.val) return;
             if (newNode.val < tree.val) {
                 if (tree.left == null){
                     tree.left = newNode;
                     return;
                 }else {
                     searchAndInsertBSTree(tree.left, newNode);
                 }
             }else{
                 if (tree.right == null){
                     tree.right = newNode;
                     return;
                 }else {
                     searchAndInsertBSTree(tree.right, newNode);
                 }
             }
         }
     }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}



