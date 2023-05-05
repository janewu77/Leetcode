package Contest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.time.LocalTime.now;

public class N1456MMaximumNumberofVowelsinaSubstringofGivenLength {


    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun(2, "zpuerktejfp", 3);
        System.out.println(now());
        System.out.println("==================");
    }

    //contest: 92  330 ; 97 331; 98 333
    //2547 1494 460 1908 1626 2565. 2564 1259
    static private void doRun(int expect, String s, int k) {
        long res = new N1456MMaximumNumberofVowelsinaSubstringofGivenLength().maxVowels(s,k);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //1. Slide window
    //Time: 16ms 63%; Memory: 43.3MB 41%
    //Time: O(N); Space:O(1)
    public int maxVowels(String s, int k) {
        int res = 0, count = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            if (isVowel(s.charAt(right++))) count++;
            if (right - left > k)
                if (isVowel(s.charAt(left++))) count--;
            res = Math.max(res, count);
            if (res == k) return k;
        }
        return res;
    }

    private boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
