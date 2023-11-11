/*
 * https://cses.fi/problemset/task/1681/
 */
import java.io.*;
import java.util.*;

/*
 * Approach from -> 
 * https://www.youtube.com/watch?v=g_1qUIlpuzg&list=PL2S6Mj7iLqEjNVq0e-pZ9rSnpAacHzVm3&index=24
 *
 * First of all, we need to prune some unnecessary nodes - in this Topological Sorting helps!
 * After that, we can observe that number of ways reaching a particular node = summation of the 
 * number of ways to reach its parents. 
 */
public class GameRoutes {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		final int mod = (int)1e9 + 7;
		int nodes = Integer.parseInt(temp[0]), edges = Integer.parseInt(temp[1]);
		int[] indegree = new int[nodes + 1], countPaths = new int[nodes + 1];
		Map<Integer, List<Integer> > graph = new HashMap<>();
		while (edges-- > 0) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			indegree[v]++;
		}
		// Pruning out unnecessary nodes
		Queue<Integer> q = new LinkedList<>();
		for (int i = 2; i < nodes; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}
		while (!q.isEmpty()) {
			int node = q.poll();
			if (node == 1) // this is a necessary node, so not to be processed in this section!
				continue;
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (--indegree[neighbor] == 0)
					q.offer(neighbor);
			}
		}
		q.offer(1);
		countPaths[1] = 1;
		while (!q.isEmpty()) {
			int node = q.poll();
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				countPaths[neighbor] = (countPaths[neighbor] + countPaths[node]) % mod;
				if (--indegree[neighbor] == 0)
					q.offer(neighbor);
			}
		}
		System.out.println(countPaths[nodes]);
	}
}
