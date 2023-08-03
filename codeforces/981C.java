/*
 * https://codeforces.com/problemset/problem/981/C
 */
import java.io.*;
import java.util.*;


public class Main {

	private static Map<Integer, List<Integer> > graph = new HashMap<>();

	private static int bfs(int src, int par) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(src);
		int node = 0;
		Set<Integer> vis = new HashSet<>();
		while (!q.isEmpty()) {
			node = q.poll();
			if (vis.contains(node))
				continue;
			vis.add(node);
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (!vis.contains(neighbor) && neighbor != par)
					q.offer(neighbor);
			}
		}
		return node;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nodes = Integer.parseInt(br.readLine().trim()), count = 0, maxDegree = 0, maxDegreeNode = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i < nodes; i++) {
			String[] temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			map.put(u, map.getOrDefault(u, 0) + 1);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
			map.put(v, map.getOrDefault(v, 0) + 1);
		}
		for (int i = 1; i <= nodes; i++) {
			count += (map.get(i) > 2) ? 1 : 0;
			if (maxDegree < map.get(i)) {
				maxDegree = map.get(i);
				maxDegreeNode = i;
			}
		}
		if (count > 1)
			System.out.println("No");
		else {
			System.out.println("Yes");
			System.out.println(maxDegree);
			for (int neighbor : graph.get(maxDegreeNode)) {
				int farthestNode = bfs(neighbor, maxDegreeNode);
				System.out.println(maxDegreeNode + " " + farthestNode);
			}
		}
	}
}
