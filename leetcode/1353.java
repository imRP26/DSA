/*
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 */



/*
 * Approach of Priority Queue from -> 
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/solutions/510262/detailed-analysis-let-me-lead-you-to-the-solution-step-by-step/
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/solutions/1304132/priority-queue-step-by-step-explanation/
 */
class Solution {
    public int maxEvents(int[][] events) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int res = 0, n = events.length, i = 0;
        for (int d = 1; d <= 100000; d++) {
            while (!minPQ.isEmpty() && minPQ.peek() < d)
                minPQ.poll();
            while (i < n && events[i][0] == d)
                minPQ.offer(events[i++][1]);
            if (!minPQ.isEmpty()) {
                minPQ.poll();
                res++;
            }
        }
        return res;
    }
}



/*
 * Approach of Segment Tree from -> 
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/solutions/718239/thinking-process-greedy-segment-tree/
 */
class Solution {

    private int[] times = new int[400005];

    private void build(int idx, int low, int high) {
        if (low == high)
            times[idx] = low;
        else {
            int mid = low + (high - low) / 2;
            build(2 * idx, low, mid);
            build(2 * idx + 1, mid + 1, high);
            times[idx] = Math.min(times[2 * idx], times[2 * idx + 1]);
        }
    }

    private void update(int idx, int low, int high, int pos) {
        if (low == high)
            times[idx] = Integer.MAX_VALUE;
        else {
            int mid = low + (high - low) / 2;
            if (pos <= mid)
                update(2 * idx, low, mid, pos);
            else
                update(2 * idx + 1, mid + 1, high, pos);
            times[idx] = Math.min(times[2 * idx], times[2 * idx + 1]);
        }
    }

    private int getFirstAvailableDay(int idx, int low, int high, int left, int right) {
        if (left <= low && right >= high)
            return times[idx];
        int mid = low + (high - low) / 2;
        if (left <= mid) {
            int day = getFirstAvailableDay(2 * idx, low, mid, left, right);
            if (day != Integer.MAX_VALUE)
                return day;
        }
        if (mid < right) {
            int day = getFirstAvailableDay(2 * idx + 1, mid + 1, high, left, right);
            if (day != Integer.MAX_VALUE)
                return day;
        }
        return Integer.MAX_VALUE;
    }

    public int maxEvents(int[][] events) {
        int res = 0, n = (int)1e5;
        build(1, 1, n);
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        for (int[] event : events) {
            int firstAvailableDay = getFirstAvailableDay(1, 1, n, event[0], event[1]);
            if (event[0] <= firstAvailableDay && firstAvailableDay <= event[1]) {
                res++;
                update(1, 1, n, firstAvailableDay);
            }
        }
        return res;
    }
}



/*
 * This is what the question actually intends to ask :-
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/comments/2075217/
 */
