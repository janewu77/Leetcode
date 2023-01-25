package LeetCode;


import utils.comm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalTime.now;

/**
 * Given a string s, partition s such that every
 * substring
 *  of the partition is a
 * palindrome
 * . Return all possible palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 */
public class N131MPalindromePartitioning {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");


        doRun("[[a, a, b],[aa, b]]", "aab");
        doRun("[[a]]", "a");

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, String s) {
        List<List<String>> res1 = new N131MPalindromePartitioning().partition(s);
        comm.printListListString(expect, res1);
    }

    //5.backtracking + pre-compute
    //Runtime: 9ms 90%; Memory: 174MB 24%
    //Time: O(N * N + 2^N); Space: O(N * N)
    public List<List<String>> partition(String s) {
        int n = s.length();
        String[][] palindromeList = new String[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                if (isPalindrome(s, i, j)) palindromeList[i][j] = s.substring(i, j + 1);

        List<List<String>> res = new ArrayList<>();
        helper_backtracking(n, palindromeList, 0, new ArrayList<>(), res);
        return res;
    }
    private void helper_backtracking(int n, String[][] pList, int begin,
                                     List<String> list, List<List<String>> res){
        if (begin == n)
            res.add(new ArrayList<>(list));

        for (int i = begin; i < n; i++) {
            if (pList[begin][i] != null) {
                list.add(pList[begin][i]);
                helper_backtracking(n, pList, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }

    //4.backtracking + DP
    //Runtime: 10ms 66%; Memory: 54.7MB 77%
    //Time: O(2^N * N ); Space: O(N * N)
    public List<List<String>> partition_4(String s) {
        List<List<String>> res = new ArrayList<>();
        helper_backtracking(s, 0, new ArrayList<>(), res, new boolean[s.length()][s.length()]);
        return res;
    }
    private void helper_backtracking(String s, int begin,
                                     List<String> list, List<List<String>> res, boolean[][] dp){
        if (begin == s.length()){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int idx = begin; idx >= 0 && idx < s.length(); idx++){
            idx = s.indexOf(s.charAt(begin), idx);
            if (idx < 0) return;
            //if (idx - begin > 2) System.out.println(dp[begin + 1][idx - 1]);
            if (s.charAt(begin) == s.charAt(idx) && (idx - begin <= 2 || dp[begin + 1][idx - 1])){
                dp[begin][idx] = true;
                list.add(s.substring(begin, idx + 1));
                helper_backtracking(s, idx + 1, list, res, dp);
                list.remove(list.size() - 1);
            }
        }
    }

    //3.backtracking
    //Runtime: 14ms 48%; Memory: 194MB 12%
    //Time: O(2^N * N ); Space: O(N)
    public List<List<String>> partition_3(String s) {
        List<List<String>> res = new ArrayList<>();
        helper_backtracking(s, 0, new ArrayList<>(), res);
        return res;
    }
    private void helper_backtracking(String s, int begin, List<String> list, List<List<String>> res){
        if (begin == s.length()){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int idx = begin; idx >= 0 && idx < s.length(); idx++){
            idx = s.indexOf(s.charAt(begin), idx);
            if (idx < 0) return;

            if (isPalindrome(s, begin, idx)) {
                list.add(s.substring(begin, idx + 1));
                helper_backtracking(s, idx + 1, list, res);
                list.remove(list.size() - 1);
            }
        }
    }


    //2.iteration
    //Runtime: 14ms 48%; Memory: 194MB 12%
    //Time: O((2 ^ N) * N); Space: O(2 ^ N + N)
    public List<List<String>> partition_2(String s) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(new ArrayList<>(Arrays.asList(s.substring(0, 1))));

        List<Integer> tobeDelete = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            int listSize = lists.size();
            for (int j = 0; j < listSize; j++) {
                List<String> list = lists.get(j);
                String tmp = list.get(list.size() - 1);
                if (isPalindrome(tmp)){
                    List<String> list2 = new ArrayList<>(list);
                    list2.add(s.substring(i, i + 1));
                    lists.add(list2);
                }
                tmp = tmp + s.charAt(i);
                if (i != s.length() - 1 || isPalindrome(tmp))
                    list.set(list.size() - 1, tmp);
                else tobeDelete.add(j);
            }
        }

        for (int i = tobeDelete.size() - 1; i >= 0; i--)
            lists.remove(lists.get(tobeDelete.get(i)));

        return lists;
    }

    //1.bitmask
    //Runtime: 24ms 20%; Memory: 73.3MB 38%
    //Time: O(2^N * N * N); Space: O(N)
    public List<List<String>> partition_1(String s) {
        List<List<String>> res = new ArrayList<>();

        for (int i = (1 << (s.length() - 1)); i < (1 << s.length()); i++){
            List<String> list = new ArrayList<>();
            int mask = i, lastIdx = -1;
            for (int idx = 0; mask > 0; mask >>= 1, idx++){
                if ((mask & 1) == 1){
                    if (!isPalindrome(s, lastIdx + 1, idx)) {
                        list.clear(); break;
                    }
                    list.add(s.substring(lastIdx + 1, idx + 1));//Space: O(N)
                    lastIdx = idx;
                }
            }
            if (!list.isEmpty()) res.add(list);
        }
        return res;
    }

    //Time: O(N); Space: O(1)
    public boolean isPalindrome(String s, int left, int right) {
        for (; left < right; left++, right--)
            if (s.charAt(left) != s.charAt(right)) return false;
        return true;
    }

    public boolean isPalindrome(String s) {
        for (int left = 0, right = s.length() - 1; left < right; left++, right--)
            if (s.charAt(left) != s.charAt(right)) return false;
        return true;
    }
}
