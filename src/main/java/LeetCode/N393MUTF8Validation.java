package LeetCode;


import static java.time.LocalTime.now;

/**
 * Given an integer array data representing the data, return whether it is a valid UTF-8 encoding
 * (i.e. it translates to a sequence of valid UTF-8 encoded characters).
 *
 * A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
 *
 * For a 1-byte character, the first bit is a 0, followed by its Unicode code.
 * For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes
 * with the most significant 2 bits being 10.
 * This is how the UTF-8 encoding would work:
 *
 *      Number of Bytes   |        UTF-8 Octet Sequence
 *                        |              (binary)
 *    --------------------+-----------------------------------------
 *             1          |   0xxxxxxx
 *             2          |   110xxxxx 10xxxxxx
 *             3          |   1110xxxx 10xxxxxx 10xxxxxx
 *             4          |   11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * x denotes a bit in the binary form of a byte that may be either 0 or 1.
 *
 * Note: The input is an array of integers. Only the least significant 8 bits of each integer
 * is used to store the data. This means each integer represents only 1 byte of data.
 *
 *
 *
 * Example 1:
 *
 * Input: data = [197,130,1]
 * Output: true
 * Explanation: data represents the octet sequence: 11000101 10000010 00000001.
 * It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
 * Example 2:
 *
 * Input: data = [235,140,4]
 * Output: false
 * Explanation: data represented the octet sequence: 11101011 10001100 00000100.
 * The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
 * The next byte is a continuation byte which starts with 10 and that's correct.
 * But the second continuation byte does not start with 10, so it is invalid.
 *
 *
 * Constraints:
 *
 * 1 <= data.length <= 2 * 104
 * 0 <= data[i] <= 255
 */

/**
 * M - [120+
 */
public class N393MUTF8Validation {

    public static void main(String[] args){
        System.out.println(now());
        int[] data;

//        data = new int[]{197,130,1};
//        doRun(true, data);

        data = new int[]{248,130,130,130};
        doRun(false, data);

        data = new int[]{250,145,145,145,145};
        doRun(false, data);

        data = new int[]{178,140,4};
        doRun(false, data);

        data = new int[]{255,140,4};
        doRun(false, data);

        data = new int[]{230,136,145};
        doRun(true, data);

        data = new int[]{235,140,4};
        doRun(false, data);

        data = new int[]{5,140,4};
        doRun(false, data);

        data = new int[]{6,140,4};
        doRun(false, data);

        System.out.println(now());
        System.out.println("==================");

    }

    private static void doRun(boolean expected, int[] data){
        boolean res = new N393MUTF8Validation().validUtf8(data);
        System.out.println("["+(expected == res)+"].expected:"+ expected+".res:"+res);
    }


    //Runtime: 1 ms, faster than 100.00% of Java online submissions for UTF-8 Validation.
    //Memory Usage: 47.5 MB, less than 73.06% of Java online submissions for UTF-8 Validation.
    //Bit Manipulation
    //Time: O(N); Space : O(1)
    public boolean validUtf8(int[] data) {
        int i = 0;
        //int mask7 = 1 << 7;  // 1000 0000
        //int mask6 = 1 << 6;  // 0100 0000
        int mask11 = 3 << 6; // 1100 0000
        int mask10 = 1 << 7; // 1000 0000

        while (i < data.length) {
            int mask = 1 << 7;
            if ((data[i] & mask) != 0){
                int idx = 0;
                mask = mask >> 1;
                while ((data[i] & mask) != 0 && idx < 4){
                    idx++; mask = mask >> 1;
                }

                if (idx <= 0 || idx > 3 || idx + i >=  data.length) return false;

                for (int j = 1; j <= idx; j++)
                    if ((data[i + j] & mask11) != mask10) return false;
                    //if ((data[i + j] & mask7) == 0 || (data[i + j] & mask6) != 0) return false;
                i += idx ;
            }
            i++;
        }
        return true;
    }

    //Runtime: 5 ms, faster than 35.49% of Java online submissions for UTF-8 Validation.
    //Memory Usage: 42.7 MB, less than 93.78% of Java online submissions for UTF-8 Validation.
    //String Manipulation.
    //Time: O(N); Space: O(1)
    public boolean validUtf8_1(int[] data) {
        int i = 0;
        while (i < data.length) {

            //Only the least significant 8 bits will be needed
            String binRep = Integer.toBinaryString(data[i] | 256).substring(1);
            if (binRep.charAt(0) == '1') {

                int idx = binRep.indexOf("0");
                if (idx <= 1 || idx > 4
                        || idx + i - 1 >=  data.length) return false;

                for (int j = 1; j < idx; j++)
                    //if (data[i + j] < 128) return false;
                    if (!Integer.toBinaryString(data[i+j] | 256).substring(1).startsWith("10")) return false;

                i += idx - 1;
            }
            i++;
        }
        return true;
    }

    //Runtime: 1 ms, faster than 100.00% of Java online submissions for UTF-8 Validation.
    //Memory Usage: 42.8 MB, less than 93.01% of Java online submissions for UTF-8 Validation.
    //fit Testcases ('data' must consist of values from 0 to 255 only)
    //Time: O(N); Space: O(1)
    public boolean validUtf8_0(int[] data) {
        int i = 0;
        while (i < data.length) {
            if (data[i] < 128) {
                i++; continue;
            }

            if (data[i] >= (128 + 64) && data[i] < (128 + 64 + 32 + 16 + 8)){
                int idx = 1;
                if (data[i] >= 128 + 64 + 32 + 16) idx = 3;
                else if (data[i] >= 128 + 64 + 32) idx = 2;

                if (i + idx > data.length - 1) return false;
                while ( idx-- > 0)  if (data[++i] < 128) return false;

            }else return false;
            i++;
        }
        return true;
    }

}
