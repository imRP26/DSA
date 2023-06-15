import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int nodes = Integer.parseInt(br.readLine());
		Map<Integer, Set<Integer> > graph = new HashMap<>();
		for (int i = 2; i <= nodes; i++) {
			int par = Integer.parseInt(br.readLine());
			graph.computeIfAbsent(par, k -> new HashSet<>()).add(i);
		}
		boolean flag = true;
		for (int i = 1; i <= nodes; i++) {
			if (graph.getOrDefault(i, Collections.emptySet()).isEmpty())
				continue;
			int leaves = 0;
			for (int x : graph.get(i)) {
				if (graph.getOrDefault(x, Collections.emptySet()).isEmpty())
					leaves++;
			}
			if (leaves < 3) {
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println("Yes");
		else 
			System.out.println("No");
	} 
}
