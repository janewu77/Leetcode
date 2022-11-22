package Contest;

import ADraft.TreeDemo.*;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;


/**
 * You are given the root of a binary tree with unique values.
 *
 * In one operation, you can choose any two nodes at the same level and swap their values.
 *
 * Return the minimum number of operations needed to make the values at each level sorted in a strictly increasing order.
 *
 * The level of a node is the number of edges along the path between it and the root node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,4,3,7,6,8,5,null,null,null,null,9,null,10]
 * Output: 3
 * Explanation:
 * - Swap 4 and 3. The 2nd level becomes [3,4].
 * - Swap 7 and 5. The 3rd level becomes [5,6,8,7].
 * - Swap 8 and 7. The 3rd level becomes [5,6,7,8].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * Example 2:
 *
 *
 * Input: root = [1,3,2,7,6,5,4]
 * Output: 3
 * Explanation:
 * - Swap 3 and 2. The 2nd level becomes [2,3].
 * - Swap 7 and 4. The 3rd level becomes [4,6,5,7].
 * - Swap 6 and 5. The 3rd level becomes [4,5,6,7].
 * We used 3 operations so return 3.
 * It can be proven that 3 is the minimum number of operations needed.
 * Example 3:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 0
 * Explanation: Each level is already sorted in increasing order so return 0.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 1 <= Node.val <= 105
 * All the values of the tree are unique.
 */

/**
 * M - [time: 120+
 */

//2471. Minimum Number of Operations to Sort a Binary Tree by Level
public class N6235MMinimumNumberofOperationstoSortaBinaryTreebyLevel {


     static public void main(String... args) throws IOException{
         System.out.println(now());
         System.out.println("==================");
//
//        System.out.println(a);
//        System.out.println(A);
//        //doRun(0, 0);
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int n) {
//        long res = new C0806().n1(n);

//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1.BFS
    //Runtime: 58 ms, faster than 75.00% of Java online submissions for Minimum Number of Operations to Sort a Binary Tree by Level.
    //Memory Usage: 59 MB, less than 50.00% of Java online submissions for Minimum Number of Operations to Sort a Binary Tree by Level.
    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int count = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();
            int[] data = new int[len];
            int[] dataOrderd = new int[len];
            Map<Integer, Integer> map = new HashMap<>();
            int idx = 0;
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                dataOrderd[idx] = node.val;
                data[idx] = node.val;
                map.put(node.val, idx);
                idx++;
            }

            Arrays.sort(dataOrderd);
            for (int i = 0; i < len; i++) {
                if (data[i] == dataOrderd[i]) continue;

                int j = map.get(dataOrderd[i]);
                int tmp = data[i];
                data[i] = data[j];
                data[j] = tmp;
                map.put(tmp, j);
                count++;
            }
        }
        return count;
    }

}


