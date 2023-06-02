/*
 * https://leetcode.com/problems/word-break/
 */



/*
 * Recursion (Backtracking) Approach (TLE) from -> 
 * https://leetcode.com/problems/word-break/solutions/169383/solved-the-time-complexity-of-the-brute-force-method-should-be-o-2-n-and-prove-it-below/
 */
class Solution {

    private Set<String> words;

    private boolean backtracking(String s) {
        int len = s.length();
        if (len == 0)
            return true;
        for (int i = 1; i <= len; i++) {
            if (words.contains(s.substring(0, i)) && backtracking(s.substring(i)))
                return true;
        }
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        words = new HashSet<>(wordDict);
        return backtracking(s);
    }
}



/*
 * Approach of DP from -> 
 * https://leetcode.com/problems/word-break/solutions/43790/java-implementation-using-dp-in-two-ways/comments/42970
 * Also, explanation from -> 
 * https://leetcode.com/problems/word-break/solutions/43814/c-dynamic-programming-simple-and-fast-solution-4ms-with-optimization/
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        /*
		 * dp[i] = True, if s.substring(0, i) can be segmented into a space-separated 
         * sequence of 1 or more dictionary words, else, False.
		 */
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && set.contains(s.substring(j, i));
                if (dp[i])
                    break;
            }
        }
        return dp[len];
    }
}



/*
 * Approach of Top-Down DP (Memoization) from official LC Editorial 
 */
class Solution {
	
	private boolean memoization(String s, Set<String> words, int idx, Boolean[] dp) {
		int len = s.length();
		if (idx == len)
			return true;
		if (dp[idx] != null)
			return dp[idx];
		for (int i = idx + 1; i <= len; i++) {
			if (words.contains(s.substring(idx, i)) && memoization(s, words, i, dp))
				return dp[idx] = true;
		}
		return dp[idx] = false;
	}
	
	public boolean wordBreak(String s, List<String> wordDict) {
		return memoization(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
	}
}



/*
 * Approach of Trie + DP from -> 
 * https://leetcode.com/problems/word-break/solutions/43790/java-implementation-using-dp-in-two-ways/comments/43007
 */
class Solution {

    class TrieNode {
        
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }

    private TrieNode root = new TrieNode();

    private void addWord(String w) {
        TrieNode currNode = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (currNode.children[i] == null)
                currNode.children[i] = new TrieNode();
            currNode = currNode.children[i];
        }
        currNode.isWord = true;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict)
            addWord(w);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            TrieNode currNode = root;
            for (int j = i; currNode != null && j < len; j++) {
                currNode = currNode.children[s.charAt(j) - 'a'];
                if (currNode != null && currNode.isWord && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0]; 
    }
}



/*
 * Approach of BFS from Official LC Editorial
 */
class Solution {
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> words = new HashSet<>(wordDict);
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		int len = s.length();
		boolean[] visited = new boolean[len];
		while (!q.isEmpty()) {
			int startIdx = q.poll();
			if (visited[startIdx])
				continue;
			for (int endIdx = startIdx + 1; endIdx <= len; endIdx++) {
				if (words.contains(s.substring(startIdx, endIdx))) {
					q.offer(endIdx);
					if (endIdx == len)
						return true;
				}
			}
			visited[startIdx] = true;
		}
		return false;
	}
}
