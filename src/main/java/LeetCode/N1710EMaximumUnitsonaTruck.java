package LeetCode;


import java.util.Arrays;
import java.util.Comparator;

/**
 * You are assigned to put some amount of boxes onto one truck.
 * You are given a 2D array boxTypes, where boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *
 * numberOfBoxesi is the number of boxes of type i.
 * numberOfUnitsPerBoxi is the number of units in each box of the type i.
 * You are also given an integer truckSize, which is the maximum number of
 * boxes that can be put on the truck. You can choose any boxes to put on the
 * truck as long as the number of boxes does not exceed truckSize.
 *
 * Return the maximum total number of units that can be put on the truck.
 *
 *
 *
 * Example 1:
 * Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * Output: 8
 * Explanation: There are:
 * - 1 box of the first type that contains 3 units.
 * - 2 boxes of the second type that contain 2 units each.
 * - 3 boxes of the third type that contain 1 unit each.
 * You can take all the boxes of the first and second types, and one box of the third type.
 * The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
 *
 *
 * Example 2:
 * Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
 * Output: 91
 *
 *
 * Constraints:
 * 1 <= boxTypes.length <= 1000
 * 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
 * 1 <= truckSize <= 106
 *
 */

/**
 * E
 *  - arrays的排序实现
 */
public class N1710EMaximumUnitsonaTruck {

    public static void main(String[] args){

        int[][] boxTypes = {{1,3},{2,2},{3,1}};
        int truckSize = 4;

        int result = new N1710EMaximumUnitsonaTruck().maximumUnits(boxTypes,truckSize);
        System.out.println("expected Result: 8. result:"+result);

        // [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
        int[][] boxTypes2 = {{5,10}, {2,5}, {4,7}, {3,9}};
        int truckSize2 = 10;
        result = new N1710EMaximumUnitsonaTruck().maximumUnits(boxTypes2,truckSize2);
        System.out.println("expected Result: 91. result:"+result);

    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        //按unit数量倒序
        Arrays.sort(boxTypes, (a,b) -> ( b[1]-a[1]));
        int result = 0;
        for(int[] boxType: boxTypes){
            if (truckSize - boxType[0] >= 0){
                result += boxType[0] * boxType[1];
                truckSize = truckSize - boxType[0];
            }else{
                result += truckSize * boxType[1];
                truckSize = 0;
            }
            if (truckSize == 0) break;
        }
        return result;
    }

}
