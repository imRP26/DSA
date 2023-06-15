/*
 * https://cses.fi/problemset/task/1674
 */
import java.io.*;
import java.lang.*;
import java.util.*;


/* 
 * The Java code gives TLE, but the CPP code gives AC!!
 * Solution can be referred from Karthik Arora's video here -> https://www.youtube.com/watch?v=fGznXJ-LTbI&list=PLb3g_Z8nEv1j_BC-fmZWHFe6jmU_zv-8s&t=34s
 */
public class Subordinates {

	private static int[] subtreeNodes;
	private static Map<Integer, List<Integer> > graph = new HashMap<>();

	private static int dfs(int node) {
		if (graph.get(node).isEmpty())
			return subtreeNodes[node] = 0;
		int count = 0;
		for (int neighbor : graph.get(node))
			count += 1 + dfs(neighbor);
		return subtreeNodes[node] = count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nodes = Integer.parseInt(br.readLine()), node = 2;
		String[] temp = br.readLine().split(" ");
		for (int i = 1; i <= nodes; i++)
			graph.put(i, new ArrayList<>());
		for (int i = 0; i < nodes - 1; i++) {
			int par = Integer.parseInt(temp[i]);
			graph.computeIfAbsent(par, k -> new ArrayList<>()).add(node++);
		}		
		subtreeNodes = new int[1 + nodes];
		Arrays.fill(subtreeNodes, -1);
		dfs(1);
		for (int i = 1; i <= nodes; i++)
			System.out.print(subtreeNodes[i] + " ");
	}
}
