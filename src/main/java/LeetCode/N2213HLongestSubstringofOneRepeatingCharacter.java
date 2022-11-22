package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

/**
 *
 * You are given a 0-indexed string s. You are also given a 0-indexed string queryCharacters of length k
 * and a 0-indexed array of integer indices queryIndices of length k, both of which are used to describe k queries.
 *
 * The ith query updates the character in s at index queryIndices[i] to the character queryCharacters[i].
 *
 * Return an array lengths of length k where lengths[i] is the length of the
 * longest substring of s consisting of only one repeating character after the ith query is performed.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babacc", queryCharacters = "bcb", queryIndices = [1,3,3]
 * Output: [3,3,4]
 * Explanation:
 * - 1st query updates s = "bbbacc". The longest substring consisting of one repeating
 * character is "bbb" with length 3.
 * - 2nd query updates s = "bbbccc".
 *   The longest substring consisting of one repeating character can be "bbb" or "ccc" with length 3.
 * - 3rd query updates s = "bbbbcc". The longest substring consisting of one repeating
 * character is "bbbb" with length 4.
 * Thus, we return [3,3,4].
 * Example 2:
 *
 * Input: s = "abyzz", queryCharacters = "aa", queryIndices = [2,1]
 * Output: [2,3]
 * Explanation:
 * - 1st query updates s = "abazz". The longest substring consisting of one repeating
 * character is "zz" with length 2.
 * - 2nd query updates s = "aaazz". The longest substring consisting of one repeating
 * character is "aaa" with length 3.
 * Thus, we return [2,3].
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * k == queryCharacters.length == queryIndices.length
 * 1 <= k <= 105
 * queryCharacters consists of lowercase English letters.
 * 0 <= queryIndices[i] < s.length
 */

/***
 * H - time: 120+
 * - segment tree
 */
public class N2213HLongestSubstringofOneRepeatingCharacter {

