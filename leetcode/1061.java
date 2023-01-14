/*
 * https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
 */



/* 
 * My AC version -> Graph construction + BFS run for each individual char in 
 * baseStr
 */ 
class Solution1 {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        Map<Character, Set<Character> > graph = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i), ch2 = s2.charAt(i);
            graph.computeIfAbsent(ch1, val -> new HashSet<>()).add(ch2);
            graph.computeIfAbsent(ch2, val -> new HashSet<>()).add(ch1);
        }
        String result = "";
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < baseStr.length(); i++) {
            char ch = baseStr.charAt(i), lowestChar = ch;
            visited.clear();
            Queue<Character> queue = new LinkedList<>();
            queue.offer(ch);
            while (!queue.isEmpty()) {
                char x = queue.poll();
                if (visited.contains(x) || !graph.containsKey(x))
                    continue;
                visited.add(x);
                for (char y : graph.get(x)) {
                    if (visited.contains(y))
                        continue;
                    queue.offer(y);
                    if (y < lowestChar)
                        lowestChar = y;
                }
            }
            result += lowestChar;
        }
        return result;
    }
}



/*
 * Pre-computing the lowest possible char for every char encountered in baseStr, 
 * but same method as above
 */
class Solution2 {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        Map<Character, Set<Character> > graph = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i), ch2 = s2.charAt(i);
            graph.computeIfAbsent(ch1, val -> new HashSet<>()).add(ch2);
            graph.computeIfAbsent(ch2, val -> new HashSet<>()).add(ch1);
        }
        Set<Character> visited = new HashSet<>();
        char[] charMapping = new char[26];
        Arrays.fill(charMapping, 'z');
        for (Map.Entry<Character, Set<Character> > entry : graph.entrySet()) {
            char ch = entry.getKey(), lowestChar = ch;
            Queue<Character> queue = new LinkedList<>();
            queue.offer(ch);
            visited.clear();
            while (!queue.isEmpty()) {
                char x = queue.poll();
                if (visited.contains(x) || !graph.containsKey(x))
                    continue;
                visited.add(x);
                for (char y : graph.get(x)) {
                    if (visited.contains(y))
                        continue;
                    queue.offer(y);
                    if (y < lowestChar)
                        lowestChar = y;
                }
            }
            charMapping[ch - 'a'] = lowestChar;
        }
        String result = "";
        for (int i = 0; i < baseStr.length(); i++) {
            char ch1 = baseStr.charAt(i), ch2 = charMapping[ch1 - 'a'];
            result += (ch1 < ch2) ? ch1 : ch2;
        }
        return result;
    }
}



/*
 * Approach using DSU from 
 * https://leetcode.com/problems/lexicographically-smallest-equivalent-string/solutions/2867563/lexicographically-smallest-equivalent-string/?orderBy=most_votes
 */
class Solution3 {

	int[] parent = new int[26];

	private int findParent(int x) {
		while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
		}
		return x;
	}

	private void performUnion(int x, int y) {
		x = findParent(x);
		y = findParent(y);
		if (x == y)
			return;
		if (x < y)
			parent[y] = x;
		else 
			parent[x] = y;
	}

	public String smallestEquivalentString(String s1, String s2, String baseStr) {
		for (int i = 0; i < 26; i++)
			parent[i] = i;
		for (int i = 0; i < s1.length(); i++)
			performUnion(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
		String result = "";
		for (char c : baseStr.toCharArray())
			result += (char)(findParent(c - 'a') + 'a');
		return result;
	}
}
