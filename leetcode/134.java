/*
 * https://leetcode.com/problems/gas-station/
 */



/*
 * My Naive TLE Approach
 */ 
class Solution1 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startIndex, n = gas.length;
        boolean flag = false;
        for (startIndex = 0; startIndex < n; startIndex++) {
            int gasAmnt = gas[startIndex] - cost[startIndex];
            if (gasAmnt < 0)
                continue;
            int currIndex = (startIndex + 1) % n;
            while (currIndex != startIndex) {
                gasAmnt += gas[currIndex] - cost[currIndex];
                if (gasAmnt < 0)
                    break;
                currIndex = (currIndex + 1) % n;
            }
            if (currIndex == startIndex && gasAmnt >= 0) {
                flag = true;
                break;
            }
        }
        return flag ? startIndex : -1;
    }
}



/*
 * (Intuitive) Approach from 
 * https://www.youtube.com/watch?v=lJwbPZGo05A
 */
class Solution2 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, totalGas = 0, totalCost = 0, tank = 0, startIndex = 0;
        for (int i = 0; i < n; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }
        if (totalGas < totalCost)
            return -1;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++)
            diff[i] = gas[i] - cost[i];
        for (int i = 0; i < n; i++) {
            tank += diff[i];
            if (tank < 0) {
                startIndex = i + 1;
                tank = 0;
            }
        }
        return startIndex;
    }
}
