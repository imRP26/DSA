/*
 * https://cses.fi/problemset/task/1679/
 */
import java.io.*;
import java.util.*;


public class CourseSchedule {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int nodes = Integer.parseInt(temp[0]), edges = Integer.parseInt(temp[1]);
		Map<Integer, List<Integer> > graph = new HashMap<>();
		int[] indegree = new int[nodes + 1];
		for (int i = 1; i <= edges; i++) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			indegree[v]++;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= nodes; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}
		Set<Integer> vis = new HashSet<>();
		List<Integer> res = new ArrayList<>();
		while (!q.isEmpty()) {
			int node = q.poll();
			if (!vis.add(node))
				continue;
			res.add(node);
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (--indegree[neighbor] == 0)
					q.offer(neighbor);
			}
		}
		if (res.size() == nodes) {
			for (int node : res)
				System.out.print(node + " ");
		}
		else 
			System.out.print("IMPOSSIBLE");
	}
}
