/*
 * https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/
 */



/*
 * Okay so now I'm questioning my very existence!
 * Approach from -> 
 * https://leetcode.com/problems/maximum-number-of-groups-with-increasing-length/solutions/3803590/simple-python-math-solution-o-nlogn-time-complexity-only-7-lines-of-code/
 */
class Solution {
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        int n = usageLimits.size(), res = 0;
        long sum = 0;
        Collections.sort(usageLimits);
        for (int x : usageLimits) {
            sum += x;
            res += (sum >= (res + 1) * (res + 2) / 2) ? 1 : 0;
        }
        return res;
    }
}
