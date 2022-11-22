package LeetCode;

import java.util.*;

/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 */

/**
 * M - [time: 30-
 */
public class N1202MSmallestStringWithSwaps {

    //Runtime: 107 ms, faster than 31.30% of Java online submissions for Smallest String With Swaps.
    //Memory Usage: 100.9 MB, less than 52.29% of Java online submissions for Smallest String With Swaps.
    //union find + heap
    //Time: O(N * logN); Space: O(N)
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        //Time: O(N); Space: O(N + N)
        UnionFind uf = new UnionFind(s.length());
        char[] chars = new char[s.length()];

        //Time: O(P);
        for (List<Integer> pair : pairs)
            uf.union(pair.get(0), pair.get(1));

        //Time: O(N * logN); Space: O(N)
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            int root = uf.find(i);
            PriorityQueue<Character> heap = map.getOrDefault(root, new PriorityQueue());
            map.put(root, heap);
            heap.add(s.charAt(i));
        }

        //Time: O(N);
        for (int i = 0; i < s.length(); i++)
            chars[i] = map.get(uf.find(i)).poll();

        return String.valueOf(chars);
    }

    class UnionFind {
        int[] data;
        int[] rank;

        public UnionFind(int n){
            data = new int[n];
            rank = new int[n];
            for (int i = 0; i <n; i++){
                data[i] = i; rank[i] = 1;
            }
        }
        public int find(int x){
            if (data[x] == x) return x;
            return data[x] = find(data[x]);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;
            if (rank[rootX] < rank[rootY]){
                data[rootX] = rootY;
            }else{
                data[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
        }
    }
}
