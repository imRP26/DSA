/*
 * https://leetcode.com/problems/find-xor-beauty-of-array/
 */



/*
 * Beautiful Explanation from 
 * https://leetcode.com/problems/find-xor-beauty-of-array/solutions/3015014/why-just-xor-of-all-numbers-works/
 */
class Solution {
    public int xorBeauty(int[] nums) {
        int result = 0;
        for (int n : nums)
            result ^= n;
        return result;
    }
}
