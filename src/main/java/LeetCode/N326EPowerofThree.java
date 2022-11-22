package LeetCode;

/**
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 * Example 2:
 *
 * Input: n = 0
 * Output: false
 * Example 3:
 *
 * Input: n = 9
 * Output: true
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 */


/**
 * 虽然是个E,但它的解法让人开眼界。
 */
public class N326EPowerofThree {

    //这个有点赖。利用了最大值，和质数的特定
    //1162261467 = 3的19次方
    //Approach 4: Integer Limitations
    //Time: O(1); Space: O(1)
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    //用到公式 。  i = x的n次方的， 求n公式。
    //注意这里有精度问题
    //性能取决于Math.log10
    //Approach 3: Mathematics
    public boolean isPowerOfThree_3(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        //return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }

    //换base ，然后检查是否是  10000,10...这种格式
    //但换base的算法本身是和1类似的！加上匹配之后。性能比1差。
    //Approach 2: Base Conversion
    public boolean isPowerOfThree_2(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }

    //1。常规做法。第一反就是这么写
    //Approach 1: Loop Iteration
    public boolean isPowerOfThree_1(int n) {
        if (n < 1) return false;
        while (n % 3 == 0) n /= 3;
        return n == 1;
    }
}
