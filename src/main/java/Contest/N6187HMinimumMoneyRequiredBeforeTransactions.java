package Contest;


import static java.time.LocalTime.now;


/**
 * You are given a 0-indexed 2D integer array transactions, where transactions[i] = [costi, cashbacki].
 *
 * The array describes transactions, where each transaction must be completed exactly once
 * in some order. At any given moment, you have a certain amount of money.
 * In order to complete transaction i, money >= costi must hold true. After performing a transaction,
 * money becomes money - costi + cashbacki.
 *
 * Return the minimum amount of money required before any transaction so that all of
 * the transactions can be completed regardless of the order of the transactions.
 *
 *
 *
 * Example 1:
 *
 * Input: transactions = [[2,1],[5,0],[4,2]]
 * Output: 10
 * Explanation:
 * Starting with money = 10, the transactions can be performed in any order.
 * It can be shown that starting with money < 10 will fail to complete all transactions in some order.
 * Example 2:
 *
 * Input: transactions = [[3,0],[0,3]]
 * Output: 3
 * Explanation:
 * - If transactions are in the order [[3,0],[0,3]], the minimum money required to complete the transactions is 3.
 * - If transactions are in the order [[0,3],[3,0]], the minimum money required to complete the transactions is 0.
 * Thus, starting with money = 3, the transactions can be performed in any order.
 *
 *
 * Constraints:
 *
 * 1 <= transactions.length <= 105
 * transactions[i].length == 2
 * 0 <= costi, cashbacki <= 109
 */

/**
 * H - [time: 120+
 */
//2412. Minimum Money Required Before Transactions
public class N6187HMinimumMoneyRequiredBeforeTransactions {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        char x = 'a';
        char x1 = 'Z';
        char x2 = 'B';
        System.out.println('a');
        System.out.println('Z');

        //5, 5,3, -3 -4 -10
        //13
        data2 = new int[][]{{7,2},{5,0}, {4,1}, {5,8}, {5,9},{0,10}};
        doRun(18, data2);

        data2 = new int[][]{{7,2},{5,0},{4,1}};
        doRun(15, data2);
        data2 = new int[][]{{2,1}, {5,0}, {4,2}};
        doRun(10, data2);

        data2 = new int[][]{{7,2},{5,0},{4,1},{0,8}};
        doRun(15, data2);

        data2 = new int[][]{{3,0}, {0,3}};
        doRun(3, data2);

        data2 = new int[][]{{3,0}, {0,3}, {0,3}};
        doRun(3, data2);

        data2 = new int[][]{{3,0}, {1,4}, {1,4}};
        doRun(4, data2);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(long expect, int[][] transactions) {
        long res = new N6187HMinimumMoneyRequiredBeforeTransactions()
                .minimumMoney(transactions);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //transaction[0] - transaction[1] is The lose of one transaction.
    //公式：  【总损耗 -（最后一次交易的损耗）】 + 最后一次交易的所需要的成本
    //公式     总损耗 - (t0 - t1) + t0
    //这里需要注意赚钱的交易，就是 t0<=t1的那些交易。 t0 - t1 < -0 出现负损耗。这样的损耗不应计入。
    //所以修订公式：
    //公式： 当t0 > t1时 ： 总正损耗 - (t0 - t1) + t0 ； 当t0 < t1时 ：总正损耗 + t0
    //公式： 当t0 > t1时 ： 总正损耗 + t1 ； 当t0 < t1时 ：总正损耗 + t0
    //公式： 总正损耗 + (t0 > t1? t1: t0)
    //公式： 结果 = Max（总正损耗 + (t0 > t1? t1: t0)）
    //公式： 结果 = 总正损耗 + Max（(t0 > t1? t1: t0)）

    //Runtime: 4 ms, faster than 100.00% of Java online submissions for Minimum Money Required Before Transactions.
    //Memory Usage: 97 MB, less than 100.00% of Java online submissions for Minimum Money Required Before Transactions.
    //Time: O(N); Space: O(1)
    public long minimumMoney(int[][] transactions) {

        long totalFee = 0;
        int maxIncome = 0;
        for(int[] transaction : transactions) {
            maxIncome = Math.max(maxIncome, Math.min(transaction[0], transaction[1]));
            totalFee += Math.max(0, transaction[0] - transaction[1]);
        }
        return totalFee + maxIncome;
    }
}
