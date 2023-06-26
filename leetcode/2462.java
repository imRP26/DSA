/*
 * https://leetcode.com/problems/total-cost-to-hire-k-workers/
 */



/*
 * My approach using 1 heap
 */
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length, remStart = 0, remEnd = 0;
        long res = 0;
        Set<Integer> indices = new HashSet<>();
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> (a[0] != b[0]) ? a[0] - b[0] : a[1] - b[1]);
        for (int i = 0; i < candidates; i++) {
            minPQ.offer(new int[] {costs[i], i});
            indices.add(i);
        } 
        for (int i = n - 1; i >= n - candidates; i--) {
            if (indices.contains(i))
                break;
            indices.add(i);
            minPQ.offer(new int[] {costs[i], i});
        }
        while (k-- > 0) {
            int[] temp = minPQ.poll();
            res += temp[0];
            if (temp[1] >= 0 && temp[1] < candidates + remStart) {
                remStart++;
                int newIdx = candidates - 1 + remStart;
                if (newIdx < 0 || newIdx >= n || indices.contains(newIdx))
                    continue; 
                indices.add(newIdx);
                minPQ.offer(new int[] {costs[newIdx], newIdx});
            }
            else {
                remEnd++;
                int newIdx = n - candidates - remEnd;
                if (newIdx < 0 || newIdx >= n || indices.contains(newIdx))
                    continue;
                indices.add(newIdx);
                minPQ.offer(new int[] {costs[newIdx], newIdx});
            }
        }
        return res;
    }
}



/*
 * Approach of 2 <Integer> Priority Queues from LC Official Editorial 
 */
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length, nextHead = candidates, nextTail = n - candidates - 1;
        PriorityQueue<Integer> headWorkers = new PriorityQueue<>(), tailWorkers = new PriorityQueue<>();
        for (int i = 0; i < candidates; i++)
            headWorkers.offer(costs[i]);
        for (int i = Math.max(candidates, n - candidates); i < n; i++)
            tailWorkers.offer(costs[i]);
        long res = 0;
        while (k-- > 0) {
            if (tailWorkers.isEmpty() || (!headWorkers.isEmpty() && headWorkers.peek() <= tailWorkers.peek())) {
                res += headWorkers.poll();
                if (nextHead <= nextTail)
                    headWorkers.offer(costs[nextHead++]);
            }
            else {
                res += tailWorkers.poll();
                if (nextHead <= nextTail)
                    tailWorkers.offer(costs[nextTail--]);
            }
        }
        return res;
    }
}



/*
 * Same Approach as approach 1 of mine from above, but with different syntax
 * This was again from LC Official Editorial!
 */
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });
        int n = costs.length, nextHead = candidates, nextTail = n - candidates - 1;
        long res = 0;
        for (int i = 0; i < candidates; i++)
            minPQ.offer(new int[] {costs[i], 0});
        for (int i = Math.max(candidates, n - candidates); i < n; i++)
            minPQ.offer(new int[] {costs[i], 1});
        while (k-- > 0) {
            int[] currWorker = minPQ.poll();
            int currCost = currWorker[0], currID = currWorker[1];
            res += currCost;
            if (nextHead <= nextTail) {
                if (currID == 0)
                    minPQ.offer(new int[] {costs[nextHead++], 0});
                else
                    minPQ.offer(new int[] {costs[nextTail--], 1});
            }
        }
        return res;
    }
}
