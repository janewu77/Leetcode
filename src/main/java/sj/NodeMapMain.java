package sj;

import java.util.Arrays;

public class NodeMapMain {

    public static void main(String[] args) {
        int nodeCount=4;
        int[][] edges = {{0,1}, {0,2},{3,0}, {2,1}};
        System.out.println("************");
        System.out.println("result[3,0,2,1]:");
        int[] result = (new NodeMap()).findOrder(nodeCount,edges);
        Arrays.stream(result).forEach(System.out::print);
        System.out.println("");

        //test input
        testInput();

        //test other
        testOther();
    }

    static void testOther(){
        NodeMap nodeMap = new NodeMap();

        System.out.println("************");
        System.out.println("*单边【0123】");
        int[][] edges = {{0,1}, {2,3},{1,2}};
        Arrays.stream( nodeMap.findOrder(4,edges)).forEach(System.out::print);
        System.out.println("");

        System.out.println("************");
        System.out.println("*多入口【0123】");
        int[][] edges2 = {{0,2}, {1,2},{1,3}};
        Arrays.stream( nodeMap.findOrder(4,edges2)).forEach(System.out::print);
        System.out.println("");

        System.out.println("************");
        System.out.println("*孤边【012345】");
        int[][] edges3 = {{4,5},{0,2}, {1,2},{1,3}};
        Arrays.stream( nodeMap.findOrder(6,edges3)).forEach(System.out::print);
        System.out.println("");

//        System.out.println("************");
//        System.out.println("*圈【failed】");
//        int[][] edges4 =  {{0,1}, {1,2},{2,3},{3,1}};
//        Arrays.stream( nodeMap.findOrder(4,edges4)).forEach(System.out::print);
//        System.out.println("");

    }

    static void testInput(){
        int nodeCount=4;
        int[][] edges = {{0,1}, {0,2},{3,0}, {2,1}};
        NodeMap nodeMap = new NodeMap();

        //点为零
        System.out.println("************");
        System.out.println("*result-2:【】");
        Arrays.stream( nodeMap.findOrder(0,edges)).forEach(System.out::print);
        System.out.println("");

        //没有边
        System.out.println("************");
        System.out.println("*result-3:【-1-1-1】");
        Arrays.stream( nodeMap.findOrder(3,null)).forEach(System.out::print);
        System.out.println("");

        //错边：边有多个点
        System.out.println("************");
        System.out.println("*result-4:【3021】");
        int[][] edges2 = {{0,1}, {0,2},{3,0}, {2,1},{8,1,3}};
        Arrays.stream( nodeMap.findOrder(nodeCount,edges2)).forEach(System.out::print);
        System.out.println("");
    }

}

