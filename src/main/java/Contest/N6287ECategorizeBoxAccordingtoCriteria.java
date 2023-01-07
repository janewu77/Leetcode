package Contest;


import static java.time.LocalTime.now;

/**
 * Given four integers length, width, height, and mass, representing the dimensions and mass of a box, respectively, return a string representing the category of the box.
 *
 * The box is "Bulky" if:
 * Any of the dimensions of the box is greater or equal to 104.
 * Or, the volume of the box is greater or equal to 109.
 * If the mass of the box is greater or equal to 100, it is "Heavy".
 * If the box is both "Bulky" and "Heavy", then its category is "Both".
 * If the box is neither "Bulky" nor "Heavy", then its category is "Neither".
 * If the box is "Bulky" but not "Heavy", then its category is "Bulky".
 * If the box is "Heavy" but not "Bulky", then its category is "Heavy".
 * Note that the volume of the box is the product of its length, width and height.
 *
 *
 *
 * Example 1:
 *
 * Input: length = 1000, width = 35, height = 700, mass = 300
 * Output: "Heavy"
 * Explanation:
 * None of the dimensions of the box is greater or equal to 104.
 * Its volume = 24500000 <= 109. So it cannot be categorized as "Bulky".
 * However mass >= 100, so the box is "Heavy".
 * Since the box is not "Bulky" but "Heavy", we return "Heavy".
 * Example 2:
 *
 * Input: length = 200, width = 50, height = 800, mass = 50
 * Output: "Neither"
 * Explanation:
 * None of the dimensions of the box is greater or equal to 104.
 * Its volume = 8 * 106 <= 109. So it cannot be categorized as "Bulky".
 * Its mass is also less than 100, so it cannot be categorized as "Heavy" either.
 * Since its neither of the two above categories, we return "Neither".
 *
 *
 * Constraints:
 *
 * 1 <= length, width, height <= 105
 * 1 <= mass <= 103
 */

//2525. Categorize Box According to Criteria
public class N6287ECategorizeBoxAccordingtoCriteria {

     static public void main(String... args) {
         System.out.println(now());
         System.out.println("==================");
         doRun("Both", 100_000, 100_000, 100_000, 727);
        doRun("Both", 2909, 3968, 3272, 727);
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, int length, int width, int height, int mass) {
        String res = new N6287ECategorizeBoxAccordingtoCriteria().categorizeBox( length,  width,  height,  mass);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 0ms 100%; Memory: 39.7MB 80%
    //Time: O(1); Space: O(1)
    public String categorizeBox(int length, int width, int height, int mass) {
        int m104 = 10_000, m109 = 1_000_000_000;

        boolean isHeavy = mass >= 100;
        boolean isBulky = length >= m104 || width >= m104 || height >= m104;
        if (!isBulky) {
            long prod = length * width;
            isBulky = prod >= m109 ? true : prod * height >= m109 ? true: false;
        }

        if (isBulky && isHeavy) return "Both";
        if (isBulky) return "Bulky";
        if (isHeavy) return "Heavy";
        return "Neither";
    }

}


