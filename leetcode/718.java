/*
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */



/*
 * Approach 1 of LC Official Editorial - somehow AC, LC has become quite irregular!
 */
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, res = 0;
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < len2; i++)
            map.computeIfAbsent(nums2[i], k -> new ArrayList<>()).add(i);
        for (int i = 0; i < len1; i++) {
            for (int j : map.getOrDefault(nums1[i], Collections.emptyList())) {
                int k = 0;
                while (i + k < len1 && j + k < len2 && nums1[i + k] == nums2[j + k])
                    k++;
                res = Math.max(res, k);
            }
        }
        return res;
    }
}



/*
 * Approach 2 of LC Official Editorial - Binary Search with naive checking
 * Good method to know about new syntaxes!! - Again AC though!!! xD
 */
class Solution {

    private boolean isValid(int[] nums1, int[] nums2, int len) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i + len <= nums1.length; i++)
            set.add(Arrays.toString(Arrays.copyOfRange(nums1, i, i + len)));
        for (int i = 0; i + len <= nums2.length; i++) {
            if (set.contains(Arrays.toString(Arrays.copyOfRange(nums2, i, i + len))))
                return true;
        }
        return false;
    }

    public int findLength(int[] nums1, int[] nums2) {
        int low = 0, high = Math.min(nums1.length, nums2.length), res = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(nums1, nums2, mid)) {
                low = mid + 1;
                res = mid;
            }
            else
                high = mid - 1;
        }
        return res;
    }
}



/*
 * Approach 3 of LC official editorial - DP
 */
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int res = 0, len1 = nums1.length, len2 = nums2.length;
        int[][] dp = new int[1 + len1][1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }
}



/*
 * Approach 4 of LC Official Editorial - Binary Search + Rolling Hash
 */

