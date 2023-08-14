/*
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 */



/*
 * Follow this template and the approach given here -> 
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/solutions/138154/the-c-merge-sort-template-for-pairs-i-j-problem/
 */
class Solution {

    private int[][] arr;
    private List<Integer> res = new ArrayList<>();

    /*
     * Aim is to perform the merge sort in descending order!
     * This simplifies the calculations as it calculates the number of inversions here, i.e., 
     * the number of smaller numbers after a larger number, which is basically asked in the question!
     */
    private void merge(int low, int mid, int high) {
        int i = low, j = mid + 1, k = 0;
        int[][] temp = new int[high - low + 1][2];
        while (i <= mid && j <= high) {
            if (arr[i][0] <= arr[j][0])
                temp[k++] = arr[j++];
            else {
                res.set(arr[i][1], res.get(arr[i][1]) + high - j + 1);
                temp[k++] = arr[i++];
            }
        }
        while (i <= mid)
            temp[k++] = arr[i++];
        while (j <= high)
            temp[k++] = arr[j++];
        for (i = low; i <= high; i++)
            arr[i] = temp[i - low];
    }

    private void mergeSort(int low, int high) {
        if (low >= high)
            return;
        int mid = low + (high - low) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        merge(low, mid, high);
    }

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        for (int i = 0; i < n; i++)
            res.add(0);
        mergeSort(0, n - 1);
        return res;
    }
}
