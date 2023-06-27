/*
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 */



/*
 * Approach of Priority Queue from LC Official Editorial
 */
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer> > res = new ArrayList<>();
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0] && a[1] == b[1])
                return a[2] - b[2];
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        Set<Pair<Integer, Integer> > set = new HashSet<>();
        minPQ.offer(new int[] {nums1[0] + nums2[0], 0, 0});
        set.add(new Pair(0, 0));
        int n1 = nums1.length, n2 = nums2.length;
        while (!minPQ.isEmpty() && res.size() < k) {
            int[] temp = minPQ.poll();
            int i1 = temp[1], i2 = temp[2];
            res.add(List.of(nums1[i1], nums2[i2]));
            if (i2 + 1 < n2 && !set.contains(new Pair(i1, i2 + 1))) {
                minPQ.offer(new int[] {nums1[i1] + nums2[i2 + 1], i1, i2 + 1});
                set.add(new Pair(i1, i2 + 1));
            }
            if (i1 + 1 < n1 && !set.contains(new Pair(i1 + 1, i2))) {
                minPQ.offer(new int[] {nums1[i1 + 1] + nums2[i2], i1 + 1, i2});
                set.add(new Pair(i1 + 1, i2));
            }
        }
        return res;
    }
}
