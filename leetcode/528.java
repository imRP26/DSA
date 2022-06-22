import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/random-pick-with-weight/
 */



// Concept of Prefix Sum and Binary Search
class Solution1 {
    
    int[] cumulativeSum;
    Random random = new Random();
    
    public Solution1(int[] w) {
        int n = w.length;
        cumulativeSum = new int[n];
        cumulativeSum[0] = w[0];
        for (int i = 1; i < w.length; i++)
            cumulativeSum[i] = w[i] + cumulativeSum[i - 1];
        this.cumulativeSum = cumulativeSum;
    }
    
    public int binarySearch(int x) {
        int low = 0, high = cumulativeSum.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (cumulativeSum[mid] == x)
                return mid;
            if (cumulativeSum[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
    
    public int pickIndex() {
        int cSum = cumulativeSum[cumulativeSum.length - 1];
        int rnd = random.nextInt(cSum) + 1;
        return binarySearch(rnd);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */



// Same above concept applied in a different way
class Solution2 {

    Random random;
    int[] wSums;

    public Solution2(int[] w) {
        this.random = new Random();
        wSums = w.clone();
        Arrays.parallelPrefix(wSums, Integer::sum);
        this.wSums = wSums;
    }

    public int pickIndex() {
        int n = wSums.length;
        // generating random number in [1, cumulativeSum]
        int idx = random.nextInt(wSums[n - 1]) + 1;
        /*
         * This returns the index of the random number, if the number doesn't 
         * exist in the array, then it returns :- 
         *              -(the position in which it should have been) - 1
         */
        int index = Arrays.binarySearch(wSums, idx);
        return index >= 0 ? index : -index - 1;
    }
}



// Solution using TreeMap
class Solution3 {

    int count = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    Random rnd = new Random();
    
    public Solution3(int[] w) {
        for (int i = 0; i < w.length; i++) {
            count += w[i];
            map.put(count, i);
        }
    }

    public int pickIndex() {
        /*
         * TreeMap.ceilingKey()
         * This method is present in the TreeMap interface inside the java.util 
         * package. 
         * Its used to return the least key >= the key passed as an argument.
         * It accepts 1 parameter, which is the key which needs to be matched.
         * It returns either of the 2 values :- 
           (1) Key-value : The least key >= the key passed as an argument.
           (2) Null : If no such key exists.
         ********* 
         * TreeMap.higherKey()
         * This method of java.util.TreeMap class is used to return the least 
         * key strictly > the given key, or null if there is no such key.
         * It takes the key k as a parameter and returns the least key > key, 
         * or null if there is no such key.
         * It throws the NullPointerException if the specified key is null and 
         * this map uses natural ordering, or its comparator does not permit 
         * null keys.
         */
        //int key = map.ceilingKey(rnd.nextInt(count) + 1);
        int key = map.higherKey(rnd.nextInt(count));
        return map.get(key);
    }
}
