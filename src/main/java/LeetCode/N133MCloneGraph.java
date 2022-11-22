package LeetCode;


import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed).
 * For example, the first node with val == 1, the second node with val == 2, and so on.
 * The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph.
 * Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy
 * of the given node as a reference to the cloned graph.
 *
 *
 *
 * Example 1:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 *
 *
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 *
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 *
 *
 * Constraints:
 *
 * The number of nodes in the graph is in the range [0, 100].
 * 1 <= Node.val <= 100
 * Node.val is unique for each node.
 * There are no repeated edges and no self-loops in the graph.
 * The Graph is connected and all nodes can be visited starting from the given node.
 */

/**
 * M - [time: 30-]
 *
 */
public class N133MCloneGraph {

    public static void main(String[] args){
        N133MCloneGraph mClass = new N133MCloneGraph();
        Node graph = mClass.buildGraph();

        Node graph2 = mClass.cloneGraph(graph);
        System.out.println(graph2.val);

    }

    public Node buildGraph(){
//        N133MCloneGraph mClass = new N133MCloneGraph();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        return node1;
    }

    //Runtime: 26 ms, faster than 91.07% of Java online submissions for Clone Graph.
    //Memory Usage: 42.5 MB, less than 88.23% of Java online submissions for Clone Graph.
    //BFS: HashMap + Queue
    //Time: O(V + E); Space:O(V)
    public Node cloneGraph(Node node) {
        if (node == null) return node;

        //Space:O(V)
        Map<Node, Node> old2New = new HashMap<>();

        //Space: worst case:O(V)
        Queue<Node> q = new LinkedList<>();
        old2New.put(node, new Node(node.val));
        q.add(node);

        while(!q.isEmpty()){
            Node n = q.poll();
            for (Node neighbor: n.neighbors){
                if (!old2New.containsKey(neighbor)) {
                    old2New.put(neighbor, new Node(neighbor.val));
                    q.add(neighbor);
                }
                old2New.get(n).neighbors.add(old2New.get(neighbor));
            }
        }
        return old2New.get(node);
    }

    //48ms; 44mb
    //新建的时候，附在原结点上；再修正指向。
    //写起来过于复杂。
    public Node cloneGraph2(Node node) {
        if (node == null) return node;

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()){
            Node n = q.poll();
            if (!isHandled(n)){
                for (Node neighbor: n.neighbors) if (!isHandled(neighbor)) q.add(neighbor);
                n.neighbors.add(0, new Node(n.val+1000));
            }
        }

        q.add(node);
        while(!q.isEmpty()){
            Node n = q.poll();
            if (n.neighbors.get(0).neighbors.size() + 1 != n.neighbors.size()){
                Node myCopy = n.neighbors.get(0);
                for(int i = 1; i<n.neighbors.size();i++){
                    Node neighborI = n.neighbors.get(i);
                    myCopy.neighbors.add(neighborI.neighbors.get(0));
                    if (neighborI.neighbors.get(0).neighbors.size() + 1 != neighborI.neighbors.size()) q.add(neighborI);
                }
            }
        }

        Node newGraph = node.neighbors.get(0);
        q.add(node);
        while(!q.isEmpty()){
            Node n = q.poll();
            if(isHandled(n)) {
                n.neighbors.get(0).val -= 1000;
                n.neighbors.remove(0);
                for (Node neighbor: n.neighbors) if (isHandled(neighbor)) q.add(neighbor);
            }
        }
        return newGraph;
    }


    private boolean isHandled(Node node){
        if (node.neighbors.size() <= 0) return false;
        Node firstNeighbor = node.neighbors.get(0);
        return firstNeighbor.val > 100;
    }



    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
