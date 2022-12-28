/*
 * https://leetcode.com/problems/minimum-operations-to-halve-array-sum/
 */



/*
 * Simple Max PQ
 * https://leetcode.com/problems/minimum-operations-to-halve-array-sum/solutions/1863888/java-python-3-priorityqueue-heap-w-brief-explanation-and-analysis/
 */ 
class Solution {
    public int halveArray(int[] nums) {
        PriorityQueue<Double> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        double arraySum = 0, tempSum = 0;
        int numOps = 0;
        for (int n : nums) {
            maxPQ.add((double)n);
            arraySum += (double)n;
        }
        tempSum = arraySum;
        while (2.0 * tempSum > arraySum) {
            double x = maxPQ.poll();
            tempSum -= x;
            x /= 2.0;
            tempSum += x;
            maxPQ.add(x);
            numOps++;
        }
        return numOps;
    }
}
