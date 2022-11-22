package LeetCode;

public class N647MPalindromicSubstrings {



    //1.Brute force
    //Runtime: 1207 ms, faster than 5.03% of Java online submissions for Palindromic Substrings.
    //Memory Usage: 42.3 MB, less than 50.96% of Java online submissions for Palindromic Substrings.
    //Time: O(N * N * N); Space: O(1)
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) res++;
            }
        }
        return res;
    }

    private boolean isPalindrome(String s , int l, int r){
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }
}
