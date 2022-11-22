package LeetCode.Database;


/**
 *
 * There is a special square room with mirrors on each of the four walls.
 * Except for the southwest corner, there are receptors on each of the remaining corners, numbered 0, 1, and 2.
 *
 * The square room has walls of length p and a laser ray from the southwest
 * corner first meets the east wall at a distance q from the 0th receptor.
 *
 * Given the two integers p and q, return the number of the receptor that the ray meets first.
 *
 * The test cases are guaranteed so that the ray will meet a receptor eventually.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: p = 2, q = 1
 * Output: 2
 * Explanation: The ray meets receptor 2 the first time it gets reflected back to the left wall.
 * Example 2:
 *
 * Input: p = 3, q = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= q <= p <= 1000
 *
 */

/**
 * M - [Time: 未】
 *
 */
public class N858MMirrorReflection {

    //p & -p 奇偶性
    public int mirrorReflection_3(int p, int q) {
        return (p & -p) > (q & -q) ? 2 : (p & -p) < (q & -q) ? 0 : 1;
    }

    //更精炼的写法 bit操作
    // （p & 1) == 0 偶数。（偶数的最末bit为零）
    // p >>= 1 右移等同于 整除2
    public int mirrorReflection_2(int p, int q) {
        while ((p & 1) == 0 && (q & 1) == 0) { p >>= 1;  q >>= 1; }
        return 1 + (q & 1) - (p & 1); //这个应该是正好能凑。
    }

    //网上看来的。
    //此处需要画图。
    // 把方块横向画出来，斜率：p/q.
    // 要到达某个顶点，必然存在二个整数m,n, 使得 q/p == mp/np >> q/p == m/n >> mp == nq
    //2。怎么算哪个角？0？1？2？
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Mirror Reflection.
    //Memory Usage: 40.9 MB, less than 52.78% of Java online submissions for Mirror Reflection.
    public int mirrorReflection_1(int p, int q) {
        int m = 1, n = 1;
        //这里利用了int是整型。最后一定会算出一对整数mn,由于是n++的方式。不会出现倍数。
        while(m * p != n * q){
            n++;
            m = n * q / p;
        }

        //不会有 偶偶的情况，因为 偶偶 之前一定有 奇奇。
        if (m % 2 == 0 && n % 2 == 1) return 0; //偶q奇p
        if (m % 2 == 1 && n % 2 == 1) return 1; //奇q奇p
        if (m % 2 == 1 && n % 2 == 0) return 2; //奇q偶p
        return -1;
    }


}
