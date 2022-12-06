/*
 * Naive O(n log n) solution first up
 */
class Solution1 {
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int i = 1;
        while (true) {
            if (Arrays.binarySearch(nums, i) < 0)
                return i;
            i++;
        }
    }
}



/*
 * 
 */
