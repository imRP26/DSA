import java.util.*;

/*
 * https://leetcode.com/problems/number-of-islands-ii/
 */



// Basic Union Find - fuck, what's even happening??
class Solution1 {

    int findParent(int[] parent, int u) {
        while (u != parent[u]) {
            parent[u] = parent[parent[u]];
            u = parent[u];
        }
        return u;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        List<Integer> answer = new ArrayList<>();
        int islands = 0;
        int[] parent = new int[m * n];
        Arrays.fill(parent, -1);
        for (int[] position : positions) {
            int node = position[0] * n + position[1];
            if (parent[node] != -1) {
                answer.add(islands);
                continue;
            }
            parent[node] = node; // adding a new island
            islands++; //assuming that new point is isolated island
            for (int[] direction : directions) {
                int x = direction[0] + position[0], y = direction[1] + position[1], 
                    neighbor = x * n + y;
                if (x < 0 || y < 0 || x >= m || y >= n || parent[neighbor] == -1)
                    continue;
                int neighborParent = findParent(parent, neighbor);
                /*
                 * In case of an inequality between neighborParent and node, we 
                 * assign node to be equal to neighborParent, and this is very 
                 * important since if we do the other way round, i.e., assign 
                 * neighborParent to be node, then the count of islands decreases 
                 * in those cases when it shouldn't decrease, e.g., assume that 
                 * for the position (x, y) we make it a land, now we go on checking 
                 * about the neighbors of (x, y) and it may so have happened that 
                 * they were already marked, now if node isn't made to be equal 
                 * to neighborParent, then island count decreases (wrongly) for 
                 * all those cells that don't have their parent equal to node.
                 */
                if (neighborParent != node) { // when the neighbor is in another island
                    parent[node] = neighborParent; // union-ing the 2 islands
                    node = neighborParent; // very important step
                    islands--;
                }
            }
            answer.add(islands);
        }
        return answer;
    }
}
