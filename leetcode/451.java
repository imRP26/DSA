import java.utils.*;

/*
 * Solution Link in LC Discuss for referral :-
 * https://leetcode.com/problems/sort-characters-by-frequency/discuss/93420/Java-O(n)-Bucket-Sort-Solution-O(nlogm)-PriorityQueue-Solution-easy-to-understand
 */



// Max-Priority Queue
class Solution1 {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        PriorityQueue<Map.Entry<Character, Integer> > maxPQ = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        maxPQ.addAll(map.entrySet()); // waow, new syntax learnt!!
        StringBuilder sb = new StringBuilder();
        while (!maxPQ.isEmpty()) {
            Map.Entry entry = maxPQ.poll();
            for (int i = 0; i < (int)(entry.getValue()); i++)
                sb.append(entry.getKey());
        }
        return sb.toString();
    }
}



/* 
 * Priority Queue solution modification - for same frequency, maintain 
 * the same sequence as the character sequence in the original string
 */
class Solution2 {
    public String frequencySort(String s) {
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c))
                map.put(c, new int[] {1, i});
            else {
                int[] frequencyAndSequence = map.get(c);
                frequencyAndSequence[0]++;
                map.put(c, frequencyAndSequence);
            }
        }
        PriorityQueue<Map.Entry<Character, int[]> > maxPQ = new PriorityQueue<>(
            (a, b) -> a.getValue()[0] == b.getValue()[0] ? a.getValue()[1] - b.getValue()[1] : 
                                                           b.getValue()[0] - a.getValue()[0]
        );
        maxPQ.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!maxPQ.isEmpty()) {
            Map.Entry<Character, int[]> entry = maxPQ.poll();
            for (int i = 0; i < entry.getValue()[0]; i++)
                sb.append(entry.getKey());
        }
        return sb.toString();
    }
}



// Bucketing Logic
class Solution3 {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        List<Character>[] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList<>();
            bucket[frequency].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int position = bucket.length - 1; position >= 0; position--) {
            if (bucket[position] == null)
                continue;
            for (char c : bucket[position]) {
                for (int i = 0; i < pos; i++)
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
