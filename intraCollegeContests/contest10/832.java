/*
 * Question Link -> https://leetcode.com/problems/flipping-an-image/
 */



 // My Naive solution
class Solution1 {
    public int[][] flipAndInvertImage(int[][] image) {
        int rows = image.length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows / 2; j++) {
                int temp = image[i][j];
                image[i][j] = 1 - image[i][rows - j - 1];
                image[i][rows - j - 1] = 1 - temp;
            }
            if (rows % 2 == 1)
                image[i][rows / 2] = 1 - image[i][rows / 2];
        }
        return image;
    }
}



// A bit of bitwise operators
class Solution2 {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for (int[] row : image) {
            for (int i = 0; i * 2 < n; i++) {
                if (row[i] == row[n - i - 1])
                    row[i] = row[n - i - 1] ^= 1;
            }
        }
        return image;
    }
}
