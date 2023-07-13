/*
 * https://cses.fi/problemset/task/1131
 */
import java.io.*;
import java.lang.*;
import java.util.*;

/*
 * Algorithm for finding the Diameter :-
 * (1) Choose any random node, do BFS and record the last node.
 * (2) Do BFS starting from the previously found last node.
 * (3) Record the diameter obtained, i.e., the distance from the source-node to the 
 *     last node found in the 2nd BFS.
 */
class TreeDiameter {
	
	private static Map<Integer, List<Integer> > graph = new HashMap<>();
	
	private static int[] bfs(int node) {
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> vis = new HashSet<>();
		q.offer(node);
		q.offer(-1);
		int level = 0, lastNode = 0;
		while (!q.isEmpty()) {
			node = q.poll();
			if (node == -1) {
				if (!q.isEmpty())
					q.offer(-1);
				level++;
				continue;
			}
			if (vis.contains(node))
				continue;
			vis.add(node);
			lastNode = node;
			for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
				if (!vis.contains(neighbor))
					q.offer(neighbor);
			}
		}
		return new int[] {lastNode, level - 1};
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] temp;
		for (int i = 1; i < n; i++) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
		}
		int[] arr = bfs(1);
		arr = bfs(arr[0]);
		System.out.println(arr[1]);
	}
}
