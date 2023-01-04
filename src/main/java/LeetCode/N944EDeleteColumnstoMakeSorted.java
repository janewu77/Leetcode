package LeetCode;


/**
 * You are given an array of n strings strs, all of the same length.
 *
 * The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:
 *
 * abc
 * bce
 * cae
 * You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
 *
 * Return the number of columns that you will delete.
 */
public class N944EDeleteColumnstoMakeSorted {

    //1.matrix traversing
    //Runtime: 17ms 44%; Memory: 48MB 31%
    //Time: O(N * M); Space:O(1)
    //let n be the number of words in input arrays.
    //let m be the length of word.
    public int minDeletionSize(String[] strs) {
        int res = 0;
        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    res++; break;
                }
            }
        }
        return res;
    }

}
