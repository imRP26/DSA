/*
 * https://leetcode.com/problems/course-schedule-ii/
 */



/*
 * Simple Topological Sorting using Kahn's algorithm
 */
class Solution1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses], result = new int[numCourses];
        int counter = 0;
        Map<Integer, List<Integer> > graph = new HashMap<>();
        for (int[] edge : prerequisites) {
            indegree[edge[0]]++;
            graph.computeIfAbsent(edge[1], val -> new ArrayList<>()).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] != 0)
                continue;
            queue.offer(i); 
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[counter++] = node;
            if (!graph.containsKey(node))
                continue;
            for (int neighbor : graph.get(node)) {
                if (--indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }
        if (counter != numCourses)
            return new int[] {};
        return result;
    }
}
