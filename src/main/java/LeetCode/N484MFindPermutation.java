package LeetCode;


import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A permutation perm of n integers of all the integers in the range [1, n]
 * can be represented as a string s of length n - 1 where:
 *
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 * Given a string s, reconstruct the lexicographically smallest permutation perm and return it.
 *
 * Example 1:
 * Input: s = "I"
 * Output: [1,2]
 * Explanation: [1,2] is the only legal permutation that can represented by s,
 * where the number 1 and 2 construct an increasing relationship.
 *
 *
 * Example 2:
 * Input: s = "DI"
 * Output: [2,1,3]
 * Explanation: Both [2,1,3] and [3,1,2] can be represented as "DI",
 * but since we want to find the smallest lexicographical permutation, you should return [2,1,3]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either 'I' or 'D'.
 *
 *
 */

/**
 * M - [time: 120-]
 *  - 其实并不难，没注意时间花在哪里了（可能做家务去了。。）
 */
public class N484MFindPermutation {
    public static void main(String[] args){
        doRun("12", "I");
        doRun("1234", "III");
        doRun("4321", "DDD");
        doRun("213", "DI");
        doRun("1243765", "IIDIDD");
        doRun("3214657", "DDIIDI");

        doRun("132", "ID");
    }

    private static void doRun(String expected, String s){
        int[] res = new N484MFindPermutation().findPermutation(s);

        String strRes = Arrays.stream(res).mapToObj(i -> String.valueOf(i)).collect(Collectors.joining(""));
        System.out.println("["+(expected.equals(strRes))+"].[s:"+s+"][expected:"+ expected+"] res:"+strRes);
    }

    ////////////////////////////////////////////////////////////////////////
    //Runtime: 2 ms, faster than 100.00% of Java online submissions for Find Permutation.
    //Memory Usage: 44.8 MB, less than 90.43% of Java online submissions for Find Permutation.
    //one pass
    //Time：O(N); Space: O(1)
    //Concept: DDDD means 54321, III means 1234. so fill them backward and forward respectively
    public int[] findPermutation(String s) {
        int[] res = new int[s.length()+1];
        int n = 1;
        int left = 0;

        int idx = s.indexOf('D');
        while (idx != -1){

            //I:
            while (left < idx)
                res[left] = (res[left] == 0) ? n++ : res[left++];//I:forward

            //D:
            int nextI = s.indexOf('I', idx);
            if (nextI == -1) nextI = s.length();

            int x = nextI;
            while (x > idx) res[x--] = n++; //D: backward

            //next
            idx = s.indexOf('D', nextI);
        }

        //I
        while (left < res.length)
            res[left] = (res[left] == 0) ? n++ : res[left++];

        return res;
    }
}
