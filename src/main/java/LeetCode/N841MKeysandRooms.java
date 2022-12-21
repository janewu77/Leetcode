package LeetCode;

import utils.comm;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

/**
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 *
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 *
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: rooms = [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We visit room 0 and pick up key 1.
 * We then visit room 1 and pick up key 2.
 * We then visit room 2 and pick up key 3.
 * We then visit room 3.
 * Since we were able to visit every room, we return true.
 * Example 2:
 *
 * Input: rooms = [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
 *
 *
 * Constraints:
 *
 * n == rooms.length
 * 2 <= n <= 1000
 * 0 <= rooms[i].length <= 1000
 * 1 <= sum(rooms[i].length) <= 3000
 * 0 <= rooms[i][j] < n
 * All the values of rooms[i] are unique.
 */
public class N841MKeysandRooms {

    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");

        //[[1,3],[3,0,1],[2],[0]]
        doRun(true, new int[][]{{1}, {2}, {3}, {}});
        doRun(false, new int[][]{{1,3},{3,0,1},{2},{0}});
        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(boolean expect, int[][] rooms) {
        List<List<Integer>> keys = comm.convert2DArr2List(rooms);
        boolean res = new N841MKeysandRooms().canVisitAllRooms(keys);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.DFS
    //Runtime: 0ms, 100%; Memory: 42.1MB 82%
    //Time: O(N + E); Space: O(N)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        visited[0] = true;
        int count = helper_dfs(rooms, 0, 1, visited);
        return count == rooms.size();
    }

    private int helper_dfs(List<List<Integer>> rooms, int roomID, int count, boolean[] visited){
        List<Integer> keys = rooms.get(roomID);
        for (int key: keys) {
            if (!visited[key]){
                visited[key] = true;
                count = helper_dfs(rooms, key, ++count, visited);
            }
        }
        return count;
    }

    //1.BFS + Set
    //Runtime: 2ms, 70%; Memory: 41.9MB 89%
    //Time: O(N + E); Space: O(N)
    public boolean canVisitAllRooms_1(List<List<Integer>> rooms) {
        Set<Integer> keySet = new HashSet<>();
        keySet.add(0);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            List<Integer> keys = rooms.get(queue.poll());
            for (int key: keys)
                if (keySet.add(key)) queue.add(key);
        }
        return keySet.size() == rooms.size();
    }
}
