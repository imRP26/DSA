import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
 */



// Extremely Naive Solution - TLE
class Solution1 {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int numQueries = queries.length;
        int[] answer = new int[numQueries];
        Arrays.sort(nums);
        for (int i = 0; i < numQueries; i++) {
            if (nums[0] > queries[i][1]) {
                answer[i] = -1;
                continue;
            }
            int result = 0;
            for (int n : nums) {
                if (n > queries[i][1])
                    break;
                result = Math.max(result, queries[i][0] ^ n);
            }
            answer[i] = result;
        }
        return answer;
    }
}



// Trie wala Solution from Striver
class TrieNode {
	TrieNode[] links;
	
	public TrieNode() {
		links = new TrieNode[2];
	}
	
	boolean containsKey(int index) {
		return links[index] != null;
	}
	
	TrieNode get(int index) {
		return links[index];
	}
	
	void put(int index, TrieNode trienode) {
		links[index] = trienode;
	}
}


class Trie {
	private static TrieNode root;
	
	Trie() {
		root = new TrieNode();
	}
	
	public static void insert(int num) {
		TrieNode trienode = root;
		for (int i = 31; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (!trienode.containsKey(bit))
				trienode.put(bit, new TrieNode());
			trienode = trienode.get(bit);
		} 
	}
	
	public int getMax(int num) {
		TrieNode trienode = root;
		int result = 0;
		for (int i = 31; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (trienode.containsKey(1 - bit)) {
				result = result | (1 << i);
				trienode = trienode.get(1 - bit);
			}
			else
				trienode = trienode.get(bit);
		}
		return result;
	}
}


class Solution2 {
    public int[] maximizeXor(int[] nums, int[][] queries) {
    	Arrays.sort(nums);
		int numQueries = queries.length;
		List<List<Integer>> offlineQueries = new ArrayList<List<Integer>>();
		for (int i = 0; i < numQueries; i++) {
			List<Integer> temp = new ArrayList<>();
			temp.add(queries[i][0]);
			temp.add(queries[i][1]);
			temp.add(i);
			offlineQueries.add(temp);
		}
		Collections.sort(offlineQueries, new Comparator<List<Integer>> () {
			@Override
			public int compare(List<Integer> a, List<Integer> b) {
				return a.get(1).compareTo(b.get(1));
			}
		});
		int index = 0, n = nums.length;
		Trie trie = new Trie();
		int[] result = new int[numQueries];
		Arrays.fill(result, -1);
		for (int i = 0; i < numQueries; i++) {
			while (index < n && nums[index] <= offlineQueries.get(i).get(1)) {
				Trie.insert(nums[index]);
				index++;
			}
			int queryIndex = offlineQueries.get(i).get(2);
			if (index != 0)
				result[queryIndex] = trie.getMax(offlineQueries.get(i).get(0));
		}
		return result;
    }
}



// A more Java-ic solution?
class Solution3 {

    class Trie {
        Trie[] children;

        public Trie() {
            children = new Trie[2];
        }
    }

    Trie root;

    public void insert(int num) {
        Trie currNode = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            if (currNode.children[bit] == null)
                currNode.children[bit] = new Trie();
            currNode = currNode.children[bit];
        }
    }

    public int findMax(int num) {
        int result = 0;
        Trie currNode = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            if (currNode.children[bit ^ 1] != null) {
                result |= 1 << i;
                currNode = currNode.children[bit ^ 1];
            }
            else
                currNode = currNode.children[bit];
        }
        return result;
    }

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length, numQueries = queries.length, index = 0;
        root = new Trie();
        int[][] offlineQueries = new int[numQueries][3];
        for (int i = 0; i < numQueries; i++) {
            offlineQueries[i][0] = queries[i][0];
            offlineQueries[i][1] = queries[i][1];
            offlineQueries[i][2] = i;
        }
        int[] result = new int[numQueries];
        Arrays.sort(nums);
        Arrays.sort(offlineQueries, (o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < numQueries; i++) {
            while (index < n && nums[index] <= offlineQueries[i][1]) {
                insert(nums[index]);
                index++;
            }
            if (index == 0)
                result[offlineQueries[i][2]] = -1;
            else
                result[offlineQueries[i][2]] = findMax(offlineQueries[i][0]);
        }
        return result;
    }
}



// Binary Search + 2-Pointers -> To be seen later
