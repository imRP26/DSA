import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/triangle/
 */



/*
 * Simple Top-Bottom approach of DP - used extra space of O(n^2)
 * Minimum cost of reaching a cell at the bottom row of the given triangle = 
   Minimum cost of reaching a cell at the penultimate bottom row of the given triangle + 
   Cost of going to a cell at the next row (which is = the number present there)
   Now, this can be generalized across all the rows of the given input triangle.
*/
class Solution1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size(), m = triangle.get(n - 1).size(), result = Integer.MAX_VALUE;
        int[][] dp = new int[n][m];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            m = triangle.get(i).size();
            for (int j = 0; j < m; j++) {
                int x = triangle.get(i).get(j);
                if (j == 0)
                    dp[i][j] = dp[i - 1][j] + x;
                else if(j == m - 1)
                    dp[i][j] = dp[i - 1][j - 1] + x;
                else
                    dp[i][j] = Math.min(dp[i - 1][j] + x, dp[i - 1][j - 1] + x);
            }
        }
        for (int i = 0; i < dp[n - 1].length; i++)
            result = Math.min(result, dp[n - 1][i]);
        return result;
    }
}



// Bottom-Up Approach - extra space used is O(1)
class Solution2 {
    public int minimumTotal(List<List<Integer> > triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + 
                                Math.min(triangle.get(i + 1).get(j), 
                                        triangle.get(i + 1).get(j + 1)));
        }
        return triangle.get(0).get(0);
    }
}



/*
 * Not modifying the original list, but using a list copied from the last row 
 * in the triangle.
 */
class Solution3 {
    public int minimumTotal(List<List<Integer> > triangle) {
        int n = triangle.size();
        List<Integer> list = new ArrayList<>(triangle.get(n - 1));
        for (int i = n - 2; i >= 0; i --) {
            for (int j = 0; j <= i; j++)
                list.set(j, triangle.get(i).get(j) + 
                                Math.min(list.get(j), list.get(j + 1)));
        }
        return list.get(0);
    }
}



/*
 * This problem is quite well-formed in my opinion. The triangle has a tree-like 
 * structure, which would lead people to think about traversal algorithms such 
 * as DFS. However, if looked upon closely, it can be noticed that the adjacent 
 * nodes always share a 'branch'. 
 * In other words, there are overlapping subproblems. Also, suppose x and y are 
 * 'children' of k. Once minimum paths from x and y to the bottom are known, 
 * the minimum path starting from k can be decided in O(1), i.e., optimal 
 * substructure. 
 * Therefore, DP would be the best solution to this problem in terms of time 
 * complexity.
 */
