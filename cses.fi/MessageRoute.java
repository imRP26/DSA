/*
 * https://cses.fi/problemset/task/1667/
 */
import java.io.*;
import java.util.*;

public class MessageRoute {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String[] inputLine = bufferedReader.readLine().trim().split(" ");
		int nodes = Integer.parseInt(inputLine[0]);
		int edges = Integer.parseInt(inputLine[1]);
		int[] parent = new int[nodes + 1];
		Arrays.fill(parent, -1);
		parent[1] = 0;
		Map<Integer, List<Integer> > graph = new HashMap<>();
		while (edges-- > 0) {
			inputLine = bufferedReader.readLine().trim().split(" ");
			int u = Integer.parseInt(inputLine[0]);
			int v = Integer.parseInt(inputLine[1]);
			graph.computeIfAbsent(u, node -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, node -> new ArrayList<>()).add(u);
		}
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		while (!q.isEmpty()) {
			int node = q.poll();
			List<Integer> neighbors = graph.getOrDefault(node, Collections.emptyList());
			for (int neighbor: neighbors) {
			  if (parent[neighbor] != -1) {
					continue;
				}
				parent[neighbor] = node;
				q.offer(neighbor);
			}
		}
		if (parent[nodes] == -1) {
			System.out.println("IMPOSSIBLE");
		} else {
			int node = nodes;
			List<Integer> path = new ArrayList<>();
			while (parent[node] != -1) {
				path.add(node);
				node = parent[node];
			}
			int pathLength = path.size();
			System.out.println(pathLength);
			for (int i = pathLength - 1; i >= 0; i--) {
				System.out.print(path.get(i) + " ");
			}
		}
	}
}
