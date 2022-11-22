package LeetCode;

import utils.comm;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary
 * wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest
 * transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 *
 */

/**
 * H - [time: 120+
 * - bfs + backtracking
 */
//
public class N126HWordLadderII {


    public static void main(String[] args){
        String[] data;
        String expect;

        System.out.println(now());

        data = new String[]{"lot","log","cog","hot","dot","dog"};
        expect = "[[hit, hot, lot, log, cog],[hit, hot, dot, dog, cog]]";
//        expect = "[[hit, hot, dot, dog, cog],[hit, hot, lot, log, cog]]";
        doRun(expect,"hit", "cog", comm.strArr2List(data));

        data = new String[]{"hot","dot","dog","lot","log"};
        expect = "[]";
        doRun(expect,"hit", "cog", comm.strArr2List(data));

        data = new String[]{"most","mist","miss","lost","fist","fish"};
        expect = "[[lost, most, mist, miss]]";
        doRun(expect,"lost", "miss", comm.strArr2List(data));

        data = new String[]{"lest","leet","lose","code","lode","robe","lost"};
        expect = "[[leet, lest, lost, lose, lode, code]]";
        doRun(expect,"leet", "code", comm.strArr2List(data));

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res1 = new N126HWordLadderII()
                .findLadders(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        String res = "[" + res1.stream().map(x->x.toString()).collect(Collectors.joining(",")) +"]";
        //String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //本质上和1一样。区别是：这里用了单独的node，并保存parent信息。
    //Runtime: 18 ms, faster than 77.27% of Java online submissions for Word Ladder II.
    //Memory Usage: 52.2 MB, less than 22.65% of Java online submissions for Word Ladder II.
    //BFS + backtracking
    class Node126{
        String word;
        Set<Node126> parent = null;
        public Node126(String w){
            word = w;
            parent = new HashSet<>();
        }
        public Node126(String w, Node126 p){
            word = w;
            if (parent == null) parent = new HashSet<>();
            parent.add(p);
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Node126> allUsed = new HashMap<>(); //word: neighbours
        Set<String> wordsSet = new HashSet<>(wordList);
        if (wordsSet.contains(beginWord))
            wordsSet.remove(beginWord);

        Node126 root = new Node126(beginWord);

        Queue<Node126> queue = new LinkedList<>();
        queue.add(root);

        Set<String> usedAll = new HashSet<>();
        usedAll.add(beginWord);

        //BFS
        while (!queue.isEmpty()) {
            Set<String> usedSet = new HashSet<>();
            boolean isMeet = false;
            for (int i = queue.size() - 1; i >= 0; i--) {
                Node126 currNode = queue.poll();

                if (currNode.word.equals(endWord)) {
                    //output(currNode, beginWord, res);
                    List<String> path = new ArrayList<>();
                    path.add(currNode.word);
                    //输出当前的可达路径
                    output_backtracking(currNode, endWord,res, path);

                    isMeet = true;
                    continue;
                }

                Set<String> neighbours = findNeighbours(currNode.word, wordsSet);

                for (String neighbour : neighbours) {
                    Node126 child = new Node126(neighbour, currNode);
                    usedSet.add(neighbour);
                    if (!allUsed.containsKey(neighbour)) {
                        queue.add(child);
                        allUsed.put(neighbour, child);
                    }else{
                        allUsed.get(neighbour).parent.add(currNode);
                    }
                }
            }//End for

            if (isMeet) break;
            wordsSet.removeAll(usedSet);
        }
        return res;
    }

    //输出结果 ，注意这里的顺序。 一个节点可能有多个父。
    private void output_backtracking(Node126 node, String endWord, List<List<String>> res, List<String> path){
        if (node.word.equals(endWord)){
            List<String> tmpPath = new ArrayList<>(path);
            Collections.reverse(tmpPath);
            res.add(tmpPath);
        }
        for (Node126 p : node.parent){
            path.add(p.word);
            output_backtracking(p, endWord, res, path);
            path.remove(path.size() - 1);
        }
    }


    //Runtime: 16 ms, faster than 83.69% of Java online submissions for Word Ladder II.
    //Memory Usage: 42.8 MB, less than 93.51% of Java online submissions for Word Ladder II.
    //DFS + backtracking
    public List<List<String>> findLadders_1(String beginWord, String endWord, List<String> wordList) {

        Map<String, Set<String>> graph = helper_dfs(new HashSet<>(wordList), beginWord, endWord);

        List<List<String>> res = new ArrayList<>();
        List<String> currPath = new ArrayList<>();
        currPath.add(endWord);

        help_backtrack(graph, endWord, beginWord, currPath, res);
        return res;
    }

    private void help_backtrack(Map<String, Set<String>> graph, String beginWord, String endWord,
                                List<String> currPath, List<List<String>> res){
        if (beginWord.equals(endWord)) {
            List<String> tempPath = new ArrayList<>(currPath);
            Collections.reverse(tempPath);
            res.add(tempPath);
            return;
        }

        if (!graph.containsKey(beginWord)) return;

        Set<String> neighbours = graph.get(beginWord);
        for (String neighbour: neighbours) {
            currPath.add(neighbour);
            help_backtrack(graph, neighbour, endWord, currPath, res);
            currPath.remove(currPath.size() - 1);
        }
    }

    private Map<String, Set<String>> helper_dfs(Set<String> wordsSet, String beginWord, String endWord){
        Map<String, Set<String>> adjList = new HashMap<>(); //word: neighbours

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        if (wordsSet.contains(beginWord))
            wordsSet.remove(beginWord);

        while (!queue.isEmpty()) {
            Set<String> usedSet = new HashSet<>();
            boolean isMeet = false;
            for (int i = queue.size() - 1; i >= 0; i--) {
                String currWord = queue.poll();
                Set<String> neighbours = findNeighbours(currWord, wordsSet);

                for (String neighbour : neighbours) {
                    usedSet.add(neighbour);

                    if (!adjList.containsKey(neighbour)) {
                        queue.add(neighbour);
                        adjList.put(neighbour, new HashSet<>());
                    }
                    adjList.get(neighbour).add(currWord);
                }
                if (currWord.equals(endWord)) isMeet = true;
            }//End for
            if (isMeet) break;
            wordsSet.removeAll(usedSet);
        }
        return adjList;
    }

    private Set<String> findNeighbours(String word, Set<String> wordsSet) {
        Set<String> group = new HashSet<>();

        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[i] = c;
                String candidate = String.valueOf(chars);
                if (wordsSet.contains(candidate))
                    group.add(candidate);
            }
            chars[i] = oldChar;
        }
        return group;
    }

}
