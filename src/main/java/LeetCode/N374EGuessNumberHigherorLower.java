package LeetCode;

public class N374EGuessNumberHigherorLower {


    //2.Binary search
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Guess Number Higher or Lower.
    //Memory Usage: 39.2 MB, less than 83.85% of Java online submissions for Guess Number Higher or Lower.
    //Time: O(logN); Space: O(1)
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left <= right) {
            //int num = left + (right - left) / 2;
            int num = (left + right) >>> 1; //ignore sign
            int res = guess(num);
            if (res > 0) left = num + 1;
            else if (res < 0) right = num - 1;
            else return num;
        }
        return 0;
    }


    //1.Brute force
    //TLE
    //Time: O(N); Space: O(1)
    public int guessNumber_1(int n) {
        for (int i = 1; i <= n; i++)
            if (guess(i) == 0) return i;
        return 0;
    }


    private int guess(int n){
        return 0;
    }
}
