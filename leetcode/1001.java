/*
 * https://leetcode.com/problems/grid-illumination/
 */



/*
 * Approach using Hash Tables dilligently and properly from -> 
 * https://leetcode.com/problems/grid-illumination/solutions/243076/java-clean-code-o-n-time-and-o-n-space-beats-100/
 */
class Solution {
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> rowLampCount = new HashMap<>(), colLampCount = new HashMap<>(), diagonalLampCount = new HashMap<>(), antiDiagonalLampCount = new HashMap<>(), posLampCount = new HashMap<>();
        for (int[] lamp : lamps) {
            int x = lamp[0], y = lamp[1];
            rowLampCount.put(x, rowLampCount.getOrDefault(x, 0) + 1);
            colLampCount.put(y, colLampCount.getOrDefault(y, 0) + 1);
            diagonalLampCount.put(x - y, diagonalLampCount.getOrDefault(x - y, 0) + 1);
            antiDiagonalLampCount.put(x + y, antiDiagonalLampCount.getOrDefault(x + y, 0) + 1);
            posLampCount.put(n * x + y, posLampCount.getOrDefault(n * x + y, 0) + 1);
        }
        int q = queries.length;
        int[] res = new int[q];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {0, 0}};
        for (int i = 0; i < q; i++) {
            int x = queries[i][0], y = queries[i][1];
            res[i] = (rowLampCount.getOrDefault(x, 0) > 0 || colLampCount.getOrDefault(y, 0) > 0 || diagonalLampCount.getOrDefault(x - y, 0) > 0 || antiDiagonalLampCount.getOrDefault(x + y, 0) > 0) ? 1 : 0;
            for (int[] dir : dirs) {
                int x1 = x + dir[0], y1 = y + dir[1];
                if (x1 >= 0 && x1 < n && y1 >= 0 && y1 < n && posLampCount.containsKey(n * x1 + y1)) {
                    int numLamps = posLampCount.get(n * x1 + y1);
                    posLampCount.remove(n * x1 + y1);
                    rowLampCount.put(x1, rowLampCount.getOrDefault(x1, 1) - numLamps);
                    colLampCount.put(y1, colLampCount.getOrDefault(y1, 1) - numLamps);
                    diagonalLampCount.put(x1 - y1, diagonalLampCount.getOrDefault(x1 - y1, 1) - numLamps);
                    antiDiagonalLampCount.put(x1 + y1, antiDiagonalLampCount.getOrDefault(x1 + y1, 1) - numLamps);
                }
            }
        }
        return res;
    }
}
