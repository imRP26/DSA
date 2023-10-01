/*
 * https://leetcode.com/problems/implement-trie-ii-prefix-tree/
 */



/*
 * Simple Jugaadu approach that worked wonders!!
 */
class Trie {

    class TrieNode {
        TrieNode[] children;
        int numWordsPartial, numWordsFull;
        TrieNode() {
            children = new TrieNode[26];
            numWordsPartial = 0;
            numWordsFull = 0;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                node.children[idx] = new TrieNode();
            node = node.children[idx];
            node.numWordsPartial++;
        }
        node.numWordsFull++;
    }
    
    public int countWordsEqualTo(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return 0;
            node = node.children[idx];
        }
        return node.numWordsFull;
    }
    
    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null)
                return 0;
            node = node.children[idx];
        }
        return node.numWordsPartial;
    }
    
    public void erase(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            node = node.children[idx];
            node.numWordsPartial--;
        }
        node.numWordsFull--;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */
