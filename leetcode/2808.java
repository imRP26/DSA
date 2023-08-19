/*
 * https://leetcode.com/problems/minimum-seconds-to-equalize-a-circular-array/
 */



/* 
 * A slippery ad-hoc approach from -> 
 * https://leetcode.com/problems/minimum-seconds-to-equalize-a-circular-array/solutions/3867958/java-python-3-min-of-the-longest-distance-between-same-numbers/
 */
class Solution {
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, List<Integer> > map = new HashMap<>();
        int n = nums.size(), res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            map.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        for (List<Integer> list : map.values()) {
            list.add(list.get(0) + n); // important!!!
            int maxlen = 0;
            for (int i = 1; i < list.size(); i++)
                maxlen = Math.max(maxlen, list.get(i) - list.get(i - 1));
            res = Math.min(res, maxlen / 2);
        }
        return res;
    }
}
