/*
 * https://cses.fi/problemset/task/1668/
 */
import java.io.*;
import java.util.*;

// Getting TLE, but correct approach! :(
public class BuildingTeams {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String[] inputLine = bufferedReader.readLine().trim().split(" ");
		int nodes = Integer.parseInt(inputLine[0]);
		int edges = Integer.parseInt(inputLine[1]);
		Map<Integer, List<Integer> > graph = new HashMap<>();
		while (edges-- > 0) {
			inputLine = bufferedReader.readLine().trim().split(" ");
			int u = Integer.parseInt(inputLine[0]);
			int v = Integer.parseInt(inputLine[1]);
			graph.computeIfAbsent(u, node -> new ArrayList<>()).add(v);
			graph.computeIfAbsent(v, node -> new ArrayList<>()).add(u);
		}
		int[] nodeColour = new int[nodes + 1];
		boolean isBipartitePossible = true;
		for (int node = 1; node <= nodes; node++) {
			if (nodeColour[node] != 0) {
				continue;
			}
			nodeColour[node] = 1;
			Queue<Integer> q = new LinkedList<>();
			q.offer(node);
			while (!q.isEmpty()) {
				int vertex = q.poll();
				List<Integer> neighbors = graph.getOrDefault(vertex, Collections.emptyList());
				for (int neighbor: neighbors) {
					if (nodeColour[neighbor] == nodeColour[vertex]) {
						isBipartitePossible = false;
						break;
					}
					if (nodeColour[neighbor] == 0) {
						nodeColour[neighbor] = 3 - nodeColour[vertex];
						q.offer(neighbor);
					}
				}
			}
			if (!isBipartitePossible) {
				break;
			}
		}
		if (!isBipartitePossible) {
			System.out.println("IMPOSSIBLE");
		} else {
			for (int i = 1; i <= nodes; i++) {
				System.out.print(nodeColour[i] + " ");
			}
		}
	}
}
