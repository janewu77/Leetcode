package Contest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

/**
 * You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
 *
 * The chemistry of a team is equal to the product of the skills of the players on that team.
 *
 * Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into teams such that the total skill of each team is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: skill = [3,2,5,1,3,4]
 * Output: 22
 * Explanation:
 * Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
 * The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.
 * Example 2:
 *
 * Input: skill = [3,4]
 * Output: 12
 * Explanation:
 * The two players form a team with a total skill of 7.
 * The chemistry of the team is 3 * 4 = 12.
 * Example 3:
 *
 * Input: skill = [1,1,2,3]
 * Output: -1
 * Explanation:
 * There is no way to divide the players into teams such that the total skill of each team is equal.
 *
 *
 * Constraints:
 *
 * 2 <= skill.length <= 105
 * skill.length is even.
 * 1 <= skill[i] <= 1000
 */
//2491. Divide Players Into Teams of Equal Skill
public class N6254MDividePlayersIntoTeamsofEqualSkill {


    //3.counter array
    //Runtime: 3 ms, faster than 100.00% of Java online submissions for Divide Players Into Teams of Equal Skill.
    //Memory Usage: 50.3 MB, less than 71.43% of Java online submissions for Divide Players Into Teams of Equal Skill.
    //Time: O(N); Space: O(1)
    public long dividePlayers(int[] skill) {
        int[] counter = new int[1001];
        int sum = 0;
        for (int i = 0; i < skill.length; i++) {
            sum += skill[i];
            counter[skill[i]]++;
        }

        int teams = skill.length / 2;
        if (sum % teams != 0) return -1;
        int avg = sum / teams;

        long res = 0;
        for (int i = 1; i < counter.length; i++) {
            if (counter[i] <= 0) continue;
            int x = avg - i;
            if (counter[i] != counter[x]) return -1;

            res += i * x * ((i == x) ? counter[i] / 2 : counter[i]);
            counter[i] = counter[x] = 0;
        }
        return res;
    }

    //2.sort + two pointers
    //Runtime: 28 ms, faster than 85.71% of Java online submissions for Divide Players Into Teams of Equal Skill.
    //Memory Usage: 73.6 MB, less than 14.29% of Java online submissions for Divide Players Into Teams of Equal Skill.
    //Time: O( N * log(N) + N); Space: O(log(N))
    //Time: O( N * log(N)); Space: O(log(N))
    public long dividePlayers_2(int[] skill) {
        //Time: O( N * log(N)); Space: O(log(N))
        Arrays.sort(skill);

        int left = 0, right = skill.length - 1;
        int sum = skill[left] + skill[right];
        long res = 0;
        while (left < right) {
            if (sum != skill[left] + skill[right]) return -1;
            res += skill[left] * skill[right];
            left++; right--;
        }
        return res;
    }

    //1. sum + HashMap
    //Runtime: 111 ms, faster than 14.29% of Java online submissions for Divide Players Into Teams of Equal Skill.
    //Memory Usage: 87.2 MB, less than 14.29% of Java online submissions for Divide Players Into Teams of Equal Skill.
    //Time: O(N + N); Space: O(N)
    //Time: O(N); Space: O(N)
    public long dividePlayers_1(int[] skill) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < skill.length; i++) {
            sum += skill[i];
            map.put(skill[i], map.getOrDefault(skill[i], 0) + 1);
        }

        int avg = sum / (skill.length / 2);
        if (avg * (skill.length / 2) != sum) return -1;

        long res = 0;
        for (int i = 0; i < skill.length; i++) {
            if (!map.containsKey(skill[i])) continue;

            int x = avg - skill[i];
            if (!map.containsKey(x)) return -1;

            map.put(skill[i], map.get(skill[i]) - 1);
            if (map.get(skill[i]) == 0) map.remove(skill[i]);

            map.put(x, map.get(x) - 1);
            if (map.get(x) == 0) map.remove(x);

            res += skill[i] * x;
        }

        return res;
    }

}


