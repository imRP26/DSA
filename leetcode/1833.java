/*
 * https://leetcode.com/problems/maximum-ice-cream-bars/
 */



/*
 * Simple Greedy
 */ 
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int result = 0;
        for (int coin : costs) {
            if (coin > coins)
                break;
            result++;
            coins -= coin;
        }
        return result;
    }
}
