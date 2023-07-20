/*
 * https://leetcode.com/problems/destroying-asteroids/
 */



/*
 * Sorting + Greedy Approach!
 */
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        Arrays.sort(asteroids);
        long m = mass;
        for (int a : asteroids) {
            if (m < a)
                return false;
            m += a;
        }
        return true;
    }
}
