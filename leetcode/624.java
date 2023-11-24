/*
 * https://leetcode.com/problems/maximum-distance-in-arrays/
 */



/*
 * Approach of Greedy from LC Official Editorial!
 */
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int minVal = arrays.get(0).get(0), maxVal = arrays.get(0).get(arrays.get(0).size() - 1), result = Integer.MIN_VALUE;
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> list = arrays.get(i);
            result = Math.max(result, Math.abs(list.get(list.size() - 1) - minVal));
            result = Math.max(result, Math.abs(maxVal - list.get(0)));
            minVal = Math.min(minVal, list.get(0));
            maxVal = Math.max(maxVal, list.get(list.size() - 1));
        }
        return result;
    }
}
