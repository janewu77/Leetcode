package Contest;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * 2424. Longest Uploaded Prefix
 * User Accepted:4920
 * User Tried:5953
 * Total Accepted:5063
 * Total Submissions:11285
 * Difficulty:Medium
 * You are given a stream of n videos, each represented by a distinct number from 1 to n that you need to "upload" to a server. You need to implement a data structure that calculates the length of the longest uploaded prefix at various points in the upload process.
 *
 * We consider i to be an uploaded prefix if all videos in the range 1 to i (inclusive) have been uploaded to the server. The longest uploaded prefix is the maximum value of i that satisfies this definition.
 *
 * Implement the LUPrefix class:
 *
 * LUPrefix(int n) Initializes the object for a stream of n videos.
 * void upload(int video) Uploads video to the server.
 * int longest() Returns the length of the longest uploaded prefix defined above.
 *
 *
 * Example 1:
 *
 * Input
 * ["LUPrefix", "upload", "longest", "upload", "longest", "upload", "longest"]
 * [[4], [3], [], [1], [], [2], []]
 * Output
 * [null, null, 0, null, 1, null, 3]
 *
 * Explanation
 * LUPrefix server = new LUPrefix(4);   // Initialize a stream of 4 videos.
 * server.upload(3);                    // Upload video 3.
 * server.longest();                    // Since video 1 has not been uploaded yet, there is no prefix.
 *                                      // So, we return 0.
 * server.upload(1);                    // Upload video 1.
 * server.longest();                    // The prefix [1] is the longest uploaded prefix, so we return 1.
 * server.upload(2);                    // Upload video 2.
 * server.longest();                    // The prefix [1,2,3] is the longest uploaded prefix, so we return 3.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * 1 <= video <= 105
 * All values of video are distinct.
 * At most 2 * 105 calls in total will be made to upload and longest.
 * At least one call will be made to longest.
 */

/**
 * M - [time: 10-
 */

//2424. Longest Uploaded Prefix
public class N6197MLongestUploadedPrefix {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun(true, "abaccb", new int[]{1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(int expect, String beginWord, String endWord, List<String> wordList) {
//        int res = new N2()
//                .ladderLength(beginWord, endWord, wordList);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
//        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //Runtime: 40 ms, faster than 77.78% of Java online submissions for Longest Uploaded Prefix.
    //Memory Usage: 103.2 MB, less than 66.67% of Java online submissions for Longest Uploaded Prefix.
    //Union Find
    class LUPrefix{
        UnionFind uf;

        //Time:O(N); Space:O(N)
        public LUPrefix(int n) {
            uf = new UnionFind(n + 1);
        }

        public void upload(int video) {
            uf.union(video - 1, video);
        }

        public int longest() {
            return uf.find(0);
        }

        public class UnionFind {
            private int[] group;

            public UnionFind(int size) {
                group = new int[size];
                for (int i = 0; i < size; i++) {
                    group[i] = i;
                }
            }

            public int find(int x) {
                return x == group[x] ? x : (group[x] = find(group[x]));
            }

            public void union(int x, int y) {
                group[x] = group[y];
            }
        }
    }

    //Runtime: 38 ms, faster than 77.78% of Java online submissions for Longest Uploaded Prefix.
    //Memory Usage: 101.2 MB, less than 77.78% of Java online submissions for Longest Uploaded Prefix.
    //array
    class LUPrefix_2 {
        int[] data;
        int progress = 0;

        //Time: O(1); Space:O(N)
        public LUPrefix_2(int n) {
            data = new int[n + 1];
        }

        //Time: O(1)
        public void upload(int video) {
            data[video - 1] = 1;
        }

        //Time: worst case: O(N)
        public int longest() {
            while (data[progress] == 1) progress++;
            return progress;
        }
    }

    //Runtime: 184 ms, faster than 11.11% of Java online submissions for Longest Uploaded Prefix.
    //Memory Usage: 153.4 MB, less than 33.33% of Java online submissions for Longest Uploaded Prefix.
    //heap
    class LUPrefix_heap {
        int progress = 0;
        PriorityQueue<Integer> heap;

        //Space:O(N)
        public LUPrefix_heap(int n) {
            heap = new PriorityQueue<>();
        }

        //Time:O(logN)
        public void upload(int video) {
            if (progress + 1 == video) progress++;
            else heap.add(video);
        }

        //Time: O(N)
        public int longest() {
            while (!heap.isEmpty() && heap.peek() == progress + 1){
                heap.poll();
                progress++;
            }
            return progress;
        }
    }

}
