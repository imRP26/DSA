/*
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/description/
 */



/*
 * HashMap
 */
class Solution {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tasks)
            map.put(t, map.getOrDefault(t, 0) + 1);
        int result = 0;
        /*
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getValue();
            if (x < 2)
                return -1;
            if (x % 3 == 0)
                result += x / 3;
            else
                result += x / 3 + 1;
        }
        */
        for (int freq : map.values()) {
            if (freq < 2)
                return -1;
            result += (freq % 3 == 0) ? freq / 3 : freq / 3 + 1;
        }
        return result;
    }
} 
