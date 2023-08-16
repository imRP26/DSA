/* 
 * https://leetcode.com/problems/sliding-window-maximum/
 */



/*
 * Simple Approach referred from Striver's video!
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length, idx = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[n - k + 1];
        for (int i = 0; i < k; i++) {
            // maintaining descending order of elements inside the deque
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast(); // removal is done from the end since order of elements is descending
            dq.offerLast(i);
        }
        res[idx++] = nums[dq.peekFirst()];
        for (int i = k; i < n; i++) {
            if (dq.peekFirst() <= i - k) // removing any elements outside the range
                dq.pollFirst();
            /*
             * maintaining descending order of elements inside the deque
             * removal is done from the end since order of elements is descending
             */
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast();
            dq.offerLast(i);
            // the 1st element must be the maximum since elements within the deque are in descending order
            res[idx++] = nums[dq.peekFirst()]; 
        }
        return res;
    }
}
