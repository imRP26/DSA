import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/pascals-triangle-ii/
 */



// My Naive Solution
class Solution1 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row1 = new ArrayList<>();
		row1.add(1);
		List<Integer> row2 = new ArrayList<>();
		for (int i = 1; i <= rowIndex; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i)
					row2.add(1);
				else
					row2.add(row1.get(j - 1) + row1.get(j));
			}
			row1 = row2;
			row2 = new ArrayList<>();
		}
		return row1;
    }
}



// 
