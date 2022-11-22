package Contest;

import ADraft.TreeDemo.*;

import java.util.*;

import static java.time.LocalTime.now;


/**
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
 *
 * For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
 * Return the root of the reversed tree.
 *
 * A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
 *
 * The level of a node is the number of edges along the path between it and the root node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,3,5,8,13,21,34]
 * Output: [2,5,3,8,13,21,34]
 * Explanation:
 * The tree has only one odd level.
 * The nodes at level 1 are 3, 5 respectively, which are reversed and become 5, 3.
 * Example 2:
 *
 *
 * Input: root = [7,13,11]
 * Output: [7,11,13]
 * Explanation:
 * The nodes at level 1 are 13, 11, which are reversed and become 11, 13.
 * Example 3:
 *
 * Input: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * Output: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * Explanation:
 * The odd levels have non-zero values.
 * The nodes at level 1 were 1, 2, and are 2, 1 after the reversal.
 * The nodes at level 3 were 1, 1, 1, 1, 2, 2, 2, 2, and are 2, 2, 2, 2, 1, 1, 1, 1 after the reversal.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 214].
 * 0 <= Node.val <= 105
 * root is a perfect binary tree.
 */

/**
 * M - [time: 20-
 */
//2415. Reverse Odd Levels of Binary Tree
public class N6182MReverseOddLevelsofBinaryTree {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
//        int res = new C03()
//                .ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 14 ms, faster than 61.54% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //Memory Usage: 48.8 MB, less than 84.62% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //DFS + iteration
    //Time: O(N); Space:O(N)
    public TreeNode reverseOddLevels_5(TreeNode root) {
        if (root.left == null) return root;

        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> stackLevel = new LinkedList<>(); //tracking node's level

        stack.add(root.left);
        stack.add(root.right);
        stackLevel.push(1);

        while (!stackLevel.isEmpty()){
            int level = stackLevel.pop();

            TreeNode leftNode = stack.pop();
            TreeNode rightNode = stack.pop();

            if (level % 2 == 1) {
                //reverse
                int tmp = rightNode.val;
                rightNode.val = leftNode.val;
                leftNode.val = tmp;
            }

            //all parent nodes have two children in a perfect binary tree
            if (leftNode.left != null) {
                stack.add(leftNode.right);
                stack.add(rightNode.left);

                stack.add(leftNode.left);
                stack.add(rightNode.right);

                stackLevel.add(level + 1);
                stackLevel.add(level + 1);
            }
        }
        return root;
    }


    //Runtime: 1 ms, faster than 84.62% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //Memory Usage: 48.9 MB, less than 84.62% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //DFS + recursion
    //Time: O(N); Space:O(N)
    public TreeNode reverseOddLevels_4(TreeNode root) {
        helper_dfs(root.left, root.right, 1);
        return root;
    }
    private void helper_dfs(TreeNode leftNode, TreeNode rightNode, int level){
        if (leftNode == null || rightNode == null) return;

        if (level % 2 == 1) {
            int tmp = rightNode.val;
            rightNode.val = leftNode.val;
            leftNode.val = tmp;
        }

        //from outside to inside
        helper_dfs(leftNode.left, rightNode.right, level + 1);
        helper_dfs(leftNode.right, rightNode.left, level + 1);
    }

    //不翻转。 用二次进队，来进行翻转。
    //Runtime: 30 ms, faster than 53.85% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //Memory Usage: 77.5 MB, less than 7.69% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //BFS : queue + int array
    public TreeNode reverseOddLevels_3(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int layer = 1;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();

            int[] list = null;
            if (layer % 2 == 0) {
                //collect node.val
                list = new int[queueSize];
                for (int i = 0; i < queueSize; i++) {
                    TreeNode node = queue.poll();
                    list[i]= node.val;
                    queue.add(node); //in queue again
                }
            }

            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                if (layer % 2 == 0)
                    node.val = list[list.length - 1 - i]; //update val
            }

            layer++;
        }
        return root;
    }


    //用 数组 反转
    //Runtime: 15 ms, faster than 53.85% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //Memory Usage: 76.5 MB, less than 7.69% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //BFS + Queue + array reverse (TreeNode array)
    //Time:O(N + N/2); Space: O(N + N/2)
    //Time:O(N); Space: O(N)
    public TreeNode reverseOddLevels_2(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int layer = 1;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();

            TreeNode[] list = null;
            if (layer % 2 == 0) list = new TreeNode[queueSize];

            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                if (layer % 2 == 0) list[i]= node;
            }

            if (layer % 2 == 0) reverse(list);
            layer++;
        }
        return root;
    }

    private void reverse(TreeNode[] list){
        for (int i = 0; i < list.length / 2; i++){
            int tmp = list[i].val;
            int j = list.length - 1 - i;
            list[i].val = list[j].val;
            list[j].val = tmp;
        }
    }

    //用 deque 反转
    //Runtime: 10 ms, faster than 69.23% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //Memory Usage: 45.6 MB, less than 84.62% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //BFS + Queue + deque
    //Time:O(N + N/2); Space: O(N + N)
    //Time:O(N); Space: O(N)
    public TreeNode reverseOddLevels_1(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Deque<TreeNode> deque = new LinkedList<>(); //ArrayDeque

        int layer = 1;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                if (layer % 2 == 0) deque.add(node);
            }

            if (layer % 2 == 0) {
                while (!deque.isEmpty()) {
                    int tmp = deque.peekFirst().val;
                    deque.peekFirst().val = deque.peekLast().val;
                    deque.peekLast().val = tmp;
                    deque.pollFirst();
                    if (!deque.isEmpty()) deque.pollLast();
                }
            }
            layer++;
        }
        return root;
    }


    //Runtime: 12 ms, faster than 69.23% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //Memory Usage: 46.3 MB, less than 84.62% of Java online submissions for Reverse Odd Levels of Binary Tree.
    //BFS Deque
    //Time:O(N); Space: O(N)
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return root;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addFirst(root.left);
        queue.addLast(root.right);

        int layer = 1;
        while (!queue.isEmpty()) {
            Deque<TreeNode> nextLevel = new ArrayDeque<>();
            while (!queue.isEmpty()) {

                TreeNode leftNode = queue.pollFirst();
                TreeNode rightNode = queue.pollLast();

                if ((layer & 1) == 1){
                    int tmp = leftNode.val;
                    leftNode.val = rightNode.val;
                    rightNode.val = tmp;
                }

                if (leftNode.right != null) {

                    nextLevel.addFirst(leftNode.right);
                    nextLevel.addLast(rightNode.left);

                    nextLevel.addFirst(leftNode.left);
                    nextLevel.addLast(rightNode.right);
                }
            }
            layer++;
            queue = nextLevel;
        }
        return root;
    }
}
