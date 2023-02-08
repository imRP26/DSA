/*
 * https://leetcode.com/problems/course-schedule/
 */


 
/*
 * Simple solution using Topological Sorting by Kahn's algorithm
 */ 
class Solution1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer> > graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int index = 0;
        for (int[] prereq: prerequisites) {
            graph.computeIfAbsent(prereq[1], val -> new ArrayList<>()).add(prereq[0]);
            indegree[prereq[0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            index++;
            if (!graph.containsKey(node))
                continue;
            for (int neighbor : graph.get(node)) {
                if (--indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }
        return index == numCourses;
    }
}
