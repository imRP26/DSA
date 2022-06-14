/*
 * Question Link -> 
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
 */



/* 
 * Initially, let's just assume that the input array nums is linear, and not 
 * circular. Now, when all the 1s in the array are eventually grouped together 
 * (through swaps), that results into a particular subarray which contains only 
 * 1s, and the size of that subarray = number of 1s present in the parent array.
 * So, we can go through each subarray of that specific length in the parent array, 
 * and for each subarray, the number of swaps will then be = the number of 0s 
 * present in that subarray and the final answer will be the minimum of such swaps 
 * across all the subarrays.
 * For treating the circular case, the original array can be appended to iteself. 
*/
class Solution1 {
    public int minSwaps(int[] nums) {
        int n = nums.length, result = Integer.MAX_VALUE, numZeroes = 0, numOnes = 0, low = 0;
        for (int num : nums) {
            numOnes += num;
            numZeroes += 1 - num;
        }
        // handling the base case, wherein no swaps are needed
        if (numZeroes == n || numOnes <= 1 || numOnes == n)
            return 0;
        numZeroes = 0;
        int[] duplicateNums = new int[2 * n];
        for (int i = 0; i < 2 * n; i++)
            duplicateNums[i] = nums[i % n];
        for (int high = 0; high < 2 * n; high++) {
            if (duplicateNums[high] == 0)
                numZeroes++;
            if (high - low + 1 == numOnes) {
                result = Math.min(result, numZeroes);
                if (duplicateNums[low++] == 0)
                    numZeroes--;
            }
        }
        return result;
    }
}



// A bit more efficient solution w.r.t. Space Complexity
class Solution2 {
    public int minSwaps(int[] nums) {
        int len = nums.length, numOnes = 0;
        for (int n : nums)
            numOnes += n;
        if (numOnes <= 1 || numOnes == len)
            return 0;
        int minimumSwaps = Integer.MAX_VALUE, low = 0, numZeroes = 0;
        for (int i = low; i < numOnes; i++)
            numZeroes += 1 - nums[i];
        minimumSwaps = Math.min(minimumSwaps, numZeroes);
        for (low = 1; low < len; low++) {
            int high = (low + numOnes - 1) % len;
            if (nums[high] == 0 && nums[low - 1] == 1)
                numZeroes++;
            else if(nums[high] == 1 && nums[low - 1] == 0)
                numZeroes--;
            minimumSwaps = Math.min(minimumSwaps, numZeroes);
        }
        return minimumSwaps;
    }
}
