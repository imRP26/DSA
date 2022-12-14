import java.util.*;

/*
 * https://leetcode.com/problems/paint-house-ii
 */



// Naive Solution
class Solution1 {
    public int minCostII(int[][] costs) {
        int numHouses = costs.length, numColors = costs[0].length, result = Integer.MAX_VALUE;
        for (int i = 1; i < numHouses; i++) {
            for (int j = 0; j < numColors; j++) {
                int minVal = Integer.MAX_VALUE;
                for (int k = 0; k < numColors; k++) {
                    if (k == j)
                        continue;
                    minVal = Math.min(minVal, costs[i - 1][k]);
                }
                costs[i][j] += minVal;
            }
        }
        for (int i = 0; i < numColors; i++)
            result = Math.min(result, costs[numHouses - 1][i]);
        return result;
    }
}



// Better Solution

