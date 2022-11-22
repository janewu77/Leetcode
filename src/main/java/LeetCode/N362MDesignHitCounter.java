package LeetCode;


import java.util.Deque;
import java.util.LinkedList;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 *
 * Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 *
 * HitCounter() Initializes the object of the hit counter system.
 * void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
 * int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 *
 *
 * Example 1:
 *
 * Input
 * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
 * [[], [1], [2], [3], [4], [300], [300], [301]]
 * Output
 * [null, null, null, null, 3, null, 4, 3]
 *
 * Explanation
 * HitCounter hitCounter = new HitCounter();
 * hitCounter.hit(1);       // hit at timestamp 1.
 * hitCounter.hit(2);       // hit at timestamp 2.
 * hitCounter.hit(3);       // hit at timestamp 3.
 * hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
 * hitCounter.hit(300);     // hit at timestamp 300.
 * hitCounter.getHits(300); // get hits at timestamp 300, return 4.
 * hitCounter.getHits(301); // get hits at timestamp 301, return 3.
 *
 *
 * Constraints:
 *
 * 1 <= timestamp <= 2 * 109
 * All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
 * At most 300 calls will be made to hit and getHits.
 *
 *
 * Follow up: What if the number of hits per second could be huge? Does your design scale?
 */
public class N362MDesignHitCounter {

    public static void main(String[] args){
        HitCounter obj = new HitCounter();

        //["HitCounter","hit","hit","hit","getHits","getHits","getHits","getHits","getHits","hit","getHits"]
        //[[],[2],[3],[4],[300],[301],[302],[303],[304],[501],[600]]
        //[null,null,null,null,3,3,2,1,0,null,1]
        obj.hit(2);
        obj.hit(3);
        obj.hit(4);
        System.out.println(obj.getHits(300));
        System.out.println(obj.getHits(301));
        System.out.println(obj.getHits(302));
        System.out.println(obj.getHits(303));
        System.out.println(obj.getHits(304));
        obj.hit(501);
        System.out.println(obj.getHits(600));
    }


    //Runtime: 1 ms, faster than 99.74% of Java online submissions for Design Hit Counter.
    //Memory Usage: 42.1 MB, less than 50.77% of Java online submissions for Design Hit Counter.
    //Deque
    //Time: O(N); Space: O(N)  when N <= 300
    //Time: O(1); Space: O(1)
    static class HitCounter {

        Deque<int[]> deque;
        public HitCounter() {
            deque = new LinkedList<>();
            deque.addFirst(new int[]{0,0});
        }

        //Time:O(1)
        public void hit(int timestamp) {
            // multiple hits arriving at the "same" timestamps.
            if (deque.peekLast()[0] == timestamp) deque.peekLast()[1]++;
            else deque.addLast(new int[]{timestamp, deque.peekLast()[1] + 1});
        }

        //Time:O(N) N <= 300
        public int getHits(int timestamp) {

            int[] guardian = null;
            while (!deque.isEmpty() && timestamp - deque.peekFirst()[0] >= 300)
                guardian = deque.pollFirst();

            if(deque.isEmpty()) deque.addFirst(new int[]{0,0});
            else if (guardian != null) deque.addFirst(guardian);

            return deque.peekLast()[1] - deque.peekFirst()[1];
        }

    }
}
