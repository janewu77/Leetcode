package Other;

import Contest.C0806;

import java.io.IOException;
import java.util.*;

import static java.time.LocalTime.now;

public class DecisionTree {



    static public void main(String... args) throws IOException {
        System.out.println(now());
        System.out.println("==================");
        //doRun(0, new int[]{1,1,2,2});

        String[] species;
        species = new String[]{"versicolor","versicolor","setosa","virginica","virginica","versicolor","versicolor","setosa","versicolor","versicolor"};
        doRun(0.24902, new double[]{1,2,3,4,5,6,7,8,9,10}, species);

        System.out.println(now());
        System.out.println("==================");
    }


    static private void doRun(double expect, double[] petal_length, String[] species) {
        double res = new DecisionTree().calculateMaxInfoGain(petal_length, species);
//        double res = new C0806().calculateEntropy(input);
        System.out.println("["+(expect == res)+"]expect:" + expect + " res:" + res);
    }


    //https://leetcode.com/explore/learn/card/decision-tree/285/implementation/2651/
    public double calculateMaxInfoGain(double[] petal_length, String[] species) {
        double h = calculateEntropy(Arrays.asList(species));

        double res = 0d;
        for (int i = 0; i < species.length; i++) {
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            for (int j = 0; j < species.length; j++) {
                if (petal_length[j] <= petal_length[i])
                    list1.add(species[j]);
                else list2.add(species[j]);
            }
            double tmp = h - calculateEntropy(list1) * list1.size() / species.length;
            tmp = tmp - calculateEntropy(list2) * list2.size() / species.length;
            res = Math.max(res, tmp);
        }
        return res;
    }
    private double calculateEntropy(List<String> input){
        Map<String, Integer> map = new HashMap();

        for(int i = 0; i< input.size(); i++){
            int c = map.getOrDefault(input.get(i), 0) + 1;
            map.put(input.get(i), c);
        }

        double res = 0;
        for(String x : map.keySet()){
            int c = map.get(x);
            double p = 1d * c / input.size();
            res += - p * (Math.log(p) / Math.log(2));
        }
        return res;
    }


    //https://leetcode.com/explore/learn/card/decision-tree/285/implementation/2650/
    public double calculateEntropy(int[] input) {
        Map<Integer, Integer> map = new HashMap();

        for(int i = 0; i< input.length; i++){
            int c = map.getOrDefault(input[i], 0) + 1;
            map.put(input[i], c);
        }

        double res = 0;
        for(int x : map.keySet()){
            int c = map.get(x);
            double p = 1d * c / input.length;
            res += - p * (Math.log(p) / Math.log(2));
        }
        return res;
    }
}
