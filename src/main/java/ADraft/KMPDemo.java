package ADraft;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

public class KMPDemo {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;

        //doRun("-1,0,0,1,2,3,4,4,0,0", "abababadd");

        doRun("-1,0,0,0,1,2,0,1,2,0", "abcabdabe");

        doRun("-1,0,0,0,0,1,2,3,0", "abcdabce");
        doRun("-1,0,0,0,0,1,2,3", "abcdabc");

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, String pattern) {
         int[] res1= new KMPDemo()
                .calcPrefixLen(pattern);
//        String res = res1.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
//        String res = comm.toString2(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //前缀计算
    public int[] calcPrefixLen(String pattern) {

        int[] res = new int[pattern.length() + 1];
        res[0] = -1; //res[1] = 0;

        int prefixLen = 0;
        int i = 1;
        while (i < pattern.length()) {

            if (pattern.charAt(prefixLen) == pattern.charAt(i)) {
                prefixLen++; i++;
                res[i] = (prefixLen);

            } else if (prefixLen > 0) {
                prefixLen = res[prefixLen]; // note  that we do not increment i here
            } else {
                i++;
                //res[i] = 0; // 'prefixLen' reached 0, so save that into ar[] and move forward
            }
        }
        return res;
    }
}
