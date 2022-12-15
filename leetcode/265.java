import java.util.*;

/*
 * https://leetcode.com/problems/paint-house-ii
 */


/*
 * My Naive Solution -> O(n * k * k)
 */
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



/*
 * Referenced from (Approach 4) :-
 * https://leetcode.com/problems/paint-house-ii/solutions/497857/paint-house-ii/ 
 */
class Solution2 {
    public int minCostII(int[][] costs) {
        int numHouses = costs.length, numColors = costs[0].length, result = Integer.MAX_VALUE;
        if (numHouses == 0)
            return 0;
        for (int i = 1; i < numHouses; i++) {
            int minColor = -1, secondMinColor = -1;
            for (int j = 0; j < numColors; j++) {
                int cost = costs[i - 1][j];
                if (minColor == -1 || cost < costs[i - 1][minColor]) {
                    secondMinColor = minColor;
                    minColor = j;
                }
                else if (secondMinColor == -1 || cost < costs[i - 1][secondMinColor])
                    secondMinColor = j;
            }
            for (int j = 0; j < numColors; j++) {
                if (j == minColor)
                    costs[i][j] += costs[i - 1][secondMinColor];
                else
                    costs[i][j] += costs[i - 1][minColor];
            }
        }
        for (int cost : costs[numHouses - 1])
            result = Math.min(result, cost);
        return result; 
    }
}


/*
 * Referenced from Approach 5
 */
class Solution3 {
    public int minCostII(int[][] costs) {
        int n = costs.length, k = costs[0].length, prevMinCost = -1, prevSecondMinCost = -1, prevMinIndex = -1;
        for (int j = 0; j < k; j++) {
            int cost = costs[0][j];
            if (prevMinCost == -1 || cost < prevMinCost) {
                prevSecondMinCost = prevMinCost;
                prevMinIndex = j;
                prevMinCost = cost;
            }
            else if (prevSecondMinCost == -1 || cost < prevSecondMinCost)
                prevSecondMinCost = cost;
        }
        for (int i = 1; i < n; i++) {
            int minCost = -1, secondMinCost = -1, minIndex = -1;
            for (int j = 0; j < k; j++) {
                int cost = costs[i][j];
                if (j == prevMinIndex)
                    cost += prevSecondMinCost;
                else 
                    cost += prevMinCost;
                if (minCost == -1 || cost < minCost) {
                    secondMinCost = minCost;
                    minIndex = j;
                    minCost = cost;
                }
                else if (secondMinCost == -1 || cost < secondMinCost)
                    secondMinCost = cost;
            }
            prevMinCost = minCost;
            prevSecondMinCost = secondMinCost;
            prevMinIndex = minIndex;
        }
        return prevMinCost;
    }
}
