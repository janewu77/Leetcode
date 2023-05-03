package LeetCode;

import java.util.*;

public class N2215EFindtheDifferenceofTwoArrays {


    //1. Set
    // Time: 11ms 86%; Memory: 43.8MB 43.75%
    // Time: O(N1 + N2); Space: O(N1 + N2)
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set0 = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int n : nums1) {
            set1.add(n);
        }
        for (int n : nums2) {
            if (set1.contains(n))
                set0.add(n);
            else set2.add(n);
        }
        set1.removeAll(set0);
        res.add(new ArrayList<>(set1));
        res.add(new ArrayList<>(set2));
        return res;
    }

}
