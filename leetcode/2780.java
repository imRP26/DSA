/*
 * https://leetcode.com/problems/minimum-index-of-a-valid-split/
 */



/*
 * An Ad-Hoc solution - just simulation of the problem statement
 */
class Solution {
    public int minimumIndex(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.size(), res = -1, dom = 0, maxfreq = -1, count = 0;
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) > maxfreq) {
                maxfreq = map.get(x);
                dom = x;
            }
        }
        int[] domfreqs = new int[n]; // domfreqs[i] = number of occurences of the dominant element till that particular element
        for (int i = 0; i < n; i++) 
            domfreqs[i] = (nums.get(i) == dom) ? ++count : count;
        for (int i = 0; i < n; i++) {
            int len1 = i + 1, len2 = n - i - 1, freq1 = domfreqs[i], freq2 = maxfreq - freq1;
            if (2 * freq1 > len1 && 2 * freq2 > len2)
                return i;
        }
        return -1;
    }
}



/*
 * 
 */
