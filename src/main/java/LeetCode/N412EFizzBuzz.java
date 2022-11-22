package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Given an integer n, return a string array answer (1-indexed) where:
 *
 * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * answer[i] == "Fizz" if i is divisible by 3.
 * answer[i] == "Buzz" if i is divisible by 5.
 * answer[i] == i (as a string) if none of the above conditions are true.
 *
 *
 * Example 1:
 * Input: n = 3
 * Output: ["1","2","Fizz"]
 *
 * Example 2:
 * Input: n = 5
 * Output: ["1","2","Fizz","4","Buzz"]
 *
 * Example 3:
 * Input: n = 15
 * Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *
 *
 * Constraints:
 * 1 <= n <= 10的4次方
 */

/**
 * 基本概念： StringBuilder、Stream的应用
 */
public class N412EFizzBuzz {

    public static void main(String[] args) {
        List<String> result = new N412EFizzBuzz().fizzBuzz_steam(55);
        for (String s : result){
           System.out.println(s);
        }
    }

    public List<String> fizzBuzz_steam(int n) {
        return IntStream.range(1, n + 1).mapToObj(
                i->{
                    if (i % 3 == 0 && i % 5 == 0) return "FizzBuzz";
                    if (i % 3 == 0) return "Fizz";
                    if (i % 5 == 0) return "Buzz";
                    return String.valueOf(i);
                }
        ).collect(Collectors.toList());
    }

    public List<String> fizzBuzz_steam2(int n) {
        return Stream.iterate(1, i-> i+1).limit(n).map(
                i->{
                    if (i % 3 == 0 && i % 5 == 0) return "FizzBuzz";
                    if (i % 3 == 0) return "Fizz";
                    if (i % 5 == 0) return "Buzz";
                    return String.valueOf(i);
                }
        ).collect(Collectors.toList());
    }

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i=1;i<=n;i++){

            if(i%3==0 || i%5==0){
                StringBuilder sb = new StringBuilder();

                if(0==i%3)
                    sb.append("Fizz");

                if(0==i%5)
                    sb.append("Buzz");

                result.add(sb.toString());
            }else{
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

}
