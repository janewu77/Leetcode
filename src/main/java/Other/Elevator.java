package Other;

import java.io.IOException;

import static java.time.LocalTime.now;

//elevator, min costs
public class Elevator {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        doRun(0, 1, new int[]{3});
        doRun(1, 3, new int[]{1, 1, 1});
        doRun(2, 3, new int[]{1, 1, 5});
        doRun(2, 5, new int[]{2, 3, 4, 5, 5});
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(int expect, int n, int[] nums) {
        int res = new Elevator().minCost(n, nums);
        System.out.println("[" + (expect == res) + "]expect:" + expect + " res:" + res);
    }

    //2.pre-sum
    //Time: O(N); Space: O(N)
    public int minCost(int n, int[] nums) {
        int[] leftCosts = new int[n];
        leftCosts[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftCosts[i] = nums[i] + leftCosts[i - 1] + leftCosts[i - 1];
        }

        int[] rightCosts = new int[n];
        rightCosts[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightCosts[i] = +nums[i] + rightCosts[i + 1] + rightCosts[i + 1];
        }

        int minCost = Integer.MAX_VALUE, level = 0;
        for (int i = 0; i < n; i++) {
            int cost = 0;
            if (i - 1 >= 0) cost += leftCosts[i - 1];
            if (i + 1 < n) cost += rightCosts[i + 1];

            if (cost < minCost) {
                minCost = cost;
                level = i;
            }
        }
        return level;
    }

    //1.brute force
    //Time: O(N * N); Space: O(1)
    public int minCost_1(int n, int[] nums) {
        int minCost = Integer.MAX_VALUE;
        int level = 0;
        for (int i = 0; i < n; i++) {
            int cost = nums[i];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                cost += Math.abs(j - i) * nums[j];
            }
            if (cost < minCost) {
                minCost = cost;
                level = i;
            }
        }
        return level;
    }


}
