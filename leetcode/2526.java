/*
 * https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream/
 */



 /*
  * Using HashMap and Deque - maybe a bit of an overkill from my side!
  */
class DataStream1 {

    Map<Integer, Integer> map;
    Deque<Integer> dq;
    int value, k;
    
    public DataStream1(int value, int k) {
        dq = new ArrayDeque<>();
        map = new HashMap<>();
        this.value = value;
        this.k = k;
    }
    
    public boolean consec(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
        dq.offerFirst(num);
        if (dq.size() > k) {
            int x = dq.pollLast();
            map.put(x, map.get(x) - 1);    
            if (map.get(x) == 0)
                map.remove(x);
        }
        if (!map.containsKey(value))
            return false;
        if (map.get(value) == k && map.size() == 1)
            return true;
        return false;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */
  


 /*
  * Simple Implementation based approach from 
  * https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream/solutions/3015486/java-simple-code-with-explanation/?orderBy=most_votes
  */
class DataStream2 {
    
    int value, k, counter;

    public DataStream2(int value, int k) {
        this.value = value;
        this.k = k;
        counter = 0;
    }

    public boolean consec(int num) {
        if (value == num)
            counter++;
        else 
            counter = 0;
        return counter >= k;
    }
} 
