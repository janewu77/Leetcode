package LeetCode;


/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * At most 3000 calls will be made to enQueue, deQueue, Front, Rear, isEmpty, and isFull.
 */

/**
 * M - [time: 20-
 */
public class N622MDesignCircularQueue {

    //Runtime: 4 ms, faster than 91.80% of Java online submissions for Design Circular Queue.
    //Memory Usage: 42.5 MB, less than 98.98% of Java online submissions for Design Circular Queue.
    //a single link
    //Time: O(1); Space: max: O(N)
    class LinkNode {
        LinkNode next;
        int val;
        public LinkNode(int v){
            val = v;
        }
    }

    class MyCircularQueue {
        LinkNode head;
        LinkNode tail;
        int capacity;
        int count;

        //Time: O(1); Space: O(N)
        public MyCircularQueue(int k) {
            capacity = k;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;

            LinkNode node = new LinkNode(value);
            if (head == null) head = node;
            else tail.next = node;
            tail = node;

            count++;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            head = head.next;
            count--;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : head.val;
        }

        public int Rear() {
            return isEmpty() ? -1 : tail.val;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }
    }

    //Runtime: 3 ms, faster than 100.00% of Java online submissions for Design Circular Queue.
    //Memory Usage: 42.8 MB, less than 93.77% of Java online submissions for Design Circular Queue.
    //Array
    //Time: O(1); Space: O(N)
    class MyCircularQueue_1 {

        int[] data;
        int idx = 0;
        int count = 0;

        //Time: O(1); Space: O(N)
        public MyCircularQueue_1(int k) {
            data = new int[k];
        }

        //Time: O(1)
        public boolean enQueue(int value) {
            if (isFull()) return false;
            data[(idx + count++) % data.length] = value;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            count--;
            idx = (idx + 1) % data.length;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : data[idx];
        }

        public int Rear() {
            return isEmpty() ? -1 : data[(data.length + idx + count -1) % data.length];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == data.length;
        }
    }
}
