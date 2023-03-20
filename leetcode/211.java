/*
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */



/*
 * Approach from official LC editorial
 */
class TrieNode {

	Map<Character, TrieNode> children;
	boolean word;

	public TrieNode() {
		this.children = new HashMap<>();
		this.word = false;
	}
}


class WordDictionary {

	private TrieNode trieRoot;

	public WordDictionary() {
		this.trieRoot = new TrieNode();
	}

	public void addWord(String word) {
		TrieNode node = this.trieRoot;
		for (char ch : word.toCharArray()) {
			if (!node.children.containsKey(ch))
				node.children.put(ch, new TrieNode());
			node = node.children.get(ch);
		}
		node.word = true;
	}

	private boolean searchInTrie(String word, TrieNode node) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (!node.children.containsKey(ch)) {
				if (ch == '.') {
					for (char x : node.children.keySet()) {
						TrieNode child = node.children.get(x);
						if (searchInTrie(word.substring(i + 1), child))
							return true;
					}
				}
				return false;
			}
			else 
				node = node.children.get(ch);
		}
		return node.word;
	}

	public boolean search(String word) {
		return searchInTrie(word, this.trieRoot);
	}
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */



/*
 * Approach from -> 
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/solutions/59554/my-simple-and-clean-java-code/?orderBy=most_votes
 */
class WordDictionary {

	class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isWord;
	}

	private TrieNode root = new TrieNode();

	public void addWord(String word) {
		TrieNode node = this.root;
		for (char c : word.toCharArray()) {
			int index = c - 'a';
			if (node.children[index] == null)
				node.children[index] = new TrieNode();
			node = node.children[index];
		}
		node.isWord = true;
	}

	private boolean match(char[] chars, int index, TrieNode node) {
		if (index == chars.length)
			return node.isWord;
		if (chars[index] == '.') {
			for (int i = 0; i < node.children.length; i++) {
				if (node.children[i] != null && match(chars, index + 1, node.children[i]))
					return true;
			}
            return false;
		}
		return node.children[chars[index] - 'a'] != null && match(chars, index + 1, node.children[chars[index] - 'a']);
	}

	public boolean search(String word) {
		return match(word.toCharArray(), 0, this.root);
	}
}
