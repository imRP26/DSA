/*
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/
 */



/*
 * Approach of TreeSet from 
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/solutions/2866931/data-stream-as-disjoint-intervals/
 */
class SummaryRanges1 {

    private Set<Integer> set;

    public SummaryRanges1() { // SC = O(N)
        set = new TreeSet<>();
    }
    
    public void addNum(int value) { // TC = O(log N)
        set.add(value);
    }
    
    public int[][] getIntervals() { // TC = O(N)
        if (set.isEmpty())
            return new int[0][2];
        List<int[]> intervals = new ArrayList<>();
        int leftBound = -1, rightBound = -1;
        for (Integer value : set) {
            if (leftBound < 0)
                leftBound = rightBound = value;
            else if (value == rightBound + 1)
                rightBound = value;
            else {
                intervals.add(new int[] {leftBound, rightBound});
                leftBound = rightBound = value;
            }
        }
        intervals.add(new int[] {leftBound, rightBound});
        return intervals.toArray(new int[0][0]); // very very important step for converting a List<int[]> to int[][]
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */ 



/*
 * Approach of TreeMap from 
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/solutions/2866931/data-stream-as-disjoint-intervals/
 */
  