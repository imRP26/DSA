/*
 * https://leetcode.com/problems/longest-happy-string/
 */



/*
 * Greedy, PriorityQueue based Approach from -> 
 * https://leetcode.com/problems/longest-happy-string/solutions/564276/java-greedy-solution-using-priorityqueue-with-explanation/comments/493720
 */
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        Queue<Pair<Character, Integer> > maxPQ = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        if (a > 0)
            maxPQ.offer(new Pair<>('a', a));
        if (b > 0)
            maxPQ.offer(new Pair<>('b', b));
        if (c > 0)
            maxPQ.offer(new Pair<>('c', c));
        while (!maxPQ.isEmpty()) {
            Pair<Character, Integer> first = maxPQ.poll();
            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == first.getKey()) {
                if (maxPQ.isEmpty())
                    return sb.toString();
                Pair<Character, Integer> second = maxPQ.poll();
                sb.append(second.getKey());
                if (second.getValue() > 1)
                    maxPQ.offer(new Pair<>(second.getKey(), second.getValue() - 1));
                maxPQ.offer(first);
            }
            else {
                int limit = Math.min(2, first.getValue());
                while (limit-- > 0)
                    sb.append(first.getKey());
                if (first.getValue() > limit)
                    maxPQ.offer(new Pair<>(first.getKey(), first.getValue() - limit));
            }
        }
        return sb.toString();
    }
}
