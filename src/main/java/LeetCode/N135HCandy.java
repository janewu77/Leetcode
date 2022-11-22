package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * There are n children standing in a line. Each child is assigned
 * a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to
 * the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to
 * distribute the candies to the children.
 *
 *
 *
 * Example 1:
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 *
 *
 * Example 2:
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 *
 * Constraints:
 *
 * n == ratings.length
 * 1 <= n <= 2 * 104
 * 0 <= ratings[i] <= 2 * 104
 */

/**
 * H - (60m做出来了，运行结果超时）
 *  - 有至少三种算法
 *  - 可以试着画图，观察（注意多条分支）
 *
 *
 */
public class N135HCandy {

        //[1,3,2,2,1]
    public static void main(String[] args){

        int[] input1 = {1,3,2,2,1};
        System.out.println("input1 expect 7, result:"+new N135HCandy().candy(input1));

        int[] input2 = {1,0,2};
        System.out.println("input2 expect 5, result:"+new N135HCandy().candy(input2));

        int[] input3 = {1,2,2};
        System.out.println("input3 expect 4, result:"+new N135HCandy().candy(input3));

        int[] input4 = {4,3,2,1};
        System.out.println("input4 expect 10, result:"+new N135HCandy().candy(input4));

        int[] input5 = {1,2,3,1,0};
        System.out.println("input5 expect 9, result:"+new N135HCandy().candy(input5));

        int[] input6 = {1,2,87,87,87,2,1};
        System.out.println("input6 expect 13, result:"+new N135HCandy().candy(input6));
        //
        int[] input7 = {2,2,1,1,1};
        System.out.println("input7 expect 6, result:"+new N135HCandy().candy(input7));

        int[] input8 = {1,1,1};
        System.out.println("input8 expect 3, result:"+new N135HCandy().candy(input8));

        int[] input9 = {1,1,1,2,1};
        System.out.println("input9 expect 6, result:"+new N135HCandy().candy(input9));

        int[] input10 = {1,2,3,4,3,3,3,2,2,2,1};
        System.out.println("input10 expect 19, result:"+new N135HCandy().candy(input10));
    }

    //Runtime: 3 ms, faster than 86.96% of Java online submissions for Candy.
    //Memory Usage: 42.9 MB, less than 89.93% of Java online submissions for Candy.
    // n(n+1)/2
    public int candy(int[] ratings) {
        if (ratings == null) return 0;
        if (ratings.length <= 1) return 1;

        //1 升 0 平 -1降
        int state = ratings[1] == ratings[0]? 0 : ratings[1] > ratings[0]? 1: -1;

        int result = 0;
        int count = 2;
        int lastTop = 0;

        if (state == 0 ){
            count = 1; result = 1;
        }

        //最后放一个假节点（平）
        for(int i = 2; i <= ratings.length; i++) {
            int newState = 0;
            if( i != ratings.length)
                 newState = ratings[i - 1] == ratings[i] ? 0 : ratings[i] > ratings[i - 1] ? 1 : -1;

            // 平平 升升 降降
            if (newState == state) {
                if (newState == 0)  result++;
                else count++;
                continue;
            }

            if (state == 0) {
                count++;
                state = newState;
                continue;
            }

            result += (count * (count + 1) / 2);

            if (state > 0) {
                if (newState == 0) {
                    lastTop = 0;
                    count = 1;
                } else {
                    result -= count;
                    lastTop = count;
                    count = 2;
                }
                state = newState;
                continue;
            }

            if (state < 0) {
                result -= count;
                result += Math.max(lastTop, count); //补上顶点

                if (newState == 0) {
                    count = 1;
                }else {
                    result--;
                    count = 2;
                }
                lastTop = 0;
                state = newState;
                continue;
            }
        }
        return result;
    }



    //Runtime: 3 ms, faster than 86.96% of Java online submissions for Candy.
    //Memory Usage: 51.7 MB, less than 51.00% of Java online submissions for Candy.
    // 与candy2 相同，只是省了一些空间
    public int candy3(int[] ratings) {
        if (ratings == null) return 0;
        if (ratings.length <= 1) return 1;

        //右边比自己小的（只看右边）
        int[] right = new int[ratings.length];
        Arrays.fill(right,1);
        for(int i = ratings.length - 2; i >=0; i--)
            if (ratings[i] > ratings[i+1])
                right[i] = right[i+1] + 1;

        //计算左边的时候，同时计算总和
        int result = right[0];
        for(int i = 1; i < ratings.length; i++){
            if (ratings[i-1] < ratings[i]) {
                right[i] = Math.max(right[i], right[i - 1] + 1);
            }
            result += right[i];
        }
        return result;
    }


    //Runtime: 6 ms, faster than 29.60% of Java online submissions for Candy.
    //Memory Usage: 51.7 MB, less than 50.80% of Java online submissions for Candy.
    public int candy2(int[] ratings) {
        if (ratings == null) return 0;
        if (ratings.length <= 1) return 1;

        //左边比自己小的
        int[] left = new int[ratings.length];
        Arrays.fill(left,1);
        for(int i = 1; i < ratings.length; i++)
            if (ratings[i-1] < ratings[i])
                left[i] = left[i-1] + 1;

        //右边比自己小的
        int[] right = new int[ratings.length];
        Arrays.fill(right,1);
        for(int i = ratings.length - 2; i >=0; i--)
            if (ratings[i] > ratings[i+1])
                right[i] = right[i+1] + 1;

        // 取大的求和
        int result = 0;
        for(int i = 0; i<ratings.length; i++)
            result += Math.max(left[i],right[i]);
        return result;
    }

    // o(N*N) 性能不好
    public int candy1(int[] ratings) {
        if (ratings == null) return 0;
        if (ratings.length <= 1) return 1;

        List<int[]> larger = new ArrayList<>();

        int result = 1;
        int prev = 1;
        for(int i = 1; i < ratings.length; i++){
            int my = 1;
            if (ratings[i-1] < ratings[i]) {
                my = prev + 1;
                larger.clear();
            } else if(ratings[i-1] == ratings[i]) {
                larger.clear();
            }else if (ratings[i-1] > ratings[i]){
                larger.add(new int[]{ratings[i-1], prev});
                if (prev == my) {
                    result += handleLarger(larger);
                }
            }
            result += my;
            prev = my;
        }
        return result;
    }

    private int handleLarger(List<int[]> larger){
        if (larger == null || larger.size() <= 0) return 0;
        int[] p = larger.get(larger.size()-1);
        p[1] = p[1] + 1;
        int result = 1;
        for (int i = larger.size() - 2; i >= 0; i--){
            int[] c = larger.get(i);
            if (c[1] <= p[1]){
                c[1] = c[1] + 1;
                p = c;
                result++;
            }else
                break;
        }
        return result;
    }
}
