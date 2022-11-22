package LeetCode;



import java.util.*;

import static java.time.LocalTime.now;

/***
 *
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example 1:
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 *
 * Example 2:
 * Input: board = [["a","b"],["c","d"]], words = ["abcb"]
 * Output: []
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] is a lowercase English letter.
 * 1 <= words.length <= 3 * 104
 * 1 <= words[i].length <= 10
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 *
 */

/**
 *
 * H - [time: 2days]
 *  - 过于扣细节了。
 *
 */
public class N212HWordSearchII {

    public static void main(String[] args) {


        char[][] board;
        String[] words;
        String expected;

        board = new char[][]{{'a','b'},{'a','a'}};
        words = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        expected = "[aaa, aaab, aaba, aba, baa]";
        doRun(expected, board, words);

        board = new char[][]{{'a','b'},{'c','d'}};
        words = new String[]{"abcb"};
        expected = "[]";
        doRun(expected, board, words);

        board = new char[][]{{'a','b','e'},{'d','c','a'}};
        words = new String[]{"abcd", "ed"};
        expected = "[abcd]";
        doRun(expected, board, words);
//
        board = new char[][]{{'e','g','c'},{'s','g','g'}};
        words = new String[]{"exgg", "d"};
        expected = "[]";
        doRun(expected, board, words);
//
        board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        words = new String[]{"oath","pea","eat","rain"};
        expected = "[eat, oath]";
        doRun(expected, board, words);
////
        board = new char[][]{{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        words = new String[]{};
        expected = "[]";
        doRun(expected, board, words);
//
        board = new char[][]{{'o'}};
        words = new String[]{"o"};
        expected = "[o]";
        doRun(expected, board, words);

        board = new char[][]{{'o','a','b','n'},{'o','t','a','e'},{'a','h','k','r'},{'a','f','l','v'}};
        words = new String[]{"oa","oaa"};
        expected = "[oa, oaa]";
        doRun(expected, board, words);

        board = new char[][]{{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        words = new String[]{"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};
        expected = "[abcdefg, befa, eaabcdgfa, gfedcbaaa]";
//        words = new String[]{"eaabcdgfa"};
//        expected = "[eaabcdgfa]";
        doRun(expected, board, words);



        board = new char[][] {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        words = new String[]{"eaaf","eaab"};
        expected = "[eaab, eaaf]";
        doRun(expected, board, words);

        board = new char[][] {{'b','x','x'},{'a','x','x'},{'a','x','x'},{'a','x','x'},
                {'a','a','a'},
                {'a','x','x'},{'a','x','x'},{'a','x','x'},{'c','x','x'}};
        words = new String[]{"aaaab","aaaac"};
        expected = "[aaaab, aaaac]";
        doRun(expected, board, words);
//
        board = new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        words = new String[]{"oath","pea","eat","rain","oathi","oathk","oathf","oate","oathii","oathfi","oathfii"};
        //expected = "[oath, oathf, oathfi, oathfii, oathk, oathi, oathii, oate, eat]";
        //expected = "[oath, oathf, oathfi, oathfii, oathi, oathii, oathk, oate, eat]";
        expected = "[eat, oate, oath, oathf, oathfi, oathfii, oathi, oathii, oathk]";
        doRun(expected, board, words);

        board = new char[][]{{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'},{'a','a','a','a','a','a','a','a','a','a','a','a'}};
        words = new String[]{"lllllll","fffffff","ssss","s","rr","xxxx","ttt","eee","ppppppp","iiiiiiiii","xxxxxxxxxx","pppppp","xxxxxx","yy","jj","ccc","zzz","ffffffff","r","mmmmmmmmm","tttttttt","mm","ttttt","qqqqqqqqqq","z","aaaaaaaa","nnnnnnnnn","v","g","ddddddd","eeeeeeeee","aaaaaaa","ee","n","kkkkkkkkk","ff","qq","vvvvv","kkkk","e","nnn","ooo","kkkkk","o","ooooooo","jjj","lll","ssssssss","mmmm","qqqqq","gggggg","rrrrrrrrrr","iiii","bbbbbbbbb","aaaaaa","hhhh","qqq","zzzzzzzzz","xxxxxxxxx","ww","iiiiiii","pp","vvvvvvvvvv","eeeee","nnnnnnn","nnnnnn","nn","nnnnnnnn","wwwwwwww","vvvvvvvv","fffffffff","aaa","p","ddd","ppppppppp","fffff","aaaaaaaaa","oooooooo","jjjj","xxx","zz","hhhhh","uuuuu","f","ddddddddd","zzzzzz","cccccc","kkkkkk","bbbbbbbb","hhhhhhhhhh","uuuuuuu","cccccccccc","jjjjj","gg","ppp","ccccccccc","rrrrrr","c","cccccccc","yyyyy","uuuu","jjjjjjjj","bb","hhh","l","u","yyyyyy","vvv","mmm","ffffff","eeeeeee","qqqqqqq","zzzzzzzzzz","ggg","zzzzzzz","dddddddddd","jjjjjjj","bbbbb","ttttttt","dddddddd","wwwwwww","vvvvvv","iii","ttttttttt","ggggggg","xx","oooooo","cc","rrrr","qqqq","sssssss","oooo","lllllllll","ii","tttttttttt","uuuuuu","kkkkkkkk","wwwwwwwwww","pppppppppp","uuuuuuuu","yyyyyyy","cccc","ggggg","ddddd","llllllllll","tttt","pppppppp","rrrrrrr","nnnn","x","yyy","iiiiiiiiii","iiiiii","llll","nnnnnnnnnn","aaaaaaaaaa","eeeeeeeeee","m","uuu","rrrrrrrr","h","b","vvvvvvv","ll","vv","mmmmmmm","zzzzz","uu","ccccccc","xxxxxxx","ss","eeeeeeee","llllllll","eeee","y","ppppp","qqqqqq","mmmmmm","gggg","yyyyyyyyy","jjjjjj","rrrrr","a","bbbb","ssssss","sss","ooooo","ffffffffff","kkk","xxxxxxxx","wwwwwwwww","w","iiiiiiii","ffff","dddddd","bbbbbb","uuuuuuuuu","kkkkkkk","gggggggggg","qqqqqqqq","vvvvvvvvv","bbbbbbbbbb","nnnnn","tt","wwww","iiiii","hhhhhhh","zzzzzzzz","ssssssssss","j","fff","bbbbbbb","aaaa","mmmmmmmmmm","jjjjjjjjjj","sssss","yyyyyyyy","hh","q","rrrrrrrrr","mmmmmmmm","wwwww","www","rrr","lllll","uuuuuuuuuu","oo","jjjjjjjjj","dddd","pppp","hhhhhhhhh","kk","gggggggg","xxxxx","vvvv","d","qqqqqqqqq","dd","ggggggggg","t","yyyy","bbb","yyyyyyyyyy","tttttt","ccccc","aa","eeeeee","llllll","kkkkkkkkkk","sssssssss","i","hhhhhh","oooooooooo","wwwwww","ooooooooo","zzzz","k","hhhhhhhh","aaaaa","mmmmm"};
//        words = new String[]{"a","aa", "b"};
        expected = "[a, aa, aaa, aaaa, aaaaa, aaaaaa, aaaaaaa, aaaaaaaa, aaaaaaaaa, aaaaaaaaaa]";
        doRun(expected, board, words);

        board = new char[][]{{'b','a','b','a','b','a','b','a','b','a'},{'a','b','a','b','a','b','a','b','a','b'},{'b','a','b','a','b','a','b','a','b','a'},{'a','b','a','b','a','b','a','b','a','b'},{'b','a','b','a','b','a','b','a','b','a'},{'a','b','a','b','a','b','a','b','a','b'},{'b','a','b','a','b','a','b','a','b','a'},{'a','b','a','b','a','b','a','b','a','b'},{'b','a','b','a','b','a','b','a','b','a'},{'a','b','a','b','a','b','a','b','a','b'}};
        words = new String[]{"ababababaa","ababababab","ababababac","ababababad","ababababae","ababababaf","ababababag","ababababah","ababababai","ababababaj","ababababak","ababababal","ababababam","ababababan","ababababao","ababababap","ababababaq","ababababar","ababababas","ababababat","ababababau","ababababav","ababababaw","ababababax","ababababay","ababababaz","ababababba","ababababbb","ababababbc","ababababbd","ababababbe","ababababbf","ababababbg","ababababbh","ababababbi","ababababbj","ababababbk","ababababbl","ababababbm","ababababbn","ababababbo","ababababbp","ababababbq","ababababbr","ababababbs","ababababbt","ababababbu","ababababbv","ababababbw","ababababbx","ababababby","ababababbz","ababababca","ababababcb","ababababcc","ababababcd","ababababce","ababababcf","ababababcg","ababababch","ababababci","ababababcj","ababababck","ababababcl","ababababcm","ababababcn","ababababco","ababababcp","ababababcq","ababababcr","ababababcs","ababababct","ababababcu","ababababcv","ababababcw","ababababcx","ababababcy","ababababcz","ababababda","ababababdb","ababababdc","ababababdd","ababababde","ababababdf","ababababdg","ababababdh","ababababdi","ababababdj","ababababdk","ababababdl","ababababdm","ababababdn","ababababdo","ababababdp","ababababdq","ababababdr","ababababds","ababababdt","ababababdu","ababababdv","ababababdw","ababababdx","ababababdy","ababababdz","ababababea","ababababeb","ababababec","ababababed","ababababee","ababababef","ababababeg","ababababeh","ababababei","ababababej","ababababek","ababababel","ababababem","ababababen","ababababeo","ababababep","ababababeq","ababababer","ababababes","ababababet","ababababeu","ababababev","ababababew","ababababex","ababababey","ababababez","ababababfa","ababababfb","ababababfc","ababababfd","ababababfe","ababababff","ababababfg","ababababfh","ababababfi","ababababfj","ababababfk","ababababfl","ababababfm","ababababfn","ababababfo","ababababfp","ababababfq","ababababfr","ababababfs","ababababft","ababababfu","ababababfv","ababababfw","ababababfx","ababababfy","ababababfz","ababababga","ababababgb","ababababgc","ababababgd","ababababge","ababababgf","ababababgg","ababababgh","ababababgi","ababababgj","ababababgk","ababababgl","ababababgm","ababababgn","ababababgo","ababababgp","ababababgq","ababababgr","ababababgs","ababababgt","ababababgu","ababababgv","ababababgw","ababababgx","ababababgy","ababababgz","ababababha","ababababhb","ababababhc","ababababhd","ababababhe","ababababhf","ababababhg","ababababhh","ababababhi","ababababhj","ababababhk","ababababhl","ababababhm","ababababhn","ababababho","ababababhp","ababababhq","ababababhr","ababababhs","ababababht","ababababhu","ababababhv","ababababhw","ababababhx","ababababhy","ababababhz","ababababia","ababababib","ababababic","ababababid","ababababie","ababababif","ababababig","ababababih","ababababii","ababababij","ababababik","ababababil","ababababim","ababababin","ababababio","ababababip","ababababiq","ababababir","ababababis","ababababit","ababababiu","ababababiv","ababababiw","ababababix","ababababiy","ababababiz","ababababja","ababababjb","ababababjc","ababababjd","ababababje","ababababjf","ababababjg","ababababjh","ababababji","ababababjj","ababababjk","ababababjl","ababababjm","ababababjn","ababababjo","ababababjp","ababababjq","ababababjr","ababababjs","ababababjt","ababababju","ababababjv","ababababjw","ababababjx","ababababjy","ababababjz","ababababka","ababababkb","ababababkc","ababababkd","ababababke","ababababkf","ababababkg","ababababkh","ababababki","ababababkj","ababababkk","ababababkl","ababababkm","ababababkn","ababababko","ababababkp","ababababkq","ababababkr","ababababks","ababababkt","ababababku","ababababkv","ababababkw","ababababkx","ababababky","ababababkz","ababababla","abababablb","abababablc","ababababld","abababable","abababablf","abababablg","abababablh","ababababli","abababablj","abababablk","ababababll","abababablm","ababababln","abababablo","abababablp","abababablq","abababablr","ababababls","abababablt","abababablu","abababablv","abababablw","abababablx","abababably","abababablz","ababababma","ababababmb","ababababmc","ababababmd","ababababme","ababababmf","ababababmg","ababababmh","ababababmi","ababababmj","ababababmk","ababababml","ababababmm","ababababmn","ababababmo","ababababmp","ababababmq","ababababmr","ababababms","ababababmt","ababababmu","ababababmv","ababababmw","ababababmx","ababababmy","ababababmz","ababababna","ababababnb","ababababnc","ababababnd","ababababne","ababababnf","ababababng","ababababnh","ababababni","ababababnj","ababababnk","ababababnl","ababababnm","ababababnn","ababababno","ababababnp","ababababnq","ababababnr","ababababns","ababababnt","ababababnu","ababababnv","ababababnw","ababababnx","ababababny","ababababnz","ababababoa","ababababob","ababababoc","ababababod","ababababoe","ababababof","ababababog","ababababoh","ababababoi","ababababoj","ababababok","ababababol","ababababom","ababababon","ababababoo","ababababop","ababababoq","ababababor","ababababos","ababababot","ababababou","ababababov","ababababow","ababababox","ababababoy","ababababoz","ababababpa","ababababpb","ababababpc","ababababpd","ababababpe","ababababpf","ababababpg","ababababph","ababababpi","ababababpj","ababababpk","ababababpl","ababababpm","ababababpn","ababababpo","ababababpp","ababababpq","ababababpr","ababababps","ababababpt","ababababpu","ababababpv","ababababpw","ababababpx","ababababpy","ababababpz","ababababqa","ababababqb","ababababqc","ababababqd","ababababqe","ababababqf","ababababqg","ababababqh","ababababqi","ababababqj","ababababqk","ababababql","ababababqm","ababababqn","ababababqo","ababababqp","ababababqq","ababababqr","ababababqs","ababababqt","ababababqu","ababababqv","ababababqw","ababababqx","ababababqy","ababababqz","ababababra","ababababrb","ababababrc","ababababrd","ababababre","ababababrf","ababababrg","ababababrh","ababababri","ababababrj","ababababrk","ababababrl","ababababrm","ababababrn","ababababro","ababababrp","ababababrq","ababababrr","ababababrs","ababababrt","ababababru","ababababrv","ababababrw","ababababrx","ababababry","ababababrz","ababababsa","ababababsb","ababababsc","ababababsd","ababababse","ababababsf","ababababsg","ababababsh","ababababsi","ababababsj","ababababsk","ababababsl","ababababsm","ababababsn","ababababso","ababababsp","ababababsq","ababababsr","ababababss","ababababst","ababababsu","ababababsv","ababababsw","ababababsx","ababababsy","ababababsz","ababababta","ababababtb","ababababtc","ababababtd","ababababte","ababababtf","ababababtg","ababababth","ababababti","ababababtj","ababababtk","ababababtl","ababababtm","ababababtn","ababababto","ababababtp","ababababtq","ababababtr","ababababts","ababababtt","ababababtu","ababababtv","ababababtw","ababababtx","ababababty","ababababtz","ababababua","ababababub","ababababuc","ababababud","ababababue","ababababuf","ababababug","ababababuh","ababababui","ababababuj","ababababuk","ababababul","ababababum","ababababun","ababababuo","ababababup","ababababuq","ababababur","ababababus","ababababut","ababababuu","ababababuv","ababababuw","ababababux","ababababuy","ababababuz","ababababva","ababababvb","ababababvc","ababababvd","ababababve","ababababvf","ababababvg","ababababvh","ababababvi","ababababvj","ababababvk","ababababvl","ababababvm","ababababvn","ababababvo","ababababvp","ababababvq","ababababvr","ababababvs","ababababvt","ababababvu","ababababvv","ababababvw","ababababvx","ababababvy","ababababvz","ababababwa","ababababwb","ababababwc","ababababwd","ababababwe","ababababwf","ababababwg","ababababwh","ababababwi","ababababwj","ababababwk","ababababwl","ababababwm","ababababwn","ababababwo","ababababwp","ababababwq","ababababwr","ababababws","ababababwt","ababababwu","ababababwv","ababababww","ababababwx","ababababwy","ababababwz","ababababxa","ababababxb","ababababxc","ababababxd","ababababxe","ababababxf","ababababxg","ababababxh","ababababxi","ababababxj","ababababxk","ababababxl","ababababxm","ababababxn","ababababxo","ababababxp","ababababxq","ababababxr","ababababxs","ababababxt","ababababxu","ababababxv","ababababxw","ababababxx","ababababxy","ababababxz","ababababya","ababababyb","ababababyc","ababababyd","ababababye","ababababyf","ababababyg","ababababyh","ababababyi","ababababyj","ababababyk","ababababyl","ababababym","ababababyn","ababababyo","ababababyp","ababababyq","ababababyr","ababababys","ababababyt","ababababyu","ababababyv","ababababyw","ababababyx","ababababyy","ababababyz","ababababza","ababababzb","ababababzc","ababababzd","ababababze","ababababzf","ababababzg","ababababzh","ababababzi","ababababzj","ababababzk","ababababzl","ababababzm","ababababzn","ababababzo","ababababzp","ababababzq","ababababzr","ababababzs","ababababzt","ababababzu","ababababzv","ababababzw","ababababzx","ababababzy","ababababzz"};
//        words = new String[]{"aa","ab"};
        expected = "[ababababab]";
        System.out.println(now());
        doRun(expected, board, words);
        System.out.println(now());
    }

    private static int c = 1;
    private static void doRun(String expected, char[][] board, String[] words){
        List<String> res = new N212HWordSearchII().findWords(board, words);
        Collections.sort(res);
        System.out.println("[" + (expected.equals(res.toString())) +"] "+(c++)+ ".result: "+ res + ".expected:"+expected);
    }


    //2.Tries + backtracking
    //Time complexity: \mathcal{O}(M(4\cdot3^{L-1}))O(M(4⋅3
    //L−1
    // )), where MM is the number of cells in the board and LL is the maximum length of words.
    //
    //It is tricky is calculate the exact number of steps that a backtracking algorithm would perform. We provide a upper bound of steps for the worst scenario for this problem. The algorithm loops over all the cells in the board, therefore we have MM as a factor in the complexity formula. It then boils down to the maximum number of steps we would need for each starting cell (i.e.4\cdot3^{L-1}4⋅3
    //L−1
    // ).
    //
    //Assume the maximum length of word is LL, starting from a cell, initially we would have at most 4 directions to explore. Assume each direction is valid (i.e. worst case), during the following exploration, we have at most 3 neighbor cells (excluding the cell where we come from) to explore. As a result, we would traverse at most 4\cdot3^{L-1}4⋅3
    //L−1
    //  cells during the backtracking exploration.
    //
    //One might wonder what the worst case scenario looks like. Well, here is an example. Imagine, each of the cells in the board contains the letter a, and the word dictionary contains a single word ['aaaa']. Voila. This is one of the worst scenarios that the algorithm would encounter.

    //Runtime: 21 ms, faster than 99.68% of Java online submissions for Word Search II.
    //Memory Usage: 42.4 MB, less than 93.08% of Java online submissions for Word Search II.
    //Time: O(W * K + M * N * 4 * (3 ^ (K-1))); Space: O(W * K + M * N * 4 * (3 ^ (K-1)))
    //let W be the number of words; K be the maximum length of words.
    //M * N : board
    public List<String> findWords(char[][] board, String[] words) {
        //build tree
        Trie root = new Trie();
        for (String word: words) root.insert(word);

        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children[board[i][j] - 'a'] != null)
                    helper(board, root, i, j, res);
            }
        }
        return res;
    }

    private void helper(char[][] board, Trie parent, int i, int j, List<String> res) {
        if (board[i][j] == '#' || parent.children[board[i][j] - 'a'] == null) return;

        Trie trie = parent.children[board[i][j] - 'a'];
        if (trie.word != null) {
            res.add(trie.word);
            trie.word = null;// no duplicated word
        }
        char c = board[i][j];
        board[i][j] = '#';

        //up down left right
        if (i - 1 >= 0 ) helper(board, trie, i - 1, j, res);
        if (i + 1 < board.length) helper(board, trie, i + 1, j, res);
        if (j - 1 >= 0) helper(board, trie, i, j - 1, res);
        if (j + 1 < board[0].length) helper(board, trie, i, j + 1, res);
        board[i][j] = c;

        //trim the tree
        if (trie.isEmpty())
            parent.children[board[i][j] - 'a'] = null;
    }

    class Trie {
        Trie[] children = new Trie[26];
        String word = null;

        public Trie() { }

        public void insert(String word) {
            Trie p = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (p.children[c] == null) p.children[c] = new Trie();
                p = p.children[c];
            }
            p.word = word;
        }

        public boolean isEmpty(){
            for (Trie child: children) if (child != null) return false;
            return true;
        }
    }

    //1.202107做的，太复杂了，没看。
    //Runtime: 360 ms, faster than 50.16% of Java online submissions for Word Search II.
    //Memory Usage: 45.2 MB, less than 36.13% of Java online submissions for Word Search II.
    // 先用words 构建树； 再按cell recursive
    private final WSIITreeNode leafNode = new WSIITreeNode(null, '|');
    public List<String> findWords_1(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        Set<Character> alpha = new HashSet<>();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                alpha.add(board[x][y]);
            }
        }
        //build tree
        WSIITreeNode root = new WSIITreeNode(null, '#');
        buildTree(root, words, alpha);

        //recursive cell
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {

                if (root.children.size() == 0) return res; //已没有字串

                char c = board[x][y];
                WSIITreeNode p = root;
                if (!p.children.containsKey(c)) continue;

                List<Character> resCharList = new ArrayList<>();//字串的中间结果
                Set<Integer> set = new HashSet<>(); //已匹配成功的
                Stack<WSIITreeNode> stack = new Stack<>(); //待匹配的邻居

                WSIITreeNode tmp = p.children.get(c);
                tmp.setXY(x,y);
                stack.push(tmp);

                while (!stack.isEmpty()) {
                    WSIITreeNode currNode = stack.pop();  //周围的cell可能有重复字母
                    p = currNode.parent.children.get(currNode.c);
                    p.copyXY(currNode);

                    if(set.contains(p.getKey())) {
                        set.remove(p.getKey());
                        resCharList.remove(resCharList.size()-1);
                        continue;
                    }

                    if (p.children.containsKey(leafNode.c)){
                        //done
                        StringBuilder sb = new StringBuilder();
                        for(char c1: resCharList) sb.append(c1);
                        sb.append(p.c);
                        res.add(sb.toString());

                        p.children.remove(leafNode.c);
                        if(p.children.size() == 0){
                            //剪枝
                            while (p.parent.c != '#' && p.parent.children.size() == 1) {
                                p = p.parent;
                                set.remove(p.getKey());
                                stack.pop();
                                resCharList.remove(resCharList.size()-1);
                            }

                            if (p.parent.c == '#') {
                                p.parent.children.remove(p.c);
                                break;
                            }

                            p.parent.children.remove(p.c);
                            p = p.parent;
                            //reset parent & remove brothers
                            set.remove(p.getKey());
                            resCharList.remove(resCharList.size()-1);
                            while(!stack.isEmpty() && stack.peek() != p) stack.pop();
                        }
                    }

                    stack.add(p);
                    set.add(p.getKey());
                    resCharList.add(p.c);
                    int i = p.x;
                    int j = p.y;
                    if (p.children.size() <= 0) continue;

                    if (j-1 >= 0){
                        char cCell = board[i][j-1];
                        if (!set.contains(p.getKey()-1) && p.children.containsKey(cCell)){
                            WSIITreeNode tmpNode = new WSIITreeNode(p, cCell);
                            tmpNode.setXY(i, j-1);
                            stack.add(tmpNode);
                        }
                    }

                    if (j+1 < board[0].length){
                        char cCell = board[i][j+1];
                        if (!set.contains(p.getKey()+1) && p.children.containsKey(cCell)){
                            WSIITreeNode tmpNode = new WSIITreeNode(p, cCell);
                            tmpNode.setXY(i, j+1);
                            stack.add(tmpNode);
                        }
                    }

                    if (i-1 >= 0){
                        char cCell = board[i-1][j];
                        if (!set.contains(p.getKey()-100) && p.children.containsKey(cCell)){
                            WSIITreeNode tmpNode = new WSIITreeNode(p, cCell);
                            tmpNode.setXY(i-1, j);
                            stack.add(tmpNode);
                        }
                    }

                    if (i+1 < board.length) {
                        char cCell = board[i+1][j];
                        if (!set.contains(p.getKey()+100) && p.children.containsKey(cCell)){
                            WSIITreeNode tmpNode = new WSIITreeNode(p, cCell);
                            tmpNode.setXY(i+1, j);
                            stack.add(tmpNode);
                        }
                    }
                }
            }
        }
        return res;
    }

    private void buildTree(WSIITreeNode root, String[] words, Set<Character> alpha){
        outer:
        for (String word: words) {
            WSIITreeNode p = root;
            for (char c : word.toCharArray()) {
                if(!alpha.contains(c)) continue outer;
            }
            for (char c : word.toCharArray()) {
                if (!p.children.containsKey(c))
                    p.children.put(c, new WSIITreeNode(p, c));
                p = p.children.get(c);
            }
            p.children.put(leafNode.c, leafNode);
        }
    }

    class WSIITreeNode {
        char c;
        private int x;
        private int y;
        private WSIITreeNode parent;
        private HashMap<Character, WSIITreeNode> children= new HashMap<>();
        WSIITreeNode(WSIITreeNode p, char c1) {
            parent = p;
            c = c1;
        }
        public void setXY(int x1, int y1){
            x = x1; y = y1;
        }
        public void copyXY(WSIITreeNode from){
            x = from.x; y = from.y;
        }
        public int getKey(){
            return x * 100 +y;
        }
    }

    ////////////////////////////////////////
    //Time limit exceeded
    public List<String> findWords1(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();

        Map<Character, List<WSIINode>> allLetters = new HashMap<>();
        WSIINode[][] boardNodes = new WSIINode[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                WSIINode node = boardNodes[i][j] != null ? boardNodes[i][j] : new WSIINode(board[i][j]);
                boardNodes[i][j] = node;

                if (j-1 >= 0) {
                    List<WSIINode> tmpNodeList = node.neighbours.getOrDefault(board[i][j-1], new ArrayList<>());
                    node.neighbours.put(board[i][j-1], tmpNodeList);

                    boardNodes[i][j-1] = boardNodes[i][j-1] != null ? boardNodes[i][j-1] : new WSIINode(board[i][j-1]);
                    tmpNodeList.add(boardNodes[i][j-1]);
                }

                if (j+1 < board[0].length) {
                    List<WSIINode> tmpNodeList = node.neighbours.getOrDefault(board[i][j+1], new ArrayList<>());
                    node.neighbours.put(board[i][j+1],tmpNodeList);

                    boardNodes[i][j+1] = boardNodes[i][j+1] == null ? new WSIINode(board[i][j+1]): boardNodes[i][j+1];
                    tmpNodeList.add(boardNodes[i][j+1]);
                }
                if (i-1 >= 0) {
                    List<WSIINode> tmpNodeList = node.neighbours.getOrDefault(board[i-1][j], new ArrayList<>());
                    node.neighbours.put(board[i-1][j],tmpNodeList);

                    boardNodes[i-1][j] = boardNodes[i-1][j] == null ? new WSIINode(board[i-1][j]): boardNodes[i-1][j];
                    tmpNodeList.add(boardNodes[i-1][j]);
                }
                if (i+1 < board.length) {
                    List<WSIINode> tmpNodeList = node.neighbours.getOrDefault(board[i+1][j], new ArrayList<>());
                    node.neighbours.put(board[i+1][j],tmpNodeList);

                    boardNodes[i+1][j] = boardNodes[i+1][j] != null ? boardNodes[i+1][j]: new WSIINode(board[i+1][j]);
                    tmpNodeList.add(boardNodes[i+1][j]);
                }

                List<WSIINode> nodes = allLetters.getOrDefault(board[i][j], new ArrayList<>());
                allLetters.put(board[i][j], nodes);
                nodes.add(node);
            }
        }
        int M = board.length * board[0].length;
        for (String word : words) {
            if (word.length() > M) continue; //the word is too long
            System.out.println("word:"+now() + word);
            int k = 0;
            // init stack
            Stack<WSIINode> stack = new Stack<>();
            List<WSIINode> charNodeList = allLetters.get(word.charAt(k++));
            if (charNodeList != null && !charNodeList.isEmpty()) stack.addAll(charNodeList);
            Set<WSIINode> set = new HashSet<>();

            while(!stack.isEmpty()){
                WSIINode node = stack.peek();

                if (set.contains(node)){
                    set.remove(node);
                    stack.pop();
                    k--;
                    continue;
                }

                if (k == word.length()){
                    res.add(word);
                    break;
                }
                set.add(node);

                char nextChar = word.charAt(k++);
                if (!allLetters.containsKey(nextChar)) break;

                List<WSIINode> nbKeys = node.neighbours.getOrDefault(nextChar, new ArrayList<>());
                for (WSIINode neighbour: nbKeys){
                    //WSIINode neighbour = boardNodes[nbKey[0]][nbKey[1]];
                    if (!set.contains(neighbour)) stack.add(neighbour);
                }
            }//End while stack

        }//End outer
        return res;
    }

