package LeetCode;


import ADraft.NestedInteger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/**
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 *
 * Return the sum of each integer in nestedList multiplied by its depth.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 * Example 2:
 *
 *
 * Input: nestedList = [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 * Example 3:
 *
 * Input: nestedList = [0]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nestedList.length <= 50
 * The values of the integers in the nested list is in the range [-100, 100].
 * The maximum depth of any integer is less than or equal to 50.
 */

/**
 * M - [time: 10-
 */
public class N339MNestedListWeightSum {

    //2.BFS
    //Runtime: 1 ms, faster than 86.78% of Java online submissions for Nested List Weight Sum.
    //Memory Usage: 39.5 MB, less than 96.41% of Java online submissions for Nested List Weight Sum.
    //Time: O(N); Space: O(N)
    //Let N be the total number of nested elements in the input list.
    public int depthSum(List<NestedInteger> nestedList) {
        Queue<List<NestedInteger>> queue = new ArrayDeque<>();
        queue.add(nestedList);

        int res = 0, layer = 1;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                List<NestedInteger> list = queue.poll();
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).isInteger()) res += list.get(j).getInteger() * layer;
                    else queue.add(list.get(i).getList());
                }
            }
            layer++;
        }
        return res;
    }


    //1.iteration(DFS)
    //Runtime: 1 ms, faster than 86.78% of Java online submissions for Nested List Weight Sum.
    //Memory Usage: 41.9 MB, less than 18.67% of Java online submissions for Nested List Weight Sum.
    //Time: O(N); Space: O(N)
    public int depthSum_1(List<NestedInteger> nestedList) {
        return  helper(nestedList, 1);
    }

    private int helper(List<NestedInteger> nestedList, int k) {
        int res = 0;
        for (int i = 0; i < nestedList.size(); i++) {
            if (nestedList.get(i).isInteger()) res += nestedList.get(i).getInteger() * k;
            else res += helper(nestedList.get(i).getList(), k + 1);
        }
        return res;
    }

}
