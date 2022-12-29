/*
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 */



/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */



/*
 * Binary Searching Each row, approach 2 from 
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/solutions/590040/leftmost-column-with-a-at-least-a-one/?orderBy=most_votes
 */
class Solution1 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> matDim = binaryMatrix.dimensions();
        int rows = matDim.get(0), columns = matDim.get(1), result = columns;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            int low = 0, high = columns - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (binaryMatrix.get(rowIndex, mid) == 1)
                    high = mid;
                else
                    low = mid + 1;
            }
            if (binaryMatrix.get(rowIndex, low) == 1)
                result = Math.min(result, low);
        }
        return result == columns ? -1 : result;
    }
}



/*
 * Same approach as above, but a little bit quicker!!
 */
class Solution2 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> matDim = binaryMatrix.dimensions();
        int rows = matDim.get(0), columns = matDim.get(1), result = columns;
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            int low = 0, high = result - 1;
            if (binaryMatrix.get(rowIndex, columns - 1) == 0)
                continue;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (binaryMatrix.get(rowIndex, mid) == 1)
                    high = mid;
                else
                    low = mid + 1;
            }
            if (binaryMatrix.get(rowIndex, low) == 1)
                result = Math.min(result, low);
        }
        return result == columns ? -1 : result;
    }
} 



/*
 * Approach 3 from the above link
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/solutions/590040/leftmost-column-with-a-at-least-a-one/?orderBy=most_votes
 */
class Solution3 {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0), columns = binaryMatrix.dimensions().get(1);
		int currentRow = 0, currentColumn = columns - 1;
		while (currentRow < rows && currentColumn >= 0) {
			if (binaryMatrix.get(currentRow, currentColumn) == 0)
				currentRow++;
			else
				currentColumn--;
		}
		return currentColumn == columns - 1 ? -1 : currentColumn + 1;
    }
}
