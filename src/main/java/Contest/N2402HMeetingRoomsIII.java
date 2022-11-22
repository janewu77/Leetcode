package Contest;


import java.util.*;

import static java.time.LocalTime.now;

/**
 * ou are given an integer n. There are n rooms numbered from 0 to n - 1.
 *
 * You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be held during the half-closed time interval [starti, endi). All the values of starti are unique.
 *
 * Meetings are allocated to rooms in the following manner:
 *
 * Each meeting will take place in the unused room with the lowest number.
 * If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed meeting should have the same duration as the original meeting.
 * When a room becomes unused, meetings that have an earlier original start time should be given the room.
 * Return the number of the room that held the most meetings. If there are multiple rooms, return the room with the lowest number.
 *
 * A half-closed interval [a, b) is the interval between a and b including a and not including b.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
 * Output: 0
 * Explanation:
 * - At time 0, both rooms are not being used. The first meeting starts in room 0.
 * - At time 1, only room 1 is not being used. The second meeting starts in room 1.
 * - At time 2, both rooms are being used. The third meeting is delayed.
 * - At time 3, both rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
 * - At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
 * Both rooms 0 and 1 held 2 meetings, so we return 0.
 * Example 2:
 *
 * Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
 * Output: 1
 * Explanation:
 * - At time 1, all three rooms are not being used. The first meeting starts in room 0.
 * - At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
 * - At time 3, only room 2 is not being used. The third meeting starts in room 2.
 * - At time 4, all three rooms are being used. The fourth meeting is delayed.
 * - At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
 * - At time 6, all three rooms are being used. The fifth meeting is delayed.
 * - At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
 * Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 1 <= meetings.length <= 105
 * meetings[i].length == 2
 * 0 <= starti < endi <= 5 * 105
 * All the values of starti are unique.
 */
public class N2402HMeetingRoomsIII {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;


        doRun_demo3(1,3, new int[][]{{0,10}, {1,9}, {2, 8}, {3, 7},{4,6}});

        doRun_demo3(1,3, new int[][]{{1,20}, {2,10}, {3, 5}, {4, 9},{6,8}});
        doRun_demo3(0,4, new int[][]{{18,19}, {3,12}, {17,19}, {2,13}, {7,10}});


        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun_demo3(int expect,int n, int[][] meetings) {
        int res = new N2402HMeetingRoomsIII().mostBooked(n, meetings);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 96 ms, faster than 62.50% of Java online submissions for Meeting Rooms III.
    //Memory Usage: 107.7 MB, less than 12.50% of Java online submissions for Meeting Rooms III.
    //Time: O((N + M) * lgN); Space: O(M + N)
    // sort  +  PriorityQueue
    //N is the number of rooms;
    //M is the number of meetings;
    public int mostBooked_1(int n, int[][] meetings) {

        //Time: O(M * lgM); Space: O(M)
        // sort meetings by start time
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        //Space: O(N)
        int[] roomCounter = new int[n];

        //Space: O(N)
        //track room's Index which is free
        PriorityQueue<Integer> freeMeetingRoom = new PriorityQueue<>();
        for(int i = 0; i < n; i++) freeMeetingRoom.add(i);

        //Space: O(N)
        // endTime : roomIdx
        PriorityQueue<int[]> occupiedMeetingRoom = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        //Time: O(M * lgN)
        int res = 0;
        for (int[] meeting : meetings){
            int[] room = new int[2];

            //release meeting rooms
            while (!occupiedMeetingRoom.isEmpty() && occupiedMeetingRoom.peek()[0] <= meeting[0])
                freeMeetingRoom.add(occupiedMeetingRoom.poll()[1]);

            if (!freeMeetingRoom.isEmpty())
                room[1] = freeMeetingRoom.poll(); //allocate a free room
            else
                room = occupiedMeetingRoom.poll();


            room[0] = Math.max(room[0], meeting[0]) - meeting[0] + meeting[1] ;
            roomCounter[room[1]]++;
            occupiedMeetingRoom.add(room);

            //result
            res = (roomCounter[room[1]] == roomCounter[res]) ? Math.min(res, room[1]) :
                    (roomCounter[room[1]] > roomCounter[res]) ? room[1] : res;
        }
        return res;
    }

    //Runtime: 123 ms, faster than 50.00% of Java online submissions for Meeting Rooms III.
    //Memory Usage: 104.5 MB, less than 37.50% of Java online submissions for Meeting Rooms III.
    //Time: O(M*N); Space: O(N)
    public int mostBooked(int n, int[][] meetings) {

        //Time: O(N * lgN); Space: O(N)
        // sort meetings by start time
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        //Space: O(N)
        //这里必须为long型！
        long[][] roomCounter = new long[n][2]; //EndTime : count

        //Time : O(M * N)
        for (int[] meeting : meetings) {
            int idx = 0;
            for (int i = 0; i < roomCounter.length; i++) {
                if (roomCounter[i][0] <= meeting[0]) {
                    idx = i; break;
                }
                if (roomCounter[i][0] < roomCounter[idx][0]) idx = i;
            }

            roomCounter[idx][0] = Math.max(meeting[0], roomCounter[idx][0]) - meeting[0] + meeting[1];
            roomCounter[idx][1]++;
        }

        int res = 0;
        for (int i = 1; i < n; i++)
            if (roomCounter[i][1] > roomCounter[res][1])
                res = i;
        return res;
    }

}
