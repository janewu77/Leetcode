package sj;

import java.util.*;

/**
 *
 * @author: Jane W.
 * @date: 2021/1/15
 * @desc:
 *
 * 现在有个工作流系统，包含了 nodes 和 edeges（点和边），运行这个工作流时需要按照顺序
 * 来执行，举例：
 *
 *
 * 上图中的执行顺序就是：输入->文本拆分->输出。现在简化输入的内容，每个节点只有节点
 * id，边只有两个 id，代表从 A->B 有一个先后执行的关系。请完成以下方法：
 * class NodeMap {
 * public int[] findOrder(int nodeCount, int[][] edges) { } }
 * 其中 nodeCount 代表 node 数量（编号从 0 至 N-1），edges 代表了所有的边，输出为 node 执 行的顺序。
 *
 * 样例：
 * 输入参数是：nodeCount=4, edges=[[0,1],[0,2],[3,0],[2,1]]
 * 输出是：[3,0,2,1]
 *
 * */

public class NodeMap {

    private Queue<Integer> queueResult = new LinkedList<>();

    public int[] findOrder(int nodeCount, int[][] edges) {

        if(nodeCount <= 0 || edges == null || edges.length <= 0)
            return new int[]{};

        //构建图
        Node[] nodeList = mergeEdges(nodeCount, edges);

        //遍历 Node list， 产生结果
        for (Node node : nodeList) {
            if(node != null && !node.isVisited) {
                visitNode(node);
//                visitNodeViaStack(node); //用栈处理
            }
        }

        //返回结果
        int[] result = new int[nodeCount];
        Arrays.fill(result, -1); //（编号从 0 至 N-1）

        int idx = 0;
        while(queueResult.size()>0){
            result[idx++] = queueResult.poll();
        }
        return result;
    }

    private Node[] mergeEdges(int nodeCount,int[][] edges){
        //构建图
        //按题意【其中 nodeCount 代表 node 数量（编号从 0 至 N-1）】的假设，采用数组存放nodes.
        //若node稀疏，则考虑其他数据结构
        Node[] nodeList = new Node[nodeCount];

        for (int[] edge : edges) {
            //Arrays.stream( edge).forEach(System.out::println);
            if (!isValid(edge, nodeCount)) {
                continue;//跳过错边
            }

            int from = edge[0];
            Node fromNode = nodeList[from];
            if (fromNode == null) {
                fromNode = new Node(from);
                nodeList[from] = fromNode;
            }

            int to = edge[1];
            Node toNode = nodeList[to];
            if (toNode == null) {
                toNode = new Node(to);
                nodeList[to] = toNode;
            }

            //edge
            //未处理重复边
            toNode.addParent(fromNode);

        }//End for

        return nodeList;
    }

    //访问指定节点及其上层节点(递归）
    //当成圈时，会出现死循环（可改栈）
    private void visitNode(Node workNode){
        for (Node pNode : workNode.parents) {
            //parent是否visited
            if(!pNode.isVisited) {
                visitNode(pNode);
            }
        }
        workNode.isVisited = true;
        queueResult.offer(workNode.value);
    }


    private Stack<Node> statckNodeList = new Stack<>();
    private void visitNodeViaStack(Node node){

        statckNodeList.push(node);
        Node workNode = statckNodeList.peek();

        while(workNode != null){

            //处理parent
            boolean isAllVisited = true;
            for (Node pNode : workNode.parents) {
                //parent是否visited
                if(!pNode.isVisited) {

                    //检查node 是否在stack里，若存在，则意味着出现Loop
                    if(statckNodeList.contains(pNode)){
                        pNode.isVisited = true;//强行破圈
                        System.out.println("("+pNode.value+")error!!!error!!!error!!!error!!!error!!!error!!!");
                    }else {
                        statckNodeList.push(pNode);
                        isAllVisited = false;
                    }
                }
            }//End for

            if(isAllVisited){
                workNode = statckNodeList.pop();
                workNode.isVisited = true;
                queueResult.offer(workNode.value);
            }

            workNode = null;
            if(!statckNodeList.isEmpty()){
                workNode = statckNodeList.peek();
            }
        }//end while

    }

    //是否是合法边
    private static boolean isValid(int[] edge,int Max){
        if(edge.length != 2)
            return false;

        for (int pv : edge) {
            if(pv<0 || pv>=Max) return false;
        }
        return true;
    }

    private class Node {

        List<Node> parents = new ArrayList<>();

        int value = -1;
        boolean isVisited = false;

        public Node(int v){
            value = v;
        }

        public void addParent(Node parent){
            parents.add(parent);
        }
    }
}
