import java.util.*;

/*
 * Question Link -> 
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 */



/*
 * Link for understanding the Space Complexity ->
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/1722805/Java-A-very-detailed-explanation-with-sim.-understanding-or-Trie-+-Bit/1238974
 * 
 */
class Node {

    Map<Integer, Node> children;
    
    Node() {
        this.children = new HashMap<>();
    }
}


class Trie {

    Node root;

    Trie() {
        this.root = new Node();
    }

    public void insert(int[] arr) {
        for (int n : arr) {
            Node currNode = this.root;
            for (int i = 31; i >= 0; i--) {
                int currBit = (n >> i) & 1;
                if (!currNode.children.containsKey(currBit))
                    currNode.children.put(currBit, new Node());
                currNode = currNode.children.get(currBit);
            }
        }
    }
}


class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        trie.insert(nums);
        int result = 0;
        for (int n : nums) {
            Node currNode = trie.root;
            int xorval = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >> i) & 1;
                if (currNode.children.containsKey(1 - bit)) {
                    xorval |= 1 << i;
                    currNode = currNode.children.get(1 - bit);
                }
                else
                    currNode = currNode.children.get(bit);
            }
            result = Math.max(result, xorval);
        }
        return result;
    }
}



/*
 * Okay, maybe the previous solution was a bit overkill.
 * When we want to handle individual bits in a logical instead of an arithmetic 
 * way, we should generally use the logical shift (>>>) instead of the 
 * arithmetic shift (>>) because the arithmetic shift doesn't shift the sign 
 * bit, but instead copies it to the next bit.
 */
class TrieNode {
    TrieNode[] children;
    
    public TrieNode() {
        children = new TrieNode[2];
    }
}


class Trie1 {
    TrieNode root;

    Trie1() {
        this.root = new TrieNode();
    }

    public void insert(int[] nums) {
        for (int n : nums) {
            TrieNode currNode = this.root;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >>> i) & 1;
                if (currNode.children[bit] == null)
                    currNode.children[bit] = new TrieNode();
                currNode = currNode.children[bit];
            }
        }
    }
}


class Solution2 {
    public int findMaximumXOR(int[] nums) {
        if (nums.length == 1)
            return 0;
        if (nums.length == 2)
            return nums[0] ^ nums[1];
        Trie1 trie = new Trie1();
        trie.insert(nums);
        int result = 0;
        for (int n : nums) {
            TrieNode currNode = trie.root;
            int xorval = 0;
            for (int i = 31; i >= 0; i--) {
                int bit = (n >>> i) & 1;
                if (currNode.children[1 - bit] != null) {
                    xorval |= 1 << i;
                    currNode = currNode.children[1 - bit];
                }
                else
                    currNode = currNode.children[bit];
            }
            result = Math.max(result, xorval);
        }
        return result;
    }
}



// Solution using Bit Manipulation and HashSet
class Solution3 {
    public int findMaximumXOR(int[] nums) {
        int result = 0, mask = 0;
        /*
         * "result" is a record of the largest XOR that we've gotten so far.
         * If its 11100 at i = 2, it means before we reach the last 2 bits, 
         * 11100 is the biggest XOR we have and we're going to explore whether 
         * we can get another two 1's and put them into "result".
         * This part is greedy, and since we're looking for the largest XOR value, 
         * we start from the very beginning, i.e., the 31st position of bits.
         */
        for (int i = 31; i >= 0; i--) {
            /*
             * The mask will grow like - 1000...00, 11000..00, 111..11.
             * For each iteration, we only care for the left parts.
             */
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int n : nums) {
                /*
                 * We only care about the left parts, e.g., if i = 2, then we have :-
                 * {1100, 1000, 0100, 0000} from {1110, 1011, 0111, 0010}
                 */
                int leftPartOfNum = mask & n;
                set.add(leftPartOfNum);
            }
            /*
             * If i = 1 and before this iteration, the "result" obtained till 
             * now is 1100, our wish is that the "result" will grow to 1110, so 
             * we try to find a candidate which can give us this "greedyTry" value.
             */
            int greedyTry = result | (1 << i);
            for (int leftPartOfNum : set) {
                /*
                 * This is the trickiest part, coming from the fact that if 
                 * a ^ b = c, then a ^ c = b.
                 * Now, we have the 'c' which is "greedyTry" and we have the 'a', 
                 * which is "leftPartOfNum".
                 * If we hope the formula a ^ b = c to be valid, then we need 
                 * the 'b', and to get 'b', we need a ^ c, so if a ^ c exists in 
                 * out set, then we're good to go.
                 */
                int anotherNum = leftPartOfNum ^ greedyTry;
                if (set.contains(anotherNum)) {
                    result = greedyTry;
                    break;
                }
            }
            /*
             * If unfortunately, we didn't get the "greedyTry", we still have our 
             * "result", so after this iteration, the "result" will stay at 1100.
             */
        }
        return result;
    }
}



// And then finally, the wholesome solution from Striver...
class Node1 {
	
	Node1[] links;
	
	public Node1() {
        links = new Node1[2];
	}
	
	boolean containsKey(int index) {
		return links[index] != null;
	}
	
	Node1 get(int index) {
		return links[index];
	}
	
	void put(int index, Node node) {
		links[index] = node;
	}
}


class Trie2 {

	private static Node1 root;
	
	// initializing the data structure here
	Trie2() {
		root = new Node1();
	}
	
	// inserting a word into the trie
	public static void insert(int num) {
		Node1 node = root;
		for (int i = 31; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (!node.containsKey(bit))
				node.put(bit, new Node());
			node = node.get(bit);
		}
	}
	
	public int getMax(int num) {
		Node1 node = root;
		int maxNum = 0;
		for (int i = 31; i >= 0; i--) {
			int bit = (num >> i) & 1;
			if (node.containsKey(1 - bit)) {
				maxNum = maxNum | (1 << i);
				node = node.get(1 - bit);
			}
			else
				node = node.get(bit);
		}
		return maxNum;
	}
}


class Solution4 {
	public int maxXOR(int n, int m, List<Integer> arr1, List<Integer> arr2) {
		Trie2 trie = new Trie2();
		for (int i = 0; i < n; i++)
			trie.insert(arr1.get(i));
		int result = 0;
		for (int i = 0; i < m; i++)
			result = Math.max(result, trie.getMax(arr2.get(i)));
		return result;
	}
}


class Main {
	public static void main(String[] args) {
		int n = 2, m = 3;
		List<Integer> arr1 = new ArrayList<>(Arrays.asList(new Integer[] {6, 8}));
		List<Integer> arr2 = new ArrayList<>(Arrays.asList(new Integer[] {7, 8, 2}));
		Solution4 obj = new Solution4();
		System.out.println(obj.maxXOR(n, m, arr1, arr2));
	}
}
