/*
 * Question Link -> 
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
*/



// Naive Solution - just simulating the problem statement
class Solution1 {
    public int maxProduct(String[] words) {
        int n = words.length, result = 0;
		for (int i = 0; i < n; i++) { // O(10^3)
			int[] arrI = new int[26];
            for (int k = 0; k < words[i].length(); k++) // O(10 ^ 3)
                arrI[words[i].charAt(k) - 'a']++;
			for (int j = 0; j < n; j++) { // O(10 ^ 3)
				if (i == j)
					continue;
				int[] arrJ = new int[26];
				for (int k = 0; k < words[j].length(); k++)
					arrJ[words[j].charAt(k) - 'a']++;
                boolean checkDuplicate = false;
				for (int k = 0; k < 26; k++) {
                    if (arrI[k] > 0 && arrJ[k] > 0) {
                        checkDuplicate = true;
                        break;
                    }
                }
				if (checkDuplicate)
					continue;
				result = Math.max(result, words[i].length() * words[j].length());
			}
		}
		return result;
    }
}



// Method based upon Bitmasking -> TC = O(n^2), SC = O(32 * n) ~ O(n)
class Solution2 {
    public int maxProduct(String[] words) {
        int n = words.length, result = 0;
		int[] bitmask = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < words[i].length(); j++)
				bitmask[i] |= (1 << (words[i].charAt(j) - 'a'));
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((bitmask[i] & bitmask[j]) == 0)
					result = Math.max(result, words[i].length() * words[j].length());
			}
		}
		return result;
    }
}
