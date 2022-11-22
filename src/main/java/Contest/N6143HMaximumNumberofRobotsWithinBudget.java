package Contest;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.
 *
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.
 *
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 *
 *
 *
 * Example 1:
 *
 * Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * Output: 3
 * Explanation:
 * It is possible to run all individual and consecutive pairs of robots within budget.
 * To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
 * It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
 * Example 2:
 *
 * Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * Output: 0
 * Explanation: No robot can be run that does not exceed the budget, so we return 0.
 *
 *
 * Constraints:
 *
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 104
 * 1 <= chargeTimes[i], runningCosts[i] <= 105
 * 1 <= budget <= 1015
 */

/**
 * H - [time: 120-
 */
public class N6143HMaximumNumberofRobotsWithinBudget {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun_demo(3, new int[]{3,1,2,2,2}, new int[]{1,1,1,1,1}, 25);

        doRun_demo(3, new int[]{3,6,1,3,4}, new int[]{2,1,3,4,5}, 25);
        doRun_demo(0, new int[]{11,12,19}, new int[]{10,8,7}, 19);

        doRun_demo(4, new int[]{11,12,74,67,37,87,42,34,18,90,36,28,34,20},
                new int[]{18,98,2,84,7,57,54,65,59,91,7,23,94,20}, 937);

        doRun_demo(3, new int[]{8,76,74,9,75,71,71,42,15,58,88,38,56,59,10,11},
                new int[]{1,92,41,63,22,37,37,8,68,97,39,59,45,50,29,37}, 412);

        doRun_demo(3, new int[]{8,76,74,9,75,71,71,42,15,58,88,38,56,59,10,11},
                new int[]{1,92,41,63,22,37,37,8,68,97,39,59,45,50,29,37}, 412);

        doRun_demo(4, new int[]{74,46,19,34,7,87,7,40,28,81,53,39,3,46,21,40,76,44,88,93,44,50,22,59,46,60,36,24,50,40,56,5,39,9,24,74,7,14,95,45,36,17,22,12,53,41,2,33,100,73,20,70,81,91,28,98,47,88,79,100,78,38,44,74,48,76,73,92,28,30,95,87},
                new int[]{11,33,15,40,8,28,97,89,51,42,17,57,45,5,63,53,23,43,76,64,86,86,89,53,94,91,78,12,90,29,79,48,35,6,88,79,82,76,44,93,83,55,65,96,86,24,54,65,94,4,26,73,51,85,47,99,17,14,76,2,39,52,58,5,15,35,79,16,94,16,59,50}, 447);


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo(int expect, int[] chargeTimes, int[] runningCosts, long budget) {
        int res = new N6143HMaximumNumberofRobotsWithinBudget().maximumRobots(chargeTimes, runningCosts, budget);
////        String res = comm.toString(res1);
//        String res = Arrays.stream(res1).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(","));
        System.out.println("[" + (expect == res) + "]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + r

    }

    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int l1=chargeTimes.length,l2=runningCosts.length;
        int[] pref1=new int[l1];
        pref1[0]=chargeTimes[0];
        for(int i=1;i<l1;i++)
        {
            pref1[i]=Math.max(pref1[i-1],chargeTimes[i]); // max calculation on each index
        }
        int size=0,i=0,sum=0,j=0,charge=0;
        int ans=-1;
        while(j!=l1)
        {
            sum+=runningCosts[j]; //calculating sum
            size++;  //Size of current window
            // System.out.println(sum +" "+ size);
            if(size==1)
            {
                charge=chargeTimes[j]; // We can Consider single size window as well
            }
            else
            {
                charge=pref1[j];
            }
            if((charge+sum*size)<=budget)
            {
                ans=Math.max(size,ans);  //ans updation
            }
            else   //If sum greater than budget
            {
                sum-=runningCosts[i++]; // remove first slide the window
                size--;
                //     System.out.println("In else "+ sum +" "+ size);
                ans=Math.max(size,ans);
            }
            j++;
        }

        return ans;
    }


    //Runtime: 24 ms, faster than 100.00% of Java online submissions for Maximum Number of Robots Within Budget.
    //Memory Usage: 51.5 MB, less than 75.00% of Java online submissions for Maximum Number of Robots Within Budget.
    //Slide window + Deque
    //Time: O(N); Space: O(N)
    public int maximumRobots_de(int[] chargeTimes, int[] runningCosts, long budget) {
        int left = 0, right = 0;
        long sum = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        while (right < runningCosts.length) {
            while(!deque.isEmpty() && deque.peekLast() < chargeTimes[right])
                deque.pollLast();
            deque.addLast(chargeTimes[right]);
            sum += runningCosts[right];
            right++;

            // By using if, it is guaranteed that the distance between i and j will not decrease once it reaches certain value.
            // As a result, n - left will be the largest value one has seen in the loop.
            if (((right - left) * sum  > (budget - deque.peekFirst())) ){
                sum -= runningCosts[left];
                if (deque.peekFirst() == chargeTimes[left]) deque.pollFirst();
                left++;
            }
        }
        return runningCosts.length - left;
    }

    //Runtime: 237 ms, faster than 75.00% of Java online submissions for Maximum Number of Robots Within Budget.
    //Memory Usage: 121.7 MB, less than 25.00% of Java online submissions for Maximum Number of Robots Within Budget.
    //Slide window + TreeMap
    //Time: O(N*logN); Space: O(N)
    public int maximumRobots_treemap(int[] chargeTimes, int[] runningCosts, long budget) {
        int left = 0, right = 0;
        long sum = 0;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (right < chargeTimes.length) {
            map.put(chargeTimes[right], map.getOrDefault(chargeTimes[right],0) + 1);
            sum += runningCosts[right];
            right++;

            if ((right - left) * sum > budget - map.lastKey()){
                sum -= runningCosts[left];
                if (map.get(chargeTimes[left]) == 1)
                    map.remove(chargeTimes[left]);
                else
                    map.put(chargeTimes[left], map.get(chargeTimes[left]) - 1);
                left++;
            }
        }
        return runningCosts.length - left;
    }

}
