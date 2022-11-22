package LeetCode;


/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions
 * after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of
 * calls to the API.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * Example 2:
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= bad <= n <= 231 - 1
 */

/**
 * M：
 *  - 二分法快速定位，O(log n) runtime complexity.
 *  - 切记："最大数"不可再加；"最小数"不可再减
 */
public class N278EFirstBadVersion {
    static int bad = 99999;

    public static void main(String[] args) {
        System.out.println("1 / 2    = " + (1 / 2) );
        System.out.println("2 / 2    = " + (2 / 2) );
        System.out.println("3 / 2    = " + (3 / 2) );

        System.out.println("Math.rint(1/2.0)    = " + Math.rint(1/2.0));
        System.out.println("Math.rint(2/2.0)    = " + Math.rint(2/2.0));
        System.out.println("Math.rint(3/2.0)    = " + Math.rint(3/2.0));

        System.out.println("Math.round(1/2.0)    = " + Math.round(1/2.0));
        System.out.println("Math.round(2/2.0)    = " + Math.round(2/2.0));
        System.out.println("Math.round(3/2.0)    = " + Math.round(3/2.0));

        System.out.println("Math.floor(1/2.0)    = " + Math.floor(1/2.0));
        System.out.println("Math.floor(2/2.0)    = " + Math.floor(2/2.0));
        System.out.println("Math.floor(3/2.0)    = " + Math.floor(3/2.0));


        runFirstBadVersion(1,1);
        runFirstBadVersion(5,0);
        runFirstBadVersion(5,6);

        runFirstBadVersion(5,1);
        runFirstBadVersion(5,2);
        runFirstBadVersion(5,3);
        runFirstBadVersion(5,4);
        runFirstBadVersion(5,5);

        runFirstBadVersion(2126753390,1702766719);
        runFirstBadVersion(75804946,67768598);

        runFirstBadVersion(131904690,27814230);
    }

    static private void runFirstBadVersion(int n, int iBad){
        bad = iBad;
        int result = (new N278EFirstBadVersion()).firstBadVersion(n);
        boolean r = (result == bad);
        if (iBad < 1 )
            r =  (result == 1);
        if (iBad > n )
            r =  (result == n);
        System.out.println("["+n+","+bad+"]result:"+result +"("+r+")");
    }

    boolean isBadVersion(int version){
        return version >= bad;
    }

    // log(n)
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r){
            // 避免大数操作。
            // 注意 l + r 可能发生溢出，所以改成 l + (r - l) / 2
            int i = l + (r - l) / 2;
            if (isBadVersion(i)) {
                r = i;
            }else{
                l = i + 1;
            }
        }
        return r;
    }

}
