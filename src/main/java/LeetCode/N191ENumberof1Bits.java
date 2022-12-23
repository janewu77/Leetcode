package LeetCode;

public class N191ENumberof1Bits {

    //1.Bit Manipulation
    //Runtime: 0ms 100%; Memory: 39.2MB 95%
    //Time: O(1); Space: O(1)
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++){
            if ((n & 1) == 1)
                count++;
            n = n >>> 1;
        }
        return count;
    }
}
