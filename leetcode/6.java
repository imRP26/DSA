/*
 * https://leetcode.com/problems/zigzag-conversion/
 */



/*
 * Approach from 
 * https://leetcode.com/problems/zigzag-conversion/solutions/3403/easy-to-understand-java-solution/?orderBy=most_votes
 */
class Solution {
	public String convert(String s, int numRows) {
		int len = s.length(), i = 0;
		StringBuffer[] sb = new StringBuffer[numRows];
		for (int j = 0; j < numRows; j++)
			sb[j] = new StringBuffer();
		while (i < len) {
			for (int j = 0; j < numRows && i < len; j++)
				sb[j].append(s.charAt(i++));
			for (int j = numRows - 2; j >= 1 && i < len; j--)
				sb[j].append(s.charAt(i++));
		}
		for (i = 1; i < numRows; i++)
			sb[0].append(sb[i]);
		return sb[0].toString();
	}
} 
