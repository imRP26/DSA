import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/find-the-duplicate-number/
 */



// My Naive Solution - SC = O(N), TC = O(N)
class Solution1 {
    public int findDuplicate(int[] nums) {
        int n = nums.length, i;
        int[] numCounts = new int[n + 1];
        for (int num : nums)
            numCounts[num]++;
        for (i = 1; i <= n; i++) {
            if (numCounts[i] > 1)
                break;
        }
        return i;
    }
}



// Using a HashSet, SC = O(N) = TC
class Solution2 {
    public int findDuplicate(int[] nums) {
        int i;
        Set<Integer> set = new HashSet<>();
        for (i = 0; i < nums.length; i++) {
            if (!set.add(nums[i]))
                break;
        }
        return nums[i];
    }
}



/*
 * Marking visited value in the input array itself - con being the input array 
 * gets modified
 */
class Solution3 {
    public int findDuplicate(int[] nums) {
        int index = 0;
        for (int num : nums) {
            index = Math.abs(num);
            if (nums[index] < 0)
                break;
            nums[index] = -nums[index];
        }
        return index;
    }
}



/*
 * Guess a number 1st - the number "mid" in the valid range [left, right]), count 
 * the number of elements of the array which are <= mid in the array. 
 * If count is strictly > mid, according to the Pigeonhole Principle, repeated 
 * elements are in the interval [left, mid]. 
 * Otherwise, the repeated element is in the interval [mid + 1, right].
 */
class Solution4 {
    public int findDuplicate(int[] nums) {
        int n = nums.length, low = 1, high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2, count = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count <= mid)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}



/*
 * Need to understand the penultimate method give here...
 * https://leetcode.com/problems/find-the-duplicate-number/discuss/1892921/Java-9-Approaches-Count-%2B-Hash-%2B-Sort-%2B-Binary-Search-%2B-Bit-%2B-Fast-Slow-Pointers
 */



/*
 * Method of Fast & Slow Pointers...
 * https://leetcode.com/problems/linked-list-cycle-ii/discuss/44781/Concise-O(n)-solution-by-using-C++-with-Detailed-Alogrithm-Description/219750
 * Let L1 = distance bewteen the head and the entry points.
 *     L2 = distance between the entry and the meeting points.
 *     x = remaining length of the cycle, i.e., the distance between the meeting 
 *         point and the start of the cycle.
 * Distance travelled by the slow pointer till the time of collision = L1 + L2 = D1
 * Distance travelled by the fast pointer till the time of collision = L1 + L2 + x + L2 = D2
 * Now, D2 = 2 * D1 => x = L1
 * 
 * Also, another interesting explanation for this :-
 * https://keithschwarz.com/interesting/code/?dir=find-duplicate
 */
class Solution5 {
    public int findDuplicate(int[] nums) {
        int slowPtr = 0, fastPtr = 0;
        do {
            slowPtr = nums[slowPtr];
            fastPtr = nums[nums[fastPtr]];
        } while (slowPtr != fastPtr);
        slowPtr = 0;
        while (slowPtr != fastPtr) {
            slowPtr = nums[slowPtr];
            fastPtr = nums[fastPtr];
        }
        return slowPtr;
    }
}