    public static void main(String[] args){
        int[] data;

        System.out.println(now());


        doRun_demo("2,3", "abyzz", "aa", new int[]{2,1});
        doRun_demo("3,3,4", "babacc", "bcb", new int[]{1,3,3});
//        doRun_demo("2,3", "abyzz", "aa", new int[]{2,1});
        doRun_demo("", "abyzz", "", new int[]{});

        doRun_demo("3,2", "aaaaa", "bc", new int[]{1,2});

        doRun_demo("4,2", "aaaaa", "bc", new int[]{0,2});

        doRun_demo("2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2",
                "fycjwimycipuoxdcnckczpschafcludaefbzbarntbkuypiilgnxcdxnyqirvzkekelbqkxcbypgqdfeypoweveszxc",
                "csqgfqydbyvbaismhxgfvrksiayqbcxpvfgqdnifiaewqzxahgwlrgztmkixdtynukphztdtmikywldzjcoolbxutzsjo",
                new int[]{29,30,21,56,42,76,89,67,77,52,5,18,83,1,65,64,40,85,58,39,82,62,39,59,30,15,28,24,49,17,20,90,47,58,43,32,55,67,58,49,59,74,42,9,64,67,89,69,80,6,87,79,82,34,12,41,72,45,33,54,21,10,51,29,87,24,67,4,14,26,0,82,60,47,54,88,3,81,13,20,25,89,11,51,37,24,15,90,79,44,40,41,18});

        doRun_demo("1,1,2,2,2,2,2,2,2,1", "geuqjmt", "bgemoegklm", new int[]{3,4,2,6,5,6,5,4,3,2});

        doRun_demo("3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3",
                "mqvivodnwlqkblnacpvqkonwaemvglhkkhcpxohmntoqhyeyvvjowcdhodujtujjooeddxvjfhpppgiw",
                "owwjnooweqoutilhpnajyjiaqvxcokqpeziczagypahzssiweedeot",
                new int[]{29,21,0,2,21,61,0,63,9,9,60,13,68,16,57,42,69,26,32,28,73,8,51,29,33,2,44,53,7,19,21,1,0,73,19,53,50,65,31,21,65,29,19,73,18,31,12,34,77,44,22,11,44,21});

        doRun_demo("14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,17,17,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,16,15,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,16,24,24,24,24,24,22,22,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,24,24,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,26,24,24,24,24,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,24,24,29,38,31,31,25,25,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24",
                "iiiiiccmmmmmmmmggghhhhhhhbbzzzzzzztttaaaagggcccccccccccccckkkkkkkfuuuuuuuuiiiiiiiqqqqqwwwwwwwddddrvvvvttttttttkkkkkkfffddmmmmmxxxxxxxxmmmmmeeeeeefffssssssissxddddddpppppppzzzccnzzzxxxxxxxrrrrmmpppvvvvvvuuuuuuussssssssyyyynnnnnnnnffffoovvvvvvvvqqqqqqqkkkdddddddkppppppt",
                "gbbbbbbbbbbbbbbbbqccccccccccddddddddddddddddddddooooooooooooooggggggggggggggffffffffffffffffffffgggggggggggggggghhhiiiiiwwwwwwbbbbbbbbbbbbbbbbbbbbbbbbzzzzzzzzzzzzzzzzxxxxxxxxxxxxxxxxxxxxxxcccccccccccfffffffffffffffffffffffjjjjjjjjjjjjjjjjjjjjjjjgggggggggggggggggggghhhhhhhhhiiiiiiiiiiiiiiiiiyyyyyyypppppppppppppppppppttttttttttttttttttttbbbbbbbbbbfffffffffffffffffvvvvvvvvvvvkkkkkkkkkkkkkkkkkkkkkoooooooooooooooeeeeeeeeeeeeeeeeeeeeeeeecccccccccccccccllllllllllllllllllllcccccccccccccccvvvvvvvvvvvvvvvvvvvvvvdddddiiiiiiiiiiiiiiiiiiiimmmmmmmmmmmmmmmmmoooollllllllllllleeeeeeeeeeeerrrrrrrrggggggggggggggggggggggkkkkkkkkkkkkkkkkkkkkkkkkkkoooovvvvvvvvvvvvuuuugggggggggggggggggggglllllllllllliiiiiiiiiiiiiiiiiiiiiissssssssssssswwwwwwwwwwwwwwwwwnnnnnnnnnwwwwwwwwwwgggggggggggggggggggqqqqqqllllllllllllllllllllllyyyyyyyyyyyyyyyyyyyyyyyyiiiiiiiiiiiiiiiiiiiiiikkkkkkkkkkkkkkkkkkkkkkkkffaaaaaaaaaaaaaaaaaaas",
                new int[]{130,69,76,73,71,67,81,70,74,68,75,72,78,82,80,79,77,225,222,217,215,218,219,224,221,220,216,223,117,115,129,127,121,134,124,122,123,131,128,118,116,126,130,133,120,125,119,132,195,196,184,190,193,186,189,188,187,183,191,192,185,194,93,96,95,99,89,88,97,87,90,92,98,94,86,91,95,97,101,88,106,105,90,102,96,104,94,87,99,100,91,103,92,89,98,93,89,92,91,81,84,79,85,93,87,82,90,78,83,86,80,88,81,83,82,74,75,73,72,71,206,208,210,205,207,209,135,118,119,127,129,128,122,136,117,123,134,126,120,133,125,115,132,116,131,137,114,124,121,130,142,143,140,147,136,138,133,135,148,146,145,144,141,139,134,137,252,254,259,244,262,248,261,249,250,247,243,264,253,251,263,257,260,245,246,255,258,256,154,158,152,155,151,159,156,153,157,150,160,61,55,59,62,69,77,71,57,58,67,73,76,56,74,60,70,72,65,63,66,68,64,75,240,235,229,245,224,238,237,236,227,234,231,241,239,230,244,226,242,246,233,232,225,243,228,78,74,79,81,75,82,73,77,76,84,66,68,65,80,70,72,69,71,67,83,6,10,9,7,5,3,4,8,11,28,42,29,32,30,31,39,43,38,40,33,35,41,34,44,36,37,116,119,117,118,115,120,121,69,67,76,85,81,78,82,79,77,73,84,74,72,70,71,68,83,75,80,89,80,87,79,81,94,86,88,78,91,84,83,85,95,92,93,96,82,90,77,193,198,190,199,197,195,194,192,191,196,181,178,182,186,179,183,174,187,176,188,184,180,175,185,190,177,189,25,29,31,28,26,27,33,30,34,24,32,211,215,216,202,218,209,207,213,201,206,205,217,221,212,214,219,220,210,203,208,204,139,132,127,131,138,129,137,134,133,126,128,135,136,140,130,102,95,94,106,111,103,100,93,108,113,92,99,109,105,97,98,101,112,114,110,104,107,96,91,42,51,44,45,39,41,46,47,48,49,40,50,52,53,43,243,245,247,242,248,234,231,232,230,239,233,240,244,249,238,237,241,235,246,236,75,72,77,70,69,67,66,73,71,78,76,80,68,74,79,42,29,45,30,40,34,32,26,37,44,39,38,33,31,41,24,43,28,25,36,35,27,5,4,3,2,1,236,240,233,229,238,230,224,227,239,228,231,234,237,222,225,235,223,226,232,221,176,173,181,175,177,182,183,189,179,186,187,184,178,174,180,185,188,197,195,198,196,69,80,68,73,71,76,75,70,78,74,72,79,77,204,201,199,200,207,203,209,206,202,205,210,208,195,194,196,192,190,191,189,193,170,188,176,187,175,182,169,177,174,179,171,180,181,183,172,173,178,189,185,168,184,186,134,132,142,154,153,131,138,148,147,140,133,155,141,137,143,151,139,156,152,136,144,146,145,150,149,135,154,156,155,157,107,105,104,102,108,110,113,106,103,111,112,109,32,30,29,31,45,35,42,46,47,43,48,33,36,52,51,38,49,39,34,44,41,37,50,40,87,91,90,94,96,85,95,93,89,86,88,92,253,243,237,248,242,256,258,247,254,239,255,246,251,252,241,244,240,249,257,238,245,250,252,253,246,251,242,244,247,249,248,250,245,243,241,113,114,108,123,117,115,111,112,118,119,121,122,109,110,120,116,107,150,148,149,146,152,154,147,151,153,95,88,87,93,92,91,94,90,96,89,221,219,217,228,232,234,225,218,227,233,223,224,230,231,229,222,216,226,220,123,122,124,127,125,126,95,106,92,105,90,89,88,86,99,100,103,94,102,98,96,101,85,87,97,91,104,93,250,232,230,236,238,246,247,249,243,240,228,234,239,235,227,231,242,248,237,233,244,245,229,241,38,42,44,51,55,47,52,50,39,54,41,46,53,40,45,57,58,43,49,59,48,56,256,237,238,236,239,258,252,248,253,246,249,250,245,257,247,251,254,235,243,241,240,244,255,242,123,122,172,183,174,179,186,169,168,175,182,176,171,178,173,184,170,181,180,177,185,195});


        doRun_demo("14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,17,17,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,16,15,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,14,16,24,24,24,24,24,22,22,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,29,24,24,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,26,24,24,24,24,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,24,24,24,24,24,29,38,31,31,25,25,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,23,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24,24",
                "iiiiiccmmmmmmmmggghhhhhhhbbzzzzzzztttaaaagggcccccccccccccckkkkkkkfuuuuuuuuiiiiiiiqqqqqwwwwwwwddddrvvvvttttttttkkkkkkfffddmmmmmxxxxxxxxmmmmmeeeeeefffssssssissxddddddpppppppzzzccnzzzxxxxxxxrrrrmmpppvvvvvvuuuuuuussssssssyyyynnnnnnnnffffoovvvvvvvvqqqqqqqkkkdddddddkppppppt",
                "gbbbbbbbbbbbbbbbbqccccccccccddddddddddddddddddddooooooooooooooggggggggggggggffffffffffffffffffffgggggggggggggggghhhiiiiiwwwwwwbbbbbbbbbbbbbbbbbbbbbbbbzzzzzzzzzzzzzzzzxxxxxxxxxxxxxxxxxxxxxxcccccccccccfffffffffffffffffffffffjjjjjjjjjjjjjjjjjjjjjjjgggggggggggggggggggghhhhhhhhhiiiiiiiiiiiiiiiiiyyyyyyypppppppppppppppppppttttttttttttttttttttbbbbbbbbbbfffffffffffffffffvvvvvvvvvvvkkkkkkkkkkkkkkkkkkkkkoooooooooooooooeeeeeeeeeeeeeeeeeeeeeeeecccccccccccccccllllllllllllllllllllcccccccccccccccvvvvvvvvvvvvvvvvvvvvvvdddddiiiiiiiiiiiiiiiiiiiimmmmmmmmmmmmmmmmmoooollllllllllllleeeeeeeeeeeerrrrrrrrggggggggggggggggggggggkkkkkkkkkkkkkkkkkkkkkkkkkkoooovvvvvvvvvvvvuuuugggggggggggggggggggglllllllllllliiiiiiiiiiiiiiiiiiiiiissssssssssssswwwwwwwwwwwwwwwwwnnnnnnnnnwwwwwwwwwwgggggggggggggggggggqqqqqqllllllllllllllllllllllyyyyyyyyyyyyyyyyyyyyyyyyiiiiiiiiiiiiiiiiiiiiiikkkkkkkkkkkkkkkkkkkkkkkkffaaaaaaaaaaaaaaaaaaas",
                new int[]{130,69,76,73,71,67,81,70,74,68,75,72,78,82,80,79,77,225,222,217,215,218,219,224,221,220,216,223,117,115,129,127,121,134,124,122,123,131,128,118,116,126,130,133,120,125,119,132,195,196,184,190,193,186,189,188,187,183,191,192,185,194,93,96,95,99,89,88,97,87,90,92,98,94,86,91,95,97,101,88,106,105,90,102,96,104,94,87,99,100,91,103,92,89,98,93,89,92,91,81,84,79,85,93,87,82,90,78,83,86,80,88,81,83,82,74,75,73,72,71,206,208,210,205,207,209,135,118,119,127,129,128,122,136,117,123,134,126,120,133,125,115,132,116,131,137,114,124,121,130,142,143,140,147,136,138,133,135,148,146,145,144,141,139,134,137,252,254,259,244,262,248,261,249,250,247,243,264,253,251,263,257,260,245,246,255,258,256,154,158,152,155,151,159,156,153,157,150,160,61,55,59,62,69,77,71,57,58,67,73,76,56,74,60,70,72,65,63,66,68,64,75,240,235,229,245,224,238,237,236,227,234,231,241,239,230,244,226,242,246,233,232,225,243,228,78,74,79,81,75,82,73,77,76,84,66,68,65,80,70,72,69,71,67,83,6,10,9,7,5,3,4,8,11,28,42,29,32,30,31,39,43,38,40,33,35,41,34,44,36,37,116,119,117,118,115,120,121,69,67,76,85,81,78,82,79,77,73,84,74,72,70,71,68,83,75,80,89,80,87,79,81,94,86,88,78,91,84,83,85,95,92,93,96,82,90,77,193,198,190,199,197,195,194,192,191,196,181,178,182,186,179,183,174,187,176,188,184,180,175,185,190,177,189,25,29,31,28,26,27,33,30,34,24,32,211,215,216,202,218,209,207,213,201,206,205,217,221,212,214,219,220,210,203,208,204,139,132,127,131,138,129,137,134,133,126,128,135,136,140,130,102,95,94,106,111,103,100,93,108,113,92,99,109,105,97,98,101,112,114,110,104,107,96,91,42,51,44,45,39,41,46,47,48,49,40,50,52,53,43,243,245,247,242,248,234,231,232,230,239,233,240,244,249,238,237,241,235,246,236,75,72,77,70,69,67,66,73,71,78,76,80,68,74,79,42,29,45,30,40,34,32,26,37,44,39,38,33,31,41,24,43,28,25,36,35,27,5,4,3,2,1,236,240,233,229,238,230,224,227,239,228,231,234,237,222,225,235,223,226,232,221,176,173,181,175,177,182,183,189,179,186,187,184,178,174,180,185,188,197,195,198,196,69,80,68,73,71,76,75,70,78,74,72,79,77,204,201,199,200,207,203,209,206,202,205,210,208,195,194,196,192,190,191,189,193,170,188,176,187,175,182,169,177,174,179,171,180,181,183,172,173,178,189,185,168,184,186,134,132,142,154,153,131,138,148,147,140,133,155,141,137,143,151,139,156,152,136,144,146,145,150,149,135,154,156,155,157,107,105,104,102,108,110,113,106,103,111,112,109,32,30,29,31,45,35,42,46,47,43,48,33,36,52,51,38,49,39,34,44,41,37,50,40,87,91,90,94,96,85,95,93,89,86,88,92,253,243,237,248,242,256,258,247,254,239,255,246,251,252,241,244,240,249,257,238,245,250,252,253,246,251,242,244,247,249,248,250,245,243,241,113,114,108,123,117,115,111,112,118,119,121,122,109,110,120,116,107,150,148,149,146,152,154,147,151,153,95,88,87,93,92,91,94,90,96,89,221,219,217,228,232,234,225,218,227,233,223,224,230,231,229,222,216,226,220,123,122,124,127,125,126,95,106,92,105,90,89,88,86,99,100,103,94,102,98,96,101,85,87,97,91,104,93,250,232,230,236,238,246,247,249,243,240,228,234,239,235,227,231,242,248,237,233,244,245,229,241,38,42,44,51,55,47,52,50,39,54,41,46,53,40,45,57,58,43,49,59,48,56,256,237,238,236,239,258,252,248,253,246,249,250,245,257,247,251,254,235,243,241,240,244,255,242,123,122,172,183,174,179,186,169,168,175,182,176,171,178,173,184,170,181,180,177,185,195});

//
        doRun_demo("2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2",
                "wjkwcubakkrhihzbrjja",
                "igyxlwcykydlnjqzjyesnjqz",
                new int[]{10,0,6,2,7,19,10,11,19,16,6,1,4,2,1,7,15,6,11,8,11,14,9,15});

        System.out.println(now());
        System.out.println("==================");

    }

