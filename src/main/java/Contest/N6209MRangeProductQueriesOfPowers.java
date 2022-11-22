package Contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;


/**
 * Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the array.
 *
 * You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti]. Each queries[i] represents a query where you have to find the product of all powers[j] with lefti <= j <= righti.
 *
 * Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query. Since the answer to the ith query may be too large, each answers[i] should be returned modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 15, queries = [[0,1],[2,2],[0,3]]
 * Output: [2,4,64]
 * Explanation:
 * For n = 15, powers = [1,2,4,8]. It can be shown that powers cannot be a smaller size.
 * Answer to 1st query: powers[0] * powers[1] = 1 * 2 = 2.
 * Answer to 2nd query: powers[2] = 4.
 * Answer to 3rd query: powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64.
 * Each answer modulo 109 + 7 yields the same answer, so [2,4,64] is returned.
 * Example 2:
 *
 * Input: n = 2, queries = [[0,0]]
 * Output: [2]
 * Explanation:
 * For n = 2, powers = [2].
 * The answer to the only query is powers[0] = 2. The answer modulo 109 + 7 is the same, so [2] is returned.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 109
 * 1 <= queries.length <= 105
 * 0 <= starti <= endi < powers.length
 */

//2438. Range Product Queries of Powers
public class N6209MRangeProductQueriesOfPowers {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun("2,4,64", 15, new int[][]{{0,1}, {2,2}, {0,3}});
        doRun("536870912,621940455", 806335498, new int[][]{{11,11},{1,8}});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, int n, int[][] queries) {
        int[] res1 = new N6209MRangeProductQueriesOfPowers()
                .productQueries(n, queries);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //3.binary operation + prefix sum
    //Runtime: 37 ms, faster than 83.33% of Java online submissions for Range Product Queries of Powers.
    //Memory Usage: 136.6 MB, less than 16.67% of Java online submissions for Range Product Queries of Powers.
    public int[] productQueries(int n, int[][] queries) {
        int[] res = new int[queries.length];
        int idx = 0;
        List<Integer> idxList = new ArrayList<>();
        while (n >= 1){
           if ((n & 1) == 1) idxList.add(idx);
           n >>= 1; idx++;
        }

        int[] preSumList = new int[idxList.size()];
        idx = 0;
        for (int p: idxList) {
            preSumList[idx] = p + (idx != 0 ? preSumList[idx - 1] : 0);
            idx++;
        }

        idx = 0;
        for(int[] query: queries){
            long product = 1;
            int sum = preSumList[query[1]];
            if (query[0] >= 1) sum -= preSumList[query[0] - 1];
            while (sum >= 30) {
                product = (product << 30) % 1_000_000_007;
                sum -= 30;
            }
            res[idx++] = (int)((product << sum) % 1_000_000_007);
        }
        return res;
    }

    //2.one pass
    //Runtime: 75 ms, faster than 50.00% of Java online submissions for Range Product Queries of Powers.
    //Memory Usage: 134.7 MB, less than 16.67% of Java online submissions for Range Product Queries of Powers.
    //one pass
    public int[] productQueries_2(int n, int[][] queries) {
        int[] res = new int[queries.length];
        int idx = 0;
        for(int[] query: queries){
            long product = 1;
            int x = n;
            for (int i = 0; i <= query[1]; i++){
                int lsb = (x & -x);
                x = x & (~lsb);
                if (i >= query[0]) product = product * lsb % 1_000_000_007;
            }
            res[idx++] = (int)product;
        }
        return res;
    }


    //1.Brute force
    //Runtime: 64 ms, faster than 83.33% of Java online submissions for Range Product Queries of Powers.
    //Memory Usage: 96 MB, less than 100.00% of Java online submissions for Range Product Queries of Powers.
    public int[] productQueries_1(int n, int[][] queries) {
        int[] res = new int[queries.length];
        List<Integer> powers = calPowers(n);

        int idx = 0;
        for(int[] query: queries){
            long product = 1;
            for (int i = query[0]; i <= query[1]; i++)
                product = product * powers.get(i) % 1_000_000_007;
            res[idx++] = (int)product;
        }
        return res;
    }

    private List<Integer> calPowers(int n) {
        List<Integer> powerList = new ArrayList<>();
        int x = 1;
        while (n >= 1) {
            if ((n & 1) == 1) powerList.add(x);
            x <<= 1; n >>= 1;
        }
        return powerList;
    }

}
