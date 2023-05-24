/*
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
 */



/*
 * Approach of Priority Queues, from this oh-so-wonderful video -> 
 * https://www.youtube.com/watch?v=o8emK4ehhq0 
 */
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length, sumQualities = 0;
        List<Pair<Double, Integer> > workers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double ratio = (double)wage[i] / quality[i];
            workers.add(new Pair(ratio, quality[i]));
        }
        Collections.sort(workers, (a, b) -> Double.compare(a.getKey(), b.getKey()));
        Queue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            int q = workers.get(i).getValue();
            maxPQ.offer(q);
            sumQualities += q;
        }
        double captainRatio = workers.get(k - 1).getKey(), minCost = captainRatio * sumQualities;
        for (int captain = k; captain < n; captain++) {
            captainRatio = workers.get(captain).getKey();
            int q = workers.get(captain).getValue();
            if (!maxPQ.isEmpty() && q < maxPQ.peek()) {
                sumQualities += q - maxPQ.poll();
                maxPQ.offer(q);
            }
            minCost = Math.min(minCost, sumQualities * captainRatio);
        }
        return minCost;
    }
}
