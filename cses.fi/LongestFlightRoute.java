/*
 * https://cses.fi/problemset/task/1680
 */
import java.io.*;
import java.util.*;


/*
 * The DP Approach works for now, the approach due to Dijkstra's gives TLE!
 */
public class LongestFlightRoute {

	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	private static int[] dp, parent;
	private static boolean[] vis;

	private static void dfs(int node) {
		vis[node] = true;
		for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
			if (!vis[neighbor])
				dfs(neighbor);
            // The 2nd condition in the below if condition helps in pruning out unnecessary paths, 
            // which don't lead to the final node
			if (dp[node] < 1 + dp[neighbor] && dp[neighbor] != -1) {
				dp[node] = 1 + dp[neighbor];
				parent[node] = neighbor;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int nodes = Integer.parseInt(temp[0]), edges = Integer.parseInt(temp[1]);
		while (edges-- > 0) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
		}
		dp = new int[nodes + 1];
		Arrays.fill(dp, -1);
		dp[nodes] = 1;
		parent = new int[nodes + 1];
		vis = new boolean[nodes + 1];
		dfs(1);
		if (dp[1] == -1)
			System.out.println("IMPOSSIBLE");
		else {
			int node = 1;
			System.out.println(dp[1]);
			while (node != 0) {
				System.out.println(node);
				node = parent[node];
			}
		}
	}

	/*
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int nodes = Integer.parseInt(temp[0]), edges = Integer.parseInt(temp[1]);
		Map<Integer, List<Integer> > graph = new HashMap<>();
		while (edges-- > 0) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
		}
		int[] dist = new int[nodes + 1], parent = new int[nodes + 1];
		Arrays.fill(dist, -1);
		dist[1] = 1;
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		while (!q.isEmpty()) {
			int node = q.poll();
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (dist[neighbor] < 1 + dist[node]) {
					dist[neighbor] = 1 + dist[node];
					parent[neighbor] = node;
					q.offer(neighbor);
				} 
			}
		}
		if (dist[nodes] == -1)
			System.out.println("IMPOSSIBLE");
		else {
			System.out.println(dist[nodes]);
			int node = nodes;
			List<Integer> path = new ArrayList<>();
			while (node != 0) {
				path.add(node);
				node = parent[node];
			}
			Collections.reverse(path);
			for (int i = 0; i < path.size(); i++)
				System.out.print(path.get(i) + " ");
		}
	}
	*/
}
