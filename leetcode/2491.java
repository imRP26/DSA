import java.util.*;


/*
 * Simple application of HashMap
 */
class Solution {
    public long dividePlayers(int[] skill) {
        int numPlayers = skill.length, numTeams = numPlayers / 2, totalSkillSum = 0;
        long result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int s : skill) {
            totalSkillSum += s;
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        if (totalSkillSum % numTeams != 0)
            return -1;
        int teamSkillSum = totalSkillSum / numTeams;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k1 = entry.getKey(), v1 = entry.getValue(), k2 = teamSkillSum - k1, v2 = map.getOrDefault(k2, 0);
            if (v1 != v2)
                return -1;
            result += (long)k1 * (long)k2 * (long)v1;
        }
        return result / 2;
    }
}
