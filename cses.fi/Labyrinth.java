/*
 * https://cses.fi/problemset/task/1193
 */
import java.io.*;
import java.util.*;


public class Labyrinth {
	
	private static int n, m, xStart, yStart, xEnd, yEnd, level;
	private static char[][] grid, movements;
	private static boolean[][] vis;
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {xStart, yStart});
		q.offer(new int[] {-1, -1});
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0], y = temp[1];
			if (x == -1 && y == -1) {
				if (!q.isEmpty())
					q.offer(temp);
				level++;
				continue;
			}
			if (vis[x][y])
				continue;
			vis[x][y] = true;
			if (x == xEnd && y == yEnd)
				return;
			if (y >= 1 && !vis[x][y - 1] && grid[x][y - 1] != '#') { // go LEFT
				movements[x][y - 1] = 'L';
				q.offer(new int[] {x, y - 1});
			}
			if (y + 1 < m && !vis[x][y + 1] && grid[x][y + 1] != '#') { // go RIGHT
				movements[x][y + 1] = 'R';
				q.offer(new int[] {x, y + 1});
			}
			if (x >= 1 && !vis[x - 1][y] && grid[x - 1][y] != '#') { // go UP
				movements[x - 1][y] = 'U';
				q.offer(new int[] {x - 1, y});
			}
			if (x + 1 < n && !vis[x + 1][y] && grid[x + 1][y] != '#') { // go DOWN
				movements[x + 1][y] = 'D';
				q.offer(new int[] {x + 1, y});
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().trim().split(" ");
		n = Integer.parseInt(temp[0]);
		m = Integer.parseInt(temp[1]);
		grid = new char[n][m];
		movements = new char[n][m];
		vis = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String row = br.readLine().trim();
			for (int j = 0; j < m; j++) {
				grid[i][j] = row.charAt(j);
				if (grid[i][j] == 'A') {
					xStart = i;
					yStart = j;
				}
				else if (grid[i][j] == 'B') {
					xEnd =i;
					yEnd = j;
				}
			}
			Arrays.fill(movements[i], '.');
		}
		bfs();
		if (vis[xEnd][yEnd]) {
			System.out.println("YES");
			System.out.println(level);
			StringBuilder sb = new StringBuilder();
			while (xEnd != xStart || yEnd != yStart) {
			    if (movements[xEnd][yEnd] == 'L') {
					yEnd += 1;
					sb.append('L'); 
				}
				else if (movements[xEnd][yEnd] == 'R') {
					yEnd -= 1;
					sb.append('R');
				}
				else if (movements[xEnd][yEnd] == 'U') {
					xEnd += 1;
					sb.append('U');
				}
				else {
					xEnd -= 1;
					sb.append('D');
				}
			}
			System.out.println(sb.reverse().toString());
		}
		else
			System.out.println("NO");
	}
} 
