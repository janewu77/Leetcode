package LeetCode;


import javafx.util.Pair;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 *
 * Implement the MaxStack class:
 *
 * MaxStack() Initializes the stack object.
 * void push(int x) Pushes element x onto the stack.
 * int pop() Removes the element on top of the stack and returns it.
 * int top() Gets the element on the top of the stack without removing it.
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 * You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
 * [[], [5], [1], [5], [], [], [], [], [], []]
 * Output
 * [null, null, null, null, 5, 5, 1, 5, 1, 5]
 *
 * Explanation
 * MaxStack stk = new MaxStack();
 * stk.push(5);   // [5] the top of the stack and the maximum number is 5.
 * stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
 * stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
 * stk.top();     // return 5, [5, 1, 5] the stack did not change.
 * stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
 * stk.top();     // return 1, [5, 1] the stack did not change.
 * stk.peekMax(); // return 5, [5, 1] the stack did not change.
 * stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
 * stk.top();     // return 5, [5] the stack did not change.
 *
 *
 * Constraints:
 *
 * -107 <= x <= 107
 * At most 105 calls will be made to push, pop, top, peekMax, and popMax.
 * There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
 */

/**
 * H - [time: 20-
 */
public class N716HMaxStack {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        N716HMaxStack n716HMaxStack = new N716HMaxStack();
        n716HMaxStack.doRun();
        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    private void doRun() {
        int res, expect;
        MaxStack obj;

        //["MaxStack","push","pop","push","push","push","push","popMax","push","pop","pop","top","push"]
        //[[],[15],[],[1],[-52],[80],[-39],[],[91],[],[],[],[36]]

        obj = new MaxStack();
        obj.push(15);
        res = obj.pop();
        expect = 15;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        obj.push(1);
        obj.push(-52);
        obj.push(80);
        obj.push(-39);
        res = obj.popMax();
        expect = 80;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        obj.push(91);
        res = obj.pop();
        expect = 91;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.pop();
        expect = -39;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);


        res = obj.top();
        expect = -52;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
        obj.push(36);
        System.out.println("-------------------------------");

        //////
        obj = new MaxStack();
        obj.push(5);
        obj.push(1);
        obj.push(5);

        res = obj.top();
        expect = 5;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.popMax();
        expect = 5;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.top();
        expect = 1;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.peekMax();
        expect = 5;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.pop();
        expect = 1;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);

        res = obj.top();
        expect = 5;
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 166 ms, faster than 21.44% of Java online submissions for Max Stack.
    //Memory Usage: 78.8 MB, less than 91.39% of Java online submissions for Max Stack.
    //List + treeset + Binary Search
    class MaxStack {
        //Space: O(N)
        List<int[]> list;//stack
        TreeSet<int[]> treeSet; //maxheap
        int idx = 0;

        //Time: O(1)
        public MaxStack() {
            list = new ArrayList<>();
            treeSet = new TreeSet<>((a, b)-> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        }

        //Time: O(logN)
        public void push(int x) {
            int[] pair = new int[]{x, idx++};
            list.add(pair);
            treeSet.add(pair);
        }

        //Time: O(logN)
        public int pop() {
            int[] pair = list.remove(list.size() - 1);
            treeSet.remove(pair);
            return pair[0];
        }

        //Time: O(1)
        public int top() {
            return list.get(list.size() - 1)[0];
        }

        //Time: O(1)
        public int peekMax() {
            return treeSet.last()[0];
        }

        //Time: O(logN)
        public int popMax() {
            int[] pair = treeSet.pollLast();
            list.remove(Collections.binarySearch(list, pair, Comparator.comparingInt(a -> a[1])));
            return pair[0];
        }

    }


    //Runtime: 55 ms, faster than 89.84% of Java online submissions for Max Stack.
    //Memory Usage: 79.2 MB, less than 91.32% of Java online submissions for Max Stack.
    class MaxStack_2 {

        Set<Integer> deleted;
        Deque<int[]> stack;
        PriorityQueue<int[]> heap;
        int idx = 0;

        public MaxStack_2() {
            deleted = new HashSet<>();
            stack = new ArrayDeque<>();
            heap = new PriorityQueue<>((a,b) -> (b[0] - a[0] == 0) ? b[1] - a[1] : b[0] - a[0]);
        }

        //Time: O(logN)
        public void push(int x) {
            int[] pair = new int[]{x, idx++};
            heap.add(pair);
            stack.push(pair);
        }

        //Time: O(logN)
        public int pop() {
            int[] pair = stack.pop();
            if (heap.peek().equals(pair)) heap.poll(); //O(logN)
            else deleted.add(pair[1]);
            removeNull();
            return pair[0];
        }

        //Time: O(1)
        public int top() {
            return stack.peek()[0];
        }

        //Time: O(1)
        public int peekMax() {
            return heap.peek()[0];
        }

        //Time: O(logN)
        public int popMax() {
            int[] pair = heap.poll(); //O(1)
            if (stack.peek().equals(pair)) stack.pop();
            else deleted.add(pair[1]);
            removeNull();
            return pair[0];
        }

        private void removeNull(){
            while (!stack.isEmpty() && deleted.contains(stack.peek()[1])) {
                deleted.remove(stack.peekLast()[1]);
                stack.pop();
            }

            while (!heap.isEmpty() && deleted.contains(heap.peek()[1])) {
                deleted.remove(heap.peek()[1]);
                heap.poll();
            }
        }

    }

    //Runtime: 109 ms, faster than 58.60% of Java online submissions for Max Stack.
    //Memory Usage: 96.7 MB, less than 78.26% of Java online submissions for Max Stack.
    //two TreeSets
    class MaxStack_treeset {
        //Space: O(N)
        TreeSet<int[]> stack;
        TreeSet<int[]> heap;
        int idx = 0;

        //Time: O(1)
        public MaxStack_treeset() {
            stack = new TreeSet<>(Comparator.comparingInt(a -> a[1]));
            heap = new TreeSet<>( (a, b)-> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);
        }

        //Time: O(logN)
        public void push(int x) {
            int[] pair = new int[]{x, idx++};
            stack.add(pair);
            heap.add(pair);
        }

        //Time: O(logN)
        public int pop() {
            int[] pair = stack.pollLast();
            heap.remove(pair);
            if (stack.isEmpty()) idx = 0;
            return pair[0];
        }

        //Time: O(1)
        public int top() {
            return stack.last()[0];
        }

        //Time: O(1)
        public int peekMax() {
            return heap.last()[0];
        }

        //Time: O(logN)
        public int popMax() {
            int[] pair = heap.pollLast();
            stack.remove(pair);
            if (stack.isEmpty()) idx = 0;
            return pair[0];
        }
    }


}
