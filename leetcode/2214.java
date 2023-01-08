/*
 * https://leetcode.com/problems/minimum-health-to-beat-game/
 */



/*
 * Simple Approach of Prefix Sum
 */
class Solution {
    public long minimumHealth(int[] damage, int armor) {
        int maxDamage = -1, maxDamageIndex = -1, n = damage.length;
        long result = 0;
        for (int i = 0; i < n; i++) {
            if (maxDamage < damage[i]) {
                maxDamage = damage[i];
                maxDamageIndex = i;
            }
        }
        damage[maxDamageIndex] = Math.max(maxDamage - armor, 0);
        for (int i = 0; i < n; i++)
            result += (long)damage[i];
        return result + 1;
    }
}
