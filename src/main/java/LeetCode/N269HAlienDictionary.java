package LeetCode;



import java.util.*;

import static java.time.LocalTime.now;

/**
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 */

/**
 * H - [time: 60+
 * todo:
 */
public class N269HAlienDictionary {


    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun("jkrtw", new String[]{"wrt","wrtkj"});
        doRun("aczb", new String[]{"ac","ab","zc","zb"});
        doRun("bdgilpqsvwxc", new String[]{"vlxpwiqbsg","cpwqwqcd"});
        doRun("abcd", new String[]{"ab","adc"});
        //yxz
        doRun("yzx", new String[]{"zy","zx"});
        doRun("zx", new String[]{"z","x"});
        doRun("z", new String[]{"z","zz"});
        doRun("z", new String[]{"zz","zz"});

        doRun("wertf", new String[]{"wrt","wrf","er","ett","rftt"});

        doRun("", new String[]{"z","x","z"});

        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, String[] words) {
        String res = new N269HAlienDictionary().alienOrder(words);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //2.BFS + topological sort
    //Runtime: 2 ms, faster than 99.89% of Java online submissions for Alien Dictionary.
    //Memory Usage: 40.3 MB, less than 98.62% of Java online submissions for Alien Dictionary.
    //Time: O(N * K); Space: O(26*26)
    //Time: O(N * K); Space: O(1)
    //let K be the largest length of the word in the input array.
    //let N be the number of strings in the input array.
    public String alienOrder(String[] words) {

        //Time: O(26 + K); Space: O(26)
        int[] indegreeList = new int[26];
        Arrays.fill(indegreeList, -1);
        for(int c :words[0].toCharArray()) indegreeList[c - 'a'] = 0;

        //Time: O(26); Space: O(26*26)
        Set<Integer>[] graph = new Set[26];
        for (int i = 0; i < 26; i++) graph[i] = new HashSet<>();
        //Time: O(N * K)
        for (int i = 1; i < words.length; i++)
            if (!buildGraph2(words[i - 1], words[i], graph, indegreeList)) return "";

        //Time: O(26); Space: O(26)
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            if (indegreeList[i] == 0) queue.add(i);
            if (indegreeList[i] != -1) count++;
        }

        //Time: O(26); Space: O(26)
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            sb.append((char)(node + 'a'));

            for (int v : graph[node])
                if ( --indegreeList[v] == 0) queue.add(v);
        }
        return sb.length() != count ? "" : sb.toString();
    }

    //Time: O(K); Space:O(1)
    private boolean buildGraph2(String str1, String str2, Set<Integer>[] graph, int[] indegreeList){
        int idx = 0;
        for (; idx < str1.length() && idx < str2.length(); idx++) {
            if (str1.charAt(idx) == str2.charAt(idx)) {
                if (indegreeList[str2.charAt(idx) - 'a'] < 0) indegreeList[str2.charAt(idx) - 'a'] = 0;
                continue;
            }
            if (indegreeList[str1.charAt(idx) - 'a'] < 0) indegreeList[str1.charAt(idx) - 'a'] = 0;
            if (indegreeList[str2.charAt(idx) - 'a'] < 0) indegreeList[str2.charAt(idx) - 'a'] = 0;
            if (graph[str1.charAt(idx) - 'a'].add(str2.charAt(idx) - 'a'))
                indegreeList[str2.charAt(idx) - 'a']++;
            break;
        }
        if (idx < str1.length() && idx == str2.length()) return false;// "abc"  "ab"
        while (idx < str2.length()) {
            if (indegreeList[str2.charAt(idx) - 'a'] < 0) indegreeList[str2.charAt(idx) - 'a'] = 0;
            idx++;
        }
        return true;
    }


    //1.DFS
    //Runtime: 2 ms, faster than 99.89% of Java online submissions for Alien Dictionary.
    //Memory Usage: 40 MB, less than 99.64% of Java online submissions for Alien Dictionary.
    //Time: O(N * K); Space: O(1)
    //let K be the largest length of the word in the input array.
    //let N be the number of strings in the input array.
    public String alienOrder_1(String[] words) {
        //Build graph
        //Time: O(26 + K); Space:  O(26 * 26) or O(E)
        List<Integer>[] graph = new List[26];
        for (int i = 0; i < 26; i++) graph[i] = new ArrayList<>();
        for(char c : words[0].toCharArray()) graph[c - 'a'].add(c - 'a');
        //Time: O(N * K)
        for (int i = 1; i < words.length; i++)
            if (!buildGraph(words[i - 1], words[i], graph)) return "";

        //count
        int count = 0;
        for (int i = 0; i < 26; i++) if (graph[i].size() > 0) count++;

        //DFS
        StringBuilder sb = new StringBuilder();
        int[] status = new int[26];//0 white; 1 gray; 2 black
        //Time: O(26); Space: O(26)
        for (int i = 0; i < 26; i++) {
            if (graph[i].size() == 0) continue;
            if (status[i] == 0) helper_dfs(graph, status, i, sb);
            else if (status[i] == 1) break;  //cycle
        }
        return sb.length() != count ? "" : sb.toString();
    }

    //Time: O(K)
    //let K be the largest length of input string.
    private boolean buildGraph(String str1, String str2, List<Integer>[] graph){
        int idx = 0;
        for (; idx < str1.length() && idx < str2.length(); idx++) {
            if (str1.charAt(idx) == str2.charAt(idx)) {
                graph[str2.charAt(idx) - 'a'].add(str2.charAt(idx) - 'a');
                continue;
            }
            graph[str2.charAt(idx) - 'a'].add(str1.charAt(idx) - 'a');
            break;
        }
        if (idx < str1.length() && idx == str2.length()) return false;// "abc"  "ab"
        while (idx < str2.length())
            graph[str2.charAt(idx) - 'a'].add(str2.charAt(idx++) - 'a');
        return true;
    }

    //Time: O(26); Space: O(26)
    //Time: O(V); Space: O(V)
    private void helper_dfs(List<Integer>[] graph, int[] status, int c, StringBuilder sb)  {
        status[c] = 1;
        for (int v: graph[c]) {
            if (v == c) continue;
            if (status[v] == 0) helper_dfs(graph, status, v , sb);
            else if (status[v] == 1) return; //cycle
        }
        status[c] = 2;
        sb.append((char)(c + 'a'));
    }
}
