import java.util.*;

/*
 * https://leetcode.com/problems/shortest-distance-to-target-color/
 */



/*
 * A map is used to store color and all of its positions in the array.
 * For each index and color in query, binary search is used to find the index's 
 * closest color index in all of color's index list
 */
class Solution1 {

    int binarySearch(int index, List<Integer> list) {
        int low = 0, high = list.size() - 1, diff = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            diff = Math.min(diff, Math.abs(list.get(mid) - index));
            if (list.get(mid) < index)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return diff;
    }

    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            map.putIfAbsent(colors[i], new ArrayList<>());
            map.get(colors[i]).add(i);
        }
        List<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            int index = query[0], targetColor = query[1];
            if (!map.containsKey(targetColor))
                result.add(-1);
            else
                result.add(binarySearch(index, map.get(targetColor)));
        }
        return result;
    }
}



/*
 * Scan from 2 ends of the colors array. Update / Keep the shortest color 
 * index.
 */
class Solution2 {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int len = colors.length;
        int[][] fromLeft = new int[len][4], fromRight = new int[len][4];
        for (int[] row : fromLeft)
            Arrays.fill(row, -1);
        for (int[] row : fromRight)
            Arrays.fill(row, -1);
        for (int i = 0, j = len - 1; i < len; i++, j--) {
            fromLeft[i][colors[i]] = i;
            fromRight[j][colors[j]] = j;
            if (i > 0) {
                for (int k = 1; k <= 3; k++) {
                    if (k != colors[i])
                        fromLeft[i][k] = fromLeft[i - 1][k];
                }
            }
            if (j + 1 < len) {
                for (int k = 1; k <= 3; k++) {
                    if (k != colors[j])
                        fromRight[j][k] = fromRight[j + 1][k];
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int[] query : queries) {
            int i = query[0], c = query[1];
            if (fromLeft[i][c] == -1 && fromRight[i][c] == -1)
                result.add(-1);
            else if (fromLeft[i][c] == -1 || fromRight[i][c] == -1)
                result.add(Math.abs(i - (fromLeft[i][c] == -1 ? fromRight[i][c] : fromLeft[i][c])));
            else
                result.add(Math.min(i - fromLeft[i][c], fromRight[i][c] - i));
        }
        return result;
    }
}
