package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of digits only.
 */
public class N93NRestoreIPAddresses {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");

        doRun("", "9245587303");
        doRun("0.0.0.0", "0000");
        doRun("", "000256");
        doRun("10.10.10.10,10.10.101.0,10.101.0.10,101.0.10.10,101.0.101.0", "10101010");

        doRun("1.0.10.23,1.0.102.3,10.1.0.23,10.10.2.3,101.0.2.3", "101023");
        doRun("255.255.11.135,255.255.111.35", "25525511135");

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, String s) {
        List<String> res1 = new N93NRestoreIPAddresses().restoreIpAddresses(s);
        Collections.sort(res1);
        String res = res1.stream().collect(Collectors.joining(","));
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //4.iteration
    //Runtime: 4ms 68%; Memory: 42.7MB 59%
    //Time: O(N * N!); Space:O(N! + N)
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 ||  s.length() < 4) return new ArrayList<>();

        List<List<Integer>> resList = new ArrayList<>();
        resList.add(new ArrayList<>(Arrays.asList(s.charAt(0) - '0')));

        for (int i = 1; i < s.length(); i++) {
            int c = s.charAt(i) - '0';

            List<List<Integer>> resListAdd = new ArrayList<>();
            List<List<Integer>> resListRemove = new ArrayList<>();
            for (List<Integer> subList : resList){
                int v = subList.get(subList.size() - 1);
                if (v == 0 || v * 10 + c > 255) {
                    if (subList.size() < 4) subList.add(c);
                    else resListRemove.add(subList);

                }else{
                    if (subList.size() < 4) {
                        List<Integer> list2 = new ArrayList<>(subList);
                        list2.add(c);
                        resListAdd.add(list2);
                    }
                    subList.set(subList.size() - 1, v * 10 + c);
                }
            }
            resList.addAll(resListAdd);
            resList.removeAll(resListRemove);
        }

        // Space: O(N)
        List<String> res = new ArrayList<>();
        for (List<Integer> list : resList){
            if (list.size() != 4) continue;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.size() - 1; j++)
                sb.append(list.get(j)).append(".");
            res.add(sb.append(list.get(list.size() - 1)).toString());
        }
        return res;
    }


    //3.BitMask
    //Runtime: 6ms 54%; Memory: 43.2MB 35%
    //Time:Time: O((2^N) * N); Space:O(N)
    public List<String> restoreIpAddresses_3(String s) {
        if (s.length() > 12 ||  s.length() < 4) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for(int i = (1 << (s.length() - 1)); i < ( 1 << s.length()); i++)
            if (Integer.bitCount(i) == 4) helper_verify(s, i, res);
        return new ArrayList<>(res);
    }

    //Time: O(N); Space: O(N)
    private void helper_verify(String s, int mask, List<String> res){
        StringBuilder sb = new StringBuilder();
        int val = 0, lastIdx = -1;
        for (int i = 0; i < s.length(); i++) {
            val = val * 10 + (s.charAt(i) - '0');
            if (val > 255 || (i - lastIdx > 1 && val < 10)) return;

            if ((mask & (1 << i)) == 0) continue;
            sb.append(val).append('.');
            val = 0; lastIdx = i;
        }
        res.add(sb.deleteCharAt(sb.length() - 1).toString());
    }

    //2.backtracking2
    //Runtime: 1ms 93%; Memory: 41MB 97%
    //Time: O( (M ^ N) * N ); Space: O(N + L)
    //let L be the length of the input String
    //let M be the number of digits in each part
    //let N be the number of parts to be separate in the input String.
    public List<String> restoreIpAddresses_2(String s) {
        if (s.length() > 12 ||  s.length() < 4) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        helper_backtracking_2(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void helper_backtracking_2(String s, int idx, List<String> list, List<String> res) {
        if (list.size() == 3) {
            if (helper_validate(s, idx, s.length() - idx)) {
                StringBuilder sb = new StringBuilder(); // Space: O(L + 3)
                for (int j = 0; j < list.size(); sb.append(list.get(j)).append("."), j++) ;
                res.add(sb.append(s.substring(idx)).toString());
            }
            return;
        }
        //Time: O(M *)
        for (int i = 1; i <= 3 && idx + i < s.length(); i++) {
            if (!helper_validate(s, idx, i)) break;
            list.add(s.substring(idx, idx + i));
            helper_backtracking_2(s, idx + i, list, res);
            list.remove(list.size() - 1);
        }
    }

    private boolean helper_validate(String s, int idx, int len){
        if (len > 3) return false;
        if (len == 1) return true;
        return (s.charAt(idx) != '0' && (len < 3 || s.substring(idx, idx + len).compareTo("255") <= 0));
    }


    //1.backtracking1
    //Runtime: 1ms 100%; Memory: 41.4MB 89%
    //Time:Time: O((2^(3 * 4) * 4); Space:O(4 + N)
    public List<String> restoreIpAddresses_1(String s) {
        if (s.length() > 12 ||  s.length() < 4) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        helper_backtracking(s, 0, new ArrayList<>(), 0, res);
        return res;
    }

    private void helper_backtracking(String s, int idx, List<Integer> list, int i, List<String> res) {
        if (i >= 4 || idx > s.length() || list.size() > 4) return;
        if (idx == s.length() && list.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.size() - 1; j++)
                sb.append(list.get(j)).append(".");
            res.add(sb.append(list.get(list.size() - 1)).toString());
            return;
        }
        if (idx >= s.length()) return;

        int val = (i == list.size()) ? 0 : list.get(i);
        int c = s.charAt(idx) - '0';
        idx++;

        if ((val * 10 + c) > 255) return;

        if (list.size() == i) {
            list.add(c);
            helper_backtracking(s, idx, list, i + 1, res);
            if (c != 0 || idx == s.length())
                helper_backtracking(s, idx, list, i, res);
            list.remove(list.size() - 1);

        } else {
            if (val == 0) return;
            list.set(i, val * 10 + c);
            helper_backtracking(s, idx, list, i + 1, res);
            if (list.get(i) < 26 || idx == s.length())
                helper_backtracking(s, idx, list, i, res);
            list.set(i, val);
        }
    }


}
