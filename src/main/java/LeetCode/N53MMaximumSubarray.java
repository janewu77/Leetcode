package LeetCode;

import java.util.*;
import static java.time.LocalTime.now;

/**
 * Given an integer array nums,
 * find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 *
 * Example 3:
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution,
 * try coding another solution using the divide and conquer approach,
 * which is more subtle.
 */

/**
 * M - T 120+
 *
 */
public class N53MMaximumSubarray {
    public static void main(String[] args){

        run(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6, "1"); //[4,-1,2,1]
        run(new int[]{1}, 1, "2");
        run(new int[]{5,4,-1,7,8}, 23, "3");
        run(new int[]{5,4,-1,7,8, -1}, 23, "4");
        run(new int[]{5,4,-1,7,8, -1, 3}, 25, "4");
        //[3,-2,-3,-3,1,3,0]
        run(new int[]{3,-2,-3,-3,1,3,0}, 4, "5");

        run(new int[]{8,-2,-4,-1,-8,3,8,8,3,4,2,-9,-1,-3,-6,8,-3,9}, 28, "6");
        run(new int[]{-1}, -1, "7");
        run(new int[]{-1,-1}, -1, "8");

        int[] data = new int[]{5356,-7142,-3862,-8237,5603,-1209,-5513,4050,637,-3649,-4230,8208,-2133,9167,-5214,9632,9761,3794,5930,-4323,-147,-2603,-1755,-9408,-8974,-6380,-4655,-9188,-8946,-2432,-3913,6066,7576,-5509,8416,7251,-4126,-3238,-9163,-9419,8474,1961,4184,-9759,1877,-1622,-7918,4145,-6791,1797,-8938,-2918,5385,1997,-7377,-7080,-1189,-8635,3423,-8093,-2098,1307,3876,-8427,3054,8208,-946,6572,-3219,-311,2443,-5119,9731,2338,2342,-4143,3730,1054,-9398,5485,5439,-1127,-9094,-44,7610,-7736,8976,-9697,-2694,-6862,4460,5759,-9724,1330,4562,-30,-5154,-8254,-2919,2893,6110,1648,9240,-3650,3374,6322,-9342,-5894,-6565,8777,-6506,1233,-4133,-9348,-6219,5927,-5456,-4975,-4660,-8896,-9916,9963,8373,9209,8729,-6322,7758,-5504,-8852,1861,851,-2910,-5642,-2934,-211,-5276,-6190,5496,800,-2172,8933,310,182,809,-2771,4259,-5782,3723,-3332,4817,9369,-1290,7458,-9060,-864,-9094,-69,5282,-1697,438,5606,-5534,1786,6966,6248,3075,9760,1797,8684,-1087,-1820,-3431,6580,4572,-962,3045,-9779,9016,8321,6838,-3806,9812,6252,-7861,-8385,-9969,8226,5229,379,-5908,6461,989,2247,2324,1315,7984,5819,-9738,2811,-3889,2040,7115,-9728,9307,-6608,2304,4018,7323,-689,1763,-9017,-3474,6189,9756,-2031,-5542,6825,6896,9101,7551,-7951,-7757,-642,-9243,-516,-9718,4234,-8462,1499,-3530,-7288,4903,-8348,-6369,-7469,-6629,8010,-545,-2717,-54,3196,-3430,-7312,-5732,5187,-6906,-751,-6475,-5088,-7571,1588,-8034,2237,4960,8776,6185,6836,-997,9569,6042,-3856,9735,1121,-2727,1865,-2468,2285,-3128,-3158,-7530,9579,3588,-2196,-7157,-3965,-5771,-9505,-1864,7515,-3277,-5112,-8245,236,-7313,-6876,-6862,3273,-6520,5619,-5777,-2312,-8997,5827,8427,-6105,-2697,-3939,-1564,-5684,-8024,-3644,7741,-1908,8647,-7423,-5248,9423,-6170,4344,-4851,-6131,1432,2726,-8880,5149,-519,-8768,-4285,1393,-2521,6614,-8113,-6731,-7011,4526,4099,-7383,9540,9663,-2019,-1020,-338,7077,3735,-2444,8753,-3008,-5111,4939,-4067,8359,-8848,4331,8224,7190,9630,9632,-9404,7544,5575,5258,-6009,-8055,621,8928,1314,8937,-7155,2560,9690,5920,8632,-9766,3624,4767,-7243,5230,6335,2934,6828,-8347,1717,-2111,5698,3206,-2976,-8386,-4540,-5061,5159,-265,-3910,-5083,7945,-363,-7361,5862,-2087,3653,-2300,-9544,8450,2257,-9743,-3450,-668,6874,-340,-5010,-3469,2381,-1204,-7243,9996,-7373,-1,-9147,-6153,-1452,-227,4238,-9453,-829,872,-4119,-528,4765,-7726,-684,4467,-407,8832,7415,-1455,-8306,-4419,-650,9005,-9524,-6582,8975,4353,-8792,3831,1284,4328,-9971,-8040,-5432,-9023,-4085,-5082,7549,4823,-6098,4917,7910,-8233,-5907,8188,2187,5657,809,-3001,1267,182,-1596,8121,5443,5954,-8048,9303,3967,-5026,-4145,-8460,8545,-1266,-4832,3109,4466,-8442,-2971,4017,-3403,8548,-2472,-4553,8957,8462,6974,3586,2163,-3173,1110,-4943,-7025,-9529,-5801,8297,1342,-532,791,1323,-7273,747,-7209,7481,5365,-7218,-1620,9427,-1410,1397,6688,6030,-8852,-1734,397,1691,3342,7412,7788,3166,55,7964,-195,7505,-1681,-2064,6392,-5137,7714,-4788,-9987,4329,2836,4481,3964,8752,8565,159,6717,8380,-6800,1224,-5319,9074,-2447,-2422,5557,-3127,8296,-4756,-6912,-8416,2456,6194,-7401,816,1007,-8816,-4105,5240,9242,-8730,8858,786,6040,300,-1619,417,5565,4060,-6319,-4192,4349,-9224,-2391,-5704,-1679,-4253,-1132,-5649,-5237,-4547,-8091,6669,1208,-5538,8142,6848,-4722,7479,-2768,7315,7202,-869,4708,-6535,1013,-7392,1454,-4962,9549,6408,5760,6104,5977,-3072,-8100,-2792,-7870,-4785,9074,-991,8494,-1563,9356,8456,7846,7206,-8233,3777,-8551,-9608,7408,34,-2783,850,8872,7792,-4032,-727,2226,-2538,-7512,-2625,-4515,6697,-8685,3409,7192,-6640,-5432,-2895,-8224,3024,5013,3036,8590,-8193,-5286,3644,6776,1336,4374,-6389,-8252,1438,2020,5909,-5874,9114,5163,4910,-887,561,9712,9895,-4714,-9793,3455,6563,2337,3324,-9387,6721,9479,1789,-4986,3233,4525,7192,8990,4802,-3378,280,-1558,-5408,-913,-5127,7603,9032,-4659,2510,-4802,-6641,-2315,8197,-3058,9611,-459,2384,5836,-8360,1149,-8033,3860,3995,-4843,-3598,-3610,-3626,7951,1083,-8370,-4587,-4599,-1846,-428,540,8935,1663,2205,8326,-418,-6126,-9211,-3108,5426,-418,-9637,9090,1725,3262,-7772,-3011,5177,-5485,-2631,-7574,-382,-6838,-6294,-4502,3957,-8260,-1605,6789,-8750,-5573,-6510,-1979,8237,-6463,-5553,-7328,-3864,-4372,2940,5941,8814,7600,-7900,-8340,432,-8168,-4376,-3789,-8290,-3372,-9075,9744,-4777,5524,6043,8141,-7115,8103,-9422,7144,-4740,-8352,2900,-8016,2133,7962,6788,2783,-5012,-7482,3935,-9729,-4701,-7825,-8439,-582,-2321,-9614,3151,7949,-751,2187,9586,849,-3088,8851,-9027,3892,3386,9243,-9079,-5408,-1940,-971,-736,-791,7348,642,9038,174,3781,5850,2793,-6772,1903,-6167,-9899,3648,-7828,3611,6123,1376,3512,-6683,5924,-3668,-7102,5432,-5940,4423,-3282,7692,7272,-8142,-2401,1983,2304,997,-2258,-5369,3898,-3388,4342,-9496,3334,-8522,-1877,-1044,-110,5761,-486,683,-4144,-7928,9711,6059,-3237,-8732,-4790,-8276,2231,-8779,8931,-5561,5532,6619,-6106,8066,-7305,-954,7351,4434,7567,-5554,-4427,-579,-613,9270,-8180,-4692,9416,-6365,-9394,-1969,918,5482,-1767,-6588,8002,-4406,-3593,3046,6922,5044,-9202,-8191,-4333,1848,-4279,769,-550,-3808,1530,4871,-3764,9609,2769,-6282,3769,-9229,4133,-1064,-1272,4680,-3667,7078,2819,-2695,-7054,-4365,-2525,-4604,3430,5685,8009,7783,-7829,3544,3238,-83,8443,-8921,1579,7783,3137,3220,9243,-9384,15,5368,3498,3400,-5854,-7973,1729,7931,-4008,3504,1598,5559,8268,7448,-8339,3526,5550,-3461,-6653,-6975,-6899,5060,-5128,-3115,2777,-7428,5142,9154,4121,3251,-9430,3891,-9418,5255,-6807,-5558,-5648,-5168,1128,-933,5583,-2331,-3959,-7218,1297,692,500,601,8172,-7744,-4239,-1168,-9273,9082,-3105,-8189,-1413,-7635,2543,9896,-770,1285,-219,7658,-7313,3174,4592,-1760,-4369,-8132,-333,2348,-3946,6407,-3258,7697,-4535,-2767,-8644,6289,1533,6301,1299,-8470,3586,459,9003,9444};
        System.out.println(now());
        run(data, -1, "t1");
        System.out.println(now());
    }

