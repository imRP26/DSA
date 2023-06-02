/*
 * https://leetcode.com/problems/top-k-frequent-words/
 */



/*
 * Approach of Min Heap
 * TC = O(N log K), SC = O(N)
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : words)
            map.put(w, map.getOrDefault(w, 0) + 1);
        Queue<String> minPQ = new PriorityQueue<>((a, b) -> (map.get(a) == map.get(b)) ? (b.compareTo(a)) : (map.get(a) - map.get(b)));
        for (String w : map.keySet()) {
            minPQ.offer(w);
            if (minPQ.size() > k)
                minPQ.poll();
        }
        List<String> result = new ArrayList<>();
        while (k-- > 0)
            result.add(minPQ.poll());
        Collections.reverse(result);
        return result;
    }
}



/*
 * Approach of Bucket Sorting + Trie from LC official Editorial
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

    private int k;
    private List<String> result;
 
    private void addWord(TrieNode root, String w) {
        TrieNode node = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null)
                node.children[i] = new TrieNode();
            node = node.children[i];
        }
        node.isWord = true;
    }

    private void getWords(TrieNode root, String prefix) {
        if (k == 0)
            return;
        if (root.isWord) {
            result.add(prefix);
            k--;
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null)
                getWords(root.children[i], prefix + (char)(i + 'a'));
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        this.k = k;
        result = new ArrayList<>();
        int n = words.length;
        TrieNode[] bucket = new TrieNode[n + 1];
        Map<String, Integer> counts = new HashMap<>();
        for (String w : words)
            counts.put(w, counts.getOrDefault(w, 0) + 1);
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            int index = entry.getValue();
            if (bucket[index] == null)
                bucket[index] = new TrieNode();
            addWord(bucket[index], entry.getKey());
        }
        for (int i = n; i >= 0; i--) {
            if (bucket[i] != null)
                getWords(bucket[i], "");
            if (this.k == 0)
                break;
        }
        return result;
    }
}
