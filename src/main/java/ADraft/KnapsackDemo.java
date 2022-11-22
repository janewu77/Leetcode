package ADraft;

public class KnapsackDemo {


    public static void main(String args[]) {
        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 50;
        int n = val.length;
        System.out.println(knapSack(W, wt, val, n));
    }


    //Time: O(N*W); Space: O(W)
    static int knapSack(int W, int wt[], int val[], int n) {
        // making and initializing dp array
        int []dp = new int[W + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int w = W; w >= 0; w--) {
                if (wt[i - 1] <= w)
                    // finding the maximum value
                    dp[w] = Math.max(dp[w], dp[w - wt[i - 1]] + val[i - 1]);
            }
        }
        return dp[W]; // returning the maximum value of knapsack
    }


    //DP : bottom up
    //Time: O(N*W); Space: O(N*W).
    static int knapSack_dp1(int W, int wt[], int val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {

                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        return K[n][W];
    }

    //recursion + memo
    //Time: O(N*W); Space: O(N*W + N).
    static int knapSack_re2(int W, int wt[], int val[], int N) {

        // Declare the table dynamically
        int dp[][] = new int[N + 1][W + 1];

        // Loop to initially filled the
        // table with -1
        for(int i = 0; i < N + 1; i++)
            for(int j = 0; j < W + 1; j++)
                dp[i][j] = -1;

        return knapSackRec(W, wt, val, N, dp);
    }

    static int knapSackRec(int W, int wt[], int val[], int n, int [][]dp) {

        // Base condition
        if (n == 0 || W == 0)
            return 0;

        if (dp[n][W] != -1)
            return dp[n][W];

        if (wt[n - 1] > W)
            // Store the value of function call
            // stack in table before return
            return dp[n][W] = knapSackRec(W, wt, val, n - 1, dp);
        else
            // Return value of table after storing
            return dp[n][W] = Math.max((val[n - 1] + knapSackRec(W - wt[n - 1], wt, val, n - 1, dp)),
                    knapSackRec(W, wt, val, n - 1, dp));
    }

    //Time: O(2^N); Space: O(N)
    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack_re1(int W, int wt[], int val[], int n) {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is
        // more than Knapsack capacity W,
        // then this item cannot be included
        // in the optimal solution
        if (wt[n - 1] > W)
            return knapSack_re1(W, wt, val, n - 1);

            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else
            return Math.max(val[n - 1] + knapSack_re1(W - wt[n - 1], wt, val, n - 1),
                    knapSack_re1(W, wt, val, n - 1));
    }


}
