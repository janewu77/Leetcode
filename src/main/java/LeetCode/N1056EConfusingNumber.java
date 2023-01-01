package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class N1056EConfusingNumber {


    //1.remainder
    //0 1 6 8 9; 2 3 4 5 7
    //Runtime: 1ms 69%; Memory: 38.9MB 91%
    //Time: log(N); Space:O(1)
    Set<Integer> invalidSet = new HashSet<>(Arrays.asList(2,3,4,5,7));
    public boolean confusingNumber(int n) {
        int res = 0, num = n;
        while (num >= 1){
            int r = num % 10;

            if (invalidSet.contains(r))
                return false;

            if (r == 6) r = 9;
            else if (r == 9) r = 6;

            res = res * 10 + r;
            num /= 10;
        }
        return n != res;
    }
}
