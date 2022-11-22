package LeetCode;

import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList
 * is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 */

/**
 * H - [time: 25-
 */
public class N127HWordLadder {

    public static void main(String[] args){
        String[] data;
//        String expect;

        System.out.println(now());
        data = new String[]{"hot","dot","dog","lot","log","cog"};
        doRun(5,"hit", "cog", comm.strArr2List(data));

        data = new String[]{"hot","dot","dog","lot","log"};
        doRun(0,"hit", "cog", comm.strArr2List(data));

        data = new String[]{"most","mist","miss","lost","fist","fish"};
        doRun(4,"lost", "miss", comm.strArr2List(data));

        data = new String[]{"lest","leet","lose","code","lode","robe","lost"};
        doRun(6,"leet", "code", comm.strArr2List(data));

        data = new String[]{"lot","log","cog","hot","dot","dog"};
        doRun(5,"hit", "cog", comm.strArr2List(data));

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
        int res = new N127HWordLadder()
                .ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 367 ms, faster than 26.23% of Java online submissions for Word Ladder.
    //Memory Usage: 45.6 MB, less than 86.17% of Java online submissions for Word Ladder.
    //BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        //bfs
        int res = 1;
        while (!queue.isEmpty()){
            Set<String> usedWordSet = new HashSet<>();
            int len = queue.size();
            for (int i = 0; i < len; i++){
                List<String> nextWordSet = findNeighbours(queue.poll(), wordSet);
                for (String nextWord: nextWordSet){
                    if (nextWord.equals(endWord)) return res + 1;
                    queue.add(nextWord);
                    usedWordSet.add(nextWord);
                }
            }
            res++;
            wordSet.removeAll(usedWordSet);
        }
        return 0;
    }

    private List<String> findNeighbours(String word, Set<String> wordSet) {
        List<String> group = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int x = 0; x < chars.length; x++) {
            char oldChar = chars[x];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oldChar) continue;
                chars[x] = c;
                String candidate = String.valueOf(chars);
                if (wordSet.contains(candidate))
                    group.add(candidate);
            }
            chars[x] = oldChar;
        }
        return group;
    }
}
