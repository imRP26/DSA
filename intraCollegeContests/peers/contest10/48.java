/*
 * Question Link -> https://leetcode.com/problems/rotate-image/
 */



// 90-degree clockwise rotation
/*
 * It can be observed that there's going to be a pattern in the rotation.
 * For the initial rotation, the 4 corners of the square can be denoted as :- 
   topLeft (0, 0), bottomLeft[rows - 1][0], topRight[0][rows - 1], 
   bottomRight[rows - 1][rows - 1].
 * The movements are as follows :- 
   bottomLeft moves into topLeft, topLeft moves into topRight; 
   topRight moves into bottomRight, bottomRight moves into bottomLeft.
*/
class Solution1 {
    public void rotate(int[][] matrix) {
        int rows = matrix.length, left = 0, right = rows - 1;
		while (left < right) {
			for (int i = 0; i < (right - left); i++) {
				int top = left, bottom = right;
				// saving the element at position topLeft
				int topLeft = matrix[top][left + i];
				// moving the element at position bottomLeft to position topLeft
				matrix[top][left + i] = matrix[bottom - i][left];
				// moving the element from position bottomRight to position bottomLeft
				matrix[bottom - i][left] = matrix[bottom][right - i];
				// moving the element from position topRight to position bottomRight
				matrix[bottom][right - i] = matrix[top + i][right];
				// moving initial element at position topLeft to position topRight
				matrix[top + i][right] = topLeft;
			}
			left++;
			right--;
		}
    }
}



// Aliter - Swap each row and then compute the transpose of the matrix
class Solution2 {
    public void rotate(int[][] matrix) {
        int low = 0, high = matrix.length - 1;
        while (low < high) {
            int[] temp = matrix[low];
            matrix[low] = matrix[high];
            matrix[high] = temp;
            low++;
            high--;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}



/*
 * As shown above, an alternate method can also be done for computing rotated 2-D 
 * matrices through 90, 180 and 270 degrees.
 * But it would be advisable to just extend the 1st solution by looping the whole 
 * outer while loop the appropriate number of times.
 */
