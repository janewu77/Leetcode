package LeetCode;


import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.
 *
 * Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).
 *
 *
 *
 * Example 1:
 *
 * Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * Explanation:
 * Person 0 has height 5 with no other people taller or the same height in front.
 * Person 1 has height 7 with no other people taller or the same height in front.
 * Person 2 has height 5 with two persons taller or the same height in front, which is person 0 and 1.
 * Person 3 has height 6 with one person taller or the same height in front, which is person 1.
 * Person 4 has height 4 with four people taller or the same height in front, which are people 0, 1, 2, and 3.
 * Person 5 has height 7 with one person taller or the same height in front, which is person 1.
 * Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.
 * Example 2:
 *
 * Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *
 */

/**
 * M -
 *  - 思路：当思路不通时，换个角度去思考。比如，当卡在(4,4)时，可转换成从"高"个人开始排。
 *  - array自定义比较器
 */
public class N406MQueueReconstructionbyHeight {

    public N406MQueueReconstructionbyHeight() {
    }

    public static void main(String[] args){

        int[][] people;
        people = new int[][]{{7,0},{4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
//        new N406MQueueReconstructionbyHeight().reconstructQueue(people);
//        nput: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
//        Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
//
//        people = new int[][]{{6,0},{5,0}, {4,0}, {3,2}, {2,2}, {1,4}};
//        Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
        int[][] result = new N406MQueueReconstructionbyHeight().reconstructQueue(people);

        System.out.println("result:");
        for(int i = 0; i< result.length; i++)
            System.out.println(result[i][0] +","+result[i][1]);
    }

    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length <=1) return people;

        //按身高倒序;位置升序
        Arrays.sort(people, (p1,p2) ->(p1[0]==p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]));

        List<int[]> result = new LinkedList<>();
        //已在队列里面的，都比我高；按位置插入即可
        for(int[] p : people) result.add(p[1], p);

        return result.toArray(new int[people.length][2]);
    }


    public int[][] reconstructQueue2(int[][] people) {
        if (people == null || people.length <=1) return people;

        //按身高倒序;位置升序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0]?  o1[1] - o2[1] : o2[0] - o1[0];
                //return o1[0]>o2[0] ? -1 : o1[0] == o2[0] ? 0: 1;
            }
        });

        List<int[]> result = new LinkedList<>();
        //已在队列里面的，都比我高；按位置插入即可
        for(int[] p : people) result.add(p[1], p);

        return result.toArray(new int[people.length][2]);
    }

}
