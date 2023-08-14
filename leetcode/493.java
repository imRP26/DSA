/*
 * https://leetcode.com/problems/reverse-pairs/
 */



/* 
 * Approach from -> 
 * https://leetcode.com/problems/reverse-pairs/solutions/112146/java-mergesort-o-n-long-n-templater-solution-for-reverse-pairs-327-and-315/
 */
class Solution {

    private void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] <= nums[j])
                temp[k++] = nums[i++];
            else
                temp[k++] = nums[j++];
        }
        while (i <= mid)
            temp[k++] = nums[i++];
        while (j <= high)
            temp[k++] = nums[j++];
        for (i = low; i <= high; i++)
            nums[i] = temp[i - low];
    }

    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high)
            return 0;
        int mid = low + (high - low) / 2, count = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high), i = low, j = mid + 1;
        while (i <= mid && j <= high) {
            if (nums[i] > (long)nums[j] * 2) {
                count += mid - i + 1;
                j++;
            }
            else
                i++;
        }
        merge(nums, low, mid, high);
        return count;
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }
}



/*
 * Same approach as above, but merge-sorting the array in Descending Order!
 */
class Solution {

    private void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j])
                temp[k++] = nums[j++];
            else
                temp[k++] = nums[i++];
        }
        while (i <= mid)
            temp[k++] = nums[i++];
        while (j <= high)
            temp[k++] = nums[j++];
        for (i = low; i <= high; i++)
            nums[i] = temp[i - low];
    }

    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high)
            return 0;
        int mid = low + (high - low) / 2, ans = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high), i = low, j = mid + 1;
        while (i <= mid && j <= high) {
            if ((long)nums[i] > (long)nums[j] * 2) {
                ans += high - j + 1;
                i++;
            }
            else
                j++;
        }
        merge(nums, low, mid, high);
        return ans;
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }
}
