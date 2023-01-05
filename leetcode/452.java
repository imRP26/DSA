/*
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/
 */



/*
 * Greedy Approach from 
 * https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/solutions/288049/minimum-number-of-arrows-to-burst-balloons/
 * Understand as to why we sort wrt end co-ordinate, this is because it ensures that we just keep track of those 
 * balloons whose end co-oridnate is after the current balloon. We don't need to worry about those balloons that 
 * may have ended before the current balloon and thus this ensures no extra headache!
 */ 
class Solution {
    public int findMinArrowShots(int[][] points) {
        int n = points.length, result = 0;
        Arrays.sort(points, (a, b) -> (a[0] != b[0]) ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        for (int i = 0; i < n; i++) {
            result++;
            int j = i + 1;
            while (j < n && points[i][1] >= points[j][0]) {
                if (points[j][1] < points[i][1]) {
                    j++;
                    break;
                }
                j++;
            }
            i = j - 1;
        }
        return result;
    }
}
