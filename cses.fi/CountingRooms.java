/*
 * https://cses.fi/problemset/task/1192
 */



/*
 * Simple BFS!
 */
import java.io.*;
import java.util.*;


public class CountingRooms {
	private static char[][] buildingMap;
	private static int rows;
	private static int columns;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String[] inputLine = bufferedReader.readLine().trim().split(" ");
		rows = Integer.parseInt(inputLine[0]);
		columns = Integer.parseInt(inputLine[1]);
		buildingMap = new char[rows][columns];
		for (int i = 0; i < rows; i++) {
			buildingMap[i] = bufferedReader.readLine().trim().toCharArray();
		}
		int numRooms = bfs();
		System.out.println(numRooms);
	}

	private static int bfs() {
		int rooms = 0;
		int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (buildingMap[row][column] == '#') {
					continue;
				}
				rooms++;
				Queue<int[]> q = new LinkedList<>();
				q.offer(new int[] {row, column});
				while (!q.isEmpty()) {
					int[] cell = q.poll();
					int x = cell[0];
					int y = cell[1];
					if (buildingMap[x][y] == '#') {
						continue;
					}
					buildingMap[x][y] = '#';
					for (int direction = 0; direction < 4; direction++) {
						int xNew = x + directions[direction][0];
						int yNew = y + directions[direction][1];
						if (isValidFloorCell(xNew, yNew)) {
							q.offer(new int[] {xNew, yNew});
						}
					}
				}
			}
		}
		return rooms;
	}

	private static boolean isValidFloorCell(int x, int y) {
		return x >= 0 && x < rows && y >= 0 && y < columns && buildingMap[x][y] == '.';
	}
}
