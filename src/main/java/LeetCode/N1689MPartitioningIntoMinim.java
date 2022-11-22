package LeetCode;

import java.util.Arrays;
import java.util.stream.Stream;

public class N1689MPartitioningIntoMinim {


    public static void main(String[] args) {
        int result = new N1689MPartitioningIntoMinim().minPartitions("13434");

        System.out.println("result:"+result);
//        System.out.println("result:"+(int)'1');
    }

    public int minPartitions(String n) {
        char maxC = '1';
        char[] charArr = n.toCharArray();
        for (char c : charArr){
            if (c > maxC) maxC = c;
            if (c == '9') return 9;
        }
        //runtime 5ms
        return (int)maxC - 48;
        //runtime 13ms
        //return maxC - '0';
    }
}
