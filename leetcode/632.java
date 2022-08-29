import java.util.*;

/*
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
 */



/*
 * Approach of 2 Pointers -> Approach 3 given in :- 
 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/solution/
 */
class Solution1 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int xMin = 0, yMin = Integer.MAX_VALUE, n = nums.size();
        int[] next = new int[n];
        boolean flag = true;
        for (int i = 0; i < n && flag; i++) {
            for (int j = 0; j < nums.get(i).size() && flag; j++) {
                int iMin = 0, iMax = 0;
                for (int k = 0; k < n; k++) {
                    if (nums.get(iMin).get(next[iMin]) > nums.get(k).get(next[k]))
                        iMin = k;
                    if (nums.get(iMax).get(next[iMax]) < nums.get(k).get(next[k]))
                        iMax = k;
                }
                if (yMin - xMin > nums.get(iMax).get(iMax) - nums.get(iMin).get(iMin)) {
                    yMin = nums.get(iMax).get(next[iMax]);
                    xMin = nums.get(iMin).get(next[iMin]);
                }
                next[iMin]++;
                if (next[iMin] == nums.get(iMin).size())
                    flag = false;
            }
        }
        return new int[] {xMin, yMin};
    }
}
