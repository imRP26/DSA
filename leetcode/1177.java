/*
 * https://leetcode.com/problems/can-make-palindrome-from-substring/
 */



/*
 * Approach 1 of Prefix Sums from 
 * https://leetcode.com/problems/can-make-palindrome-from-substring/solutions/371849/java-python-3-3-codes-each-prefix-sum-boolean-and-xor-of-characters-frequencies-then-compare/
 */
class Solution1 {
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		int len = s.length();
		int[][] prefixCounts = new int[len + 1][26];
		for (int i = 0; i < len; i++) {
			prefixCounts[i + 1] = prefixCounts[i].clone();
			prefixCounts[i + 1][s.charAt(i) - 'a']++;
		}
		List<Boolean> result = new ArrayList<>();
		for (int[] query : queries) {
			int diffPairs = 0;
			for (int i = 0; i < 26; i++)
				diffPairs += (prefixCounts[query[1] + 1][i] - prefixCounts[query[0]][i]) % 2;
			result.add(diffPairs / 2 <= query[2]);
		}
		return result;
	}
} 



/*
 * Approach 2 of Prefix Boolean from 
 * https://leetcode.com/problems/can-make-palindrome-from-substring/solutions/371849/java-python-3-3-codes-each-prefix-sum-boolean-and-xor-of-characters-frequencies-then-compare/
 */
class Solution2 {
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		int len = s.length();
		// odds[i][j]: within range [0...i) of s, if the count of (char)(j + 'a') is odd. 
		boolean[][] odds = new boolean[len + 1][26];
		for (int i = 0; i < len; i++) {
			odds[i + 1] = odds[i].clone();
			odds[i + 1][s.charAt(i) - 'a'] ^= true;
		}
		List<Boolean> result = new ArrayList<>();
		for (int[] query : queries) {
			int diffPairs = 0;
			for (int i = 0; i < 26; i++)
				diffPairs += ((odds[query[1] + 1][i] ^ odds[query[0]][i]) ? 1 : 0);
			result.add(diffPairs / 2 <= query[2]);
		}	
		return result;
	}
}



/*
 * Approach of Prefix XOR from 
 * https://leetcode.com/problems/can-make-palindrome-from-substring/solutions/371849/java-python-3-3-codes-each-prefix-sum-boolean-and-xor-of-characters-frequencies-then-compare/
 */
class Solution3 {
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		int len = s.length();
		// odds[i] : within range [0 .. i) of s, the j'th bit of odd[i] indicates even / odd count of (char)(j + 'a').
		int[] odds = new int[len + 1];
		for (int i = 0; i < len; i++)
			odds[i + 1] = odds[i] ^ (1 << (s.charAt(i) - 'a'));
        List<Boolean> result = new ArrayList<>();
		for (int[] query : queries)
			// odds[q[1] + 1] ^ odds[q[0]] indicates the count of (char)(i + 'a') in substring(q[0], q[1] + 1) is even/odd.
			result.add(Integer.bitCount(odds[query[1] + 1] ^ odds[query[0]]) / 2 <= query[2]);
		return result;
	}
}
