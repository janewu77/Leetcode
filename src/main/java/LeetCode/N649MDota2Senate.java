package LeetCode;

import java.util.*;

import static java.time.LocalTime.now;

public class N649MDota2Senate {

    static public void main(String... args){
        System.out.println(now());
        System.out.println("==================");
        doRun("Dire", "DDRRR");
        doRun("Dire", "RRDDDDRDRRRRDDDDDRDD");
        doRun("Radiant", "DDRRRR");
        doRun("Radiant", "DRRDRDRDRDDRDRDR");
        System.out.println(now());
        System.out.println("==================");
    }

    static private void doRun(String expect, String senate) {
        String res = new N649MDota2Senate().predictPartyVictory(senate);
        System.out.println("["+(expect.equals(res))+"]expect:" + expect + " res:" + res);
    }

    //1.Queue
    //Time: 12ms 64%; Memory: 43.4MB 32%
    //Time: O(N + N); Space: O(N)
    //Time: O(N); Space: O(N)
    public String predictPartyVictory(String senate) {
        char[] charList = senate.toCharArray();
        int n = charList.length;
        Queue<Integer> dQ = new LinkedList<>();
        Queue<Integer> rQ = new LinkedList<>();

        //Time: O(N)
        for (int i = 0; i < charList.length; i++){
            if ( charList[i] == 'R') rQ.add(i);
            else dQ.add(i);
        }

        //Time: O(N)
        while (!rQ.isEmpty() && !dQ.isEmpty()) {
            int idxR = rQ.poll();
            int idxD = dQ.poll();

            if (idxR < idxD) rQ.add(idxR + n);
            else dQ.add(idxD + n);
        }

        return rQ.isEmpty() ? "Dire" : "Radiant";
    }

}
