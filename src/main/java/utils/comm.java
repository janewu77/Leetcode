package utils;

import java.util.*;
import java.util.stream.Collectors;

public class comm {

    static public void print(int expect, int res){
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }
    static public void printListListString(String expect, List<List<String>> res1){
        String res = "[" + res1.stream().map(x->x.toString()).collect(Collectors.joining(",")) + "]";
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    static public void printListListInteger(String expect, List<List<Integer>> res1){
        String res = "[" + res1.stream().map(x->x.toString()).collect(Collectors.joining(",")) + "]";
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }


    static public String toString(List<List<Integer>>  results){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(results.stream().map(x->x.toString()).collect(Collectors.joining(",")));
        sb.append("]");
        return sb.toString();
    }

    static public List<String> strArr2List(String[] list){
        List<String> res = new ArrayList<>();
        for (String str: list) res.add(str);
        return res;
    }


    static Set<Integer> arr2set(int[] nums){
//        Integer[] arrInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
//        Set<Integer> set = Arrays.stream(arrInteger).collect(Collectors.toSet());
        Set<Integer> set = new HashSet<>();
        for(int n: nums) set.add(n);
        return set;
    }

}
