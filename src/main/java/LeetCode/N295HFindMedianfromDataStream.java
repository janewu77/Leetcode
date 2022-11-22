package LeetCode;

import java.util.*;


/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 *
 * Constraints:
 *
 * -105 <= num <= 105
 * There will be at least one element in the data structure before calling findMedian.
 * At most 5 * 104 calls will be made to addNum and findMedian.
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */

/**
 * H - [Time: 30-
 */
public class N295HFindMedianfromDataStream {


    //2.two heap
    //Runtime: 109 ms, faster than 98.12% of Java online submissions for Find Median from Data Stream.
    //Memory Usage: 71.6 MB, less than 85.41% of Java online submissions for Find Median from Data Stream.
    class MedianFinder {

        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        //Space: O(N)
        public MedianFinder() {
           left  = new PriorityQueue<>(Comparator.reverseOrder());
           right = new PriorityQueue<>();
        }

        //Time: O(logN)
        public void addNum(int num) {
            // left.add(num);
            // right.add(left.poll());
            // if (left.size() < right.size()) left.add(right.poll());

            if (left.isEmpty() || num <= left.peek()) left.add(num);
            else right.add(num);

            if (Math.abs(left.size() - right.size()) >= 2){
                PriorityQueue<Integer> tmp = left.size() > right.size() ? right : left;
                tmp.add(left.size() > right.size() ? left.poll() : right.poll());
            }

        }

        //Time: O(1)
        public double findMedian() {
            //return left.size() > right.size() ? left.peek():  (left.peek() + right.peek()) / 2d;
            if (left.size() == right.size()) return (left.peek() + right.peek()) / 2d;
            else return left.size() > right.size() ? left.peek(): right.peek();
        }
    }


    //1.insertion sort
    //Runtime: 307 ms, faster than 19.07% of Java online submissions for Find Median from Data Stream.
    //Memory Usage: 59.1 MB, less than 99.97% of Java online submissions for Find Median from Data Stream.
    class MedianFinder_1 {
        //Space: O(N)
        List<Integer> list;
        public MedianFinder_1() {
            list = new ArrayList<>();
        }

        //Time: O(logN + N)
        public void addNum(int num) {
            int idx = Collections.binarySearch(list, num);
            idx = (idx >= 0 ? idx : Math.abs(idx) - 1);
            list.add(idx, num);
        }

        public double findMedian() {
            int idx = list.size() / 2;
            if (list.size() % 2 == 1) return list.get(idx);
            else return (list.get(idx) + list.get(idx - 1)) / 2d;
        }
    }

}
