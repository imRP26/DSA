import java.util.*;

/*
 * Solution Link -> https://leetcode.com/problems/pascals-triangle/
 */



/*
 * Some pointers about Java, the language...
 * Since Java's ArrayList allocates the required memory in chunks (typically by 
 * doubling the size when it hits the limit), we can say that ArrayList.add() 
 * has O(1) "amortized" time complexity.
 * A really, good S.O. link to follow up on the above topic :-
 * https://stackoverflow.com/questions/64745032/how-is-the-arraylist-addtype-value-method-o1-amortized-time-complexity
 */
class Solution1 {
	public List<List<Integer> > generate(int numRows) {
		//List<List<Integer> > result = new ArrayList<List<Integer>>();
		List<List<Integer> > result = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			List<Integer> row = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i)
					row.add(1);
				else
					row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
			}
			result.add(row);
		}
		return result;
	}
}



// Another solution implementing the same idea as above
class Solution2 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        ArrayList<Integer> pre = null;
        for (int i = 1; i <= numRows; i++) {
            ArrayList<Integer> save = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i)
                    save.add(1);
                else
                    save.add(pre.get(j - 2) + pre.get(j - 1));
            }
            result.add(save);
            pre = save;
        }
        return result;
    }
}
