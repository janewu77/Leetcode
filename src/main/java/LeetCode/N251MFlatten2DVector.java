package LeetCode;

/**
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 *
 * Implement the Vector2D class:
 *
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
 * [[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
 * Output
 * [null, 1, 2, 3, true, true, 4, false]
 *
 * Explanation
 * Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
 * vector2D.next();    // return 1
 * vector2D.next();    // return 2
 * vector2D.next();    // return 3
 * vector2D.hasNext(); // return True
 * vector2D.hasNext(); // return True
 * vector2D.next();    // return 4
 * vector2D.hasNext(); // return False
 *
 *
 * Constraints:
 *
 * 0 <= vec.length <= 200
 * 0 <= vec[i].length <= 500
 * -500 <= vec[i][j] <= 500
 * At most 105 calls will be made to next and hasNext.
 *
 *
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 *
 */

import java.util.NoSuchElementException;

/**
 * M - [time: 50-]
 *  - 第一次没有注意到第二维数组可能为空。
 *
 */
public class N251MFlatten2DVector {

    public static void main(String[] args) {

        int[][] data = new int[][]{{1,2},{3},{4}};
        new N251MFlatten2DVector().doRun(data);

        data = new int[][]{{},{3}};
        new N251MFlatten2DVector().doRun(data);
    }

    void doRun(int[][] data){

        Vector2D vector2D = new Vector2D(data);
        int res = vector2D.next();    // return 1
        System.out.println(res);
        res = vector2D.next();    // return 2
        System.out.println(res);
        res = vector2D.next();    // return 3
        System.out.println(res);
        boolean hasNext = vector2D.hasNext(); // return True
        System.out.println(hasNext);
        hasNext = vector2D.hasNext(); // return True
        System.out.println(hasNext);
        res  = vector2D.next();    // return 4
        System.out.println(res);
        hasNext = vector2D.hasNext(); // return False
        System.out.println(hasNext);
        System.out.println("=============");
    }

    //Runtime: 8 ms, faster than 100.00% of Java online submissions for Flatten 2D Vector.
    //Memory Usage: 47 MB, less than 91.17% of Java online submissions for Flatten 2D Vector.
    //Time: worst case: O(N), Space:O(1)
    class Vector2D {

        private int x = 0;
        private int y = 0;
        private int[][] data;

        //Time: O(N)
        public Vector2D(int[][] vec) {
            data = vec;
            move2Next();
        }

        public int next() {
            if (!hasNext()) throw new NoSuchElementException();
            int v = data[x][y++];
            move2Next();
            return v;
        }

        public boolean hasNext() {
            return x <= data.length - 1;
        }

        //Time: worst case: O(N)
        private void move2Next(){
            if (x > data.length - 1) return;

            while (y > data[x].length - 1){
                y = 0;
                x++;
                if(x > data.length - 1) return;
            }
        }
    }
}
