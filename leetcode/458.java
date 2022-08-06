/*
 * https://leetcode.com/problems/poor-pigs/
 */



// Refer to Leetcode's official explanation
class Solution1 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int states = minutesToTest / minutesToDie + 1;
        return (int)(Math.ceil(Math.log(buckets) / Math.log(states)));
    }
}



/*
 * Similar storyline that could have been given by LeetCode :- 
 * Given N sources with exactly 1 of them sending bad signal. You are given x 
 * receivers to detect which source is sending a bad signal. A receiver can be 
 * configured to pick up signals from any number of specified sources. The bad 
 * signal will permanently damage a receiver within "minutesToDie" minutes 
 * after its received. Find the minimum "x" if given "minutesToTest" minutes to 
 * test. 
 */
