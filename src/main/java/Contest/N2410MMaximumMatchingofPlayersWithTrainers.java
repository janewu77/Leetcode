package Contest;

import java.util.Arrays;

import static java.time.LocalTime.now;

/**
 * ou are given a 0-indexed integer array players, where players[i] represents the ability of the ith player. You are also given a 0-indexed integer array trainers, where trainers[j] represents the training capacity of the jth trainer.
 *
 * The ith player can match with the jth trainer if the player's ability is less than or equal to the trainer's training capacity. Additionally, the ith player can be matched with at most one trainer, and the jth trainer can be matched with at most one player.
 *
 * Return the maximum number of matchings between players and trainers that satisfy these conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: players = [4,7,9], trainers = [8,2,5,8]
 * Output: 2
 * Explanation:
 * One of the ways we can form two matchings is as follows:
 * - players[0] can be matched with trainers[0] since 4 <= 8.
 * - players[1] can be matched with trainers[3] since 7 <= 8.
 * It can be proven that 2 is the maximum number of matchings that can be formed.
 * Example 2:
 *
 * Input: players = [1,1,1], trainers = [10]
 * Output: 1
 * Explanation:
 * The trainer can be matched with any of the 3 players.
 * Each player can only be matched with one trainer, so the maximum answer is 1.
 *
 *
 * Constraints:
 *
 * 1 <= players.length, trainers.length <= 105
 * 1 <= players[i], trainers[j] <= 109
 */
//2410. Maximum Matching of Players With Trainers
public class N2410MMaximumMatchingofPlayersWithTrainers {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //players = [4,7,9], trainers = [8,2,5,8]
        doRun(2, new int[]{4,7,9},  new int[]{8,2,5,8});
        doRun(1, new int[]{1,1,1},  new int[]{10});
        doRun(1, new int[]{10},  new int[]{2, 10});
        doRun(0, new int[]{8,10},  new int[]{2,3});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, int[] players, int[] trainers) {
        int res = new N2410MMaximumMatchingofPlayersWithTrainers()
                .matchPlayersAndTrainers( players, trainers);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 39 ms, faster than 80.00% of Java online submissions for Maximum Matching of Players With Trainers.
    //Memory Usage: 78.7 MB, less than 60.00% of Java online submissions for Maximum Matching of Players With Trainers.
    //Time: O(NlogN + N); Space: O(lgN)
    //Time: O(NlogN); Space: O(lgN)
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {

        Arrays.sort(players);
        Arrays.sort(trainers);

        int count = 0;
        int i= players.length - 1, j = trainers.length - 1;
        while (i >= 0 && j >= 0){
            if (players[i--] <= trainers[j]){
                count++; j--;
            }
        }
        return count;
    }
}
