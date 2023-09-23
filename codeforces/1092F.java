/*
 * https://codeforces.com/problemset/problem/1092/F
 */
import java.io.*;
import java.util.*;


public class Main {
	
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	private static int[] nodeval;
	private static long[] dp, subtreeSum;
	private static long totalSum = 0;
	
	private static long dfs1(int node, int parent) {
		long ans = nodeval[node];
		for (int child : graph.getOrDefault(node, Collections.emptyList())) {
			if (child != parent)
				ans += dfs1(child, node);
		}
		return subtreeSum[node] = ans;
	}
	
	private static void bfs(int src) {
		long ans = 0, level = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(src);
		q.offer(-1);
		Set<Integer> vis = new HashSet<>();
		while (!q.isEmpty()) {
			int node = q.poll();
			if (node == -1) {
				if (q.isEmpty())
					break;
				q.offer(-1);
				level++;
				continue;
			}
			if (!vis.add(node))
				continue;
			ans += level * nodeval[node];
			for (int child : graph.getOrDefault(node, Collections.emptyList())) {
				if (!vis.contains(child))
					q.offer(child);
			}
		}
		dp[src] = ans;
	}
	
	private static void dfs2(int node, int parent) {
		dp[node] = dp[parent] + totalSum - 2 * subtreeSum[node];
		for (int child : graph.getOrDefault(node, Collections.emptyList())) {
			if (child != parent)	
				dfs2(child, node);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(br.readLine().trim()), src = -1;
        long res = 0;
		String[] temp = br.readLine().trim().split(" ");
		nodeval = new int[nodes + 1];
		for (int i = 0; i < nodes; i++) {
			nodeval[i + 1] = Integer.parseInt(temp[i]);
			totalSum += nodeval[i + 1];
		}
		for (int i = 1; i < nodes; i++) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
			src = (src == -1) ? u : src;
		}
		if (src != -1) {
			subtreeSum = new long[nodes + 1];
			dfs1(src, 0);
			dp = new long[nodes + 1];
			bfs(src);
			for (int child : graph.getOrDefault(src, Collections.emptyList()))
				dfs2(child, src);
			for (int i = 1; i <= nodes; i++)
			    res = Math.max(res, dp[i]);
		}
		System.out.println(res);
    }  
}
