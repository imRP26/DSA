/*
 * https://leetcode.com/problems/4sum-ii/
 */



/*
 * Approach of HashMap from LC Official Editorial - shame on me, man!!
 */
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map12 = new HashMap<>();
        for (int n1 : nums1) {
            for (int n2 : nums2)
                map12.put(n1 + n2, map12.getOrDefault(n1 + n2, 0) + 1);
        }
        int res = 0;
        for (int n3 : nums3) {
            for (int n4 : nums4)
                res += map12.getOrDefault(-(n3 + n4), 0);
        }
        return res;
    }
}



/*
 * Approach of k-Sum from LC Official Editorial!
 * Same concept of HashMaps (as above) can be extended!
 */
class Solution {

    private int[][] lists;

    private Map<Integer, Integer> sumCount(int low, int high) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int a : lists[low])
            counts.put(a, counts.getOrDefault(a, 0) + 1);
        for (int i = low + 1; i < high; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int a : lists[i]) {
                for (int b : counts.keySet())
                    map.put(b + a, map.getOrDefault(b + a, 0) + counts.get(b));
            }
            counts = map;
        }
        return counts;
    }

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        lists = new int[][] {nums1, nums2, nums3, nums4};
        int k = lists.length;
        Map<Integer, Integer> left = sumCount(0, k / 2), right = sumCount(k / 2, k);
        int res = 0;
        for (int sum : left.keySet())
            res += left.get(sum) * right.getOrDefault(-sum, 0);
        return res;
    }
}
