/*
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/
 */



/*
 * Simulation of what the question actually asks, Approach from -> 
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/solutions/1152460/java-queue-self-explanatory/comments/904775
 */
class Solution {
    public int findTheWinner(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            q.offer(i);
        while (q.size() > 1) {
            int removal = k - 1;
            while (removal-- > 0)
                q.offer(q.poll());
            q.poll();
        }
        return q.poll();
    }
}



/*
 * Approach 3 from -> 
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/solutions/1157717/java-full-solution-and-explanation/
 */

