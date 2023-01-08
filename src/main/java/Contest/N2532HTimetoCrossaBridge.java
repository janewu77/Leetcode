package Contest;

import java.util.Comparator;
import java.util.PriorityQueue;

import static java.time.LocalTime.now;

/**
 * There are k workers who want to move n boxes from an old warehouse to a new one. You are given the two integers n and k, and a 2D integer array time of size k x 4 where time[i] = [leftToRighti, pickOldi, rightToLefti, putNewi].
 *
 * The warehouses are separated by a river and connected by a bridge. The old warehouse is on the right bank of the river, and the new warehouse is on the left bank of the river. Initially, all k workers are waiting on the left side of the bridge. To move the boxes, the ith worker (0-indexed) can :
 *
 * Cross the bridge from the left bank (new warehouse) to the right bank (old warehouse) in leftToRighti minutes.
 * Pick a box from the old warehouse and return to the bridge in pickOldi minutes. Different workers can pick up their boxes simultaneously.
 * Cross the bridge from the right bank (old warehouse) to the left bank (new warehouse) in rightToLefti minutes.
 * Put the box in the new warehouse and return to the bridge in putNewi minutes. Different workers can put their boxes simultaneously.
 * A worker i is less efficient than a worker j if either condition is met:
 *
 * leftToRighti + rightToLefti > leftToRightj + rightToLeftj
 * leftToRighti + rightToLefti == leftToRightj + rightToLeftj and i > j
 * The following rules regulate the movement of the workers through the bridge :
 *
 * If a worker x reaches the bridge while another worker y is crossing the bridge, x waits at their side of the bridge.
 * If the bridge is free, the worker waiting on the right side of the bridge gets to cross the bridge. If more than one worker is waiting on the right side, the one with the lowest efficiency crosses first.
 * If the bridge is free and no worker is waiting on the right side, and at least one box remains at the old warehouse, the worker on the left side of the river gets to cross the bridge. If more than one worker is waiting on the left side, the one with the lowest efficiency crosses first.
 * Return the instance of time at which the last worker reaches the left bank of the river after all n boxes have been put in the new warehouse.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 3, time = [[1,1,2,1],[1,1,3,1],[1,1,4,1]]
 * Output: 6
 * Explanation:
 * From 0 to 1: worker 2 crosses the bridge from the left bank to the right bank.
 * From 1 to 2: worker 2 picks up a box from the old warehouse.
 * From 2 to 6: worker 2 crosses the bridge from the right bank to the left bank.
 * From 6 to 7: worker 2 puts a box at the new warehouse.
 * The whole process ends after 7 minutes. We return 6 because the problem asks for the instance of time at which the last worker reaches the left bank.
 * Example 2:
 *
 * Input: n = 3, k = 2, time = [[1,9,1,8],[10,10,10,10]]
 * Output: 50
 * Explanation:
 * From 0  to 10: worker 1 crosses the bridge from the left bank to the right bank.
 * From 10 to 20: worker 1 picks up a box from the old warehouse.
 * From 10 to 11: worker 0 crosses the bridge from the left bank to the right bank.
 * From 11 to 20: worker 0 picks up a box from the old warehouse.
 * From 20 to 30: worker 1 crosses the bridge from the right bank to the left bank.
 * From 30 to 40: worker 1 puts a box at the new warehouse.
 * From 30 to 31: worker 0 crosses the bridge from the right bank to the left bank.
 * From 31 to 39: worker 0 puts a box at the new warehouse.
 * From 39 to 40: worker 0 crosses the bridge from the left bank to the right bank.
 * From 40 to 49: worker 0 picks up a box from the old warehouse.
 * From 49 to 50: worker 0 crosses the bridge from the right bank to the left bank.
 * From 50 to 58: worker 0 puts a box at the new warehouse.
 * The whole process ends after 58 minutes. We return 50 because the problem asks for the instance of time at which the last worker reaches the left bank.
 *
 *
 * Constraints:
 *
 * 1 <= n, k <= 104
 * time.length == k
 * time[i].length == 4
 * 1 <= leftToRighti, pickOldi, rightToLefti, putNewi <= 1000
 */
public class N2532HTimetoCrossaBridge {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        //11, 11, 11, 5, 15, 10
        doRun(115, 9,6, new int[][]{{2,6,9,4}, {4,8,7,5}, {4,6,7,6}, {2,3,3,7}, {9,3,6,8}, {2,8,8,4}});

        doRun(149, 10,6, new int[][]{{2,10,5,8}, {3,5,2,2}, {5,8,10,10}, {7,8,8,5}, {5,6,6,10}, {6,10,6,2}});
        doRun(50, 3,2, new int[][]{{1,9,1,8}, {10,10,10,10}});
        doRun(6, 1,3, new int[][]{{1,1,2,1}, {1,1,3,1}, {1,1,4,1}});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n, int k, int[][] time) {
        int res = new N2532HTimetoCrossaBridge().findCrossingTime(n, k, time);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //1. PriorityQueue
    //Runtime: 80ms 33%; Memory: 48.7MB 91%
    //time: O(K + N * logK); Space: O(N)
    public int findCrossingTime(int n, int k, int[][] time) {
        int[] worker = new int[k];
        //waiting for bridge (base on efficiency)
        PriorityQueue<Integer> workL = new PriorityQueue<>((a, b) -> (time[b][0] + time[b][2] == time[a][0] + time[a][2]) ? b - a : time[b][0] + time[b][2] - (time[a][0] + time[a][2]));
        PriorityQueue<Integer> workR = new PriorityQueue<>((a, b) -> (time[b][0] + time[b][2] == time[a][0] + time[a][2]) ? b - a : time[b][0] + time[b][2] - (time[a][0] + time[a][2]));
        //working in warehouse (base on time)
        PriorityQueue<Integer> heapL = new PriorityQueue<>(Comparator.comparingInt(a -> worker[a]));
        PriorityQueue<Integer> heapR = new PriorityQueue<>(Comparator.comparingInt(a -> worker[a]));

        //all waiting on the left side
        for (int i = 0; i < k; i++) workL.add(i);

        int assign = 0, unmoved = n, currTime = 0;
        while (unmoved > 0) {

            //move to bridge queue
            while (!heapL.isEmpty() && worker[heapL.peek()] <= currTime)
                workL.add(heapL.poll());

            while (!heapR.isEmpty() && worker[heapR.peek()] <= currTime)
                workR.add(heapR.poll());

            //cross bridge
            if (!workR.isEmpty()) {
                int idx = workR.poll();
                currTime = Math.max(worker[idx], currTime) + time[idx][2];
                worker[idx] = currTime + time[idx][3];
                heapL.add(idx);
                unmoved--;

            } else if (!workL.isEmpty()){
                int idx = workL.poll();
                if (assign == n) continue;
                currTime = Math.max(worker[idx], currTime) + time[idx][0];
                worker[idx] = currTime + time[idx][1];
                heapR.add(idx);
                assign++;

            } else {
                //time fly...
                if (!heapR.isEmpty() && !heapL.isEmpty())
                    currTime = Math.min(worker[heapL.peek()], worker[heapR.peek()]);
                else if (!heapR.isEmpty())
                    currTime = worker[heapR.peek()];
                else
                    currTime = worker[heapL.peek()];
            }
        }
        return currTime;
    }



}
