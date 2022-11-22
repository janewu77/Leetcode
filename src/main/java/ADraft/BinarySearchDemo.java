package ADraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchDemo {

    public static void main(String[] args){

        List<Integer> dataList = Arrays.asList(11,12,13);
        doRun(-1, dataList, 33);
        doRun(0, dataList, 11);
        doRun(1, dataList, 12);
        doRun(2, dataList, 13);

        doRun(-1, new ArrayList<>(), 33);

        dataList = Arrays.asList(3);
        doRun(-1, dataList, -2);
        doRun(0, dataList, 3);

        ////////////////////////////////////////
        dataList = Arrays.asList(11,22,33);
        doRun2(3, dataList, 44);
        doRun2(0, dataList, 11);
        doRun2(1, dataList, 22);
        doRun2(2, dataList, 33);
        doRun2(0, dataList, 10);
        doRun2(1, dataList, 15);
        doRun2(2, dataList, 23);


        doRun2(0, new ArrayList<>(), 33);

        dataList = Arrays.asList(3);
        doRun2(0, dataList, -2);
        doRun2(0, dataList, 3);
        doRun2(1, dataList, 13);

        dataList = Arrays.asList(2,4,6,8,10,11,12,13,14,16,18,20,22,24);
        doRun2(11, dataList, 19);
        doRun2(11, dataList, 20);
        doRun2(12, dataList, 21);

        dataList = Arrays.asList(2,4,6,8,10,11,12,13,15,16,20,20,20,24);
        doRun2(10, dataList, 19);
        doRun2(10, dataList, 20);
        doRun2(13, dataList, 21);


        System.out.println("========doRun_binarysearch==========");
        int[] data = new int[]{1,2,3,4};
        doRun_binarySearchInsert(2, data, 0, data.length - 1, 3);

        data = new int[]{1,2,3,4};
        doRun_binarySearchInsert(4, data, 0, data.length - 1, 8);

        data = new int[]{3,3,3,4};
        doRun_binarySearchInsert(0, data, 0, data.length - 1, 1);

        data = new int[]{3,5};
        doRun_binarySearchInsert(1, data, 0, data.length - 1, 4);

        data = new int[]{3,5,5,5,5,5,5};
        doRun_binarySearchInsert(1, data, 0, data.length - 1, 4);

        data = new int[]{3,5,5,5,5,5,5};
        doRun_binarySearchInsert(7, data, 0, data.length - 1, 6);

        data = new int[]{3,5,5,5,5,5,5};
        doRun_binarySearchInsert(1, data, 0, data.length - 1, 5);
    }



    private static int c = 0;
    private static void doRun(int expected, List<Integer> dataList, int target){
        int result = new BinarySearchDemo().binarySearch_cursive(dataList, target, 0, dataList.size()-1);
        System.out.println("[" + (expected == result) +"]"+(c++) +".result: " + result + ".expected:"+expected);
    }

    private static void doRun2(int expected, List<Integer> dataList, int target){
        int result = new BinarySearchDemo().binarySearchAndInsert(dataList, target, 0, dataList.size()-1);
        System.out.println("[" + (expected == result) +"]"+(c++) +".result: " + result + ".expected:"+expected);
    }

    static private void doRun_binarySearchInsert(int expect, int[] nums, int from, int to, int target) {
        int res = binarySearchInsert(nums,from,to, target);
//        String res = comm.toString(res1);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //未找到,则返回可插入位置
    static int binarySearchAndInsert(List<Integer> dataList, int target, int from, int to){
        if (dataList.size() <= 0) return 0;
        if (to < from) return  from;

        int k = (to - from) / 2 + from;
        if (dataList.get(k) == target) return k;

        if (dataList.get(k) > target) return binarySearchAndInsert(dataList,target,from, k-1);
        else return binarySearchAndInsert(dataList,target,k+1, to);
    }

    //未找到,则返回可插入位置
    //当target存在重复值时，指向第一个。
    //N2009
    static public int binarySearchInsert(int[] nums, int from, int to, int target){
        while (from <= to){
            int mid = (from + to ) / 2;
            if (nums[mid] == target) {
                while(mid >= from && nums[mid - 1] == target) mid--;
                return mid;
            }else if (nums[mid] > target){
                to = mid - 1;
            }else{
                from = mid + 1;
            }
        }
        return from;
    }

    //while | no cursive
    public int binarySearch_while(int[] dataList, int target, int from, int to){
        if (dataList.length <= 0) return  -1;
//        if (dataList.length <= 0 || to < from) return  -1;
        while(from <= to){
            int k = (to + from) / 2;
            if (dataList[k] == target) return k;
            if (dataList[k] > target) to = k-1;
            else from = k+1;
        }
        return -1;
    }

    //未找到返回 -1
    public int binarySearch_cursive(List<Integer> dataList, int target, int from, int to){
        if (dataList.size() <= 0 || to < from) return  -1;

        int k = (to + from) / 2;
        if (dataList.get(k) == target) return k;

        if (dataList.get(k) > target) return binarySearch_cursive(dataList,target,from, k-1);
        else return binarySearch_cursive(dataList,target,k+1, to);
    }

    //M34
    public int binarySearch_cursive(int[] dataList, int target, int from, int to){
        if (dataList.length <= 0 || to < from) return  -1;

        int k = (to + from) / 2;
        if (dataList[k] == target) return k;

        if (dataList[k] > target) return binarySearch_cursive(dataList,target,from, k-1);
        else return binarySearch_cursive(dataList,target,k+1, to);
    }

    //二个方向上的
    private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
        int lo = start;
        int hi = vertical ? matrix[0].length-1 : matrix.length-1;

        while (hi >= lo) {
            int mid = (lo + hi)/2;
            if (vertical) { // searching a column
                if (matrix[start][mid] < target) {
                    lo = mid + 1;
                } else if (matrix[start][mid] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { // searching a row
                if (matrix[mid][start] < target) {
                    lo = mid + 1;
                } else if (matrix[mid][start] > target) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

}
