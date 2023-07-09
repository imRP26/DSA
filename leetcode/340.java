/* 
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 */



// My O(N) TC, O(K) SC based solution
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0)
            return 0;
        Map<Character, Integer> map = new HashMap<>();
        int low = 0, high = 0, res = 0;
        while (high < s.length()) {
            char c = s.charAt(high);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (low < high && map.size() > k) {
                char ch = s.charAt(low);
                map.put(ch, map.getOrDefault(ch, 0) - 1);
                if (map.get(ch) == 0)
                    map.remove(ch);
                low++;
            }
            res = Math.max(res, high - low + 1);
            high++;
        }
        return res;
    }
}



/*
 * This method is for a clarifying question -> the interviewer may say that the 
 * string is given as an infinite stream.
 * In this situation, we can no longer use "s.charAt(leftPointer)" because 
 * "leftPointer" can be infinitely far back ("k" maybe small, but we could have 
 * a ton of repeating characters), in which case, storing the entire, infinite 
 * window in memory would be impossible, which kinda breaks the purpose of using 
 * "s.charAt(leftPointer)".
 * Thence, the key idea is that we can't assume any portion of the input string is 
 * stored in memory.
 */
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k == 0)
			return 0;
		// for keeping track of a character's last occurence
        TreeMap<Integer, Character> lastOccurence = new TreeMap<>();
		/*
		 * for keeping track of each character in the current window and the position  
		 * of its last occurence 
		 */
		Map<Character, Integer> inWindow = new HashMap<>();
		int low = 0, high, result = 1, n = s.length();
		for (high = 0; high < n; high++) {
			char ch = s.charAt(high);
			// when the window size is about to be > k
			while (inWindow.size() == k && !inWindow.containsKey(ch)) { // O(1) avg case TC
				int firstRemIndex = lastOccurence.firstKey(); // O(log k)
				char outChar = lastOccurence.get(firstRemIndex);  
				inWindow.remove(outChar); // O(1) avg case TC
				lastOccurence.remove(firstRemIndex); // O(log k)
				low = firstRemIndex + 1;
			}
			if (inWindow.containsKey(ch)) // O(1) avg case TC
				lastOccurence.remove(inWindow.get(ch)); // O(log k)
			inWindow.put(ch, high);
			lastOccurence.put(high, ch); 
			result = Math.max(result, high - low + 1);
		}
		return result;
    }
}



// Solution using LinkedHashMap
class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k == 0)
			return 0;
		int low = 0, high = 0, n = s.length(), result = 0;
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
		for (high = 0; high < n; high++) {
			char ch = s.charAt(high);
			if (map.containsKey(ch))
				map.remove(ch);
			map.put(ch, high);
			if (map.size() > k) {
				/*
				 * The map doesn't extend the Collection interface, so it doesn't have its 
				 * own iterator. Since Set extends the Collection interface, we can get an 
				 * iterator to set of keys returned by keySet().  
				 * map.keySet() returns a set view of the keys contained in this map.
				 * map.keySet().iterator().next() gives the leftmost index among all the 
				 * latest / rightmost indices of all the unique chars in the sliding window.
				 */
				ch = map.keySet().iterator().next(); // O(1)
				/*
				 * Iterator<K> itr = map.keySet().iterator();
				 * while (itr.hasNext()) {
				 *     K key = itr.next();
			     *     V value = map.get(key);
			     *     System.out.println(key + " = " + value);
		         * }
				 */
				low = map.get(ch) + 1;
				map.remove(ch);
			}
			result = Math.max(result, high - low + 1);
		}
		return result;
	}
}



// Method of LRU cache - just an expanded version of LinkedHashMap
class Solution {
	
	class Node {
		Node next, previous;
		int position;
		char ch;
		Node(char ch, int position) {
			this.ch = ch;
			this.position = position;
		}
	}

	Map<Character, Node> map = new HashMap<>();
	Node head, tail;

	public Node removeNode(Node node) {
		map.remove(node.ch);
		if (node.next != null)
			node.next.previous = node.previous;
		if (node.previous != null)
			node.previous.next = node.next;
		if (node == head)
			head = node.next;
		if (node == tail)
			tail = node.previous;
		node.previous = null;
		node.next = null;
		return node;
	}

	public Node appendNode(Node node) {
		map.put(node.ch, node);
		if (tail == null)
			head = tail = node;
		else {
			tail.next = node;
			node.previous = tail;
			tail = node;
		}
		return node;
	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		int result = 0, low = 0, high, n = s.length();
		for (high = 0; high < n; high++) {
			char currChar = s.charAt(high);
			if (map.containsKey(currChar)) {
				Node node = map.get(currChar);
				node.position = high;
				removeNode(node);
				appendNode(node);
			}
			else {
				Node node = new Node(currChar, high);
				appendNode(node);
			}
			if (map.size() > k) {
				low = head.position + 1;
				removeNode(head);
			}
			result = Math.max(result, high - low + 1);
		}
		return result;
	}
}



/* 
 * Method of Binary Search :- 
 * If there exists a result with length i, then result with length j <= i 
 * exists.
 * On the contrary, if there is no substring with at most k characters of 
 * length j <= i, then there is no such substring with length i too.
 * The maximum possible result is the length of the given string and the minimum 
 * possible result is 0. 
 * It implies that binary search can be employed and appropriate answer for 
 * O(nlog(n)) time and O(k) space can be found out.
*/
class Solution {

	Map<Character, Integer> charMap = new HashMap<>();

	public boolean substringExists(char[] arr, int mid, int k) {
		if (arr.length <= k)
			return true;
		charMap.clear();
		for (int i = 0; i < arr.length; i++) {
			if (i >= mid) {
				if (charMap.size() <= k)
					return true;
				int frequency = charMap.get(arr[i - mid]);
				if (frequency == 1)
					charMap.remove(arr[i - mid]);
				else
					charMap.put(arr[i - mid], frequency - 1);
			}
			charMap.put(arr[i], charMap.getOrDefault(arr[i], 0) + 1);
		}
		return charMap.size() <= k;
	}

	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		char[] arr = s.toCharArray();
		int high = arr.length, low = 0, mid, result = 0;
		while (low <= high) {
			mid = low + (high - low) / 2;
			if (substringExists(arr, mid, k)) {
				result = Math.max(result, mid);
				low = mid + 1;
			}
			else
				high = mid - 1;
		}
		return result;
	}
}



/* 
 * Using LinkedHashSet -> 
 * A unique list of characters up to k in number is kept and the most recent 
 * index for each unique character is kept while iterating. 
 * When the length of characters starts exceeding k, then new length starting 
 * from the character with the lowest index in the unique list so far is 
 * computed.
 * TC = O(n), SC = O(k).
*/
class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k == 0)
			return 0;
		LinkedHashSet<Character> set = new LinkedHashSet<>();
		int length = 0, prevLength = 0, result = 0;
		char[] charArr = s.toCharArray();
		int[] index = new int[256];
		for (int i = 0; i < charArr.length; i++) {
			if (!set.add(charArr[i])) {
				set.remove(charArr[i]);
				set.add(charArr[i]);
			}
			length = prevLength + 1;
			if (set.size() > k) {
				char toRemove = set.iterator().next();
				set.remove(toRemove);
				length = i - index[toRemove];
			}
			index[charArr[i]] = i;
			prevLength = length;
			result = Math.max(result, length);
		}
		return result;
	}
}
