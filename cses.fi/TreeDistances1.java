/*
 * https://cses.fi/problemset/task/1132
 */
import java.io.*;
import java.lang.*;
import java.util.*;


/*
 * Key Concept here -> when we do BFS from a particular node, then the last node obtained is 1 of the 
 * endpoints of the diameter of the tree.
 */
public class TreeDistances1 {
	
	private static int n;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private static int bfs1(int node) {
		int farthestNode = 0;
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> vis = new HashSet<>();
		q.offer(node);
		while (!q.isEmpty()) {
			node = q.poll();
			if (vis.contains(node))
				continue;
			vis.add(node);
			farthestNode = node;
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (!vis.contains(neighbor))
					q.offer(neighbor);
			}
		}
		return farthestNode;
	}
	
	private static int[] bfs2(int node) {
		int[] dist = new int[n + 1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {node, 0});
		Set<Integer> vis = new HashSet<>();
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			node = temp[0];
			int d = temp[1];
			if (vis.contains(node))
				continue;
			vis.add(node);
			dist[node] = d;
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (!vis.contains(neighbor))
					q.offer(new int[] {neighbor, d + 1});
			}
		}
		return dist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		String[] temp;
		for (int i = 1; i < n; i++) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
		}
		int dep1 = bfs1(1), dep2 = bfs1(dep1);
		int[] dist1 = bfs2(dep1), dist2 = bfs2(dep2);
		for (int i = 1; i <= n; i++)
			System.out.print(Math.max(dist1[i], dist2[i]) + " ");
	}
}