    private static void run(int[] nums , int expected, String title){
        int result = new N53MMaximumSubarray().maxSubArray(nums);
        System.out.println( "["+(result == expected)+"]"+title+".expected "+expected+"; result:"+ result);
    }

    //3.Divide and Conquer
    //Time: O(N * logN); Space:O(logN)
    public int maxSubArray(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private int helper(int[] nums, int left, int right){
        if (left > right) return Integer.MIN_VALUE;

        int currSum = 0, maxSumLeft = 0, maxSumRight = 0;
        int mid = (left + right) / 2;

        //mid is the center!
        //left (from mid to left)
        for (int i = mid - 1; i >= left; i--) {
            currSum += nums[i];
            maxSumLeft = Math.max(maxSumLeft, currSum);
        }

        currSum = 0;
        //right (from mid to right)
        for (int i = mid + 1; i <= right; i++) {
            currSum += nums[i];
            maxSumRight = Math.max(maxSumRight, currSum);
        }
        int res = maxSumLeft + nums[mid] + maxSumRight;
        res = Math.max(res, helper(nums, left, mid - 1));
        res = Math.max(res, helper(nums, mid + 1, right));
        return res;
    }

    //2.Kadane's Algorithm DP
    //Runtime: 1 ms, 100%; 51.8 MB 83%
    //Time: O(N); Space: O(1)
    public int maxSubArray_2(int[] nums) {
        int maxSum = nums[0], currSum = nums[0];
        for (int i = 1; i< nums.length; i++){
            //keep the worth keeping(larger part)
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


    //1.Brute force
    //Time Limit Exceeded
    //Time: O(N * N); Space: O(1)
    public int maxSubArray1(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        if (nums.length == 1) return nums[0];

        for(int i = 0; i < nums.length; i++){
            int tmp = 0;
            for(int j = i; j < nums.length; j++){
                tmp += nums[j];
                maxSum = Math.max(maxSum, tmp);
            }
        }
        return maxSum;
    }




    //Time Limit Exceeded
    //太复杂了
    Deque<Integer> q = new LinkedList<>();
    public int maxSubArray2(int[] nums) {
        if (nums.length == 1) return nums[0];

        int maxNum = Integer.MIN_VALUE;;
        for(int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) q.addLast(i);
            maxNum = Math.max(maxNum, nums[i]);
        }

        int result;
        if (q.isEmpty()) result = maxNum;
        else {
            result = Integer.MIN_VALUE;
            while (!q.isEmpty()) {
                int mIdx = q.pollLast();
                result = Math.max(result, maxSubArraySide(nums, mIdx, 0)
                        + nums[mIdx]
                        + maxSubArraySide(nums, mIdx, 1));
            }
        }
        return result;
    }

    private Map<Integer, Integer> memo = new HashMap<>();
    public int maxSubArraySide(int[] nums, int maxIdx, int isLeft) {
        int result = 0;
        int tmp = 0;
        //left
        if (isLeft == 0) {
            for (int l = maxIdx - 1; l >= 0; l--) {
                if (memo.containsKey(l*10+0)) {
                    tmp += memo.get(l * 10 + 0);
                    if (tmp > 0)  result += tmp;
                    memo.put((maxIdx-1)*10+isLeft, result);
                    return result;
                }
                tmp += nums[l];
                if (tmp >= 0) {
                    result += tmp;
                    tmp = 0;
                }
            }
            memo.put((maxIdx-1)*10+isLeft, result);
        } else {
            //right
            tmp = 0;
            result = 0;
            for (int r = maxIdx + 1; r < nums.length; r++) {
                if (memo.containsKey(r*10+1)) {
                    tmp += memo.get(r*10+1);
                    if (tmp > 0)  result += tmp;
                    memo.put((maxIdx+1)*10+1, result);
                    return result;
                }
                tmp += nums[r];
                if (tmp >= 0) {
                    result += tmp;
                    tmp = 0;
                }
            }
            memo.put((maxIdx+1)*10+1, result);
        }
        return result;
    }




}






