/*
 * https://leetcode.com/problems/minimum-unique-word-abbreviation/
 */



/* 
 * Solution from the approach specified in -> 
 * https://leetcode.com/problems/minimum-unique-word-abbreviation/solutions/89885/trie-bruteforce/comments/210950
 */
class Solution {
	
	private class TrieNode {
		boolean isWord;
		TrieNode[] next = new TrieNode[26];
	}
	
	private TrieNode root = new TrieNode();
	
	private void buildTrie(String[] dict, int len) {
		for (String d : dict) {
			if (d.length() != len)
				continue;
			TrieNode currNode = root;
			for (char c : d.toCharArray()) {
				int idx = c - 'a';
				if (currNode.next[idx] == null)
					currNode.next[idx] = new TrieNode();
				currNode = currNode.next[idx];
			}
			currNode.isWord = true;
		}
	}
	
	private boolean findMatch(String s, TrieNode node, int pos, int count) {
		int n = s.length();
		if (node == null)
			return false;
		if (count != 0) {
			for (int i = 0; i < 26; i++) {
				if (findMatch(s, node.next[i], pos, count - 1))
					return true;
			}
			return false;
		}
		if (pos == n)
            return node.isWord ? true : false;
		int i = pos;
		while (i < n) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				count = count * 10 + c - '0';
				i++;
			}
			else
				break;
		}
		if (count == 0) {
			char c = s.charAt(i);
			return findMatch(s, node.next[c - 'a'], i + 1, 0);
		}
		return findMatch(s, node, i, count);
	}
	
	public String minAbbreviation(String target, String[] dictionary) {
        if (target == null || target.equals(""))
			return target;
		if (dictionary == null || dictionary.length == 0)
			return target.length() + "";
		int len = target.length();
		buildTrie(dictionary, len);
        List<String> abbreviations = new ArrayList<>();
		int[] arr = new int[len];
		for (int i = 0; i < (1 << len); i++) {
			int j = i;
			for (int k = 0; k < len; k++) {
				arr[k] = ((j & 1) == 1) ? 1 : 0;
				j >>= 1;
			}
			StringBuilder sb = new StringBuilder();
			j = 0;
			while (j < len) {
				if (arr[j] == 1)
					sb.append(target.charAt(j++));
				else {
					int num0 = 0;
					while (j < len && arr[j] == 0) {
						num0++;
						j++;
					}
					sb.append(num0 + "");
				}
			}
			abbreviations.add(sb.toString());
		}
		Collections.sort(abbreviations, (a, b) -> a.length() - b.length());
		for (String s : abbreviations) {
			if (!findMatch(s, root, 0, 0))
				return s;
		}
		return "";
    }
}
