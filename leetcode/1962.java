/*
 * https://leetcode.com/problems/remove-stones-to-minimize-the-total/
 */



/*
 * https://leetcode.com/problems/remove-stones-to-minimize-the-total/solutions/2636179/remove-stones-to-minimize-the-total/?orderBy=most_votes
 */
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(Collections.reverseOrder());
        //PriorityQueue<Integer> minPQ = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < piles.length; i++) {
            if (piles[i] != 0)
                minPQ.add(piles[i]);
        }
        int minSum = 0;
        while (k-- > 0 && !minPQ.isEmpty()) {
            int x = minPQ.poll();
            x -= x / 2;
            if (x != 0)
                minPQ.add(x);
        }
        while (!minPQ.isEmpty())
            minSum += minPQ.poll();
        return minSum;
    }
}
