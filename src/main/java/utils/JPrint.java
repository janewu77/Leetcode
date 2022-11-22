package utils;


import java.util.List;

public class JPrint {

    static public void print(int[] nums,String title){
        System.out.print(title+":[");
        //Arrays.stream(nums).forEach(System.out::print);
        for(int i = 0; i< nums.length;i++){
            System.out.print(nums[i]);
            if( i != nums.length-1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    static public void print(List<List<Integer>>  results){
        // = (new N15M3Sum()).threeSum(new int[]{});

        System.out.println("reslut:"+results.size());
        System.out.print("[");
        for (List<Integer> result : results){
            System.out.print(result.toString() );
            if (results.get(results.size() - 1) != result)
                System.out.print(",");
        }
        System.out.println("]");
    }


}
