/*
 * https://leetcode.com/problems/find-anagram-mappings/
 */



/*
 * Simple HashMap -> TC = O(n), SC = O(n)
 */
class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        Map<Integer, List<Integer> > map2 = new HashMap<>();
        int n = nums1.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++)
            map2.computeIfAbsent(nums2[i], val -> new ArrayList<>()).add(i);
        for (int i = 0; i < n; i++) {
            int x = nums1[i];
            List<Integer> list = map2.get(x);
            result[i] = list.get(0);
            list.remove(0);
            map2.put(x, list);
        }
        return result;
    }
}



/*
 * Bitmasking + Sorting from Official LC Editorial -> 
 * https://leetcode.com/problems/find-anagram-mappings/editorial/
 */
class Solution {

    final int bitsToShift = 7;
    final int numToGetLastBits = (1 << bitsToShift) - 1;

    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            nums1[i] = (nums1[i] << bitsToShift) + i;
            nums2[i] = (nums2[i] << bitsToShift) + i;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] mappings = new int[n];
        for (int i = 0; i < n; i++)
            mappings[nums1[i] & numToGetLastBits] = nums2[i] & numToGetLastBits;
        return mappings;
    }
}
