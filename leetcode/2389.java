/*
 * https://leetcode.com/problems/longest-subsequence-with-limited-sum/
 */



/*
 * Approach 2 (Prefix Sum + Binary Search) from 
 * https://leetcode.com/problems/longest-subsequence-with-limited-sum/solutions/2644327/longest-subsequence-with-limited-sum/
 */
class Solution {

    private int binarySearch(int x, int[] prefixSum) {
        int low = 0, high = prefixSum.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2, val = prefixSum[mid];
            if (val == x)
                return mid + 1;
            if (val < x)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    public int[] answerQueries(int[] nums, int[] queries) {
        int numElements = nums.length, numQueries = queries.length;
        Arrays.sort(nums);
        int[] prefixSum = new int[numElements];
        prefixSum[0] = nums[0];
        for (int i = 1; i < numElements; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        int[] result = new int[numQueries];
        for (int i = 0; i < numQueries; i++)
            result[i] = binarySearch(queries[i], prefixSum);
        return result;
    }
}
