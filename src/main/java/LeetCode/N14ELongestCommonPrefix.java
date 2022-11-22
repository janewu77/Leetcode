package LeetCode;


/***
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Constraints:
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 *
 */

import java.util.*;

/**
 * E - [time: 15-]
 *     - 简单做法：indexOf
 *     - 优化1： 先找出最短字串，再用倒减 + indexOf. (最短字串，最有可能是前缀；尽可能从长往短匹配）
 *     - 复杂算法： tries . (加上class之后，性能未必好）
 *
 */
public class N14ELongestCommonPrefix {

    public static void main(String[] args) {
//
        doRun("", new String[]{"dog","racecar","car"});
        doRun("fl", new String[]{"flower","flow","flight"});
        doRun("", new String[]{"flower","","flight"});

        doRun("a", new String[]{"ab","a"});

        doRun("a", new String[]{"a"});

    }

    private static int c = 1;
    private static void doRun(String expected, String[] words){
        String res = new N14ELongestCommonPrefix().longestCommonPrefix( words);
        System.out.println("[" + (expected.equals(res)) +"] "+(c++)+ ".result:"+ res + ".expected:"+expected);
    }

    //Runtime: 5 ms, faster than 28.21% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 42.7 MB, less than 20.68% of Java online submissions for Longest Common Prefix.
    public String longestCommonPrefix(String[] strs) {

        WSIITreeNode root = new WSIITreeNode('#');
        buildTree(root, strs);
        if (root.children.size() != 1) return "";

        root = root.getChild();
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            if (root.equals(EndNode)) break;
            sb.append(root.c);
            if (root.children.size() != 1) break;
            root = root.getChild();
        }
        return sb.toString();
    }

    private final WSIITreeNode EndNode = new WSIITreeNode('-');
    private void buildTree(WSIITreeNode root, String[] words){
        for (String word: words) {
            WSIITreeNode p = root;
            if (word.isEmpty()) p.addChild(EndNode);

            for (char c : word.toCharArray()) {
                if (!p.childSet.contains(c)) p.addChild(new WSIITreeNode(c));
                p = p.getChild(c);
            }
            p.addChild(EndNode);
        }
    }

    class WSIITreeNode {
        char c;
        Set<Character> childSet = new HashSet<>();
        private List<WSIITreeNode> children = new ArrayList<>();
        WSIITreeNode(char c1) {
            c = c1;
        }
        public void addChild(WSIITreeNode node){
            childSet.add(node.c);
            children.add(node);
        }
        public WSIITreeNode getChild(){
            if (children.isEmpty()) return null;
            return children.get(0);
        }
        public WSIITreeNode getChild(char c){
            for(WSIITreeNode node: children){
                if (node.c == c) return node;
            }
            return null;
        }
    }

    //Runtime: 1 ms, faster than 88.54% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 42 MB, less than 63.49% of Java online submissions for Longest Common Prefix.
    public String longestCommonPrefix_2(String[] strs) {

        int min = 201;
        int idx = -1;
        for (int i = 0; i<strs.length; i++){
            if (strs[i].length() < min){
                min = strs[i].length(); idx = i;
            }
        }

        String tmp = strs[idx];
        boolean isDone;
        while (tmp.length() > 0) {
            isDone = true;
            for (String str: strs) {
                if (str.indexOf(tmp) != 0) {
                    isDone = false;
                    break;
                }
            }
            if (isDone) break;
            tmp = tmp.substring(0, tmp.length()-1);
        }

        return tmp;
    }

    //Runtime: 4 ms, faster than 30.80% of Java online submissions for Longest Common Prefix.
    //Memory Usage: 41.9 MB, less than 69.36% of Java online submissions for Longest Common Prefix.
    public String longestCommonPrefix_1(String[] strs) {
        String tmp = strs[0];

        int i = 0;

        outer:
        while (i < tmp.length()) {
            String sub = tmp.substring(0, i + 1);
            for (String str: strs)
                if (str.indexOf(sub) != 0) break outer;
            i++;
        }

        return tmp.substring(0, i);
    }
}
