/*
 * https://leetcode.com/problems/count-pairs-with-xor-in-a-range/
 */



/*
 * Approach from -> https://leetcode.com/problems/count-pairs-with-xor-in-a-range/solutions/1119752/java-trie-with-comments-o-15-n-truly-beats-100/
 */
class Solution {

    class TrieNode {  
        TrieNode[] children;
        int count;
        public TrieNode() {
            children = new TrieNode[2];
            count = 0;
        }
    }

    TrieNode root = new TrieNode();

    private void insertIntoTrie(int num) {
        TrieNode node = root;
        for (int i = 14; i >= 0; i--) {
            int x = (num >> i) & 1;
            if (node.children[x] == null)
                node.children[x] = new TrieNode();
            node.children[x].count++;
            node = node.children[x];
        }
    }

    private int countSmaller(int num, int k) {
        int pairs = 0;
        TrieNode node = root;
        for (int i = 14; i >= 0 && node != null; i--) {
            int x = (num >> i) & 1, y = (k >> i) & 1;
            if (y == 1) {
                /*
                 * Case 1 :- y = 1 and x = 1 => count all those numbers having 1 as the binary digit 
                 *                              in the current place and then proceed to subtree '0'.
                 * Case 2 :- y = 1 and x = 0 => count all those numbers having 0 as the binary digit in 
                 *                              the current place and then proceed to subtree '1'.
                 */
                if (node.children[x] != null)
                    pairs += node.children[x].count;
                node = node.children[1 - x];
            }
            /*
             * Case 1 :- y = 0 and x = 0 => all those numbers having 1 as the binary digit in the current 
             *                              place are invalid now and then proceed to subtree '0'.
             * Case 2 :- y = 0 and x = 1 => all those numbers having 0 as the binary digit in the current 
             *                              place are invalid now and then proceed to subtree '1'.
             */
            else
                node = node.children[x];
        }
        return pairs;
    }

    public int countPairs(int[] nums, int low, int high) {
        TrieNode root = new TrieNode();
        int res = 0;
        for (int num : nums) {
            res += countSmaller(num, high + 1) - countSmaller(num, low);
            insertIntoTrie(num);
        }
        return res;
    }
}
