import java.util.*;

/*
 * https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */



/*
 * DFS based solution
 */
class Solution1 {

    void dfs(int[] stone1, Set<int[]> visited, int[][] stones) {
        visited.add(stone1);
        for (int[] stone2 : stones) {
            if (!visited.contains(stone)) {
                if (stone[0] == stonePos[0] || stone[1] == stonePos[1])
                    dfs(stone, visited, stones);
            }
        }
    }

    public int removeStones(int[][] stones) {
        Set<int[]> visited = new HashSet<>();
        int numIslands = 0;
        for (int[] stone1 : stones) {
            if (!visited.contains(stone1)) {
                dfs(stone1, visited, stones);
                numIslands++;
            }
        }
        return stones.length - numIslands;
    }
}
