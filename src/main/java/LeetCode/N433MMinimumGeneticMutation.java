package LeetCode;


import java.util.*;

import static java.time.LocalTime.now;

public class N433MMinimumGeneticMutation {


    public static void main(String[] args) {

        Integer x = 3;
        Integer y = null;
        ;
        System.out.println(Integer.compareUnsigned(x,3) == 0);
        System.out.println(Integer.compareUnsigned(x,3) == 0 || Integer.compareUnsigned(y,0) == 0);
        System.out.println(now());
        doRun(-1, "AACCGGTT", "AACCGCAA", new String[]{"AACCGGTT","AACCGGAA"});
        doRun(3, "AACCGGTT", "AACCGCAA", new String[]{"AACCGGTA","AACCGGAA","AACCGCAA"});

        doRun(4, "AGCAAAAA", "GACAAAAA", new String[]{"AGTAAAAA","GGTAAAAA","GATAAAAA","GACAAAAA"});

        doRun(1, "AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"});

        doRun(2, "AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"});
        doRun(3, "AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC","AAACCCCC","AACCCCCC"});

        System.out.println(now());
        System.out.println("==================");
    }
    static private void doRun(int expect, String start, String end, String[] bank) {
        int res = new N433MMinimumGeneticMutation().minMutation(start, end, bank);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }

    //2.BFS
    //Runtime: 1 ms, faster than 92.51% of Java online submissions for Minimum Genetic Mutation.
    //Memory Usage: 41.8 MB, less than 58.29% of Java online submissions for Minimum Genetic Mutation.
    public int minMutation(String start, String end, String[] bank) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        Set<String> seen = new HashSet<>();
        int layer = 0;
        while(!queue.isEmpty()){
            int queueSize = queue.size();
            layer++;
            for (int i = 0; i < queueSize; i++) {
                String node = queue.poll();
                seen.add(node);
                for (String gene : bank){
                    if(!seen.contains(gene) && hasOneMutation2(node,gene)) {
                        if (gene.equals(end)) return layer;
                        queue.add(gene);
                    }
                }
            }
        }
        return -1;
    }

    private boolean hasOneMutation2(String start, String end){
        int diff = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != end.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return true;
    }

    //1.backtracking
    //Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Genetic Mutation.
    //Memory Usage: 39.9 MB, less than 97.50% of Java online submissions for Minimum Genetic Mutation.
    //Time: O(N * N); Space: O(N)
    //let N be the number of genes in bank;
    public int minMutation_1(String start, String end, String[] bank) {

        boolean hasGene = false;
        for (int i = 0; i < bank.length; i++) {
            if (bank[i].equals(end)) hasGene = true;
        }
        if (!hasGene) return -1;

        Set<String> seen = new HashSet<>();
        seen.add(end);
        int res = helper_dfs_backtracking(start, end, bank, new HashSet<>());
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int helper_dfs_backtracking(String start, String end, String[] bank, Set seen){
        if (hasOneMutation(start, end)) return seen.size() + 1;
        int res = Integer.MAX_VALUE;
        for (String gene : bank) {
            if (!seen.contains(gene) && hasOneMutation(gene, end)) {
                seen.add(gene);
                res = Math.min(res, helper_dfs_backtracking(start, gene, bank, seen));
                seen.remove(gene);
            }
        }
        return res;
    }

    //Time: O(8); Space:O(1)
    private boolean hasOneMutation(String start, String end){
        int diff = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != end.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return true;
    }




}
