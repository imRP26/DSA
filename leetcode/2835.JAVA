/*
 * https://leetcode.com/problems/minimum-operations-to-form-subsequence-with-target-sum/
 */



/*
 * Greedy Approach from -> 
 * https://leetcode.com/problems/minimum-operations-to-form-subsequence-with-target-sum/solutions/3965489/c-python-check-from-big-to-small/
 */
class Solution {
    public int minOperations(List<Integer> nums, int target) {
        long totalSum = 0;
        for (int n : nums)
            totalSum += n;
        if (totalSum < target)
            return -1;
        Collections.sort(nums, Collections.reverseOrder());
        int res = 0;
        while (target > 0) {
            int n = nums.get(0);
            nums.remove(0);
            if (totalSum - n >= target)
                totalSum -= n;
            else if (n <= target) {
                // well if we don't take this, and instead try splitting this, it'd take more number of operations as compared to smaller numbers further ahead!
                totalSum -= n;
                target -= n;
            }
            else {
                nums.add(0, n / 2);
                nums.add(0, n / 2);
                res++;
            }
        }
        return res;
    }
}



/*
 * Ditto Approach as above, but just used max-Priority Queue!
 */
class Solution {
    public int minOperations(List<Integer> nums, int target) {
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        long totalSum = 0;
        for (int n : nums) {
            totalSum += n;
            maxPQ.offer(n);
        }
        if (totalSum < target)
            return -1;
        int res = 0;
        while (target > 0) {
            int n = maxPQ.poll();
            if (totalSum - n >= target)
                totalSum -= n;
            else if (n <= target) {
                totalSum -= n;
                target -= n;
            }
            else {
                maxPQ.offer(n / 2);
                maxPQ.offer(n / 2);
                res++;
            }
        }
        return res;
    }
}



/*
 * Approach of Bit Manipulation from -> 
 * https://leetcode.com/problems/minimum-operations-to-form-subsequence-with-target-sum/solutions/3965229/handwritten-think-store-calc-in-pow-of-2-greedy/
 */
class Solution {
    public int minOperations(List<Integer> nums, int target) {
        int[] counts = new int[32];
        long totalSum = 0;
        int res = 0, idx = 0;
        for (int n : nums) {
            totalSum += n;
            for (int i = 0; i < 32; i++) {
                if (n == (1 << i)) {
                    counts[i]++;
                    break;
                }
            }
        }
        if (totalSum < target)
            return -1;
        while (idx < 32) {
            if (((1 << idx) & target) != 0) {
                if (counts[idx] > 0)
                    counts[idx]--;
                else {
                    while (idx < 31 && counts[idx] == 0) {
                        idx++;
                        res++;
                    }
                    counts[idx]--;
                    continue;
                }
            }
            if (idx < 31)
                counts[idx + 1] += counts[idx] / 2;
            idx++;
        }
        return res;
    }
}
