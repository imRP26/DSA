/*
 * https://leetcode.com/problems/detonate-the-maximum-bombs/
 */



/*
 * Generic graph construction and then BFS
 */
class Solution {

    private int bfs(int src, int nodes, Map<Integer, List<Integer> > map) {
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int num = 0;
        while (!queue.isEmpty()) {
            src = queue.poll();
            if (visited[src])
                continue;
            visited[src] = true;
            num++;
            if (!map.containsKey(src))
                continue;
            List<Integer> neighbors = map.get(src);
            for (int neighbor : neighbors) {
                if (!visited[neighbor])
                    queue.offer(neighbor);
            }
        }
        return num;
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length, result = 1;
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x1 = bombs[i][0], y1 = bombs[i][1], r1 = bombs[i][2];
            for (int j = i + 1; j < n; j++) {
                int x2 = bombs[j][0], y2 = bombs[j][1], r2 = bombs[j][2];
                float distance = (float)Math.sqrt(1.0 * (x1 - x2) * (x1 - x2) + 1.0 * (y1 - y2) * (y1 - y2));
                if (r1 * 1.0 >= distance) {
                    if (!map.containsKey(i))
                        map.put(i, new ArrayList<>());
                    map.get(i).add(j);
                }
                if (r2 * 1.0 >= distance) {
                    if (!map.containsKey(j))
                        map.put(j, new ArrayList<>());
                    map.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.max(result, bfs(i, n, map));
        return result;
    }
}



/*
 * Same bloody way of doing the above thing
 */
class Solution {

    private int bfs(int src, int nodes, Map<Integer, List<Integer> > map) {
        boolean[] visited = new boolean[nodes];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        int num = 0;
        while (!queue.isEmpty()) {
            src = queue.poll();
            if (visited[src])
                continue;
            visited[src] = true;
            num++;
            if (!map.containsKey(src))
                continue;
            List<Integer> neighbors = map.get(src);
            for (int neighbor : neighbors) {
                if (!visited[neighbor])
                    queue.offer(neighbor);
            }
        }
        return num;
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length, result = 1;
        Map<Integer, List<Integer> > map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long x1 = (long)bombs[i][0], y1 = (long)bombs[i][1], r1 = (long)bombs[i][2];
            for (int j = i + 1; j < n; j++) {
                long x2 = (long)bombs[j][0], y2 = (long)bombs[j][1], r2 = (long)bombs[j][2];
                long sqDist = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                if (r1 * r1 >= sqDist) {
                    if (!map.containsKey(i))
                        map.put(i, new ArrayList<>());
                    map.get(i).add(j);
                }
                if (r2 * r2 >= sqDist) {
                    if (!map.containsKey(j))
                        map.put(j, new ArrayList<>());
                    map.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.max(result, bfs(i, n, map));
        return result;
    }
}



/*
 * Approach of Recursive DFS from Official LC Editorial
 */
class Solution {
	
	private Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private int dfs(int src, Set<Integer> visited) {
		visited.add(src);
		int count = 1;
		for (int neighbor : graph.getOrDefault(src, new ArrayList<>())) {
			if (!visited.contains(neighbor))
				count += dfs(neighbor, visited);
		}
		return count;
	}
	
	public int maximumDetonation(int[][] bombs) {
		int n = bombs.length;
		for (int i = 0; i < n; i++) {
			int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				int xj = bombs[j][0], yj = bombs[j][1];
				if ((long)ri * ri >= (long)(xi - xj) * (xi - xj) + (long)(yi - yj) * (yi - yj))
					graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j); 
			}
		}
		int result = 0;
		for (int i = 0; i < n; i++)
			result = Math.max(result, dfs(i, new HashSet<>()));
        return result;
	}
}



/*
 * Approach of Iterative DFS from official LC Editorial
 */
class Solution {

    private Map<Integer, List<Integer> > graph = new HashMap<>();

    private int dfs(int src) {
        Stack<Integer> st = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        st.push(src);
        visited.add(src);
        while (!st.isEmpty()) {
            src = st.pop();
            for (int neib : graph.getOrDefault(src, new ArrayList<>())) {
                if (!visited.contains(neib)) {
                    visited.add(neib);
                    st.push(neib);
                }
            }
        }
        return visited.size();
    }

    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length, result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2], xj = bombs[j][0], yj = bombs[j][1], rj = bombs[j][2];
                if ((long)ri * ri >= (long)(xi - xj) * (xi - xj) + (long)(yi - yj) * (yi - yj))
                    graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                if ((long)rj * rj >= (long)(xj - xi) * (xj - xi) + (long)(yj - yi) * (yj - yi))
                    graph.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
            }
        }
        for (int i = 0; i < n; i++)
            result = Math.max(result, dfs(i));
        return result;
    }
}
