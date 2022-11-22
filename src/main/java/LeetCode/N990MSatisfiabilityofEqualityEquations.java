package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given an array of strings equations that represent relationships between variables where each string equations[i] is of length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) that represent one-letter variable names.
 *
 * Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = ["a==b","b!=a"]
 * Output: false
 * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
 * There is no way to assign the variables to satisfy both equations.
 * Example 2:
 *
 * Input: equations = ["b==a","a==b"]
 * Output: true
 * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
 *
 *
 * Constraints:
 *
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] is a lowercase letter.
 * equations[i][1] is either '=' or '!'.
 * equations[i][2] is '='.
 * equations[i][3] is a lowercase letter.
 */

/**
 * M - [time: 60-
 */
public class N990MSatisfiabilityofEqualityEquations {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        doRun(false, new String[]{"a==b","e==c","b==c","a!=e"});

        doRun(false, new String[]{"b!=a","a==b"});
        doRun(true, new String[]{"b==a","a==b"});
        doRun(true, new String[]{"c==c","b==d","x!=z"});
        doRun(false, new String[]{"a==b","b!=c","c==a"});
        doRun(false, new String[]{"a!=a"});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, String[] equations) {
        boolean res = new N990MSatisfiabilityofEqualityEquations()
                .equationsPossible(equations);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for Satisfiability of Equality Equations.
    //Memory Usage: 42.7 MB, less than 36.52% of Java online submissions for Satisfiability of Equality Equations.
    //Union-find
    //Time: O(26 + 2N*N); space:O(26)
    //Time: O(N*N); space:O(1)
    public boolean equationsPossible(String[] equations) {

        int[] unionFind = new int[26];
        for (int i = 0; i < 26; i++) unionFind[i] = i;

        for (String equation : equations)
            if (equation.charAt(1) == '=')
                union(unionFind, equation.charAt(0) - 'a', equation.charAt(3) - 'a');

        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                if (find(unionFind, equation.charAt(0) - 'a') == find(unionFind, equation.charAt(3) - 'a'))
                    return false;
            }
        }
        return true;
    }

    //Time: worst case: O(N)
    private int find(int[] uf, int x){
        while (uf[x] != x) x = uf[x];
        return uf[x];
    }

    //Time: worst case: O(N)
    private void union(int[] uf, int x , int y){
        uf[find(uf, x)] = find(uf,y);
    }

    //Runtime: 7 ms, faster than 9.33% of Java online submissions for Satisfiability of Equality Equations.
    //Memory Usage: 43.2 MB, less than 14.86% of Java online submissions for Satisfiability of Equality Equations.
    //brute force
    //Time: O(N*N); Space: O(N)
    public boolean equationsPossible_1(String[] equations) {

        List<Set<Character>> list = new LinkedList<>();

        //Time: O(N * N)
        for(String equation : equations)
            if (equation.charAt(1) == '=')
                merge(list, equation.charAt(0), equation.charAt(3));

        //Time: O(N * N)
        for(String equation : equations) {
            if (equation.charAt(1) == '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                if (a == b) return false;
                Set<Character> setA = search(list, a);
                if (setA != null && setA.contains(b)) return false;
            }
        }
        return true;
    }

    private Set<Character> search(List<Set<Character>> list, char target){
        for (Set<Character> set : list)
            if (set.contains(target)) return set;
        return null;
    }

    private void merge(List<Set<Character>> list, char a, char b){
        if (a == b) return;

        Set<Character> mergeSet = new HashSet<>();
        mergeSet.add(a); mergeSet.add(b);

        List<Set<Character>> toberemove = new ArrayList<>();
        for (Set<Character> set : list){
            if (set.contains(a) || set.contains(b)) {
                mergeSet.addAll(set);
                toberemove.add(set);
            }
        }
        for (Set<Character> set: toberemove) list.remove(set);
        list.add(mergeSet);
    }


}