    static private void doRun_demo(String expect, String s, String queryCharacters, int[] queryIndices) {
        int[] res1 = new N2213HLongestSubstringofOneRepeatingCharacter()
                .longestRepeating(s, queryCharacters, queryIndices);
        String res = Arrays.stream(res1).mapToObj(i->String.valueOf(i)).collect(Collectors.joining(","));
        //String res = comm.toString(res1);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
//        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 163 ms, faster than 89.76% of Java online submissions for Longest Substring of One Repeating Character.
    //Memory Usage: 68.7 MB, less than 84.25% of Java online submissions for Longest Substring of One Repeating Character.
    //Segment tree + iteration
    //Time: O(N*lgN + M*lgN) ; Space: O(4N)
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int len = s.length();

         int treeSize = 2;
         while (len >= 1) {
             treeSize <<= 1;
             len >>= 1;
         }

        //Space: O(4N)
        Node2213[] segmentTree = new Node2213[treeSize];

        //Time: O(N * lgN)
        for (int i = 0; i < s.length();i++)
            updateSTree(i, s.charAt(i), segmentTree);

        //Time: O(M*lgN)
        int[] res = new int[queryIndices.length];
        for (int i = 0; i < queryIndices.length;i++){
            //Time: O(lgN); Space:O(1)
            updateSTree(queryIndices[i], queryCharacters.charAt(i), segmentTree);
            res[i] = segmentTree[1].maxLen;
        }
        return res;
    }

