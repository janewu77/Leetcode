package LeetCode;

import java.util.*;
import java.util.stream.IntStream;

/**
 *
 * Suppose you are at a party with n people labeled from 0 to n - 1 and among them,
 * there may exist one celebrity. The definition of a celebrity is that all the other n - 1
 * people know the celebrity, but the celebrity does not know any of them.
 *
 * Now you want to find out who the celebrity is or verify that there is not one.
 * The only thing you are allowed to do is ask questions like: "Hi, A. Do you know B?"
 * to get information about whether A knows B. You need to find out the celebrity
 * (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 *
 * You are given a helper function bool knows(a, b) that tells you whether A knows B.
 * Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
 *
 * Return the celebrity's label if there is a celebrity at the party.
 * If there is no celebrity, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
 * Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means
 * person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j.
 * The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
 *
 *
 * Example 2:
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
 * Output: -1
 * Explanation: There is no celebrity.
 *
 *
 * Constraints:
 *
 * n == graph.length == graph[i].length
 * 2 <= n <= 100
 * graph[i][j] is 0 or 1.
 * graph[i][i] == 1
 *
 *
 * Follow up: If the maximum number of allowed calls to the API knows is 3 * n,
 * could you find a solution without exceeding the maximum number of calls?
 *
 */

/**
 * M - [time: 60+]
 *  - 性能一直不太好。
 *
 */
abstract class absN277MFindtheCelebrity{
    boolean knows(int i, int j){
        return (i == j);
    }
}
public class N277MFindtheCelebrity extends absN277MFindtheCelebrity {

    public static void main(String[] args){
        int result = new N277MFindtheCelebrity().findCelebrity(2);

        //[[1,1,1],[1,1,0],[0,0,1]]
        System.out.println(result);
    }



    private Map<Integer, Boolean> memo = new HashMap<>();
    @Override
    public boolean knows(int i, int j) {
        int key = i * 1000 +j;
        if (!memo.containsKey(key)) {
            boolean res = super.knows(i, j);
            memo.put(key, res);
            return res;
        }
        return memo.get(key);
    }

    //Runtime: 17 ms, faster than 94.42% of Java online submissions for Find the Celebrity.
    //Memory Usage: 42.7 MB, less than 84.17% of Java online submissions for Find the Celebrity.
    public int findCelebrity(int n) {
        if (n <= 1) return -1;

        int candidate = 0;
        for(int i = 1; i < n; i++){
            if (knows(candidate, i)) candidate = i;
        }

        for(int j = 0; j < n; j++){
            if (candidate == j) continue;
            if ( knows(candidate, j) || !knows(j, candidate)) return -1;
        }
        return candidate;
    }

    public int findCelebrity_stream(int n) {
        return IntStream.range(0, n)
                .filter(celebCandidate -> IntStream.range(0, n)
                        .noneMatch(i -> (i != celebCandidate) &&
                                (!knows(i, celebCandidate) || knows(celebCandidate, i))))
                .findFirst()
                .orElse(-1);
    }

    //Runtime: 84 ms, faster than 21.18% of Java online submissions for Find the Celebrity.
    //Memory Usage: 42.6 MB, less than 88.19% of Java online submissions for Find the Celebrity.
    public int findCelebrity2(int n) {
        if (n <= 1) return -1;

        Set<Integer> notCelebrity = new HashSet<>();
        for(int i = 0; i < n; i++){
            if(notCelebrity.contains(i)) continue;
            boolean isCelebrity = true;
            for(int j = 0; j < n; j++){
                if (i == j) continue;

                if (knows(i, j)) {
                    isCelebrity = false;
                    break;
                }

                if(!knows(j, i)) {
                    isCelebrity = false;
                    break;
                }else{
                    notCelebrity.add(j);
                }
            }
            if (isCelebrity) return i;
        }// End for i
        return -1;
    }


    public int findCelebrity1(int n) {
        if (n <= 1) return -1;

        Map<String, Boolean> memo = new HashMap<>();
        for(int i = 0; i < n; i++){
            boolean knowOthers = false;
            for(int j = 0; j < n; j++){
                if (i == j) continue;
                String key = i +"," +j;
                if (memo.containsKey(key)) knowOthers = memo.get(key);
                else {
                    knowOthers = knows(i, j);
                    memo.put(key, knowOthers);
                }
                if (knowOthers) break;
            }

            //know nobody
            if (!knowOthers){
                boolean everyoneKnowMe = true;
                for(int k = 0; k < n; k++){
                    if (i == k) continue;
                    String key = k +"," +i;
                    boolean knowMe;
                    if (memo.containsKey(key)) knowMe = memo.get(key);
                    else{
                        knowMe= knows(k, i);
                        memo.put(key, knowMe);
                    }
                    everyoneKnowMe &= knowMe;
                    if(!knowMe) break;
                }
                if (everyoneKnowMe) return i;
            }

        }// End for i
        return -1;
    }

}
