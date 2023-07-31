/*
 * https://cses.fi/problemset/task/1683/
 */
import java.io.*;
import java.util.*;


public class PlanetsAndKingdoms {
	
	private static Map<Integer, List<Integer> > graph = new HashMap<>(), revGraph = new HashMap<>();
	private static Set<Integer> vis = new HashSet<>();
	private static Stack<Integer> stack = new Stack<>();
	private static int[] res;
	
	private static void dfs1(int node) {
		vis.add(node);
		for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
			if (!vis.contains(neighbor))
				dfs1(neighbor);
		}
		stack.push(node);
	}
	
	private static void dfs2(int node, int root) {
		vis.add(node);
		res[node] = root;
		for (int neighbor : revGraph.getOrDefault(node, Collections.emptyList())) {
			if (!vis.contains(neighbor))
				dfs2(neighbor, root);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		int n = Integer.parseInt(temp[0]), m = Integer.parseInt(temp[1]), k = 0;
		res = new int[n + 1];
		while (m-- > 0) {
			temp = br.readLine().trim().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k1 -> new ArrayList<>()).add(v);
			revGraph.computeIfAbsent(v, k1 -> new ArrayList<>()).add(u);
		}
		for (int i = 1; i <= n; i++) {
		    if (!vis.contains(i))
		        dfs1(i);
		}
		vis.clear();
		while (!stack.isEmpty()) {
			int node = stack.pop();
			if (vis.contains(node))
				continue;
			k += 1;
			dfs2(node, k);
		}
		System.out.println(k);
		for (int i = 1; i <= n; i++)
		    System.out.print(res[i] + " ");
	}
}
