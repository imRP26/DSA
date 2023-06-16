import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int nodes = Integer.parseInt(temp[0]), res = 0, maxConsCats = Integer.parseInt(temp[1]);
		Set<Integer> catNodes = new HashSet<>();
		temp = br.readLine().split(" ");
		for (int i = 1; i <= nodes; i++) {
			if (temp[i - 1].equals("1"))
				catNodes.add(i);
		}
		Map<Integer, Set<Integer> > graph = new HashMap<>();
		for (int i = 1; i < nodes; i++) {
			temp = br.readLine().split(" ");
			int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]);
			graph.computeIfAbsent(u, k -> new HashSet<>()).add(v);
			graph.computeIfAbsent(v, k -> new HashSet<>()).add(u);
		}
		Queue<int[]> q = new LinkedList<>();
		if (catNodes.contains(1))
			q.offer(new int[] {1, 1, -1});
		else 
			q.offer(new int[] {1, 0, -1});
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int node = arr[0], consCats = arr[1], parent = arr[2];
			if (graph.get(node).size() == 1 && node != 1) {
				res++;
				continue;
			}
			for (int neighbor : graph.get(node)) {
				if (parent == neighbor)
					continue;
				if (catNodes.contains(neighbor) && 1 + consCats <= maxConsCats)
					q.offer(new int[] {neighbor, 1 + consCats, node});
				else if (!catNodes.contains(neighbor))
					q.offer(new int[] {neighbor, 0, node});
			}
		}
		System.out.println(res);
	}
}
