package LeetCode;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.
 *
 * For example, if the price of a stock over the next 7 days were [100,80,60,70,60,75,85], then the stock spans would be [1,1,1,2,1,4,6].
 * Implement the StockSpanner class:
 *
 * StockSpanner() Initializes the object of the class.
 * int next(int price) Returns the span of the stock's price given that today's price is price.
 *
 *
 * Example 1:
 *
 * Input
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * Output
 * [null, 1, 1, 1, 2, 1, 4, 6]
 *
 * Explanation
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // return 1
 * stockSpanner.next(80);  // return 1
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(70);  // return 2
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
 * stockSpanner.next(85);  // return 6
 *
 *
 * Constraints:
 *
 * 1 <= price <= 105
 * At most 104 calls will be made to next.
 */

/**
 * M - [time: 15+
 */
public class N901MOnlineStockSpan {

    //3.a monotonic stack
    //Runtime: 37 ms, faster than 97.62% of Java online submissions for Online Stock Span.
    //Memory Usage: 75 MB, less than 32.05% of Java online submissions for Online Stock Span.
    class StockSpanner {

        Deque<int[]> deque;

        //Time: O(1); Space: O(N)
        public StockSpanner() {
            deque = new ArrayDeque<>();
        }

        //Time: O(1); Space: O(1)
        public int next(int price) {
            int span = 1;
            while (!deque.isEmpty() && price >= deque.peekLast()[0])
                span += deque.pollLast()[1];
            deque.add(new int[]{price, span});
            return span;
        }
    }

    //2.
    //Runtime: 25 ms, faster than 99.22% of Java online submissions for Online Stock Span.
    //Memory Usage: 50.5 MB, less than 89.89% of Java online submissions for Online Stock Span.
    class StockSpanner_2 {

        List<Integer> list;
        List<Integer> idx;

        //Time: O(1); Space: O(N)
        public StockSpanner_2() {
            list = new ArrayList<>();
            idx = new ArrayList<>();
        }

        //Time: worst case: O(N); Space: O(1)
        public int next(int price) {
            list.add(price);
            int i = list.size() - 1;
            while (i > 0 && price >= list.get(i - 1)) i = i - idx.get(i - 1);
            int span = list.size() - i;
            idx.add(span);
            return span;
        }
    }

    //1.BruteForce
    //Runtime: 1537 ms, faster than 5.03% of Java online submissions for Online Stock Span.
    //Memory Usage: 50.3 MB, less than 95.70% of Java online submissions for Online Stock Span.
    class StockSpanner_1 {

        List<Integer> list;

        //Time: O(1); Space: O(N)
        public StockSpanner_1() {
            list = new ArrayList<>();
        }

        //Time: O(N); Space: O(1)
        public int next(int price) {
            list.add(price);
            int i = list.size() - 1;
            while (i > 0 && list.get(i - 1) <= price) i--;
            return list.size() - i;
        }
    }
}
