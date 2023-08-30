/*
 * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/solutions/2388098/c-java-google-interview-question-o-n/
 */



/*
 * Also get some more intuition from LC Official Editorial!
 * Greedy Approach from -> 
 * https://leetcode.com/problems/minimum-replacements-to-sort-the-array/solutions/2388098/c-java-google-interview-question-o-n/
 */
class Solution {
    public long minimumReplacement(int[] nums) {
        int n = nums.length, prev = nums[n - 1];
        long res = 0;
        for (int i = n - 2; i >= 0; i--) {
            int numOps = nums[i] / prev;
            if (nums[i] % prev != 0) {
                numOps++;
                prev = nums[i] / numOps;
            }
            res += numOps - 1;
        }
        return res;
    }
}



/*
 * Good explanation from this YouTube video as well -> https://www.youtube.com/watch?v=UCgbJzoSaSQ
 */
