/*
 * https://leetcode.com/problems/arithmetic-subarrays/
 */



/*
 * Naive Implementation, useless AF!
 */
class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = l[i]; j <= r[i]; j++)
                list.add(nums[j]);
            if (list.size() < 2)
                continue;
            Collections.sort(list);
            int d = list.get(1) - list.get(0);
            boolean flag = true;
            for (int j = 2; j < list.size(); j++) {
                if (list.get(j) - list.get(j - 1) != d) {
                    flag = false;
                    break;
                }
            }
            res.add(flag);
        }
        return res;
    }
}



/*
 * Approach of No Sorting from LC Official Editorial!
 */
class Solution {

    private boolean check(int[] nums, int low, int high) {
        int n = high - low + 1, minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i = low; i <= high; i++) {
            minVal = Math.min(minVal, nums[i]);
            maxVal = Math.max(maxVal, nums[i]);
            set.add(nums[i]);
        }
        if (minVal == maxVal)
            return true;
        if (((maxVal - minVal) % (n - 1)) != 0) // Basic Formula of AP
            return false;
        int d = (maxVal - minVal) / (n - 1);
        while (minVal <= maxVal) { // checking whether every element of the AP is present or not!
            if (!set.contains(minVal))
                return false;
            minVal += d;
        }
        return true;
    }

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < l.length; i++)
            res.add(check(nums, l[i], r[i]));
        return res;
    }
}