    class WSIINode {
        char c;
        private HashMap<Character, List<WSIINode>> neighbours= new HashMap<>();
        WSIINode(char c1) {
            c = c1;
        }
    }

    //////////////////////////////
//
//    //Time limit exceeded
//    private Map<Integer, Set<Integer>> cellNeighbors = new HashMap<>();
//    public List<String> findWords_1(char[][] board, String[] words) {
//        List<String> res = new ArrayList<>();
//        Map<String, WSTree> memo = new HashMap<>();
//
//        //把board上的字母放入hashmap里. value 放坐标组. （同一字母，可能出现在多处）
//        Map<Character, List<int[]>> allLetters = new HashMap<>();
//
//        for (int i = 0; i < board.length; i++){
//            for (int j = 0; j < board[0].length; j++){
//                List<int[]> pos = allLetters.getOrDefault(board[i][j], new ArrayList<>());
//                int[] xy = new int[]{i,j};
//                pos.add(xy);
//                allLetters.put(board[i][j], pos);
//
//                Set<Integer> setNeighbor = new HashSet<>();
//                int keyNeighbors = i*100+j;
//                cellNeighbors.put(keyNeighbors, setNeighbor);
//                if (j + 1 < board[0].length) setNeighbor.add(keyNeighbors+1);
//                if (j - 1 >= 0) setNeighbor.add(keyNeighbors-1);
//                if (i + 1 < board.length) setNeighbor.add(keyNeighbors+100);
//                if (i - 1 >= 0) setNeighbor.add(keyNeighbors-100);
//            }
//        }
//
//        //遍历单词表
//        int M = board.length * board[0].length;
//
//        gotoNextWord:
//        for (String word : words){
//            if (word.length() > M) continue; //the word is too long
//
//            WSTree root = initTreeViaMemo(memo,  word);
//            Set<int[]> set = new HashSet<>();
//            if (root == null) {
//                //using the first char to build tree;
//                root = new WSTree('z', new int[]{-1,-1}, 0);
//                root.setChildren(word.charAt(0), allLetters.get(word.charAt(0)));
//
//            }else{
//                WSTree tmp = root;
//                while(tmp != null) {
//                    tmp.setNeedReset();
//                    set.add(tmp.position);
//                    tmp = tmp.parent;
//                }
//            }
//
//            WSTree pointer = root;
//            //traverse one word vis tree
//            while (pointer != null && pointer.nextIdx < word.length()) {
//
//                if (pointer.getActiveChild() == 0) {
//                    if (pointer.parent != null) {
//                        pointer.disable();
//                        set.remove(pointer.position);
//                    }
//                    pointer = pointer.parent;
//                    continue;
//                }
//
//                //down to children
//                pointer = pointer.getFirstChild();
//                set.add(pointer.position);
//
//                if (pointer.nextIdx == word.length()) {
//                    //found!
//                    res.add(word);
//                    memo.put(word.substring(0, pointer.nextIdx), pointer.parent);
//                    pointer = null;
//                }else{
//                    char c = word.charAt(pointer.nextIdx);
//                    if (!allLetters.containsKey(c)) continue gotoNextWord;
//
//                    List<int[]> nextCharPos = allLetters.getOrDefault(c, new ArrayList<>());
//
//                    //only add unused neighbors.
//                    pointer.clearChild();
//                    for (int[] p : nextCharPos) {
//                        if (set.contains(p)) continue;
//
//                        if (isBeside(pointer.position, p)) pointer.addChild(c, p);
//                    }
//                }
//
//            }//End while.)
//        }// end for words. (by word)
//        return res;
//    }

//
//    private WSTree initTreeViaMemo(Map<String, WSTree> memo, String word){
//        if(memo.size() < 1) return null;
//        Set<String> keySet = memo.keySet();
//
//        String mKey = null;
//        int idx = 0;
//        for (String key : keySet){
//            if(key.length() < word.length()){
//                if (word.indexOf(key,0) != 0) continue;
//                mKey = key;
//                idx = key.length();
//            }else{
//                for(int i = word.length()-1; i >= 2 ; i--) {
//                    String sub = word.substring(0, i);
//                    if (key.indexOf(sub, 0) != 0) continue;
//
//                    mKey = key;
//                    idx = i;
//                    break;
//                }
//            }
//
//            if (mKey == null) continue;
//
//            WSTree root = memo.get(mKey);
//            while (root != null && root.nextIdx >= idx) root = root.parent;
//            return root;
//        }
//        return null;
//    }
////
//////    Map<String, Boolean> sideMemo = new HashMap<>();
//    private boolean isBeside(int[] a, int[] b) {
//        if (cellNeighbors.get(a[0] * 100 + a[1]).contains(b[0] * 100 + b[1])) return true;
//        return false;
//    }


//
//    class WSTree {
//        char c;
//        int[] position;
//        int nextIdx;
//        WSTree parent;
//        boolean active = true;
//        boolean needReset = false;
//        private List<WSTree> children = new ArrayList<>();
//
//        WSTree(char c1, int[] p, int i) {
//            c = c1;
//            position = p;
//            nextIdx = i;
//        }
//
//        public void disable() {
//            active = false;
//        }
//
//        public void setNeedReset(){
//            //active = true;
//            for (WSTree node : children) {
//                node.needReset = true;
//                node.active = true;
//            }
//        }
//
//        public int getActiveChild() {
//            if(needReset) {
//                for (WSTree node : children) {
//                    node.needReset = true;
//                    node.active = true;
//                }
//                needReset = false;
//            }
//
//            if (children.isEmpty()) return 0;
//            int k = 0;
//            for (WSTree node : children)
//                if (node.active) k++;
//            return k;
//        }
//
//        public WSTree getFirstChild() {
//            if (children.isEmpty()) return null;
//            for (WSTree node : children) {
//                if (node.active) return node;
//            }
//            return null;
//        }
//
//        public void setChildren(char c, List<int[]> childPos) {
//            children.clear();
//            if (childPos == null) return;
//            for (int[] p : childPos) {
//                WSTree child = new WSTree(c, p, this.nextIdx + 1);
//                child.parent = this;
//                children.add(child);
//            }
//        }
//
//        public void addChild(char c, int[] p) {
//            WSTree child = new WSTree(c, p, this.nextIdx + 1);
//            child.parent = this;
//            children.add(child);
//        }
//
//        public void clearChild() {
//            children.clear();
//        }
//
//    }
        ////////////////////////////////////////

//    private void printSet(Set<int[]> set){
//        System.out.println("[set]==============Begin");
//        for(int[] s: set){
//            System.out.print("["+s[0]+","+s[1]+"]");
//        }
//        System.out.println("");
//        System.out.println("[set]==============End");
//    }
//
//    private void printNode(WSTree node){
//        WSTree p = node;
//        System.out.println(p.c+"("+p.position[0]+","+p.position[1]+")");
//    }
//    private void printTree(WSTree node){
//        System.out.println("==============Begin");
//        WSTree p = node;
//        while (p.parent != null) p = p.parent;
//
//        while (p != null){
//            System.out.println(p.c+"("+p.position[0]+","+p.position[1]+") .childcount: " + p.getActiveChild());
//            p = p.getFirstChild();
//        }
//        System.out.println("==============END");
//    }

}






