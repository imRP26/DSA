/*
 * https://leetcode.com/problems/wiggle-sort/
 */
 


/*
 * My Naive TC = O(N log N), SC = O(N) solution
 */
class Solution1 {
    public void wiggleSort(int[] nums) {
        int index = 0, n = nums.length, low = 0, high = n - 1;
        int[] temp = Arrays.copyOf(nums, n);
        Arrays.sort(temp);
        while (low < high) {
            nums[index++] = temp[low++];
            nums[index++] = temp[high--];
        }
        if (low == high)
            nums[index] = temp[low];
    }
}



/*
 * Approach 1 (improvement upon the above solution wrt Space Complexity) from
 * https://leetcode.com/problems/wiggle-sort/solutions/2961467/wiggle-sort/ 
 */
class Solution2 {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i + 1 < nums.length; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        } 
    }
}



/*
 * Approach 2 (Greedy approach) from
 * https://leetcode.com/problems/wiggle-sort/solutions/2961467/wiggle-sort/
 */
class Solution3 {
    public void wiggleSort(int[] nums) {
        int n = nums.length, temp;
        if (n == 1)
            return;
        if (nums[0] > nums[1]) {
            temp = nums[0];
            nums[0] = nums[1];
            nums[1] = temp; 
        }
        boolean lessThanEquals = true;
        for (int i = 2; i < n; i++) {
            lessThanEquals = !lessThanEquals;
            if (!lessThanEquals && nums[i - 1] >= nums[i])
                continue;
            if (lessThanEquals && nums[i - 1] <= nums[i])
                continue;
            temp = nums[i];
            nums[i] = nums[i - 1];
            nums[i - 1] = temp;
        }
    }
}
 