    class Node2213{
        char leftChar, rightChar;
        int leftLen = 1, rightLen = 1;
        int maxLen = 1;
        boolean flag = true; //true : same
        public Node2213(char c){
            leftChar = rightChar = c;
        }
    }

    //Time: O(lgN); Space:O(1)
    private void updateSTree(int i, char c, Node2213[] segmentTree){
        i += segmentTree.length / 2;
        if (segmentTree[i] == null)  segmentTree[i] = new Node2213(c);
        segmentTree[i].rightChar = c;
        segmentTree[i].leftChar = c;

        while (i > 1){
            i = i >> 1;
            if (segmentTree[i] == null)  segmentTree[i] = new Node2213('$');
            if (segmentTree[i*2+1] == null)  segmentTree[i*2+1] = new Node2213('$');

            //left
            segmentTree[i].leftChar =  segmentTree[i * 2].leftChar;
            segmentTree[i].leftLen =  segmentTree[i * 2].leftLen;
            //right
            segmentTree[i].rightChar =  segmentTree[i * 2 + 1].rightChar;
            segmentTree[i].rightLen =  segmentTree[i * 2 + 1].rightLen;

            //maxLen & flag
            segmentTree[i].flag = segmentTree[i * 2].flag & segmentTree[i * 2 + 1].flag
                    & segmentTree[i * 2].rightChar == segmentTree[i * 2 + 1].leftChar;

            segmentTree[i].maxLen = Math.max(segmentTree[i * 2].maxLen, segmentTree[i * 2 + 1].maxLen);

            //do merge
            if (segmentTree[i * 2].rightChar != '$' && segmentTree[i * 2].rightChar == segmentTree[i * 2 + 1].leftChar) {
                int maxLen = segmentTree[i].maxLen;
                maxLen = Math.max(maxLen, segmentTree[i * 2].rightLen + segmentTree[i * 2 + 1].leftLen);

                if (segmentTree[i * 2].flag) {
                    segmentTree[i].leftLen += segmentTree[i * 2 + 1].leftLen;
                    maxLen = Math.max(maxLen, segmentTree[i].leftLen);
                }

                if (segmentTree[i * 2 + 1].flag) {
                    segmentTree[i].rightLen += segmentTree[i * 2].rightLen;
                    maxLen = Math.max(maxLen, segmentTree[i].rightLen);
                }

                segmentTree[i].maxLen = maxLen;
            }//End if
        }//End While
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Runtime: 428 ms, faster than 47.24% of Java online submissions for Longest Substring of One Repeating Character.
    //Memory Usage: 232.5 MB, less than 14.96% of Java online submissions for Longest Substring of One Repeating Character.
    //treeMap
    //Time: O(N * lgN + M * lgN);  Space: O(N + M)
    public int[] longestRepeating_1(String s, String queryCharacters, int[] queryIndices) {

        //Time: O(N*lgN)
        MyTreeMap2213 myTreeMap = new MyTreeMap2213(s);
        //Space: O(M)
        int[] res = new int[queryIndices.length];

        //Time:O(M * lgN)
        for (int i = 0; i < queryIndices.length; i++){
            myTreeMap.updateChar(queryIndices[i], queryCharacters.charAt(i));
            res[i] = myTreeMap.getMaxLen();
        }
        return res;
    }

    class MyTreeMap2213{
        //index: node
        private TreeMap<Integer, Node> treeMapAll = new TreeMap<>();
        //length : Set<node> : these nodes have same length
        private TreeMap<Integer, Set<Node>> treeMapMaxLen = new TreeMap<>();  //length: Set<Node>
        private char[] charsList;
        public MyTreeMap2213(String s){
            charsList = s.toCharArray();
            buildData();
        }

        public int getMaxLen(){
            return treeMapMaxLen.lastKey();
        }

        //Time: O(N*lgN)
        private void buildData(){
            int i = 0;
            while (i < charsList.length) {
                char currChar = charsList[i];
                Node node = new Node(i++);
                while (i < charsList.length && charsList[i] == currChar) i++;
                node.end = i - 1;
                add(node);
            }
        }

        //Time:O(c * lgN)
        public void updateChar(int idx, char newChar){
            if (charsList[idx] == newChar) return;

            //old node : split it or abandoned
            Node oldNode = treeMapAll.floorEntry(idx).getValue();
            //treeMapAll.remove(node.begin);
            removeViaLength(oldNode);

            if (oldNode.length() > 1) {
                Node[] newList = oldNode.split(idx);
                for (Node n : newList)
                    if (n != null) add(n);
            }

            //get neighbours
            Node leftNode = null;
            if (idx - 1 >= 0 && charsList[idx - 1] == newChar) {
                leftNode = treeMapAll.floorEntry(idx - 1).getValue();
                removeViaLength(leftNode);
            }

            if (leftNode != null)
                treeMapAll.remove(oldNode.begin);

            Node rightNode = null;
            if (idx + 1 < charsList.length && charsList[idx + 1] == newChar ) {
                rightNode = treeMapAll.get(idx + 1);
                removeViaLength(rightNode);
            }

            //merge with neighbours
            if (leftNode != null && rightNode != null){
                treeMapAll.remove(rightNode.begin);
                leftNode.end = rightNode.end;
                add(leftNode);
            }else if (leftNode != null){
                leftNode.end = leftNode.end + 1;
                add(leftNode);
            }else if (rightNode != null){
                treeMapAll.remove(rightNode.begin);
                rightNode.begin--;
                add(rightNode);
            }else{
                Node node = new Node(idx);
                add(node);
            }
            charsList[idx] = newChar;
        }

        //Time:O(lgN)
        private void add(Node node){
            treeMapAll.put(node.begin, node);
            Set set = treeMapMaxLen.getOrDefault(node.length(), new HashSet<>());
            set.add(node);
            treeMapMaxLen.put(node.length(), set);
        }

        //Time:O(lgN)
        private void removeViaLength(Node node){
            int len = node.length();
            if (treeMapMaxLen.get(len).size() == 1) treeMapMaxLen.remove(len);
            else treeMapMaxLen.get(len).remove(node);
        }

        class Node{
            int begin = -1;
            int end = -1;

            public Node(int idx){
                begin = idx;
                end = idx;
            }
            public boolean contain(int idx){
                return idx >= begin && idx <= end;
            }
            public int length() {
                return end - begin + 1;
            }

            public Node[] split(int idx){
                Node left = null;
                if (idx - 1 >= begin){
                    left = new Node(begin);
                    left.begin = begin;
                    left.end = idx - 1;
                }

                Node right = null;
                if (idx + 1 <= end){
                    right = new Node(end);
                    right.begin = idx + 1;
                    right.end = end;
                }
                return new Node[]{left, right};
            }
        }
    }

}
