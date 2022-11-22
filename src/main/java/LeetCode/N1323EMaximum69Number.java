package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class N1323EMaximum69Number {


    //2.String
    //Runtime: 6 ms, faster than 27.76% of Java online submissions for Maximum 69 Number.
    //Memory Usage: 41.9 MB, less than 6.38% of Java online submissions for Maximum 69 Number.
    public int maximum69Number (int num) {
        return Integer.parseInt(String.valueOf(num).replaceFirst("6", "9"));
    }

    //1.Integer
    //Runtime: 1 ms, faster than 88.30% of Java online submissions for Maximum 69 Number.
    //Memory Usage: 41.6 MB, less than 13.13% of Java online submissions for Maximum 69 Number.
    //Time: O(logN); Space: O(logN)
    public int maximum69Number_1(int num) {

        List<Integer> list = new ArrayList<>();
        while (num > 1){
            list.add(num % 10);
            num /= 10;
        }

        int res = 0, k = 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            res *= 10;
            if (k == 1 && list.get(i) == 6){
                res += 9;
                k--;
            }else
                res += list.get(i);
        }
        return res;
    }
}
