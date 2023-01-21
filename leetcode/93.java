/*
 * https://leetcode.com/problems/restore-ip-addresses/
 */



/*
 * Iterative Solution approach from 
 * https://leetcode.com/problems/restore-ip-addresses/solutions/30949/my-code-in-java/
 */
class Solution1 {

	private boolean isValid(String s) {
		int len = s.length();
		if (len > 3 || len == 0 || (s.charAt(0) == '0' && len > 1) || Integer.parseInt(s) > 255)
			return false;
		return true;
	}

	public List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<>();
		int len = s.length();
		for (int i = 1; i < 4 && i < len - 2; i++) {
			for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
				for (int k = j + 1; k < j + 4 && k < len; k++) {
					String s1 = s.substring(0, i), s2 = s.substring(i, j), s3 = s.substring(j, k), s4 = s.substring(k, len);
					if (isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4))
						result.add(s1 + '.' + s2 + '.' + s3 + '.' + s4);
				}
			}
		}
		return result;
	}
}



/*
 * DFS based Approach from 
 * https://leetcode.com/problems/restore-ip-addresses/solutions/30944/very-simple-dfs-solution/
 */
class Solution2 {

	int len;
	List<String> result = new ArrayList<>();

	private void dfs(String s, int index, String temp, int count) {
		if (count > 4)
			return;
		if (count == 4 && index == len)
			result.add(temp);
		for (int i = 1; i < 4; i++) {
			if (index + i > len)
				break;
			String s1 = s.substring(index, index + i);
			if ((s1.startsWith('0') && s1.length() > 1) || Integer.parseInt(s1) > 255)
				continue;
			dfs(s, index + i, temp + s1 + (count == 3 ? "" : '.'), count + 1);
		}
	}

	public List<String> restoreIpAddresses(String s) {
		len = s.length();
		if (len <= 12)
			dfs(s, 0, "", 0);
		return result;
	}
}
