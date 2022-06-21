import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/subarray-sum-equals-k/
 */



/*
 * Intuition as to why the solution with a hashmap actually works...
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/268544/Intuitively-diagnosis-problem-pattern-(Java)
 */
// TC = O(n), SC = O(n)
class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // for those (sum - k) == 0 subarrays which are valid and need to be counted
        map.put(0, 1);
        for (int n : nums) {
            sum += n;
            // result += map.getOrDefault(sum - k, 0);
            if (map.containsKey(sum - k))
                result += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }
}



/*
 * sum[i, j] = sum[0, j] - sum[0, i - 1]
 *   (k)         (sum)     (hashmap_key)
 * => sum[0, i - 1] = sum[0, j] - sum[i, j]
 *    (hashmap_key)     (sum)       (k)
 * Now that we have k and sum, as long as we can find a sum[0, i - 1], we then 
 * get a vaid subarray, i.e., as long as we have the hashmap_key, we get a 
 * valid subarray.
 * Neither do we use sliding window, nor do we put map.put(sum[0, i - 1]) every 
 * time - having said that, if all numbers were positive, this would have been 
 * fine. But -ve numbers are possible for this problem.
 */
// Going for a worse solution as compared to the above 1
class Solution2 {
    public int subarraySum(int[] nums, int k) {
        int result = 0, n = nums.length;
        for (int i = 1; i < n; i++)
            nums[i] += nums[i - 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] == k)
                result++;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] - nums[i] == k)
                    result++;
            }
        }
        return result;
    }
}
