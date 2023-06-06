/*
 * https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
 */



/*
 * My Naive Approach of Sorting, TC = O(N log N), SC = O(1)
 */
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        if (n == 2)
            return true;
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < n; i++) {
            if (arr[i] - arr[i - 1] != diff)
                return false;
        }
        return true;
    }
}



/*
 * Approach of HashSet from LC official editorial, TC = O(N), SC = O(N)
 */
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int n = arr.length, mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        if (n == 2) // by default a sequence of length 2 will be an AP
            return true;
        for (int a : arr) {
            set.add(a);
            mini = Math.min(mini, a);
            maxi = Math.max(maxi, a);
        }
        if (set.size() == 1) // when all the elements are same - AP with common difference = 0
            return true;
        // if either duplicates are present or the commonDifference is not an integer
        if (set.size() != n || (maxi - mini) % (n - 1) != 0)
            return false;
        int commonDiff = (maxi - mini) / (n - 1);
        for (int a : arr) {
            // difference between the minimum element and the current element should be divisible by the common difference
            if ((a - mini) % commonDiff != 0)
                return false;
        }
        return true;
    }
}



/*
 * Approach 3 from LC official editorial - involved in-place modification of the input
 * Basically keep the elements of the AP in their correct positions!
 * This solution kinda felt very very jugaadu!!
 */
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length, mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        if (n <= 2)
            return true;
        for (int a : arr) {
            mini = Math.min(mini, a);
            maxi = Math.max(maxi, a);
        }
        if (mini == maxi)
            return true;
        if ((maxi - mini) % (n - 1) != 0)
            return false;
        int cd = (maxi - mini) / (n - 1);
        for (int i = 0; i < n; i++) {
            if ((arr[i] - mini) % cd != 0)
                return false;
            int j = (arr[i] - mini) / cd;
            if (j == i)
                continue;
            if (arr[i] == arr[j] && i != j)
                return false;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return true;
    }
}
