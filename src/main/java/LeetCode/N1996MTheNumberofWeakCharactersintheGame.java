package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are playing a game that contains multiple characters, and each of the characters
 * has two main properties: attack and defense. You are given a 2D integer array properties
 * where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 *
 * A character is said to be weak if any other character has both attack and defense levels
 * strictly greater than this character's attack and defense levels. More formally, a character i is
 * said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 *
 * Return the number of weak characters.
 *
 *
 *
 * Example 1:
 *
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 * Example 2:
 *
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 * Example 3:
 *
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 *
 *
 * Constraints:
 *
 * 2 <= properties.length <= 105
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 105
 */

/**
 * M- 【time：120+
 */

public class N1996MTheNumberofWeakCharactersintheGame {

    public static void main(String[] args){
        System.out.println(now());
        int[] data;

        int[][] data2;

        data2 = new int[][]{{7,7},{1,2},{9,7},{7,3},{3,10},{9,8},{8,10},{4,3},{1,5},{1,5}};
        doRun(6, data2);

        data2 = new int[][]{{5,5}, {6,3}, {3,6}};
        doRun(0, data2);

        data2 = new int[][]{{1,5}, {10,4}, {4,3}};
        doRun(1, data2);

        data2 = new int[][]{{1,1}, {2,1}, {2,2}, {1,2}};
        doRun(1, data2);

        data2 = new int[][]{{2,2}, {3,3}};
        doRun(1, data2);


        System.out.println(now());
        System.out.println("==================");
    }

    private static void doRun(int expected, int[][] properties){
        int res = new N1996MTheNumberofWeakCharactersintheGame().numberOfWeakCharacters(properties);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //Runtime: 5 ms, faster than 100.00% of Java online submissions for The Number of Weak Characters in the Game.
    //Memory Usage: 86.7 MB, less than 98.15% of Java online submissions for The Number of Weak Characters in the Game.
    //Time:O(N + N + K + N); Space: O(K)
    //Time:O(N + K); Space: O(K)
    //N is the number of pairs in the given list properties, and K is the maximum attack value possible.
    public int numberOfWeakCharacters(int[][] properties) {
        int maxAttack = 0;
        for(int[] character : properties)
            maxAttack = Math.max(maxAttack, character[0]);

        int[] maxDefence = new int[maxAttack + 2];
        for(int[] character : properties)
            maxDefence[character[0]] = Math.max(maxDefence[character[0]], character[1]);

        int lastDefence = 0;
        for (int i = maxDefence.length - 1; i >= 0; i--) {
            maxDefence[i] = Math.max(lastDefence, maxDefence[i]);
            lastDefence= maxDefence[i];
        }

        int res = 0;
        for(int[] character : properties)
            if (maxDefence[character[0] + 1] > character[1]) res++;
        return res;
    }


    //Runtime: 187 ms, faster than 41.96% of Java online submissions for The Number of Weak Characters in the Game.
    //Memory Usage: 129.1 MB, less than 81.03% of Java online submissions for The Number of Weak Characters in the Game.
    //attack: ascending, defense: descending
    //Sorting
    //Time:O(NlgN + N); Space: O(logN)
    public int numberOfWeakCharacters_sort(int[][] properties) {
        //Time:O(NlgN); Space: O(logN)
        Arrays.sort(properties, (a, b) -> (a[0] == b[0]) ? (b[1] - a[1]) : a[0] - b[0]);

        int res = 0;
        int maxDefense = 0;
        //Time:O(N)
        for (int i = properties.length - 1; i >= 0; i--) {
            if (properties[i][1] < maxDefense) res++;
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }
        return res;
    }


}
