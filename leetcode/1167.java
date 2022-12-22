/*
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */



// Simple minPQ
class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        for (int stick : sticks)
            minPQ.add(stick);
        int result = 0;
        while (minPQ.size() > 1) {
            int a = minPQ.remove(), b = minPQ.remove();
            result += (a + b);
            minPQ.add(a + b);
        }
        return result;
    }
}
