package LeetCode;


import javafx.util.Pair;
import utils.comm;

import java.util.*;

import static java.time.LocalTime.now;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the
 * departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must
 * begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 * Example 2:
 *
 *
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 *
 * Constraints:
 *
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi and toi consist of uppercase English letters.
 * fromi != toi
 */

/**
 * H - [time: 120+
 */
public class N332HReconstructItinerary {

    public static void main(String[] args) {

        System.out.println(now());
        int[] data;
        int[][] data2;
        List<List<String>> tickets;


        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","KUL"));
        tickets.add(Arrays.asList("JFK","NRT"));
        tickets.add(Arrays.asList("NRT","JFK"));
        doRun("[JFK, NRT, JFK, KUL]", tickets);


        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("EZE","TIA"));
        tickets.add(Arrays.asList("EZE","AXA"));
        tickets.add(Arrays.asList("AUA","EZE"));

        tickets.add(Arrays.asList("EZE","JFK"));
        tickets.add(Arrays.asList("JFK","ANU"));
        tickets.add(Arrays.asList("JFK","ANU"));
        tickets.add(Arrays.asList("AXA","TIA"));
        tickets.add(Arrays.asList("JFK","AUA"));
        tickets.add(Arrays.asList("TIA","JFK"));
        tickets.add(Arrays.asList("ANU","EZE"));
        tickets.add(Arrays.asList("ANU","EZE"));
        tickets.add(Arrays.asList("TIA","AUA"));
        doRun("[JFK, ANU, EZE, AXA, TIA, AUA, EZE, JFK, ANU, EZE, TIA, JFK, AUA]", tickets);


        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));
        doRun("[JFK, MUC, LHR, SFO, SJC]", tickets);


        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","SFO"));
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("SFO","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        tickets.add(Arrays.asList("ATL","SFO"));
        doRun("[JFK, ATL, JFK, SFO, ATL, SFO]", tickets);

        tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK","ATL"));
        tickets.add(Arrays.asList("ATL","JFK"));
        doRun("[JFK, ATL, JFK]", tickets);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(String expect, List<List<String>> tickets) {
        List<String> res = new N332HReconstructItinerary()
                .findItinerary(tickets);
        System.out.println("["+(expect.equals(res.toString()))+"]expect:" + expect + " res:" + res);
    }


    //Runtime: 10 ms, faster than 72.31% of Java online submissions for Reconstruct Itinerary.
    //Memory Usage: 42.8 MB, less than 94.31% of Java online submissions for Reconstruct Itinerary.
    //Euler Circuit | Hierholzer
    //Iteration
    //Time: O(T * log T); Space: O(N + T)
    //T is the number of tickets
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, LinkedList<String>> graph = new HashMap<>();

        //Time: O(T);
        for (List<String> ticket: tickets)
            graph.computeIfAbsent(ticket.get(0), k-> new LinkedList<>()).add(ticket.get(1));

        //Time: O(T * log T);
        //Time: O(V * log T/V);
        for (String key : graph.keySet())
            Collections.sort(graph.get(key));

        List<String> path = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        //Time: O(T);
        while (!stack.isEmpty() ){
            String source = stack.peek();
            if (graph.containsKey(source) && !graph.get(source).isEmpty())
                stack.push(graph.get(source).removeFirst());
            else
                path.add(0, stack.pop());
        }
        return path;
    }

    //https://leetcode.com/problems/reconstruct-itinerary/discuss/78768/Short-Ruby-Python-Java-C%2B%2B
    //Runtime: 8 ms, faster than 85.60% of Java online submissions for Reconstruct Itinerary.
    //Memory Usage: 42.9 MB, less than 94.23% of Java online submissions for Reconstruct Itinerary.
    //Euler Circuit | Hierholzer
    //Iteration
    //Time: O(T); Space: O(N + T)
    //T is the number of tickets
    public List<String> findItinerary_3(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        //Time: O(T * log T);
        for (List<String> ticket: tickets) {
            graph.computeIfAbsent(ticket.get(0), k-> new PriorityQueue<>()).add(ticket.get(1));
        }

        List<String> path = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        //Time: O(T * log T);
        while (!stack.isEmpty() ){
            String source = stack.peek();
            if (graph.containsKey(source) && !graph.get(source).isEmpty())
                stack.push(graph.get(source).poll());
            else
                path.add(0, stack.pop());
        }
        return path;
    }

    //Runtime: 6 ms, faster than 95.03% of Java online submissions for Reconstruct Itinerary.
    //Memory Usage: 42.7 MB, less than 96.52% of Java online submissions for Reconstruct Itinerary.
    //Euler Circuit | Hierholzer
    //recursion
    //Time: O(T); Space: O(N + T)
    //T is the number of tickets
    public List<String> findItinerary_2(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        //Time: O(T * log T);
        for (List<String> ticket: tickets) {
            graph.computeIfAbsent(ticket.get(0), k-> new PriorityQueue<>()).add(ticket.get(1));
        }

        List<String> path = new LinkedList<>();
        helper_dfs(graph, "JFK", path);
        return path;
    }

    //Time: O(T * log T);
    public void helper_dfs(Map<String, PriorityQueue<String>> graph , String source, List<String> path) {
        while (graph.containsKey(source) && !graph.get(source).isEmpty())
            helper_dfs(graph, graph.get(source).poll(), path);
        path.add(0, source);
    }

    //Runtime: 12 ms, faster than 56.83% of Java online submissions for Reconstruct Itinerary.
    //Memory Usage: 42.7 MB, less than 98.41% of Java online submissions for Reconstruct Itinerary.
    //backtracking
    //Time: O(T + N * T * logT + T ^ d); Space: O(N + T + logT + T)
    //Time: O(T ^ d); Space: O(N + T)
    public List<String> findItinerary_1(List<List<String>> tickets) {

        //Space: O(N + T)
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, int[]> ticketsCounter = new HashMap<>();

        //Time: O(T);
        for (List<String> ticket: tickets) {
            List<String> destinations = graph.getOrDefault(ticket.get(0), new ArrayList<>());
            graph.put(ticket.get(0), destinations);
            destinations.add(ticket.get(1));
        }

        //Time: O(N * T * logT); Space: O(logT)
        for (String k : graph.keySet()) {
            List<String> ticketList = graph.get(k);
            Collections.sort(ticketList);
            ticketsCounter.put(k, new int[ticketList.size()]);
        }

        //Time: O(T ^ d); Space: O(T)
        return helper_backtracking(graph, "JFK",  ticketsCounter, new ArrayList<>(), tickets.size());
    }

    //Time: worst case: O(T ^ d); Space: O(T)
    private List<String> helper_backtracking(Map<String, List<String>> graph, String source,
                                             Map<String, int[]> ticketsCounter,
                                             List<String> path, int ticketCounts){

        if (ticketCounts == 0) {
            path.add(source);
            return path;
        }
        if (!graph.containsKey(source)) return path;

        List<String> destinations = graph.get(source);
        for (int i = 0; i < destinations.size(); i++){

            if (ticketsCounter.get(source)[i] == 1) continue;

            ticketsCounter.get(source)[i] = 1;
            List<String> nextPath = helper_backtracking(graph, destinations.get(i), ticketsCounter, new ArrayList<>(), ticketCounts - 1);
            if (nextPath.size() > 0) {
                path.add(source);
                path.addAll(nextPath);
                return path;
            }
            ticketsCounter.get(source)[i] = 0;
        }
        return path;
    }


}
