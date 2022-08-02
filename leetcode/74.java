import java.util.*;

/*
 * My Naive Approach, TC = O(N), SC = O(1)
 */
class Solution1 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][columns - 1] < target)
                continue;
            if (matrix[i][0] > target)
                break;
            if (Arrays.binarySearch(matrix[i], target) < 0)
                return false;
            return true;
        }
        return false;
    }
}



/*
 * https://www.youtube.com/watch?v=Ber2pi2C0j0 -> Neetcode's video
 * TC = O(log(rows) + log(columns))
 */
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, columns = matrix[0].length;
        int topRow = 0, bottomRow = rows - 1;
        while (topRow <= bottomRow) {
            int midRow = topRow + (bottomRow - topRow) / 2;
            if (matrix[midRow][0] > target)
                bottomRow = midRow - 1;
            else if (matrix[midRow][columns - 1] < target)
                topRow = midRow + 1;
            else {
                int low = 0, high = columns - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (matrix[midRow][mid] == target)
                        return true;
                    if (matrix[midRow][mid] < target)
                        low = mid + 1;
                    else
                        high = mid - 1;
                }
                break;
            }
        }
        return false;
    }
}
