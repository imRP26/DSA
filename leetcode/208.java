/*
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 */



/*
 * Approach 1 from -> 
 * https://leetcode.com/problems/implement-trie-prefix-tree/solutions/58966/java-my-solution-with-brief-explanations-15ms-beats-95/?orderBy=most_votes&page=2
 */
class TrieNode {
	
	boolean isWord;
	TrieNode[] children;
	
	public TrieNode() {
		this.isWord = true;
		this.children = new TrieNode[26];
	}
}


class Trie {
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode currentNode = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (currentNode.children[index] == null) {
				currentNode.children[index] = new TrieNode();
				currentNode.children[index].isWord = false;
			}
			currentNode = currentNode.children[index];
		}
		currentNode.isWord = true;
	}
	
    public boolean verifyExistence(String w, int option) {
        TrieNode currentNode = root;
        for (Character c : w.toCharArray()) {
            int index = c - 'a';
            if (currentNode.children[index] == null)
                return false;
            currentNode = currentNode.children[index];
        }
        if (option == 1)
            return currentNode.isWord;
        return true;
    }

	public boolean search(String word) {
        /*
		TrieNode currentNode = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (currentNode.children[index] == null)
				return false;
			currentNode = currentNode.children[index];
		}
		return currentNode.isWord;
        */
        return verifyExistence(word, 1);
	}
	
	public boolean startsWith(String prefix) {
        /*
		TrieNode currentNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			int index = prefix.charAt(i) - 'a';
			if (currentNode.children[index] == null)
				return false;
			currentNode = currentNode.children[index];
		}
		return true;
        */
        return verifyExistence(prefix, 2);
	}
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



/*
 * A Good NOTE about Default ACCESS SPECIFIER in Java :- 
 * https://stackoverflow.com/questions/3530065/what-is-the-default-access-specifier-in-java
 */
 