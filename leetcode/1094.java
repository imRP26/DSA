/*
 * Question Link -> https://leetcode.com/problems/car-pooling/
 */



// Simple Simulation - TC = O(no. of trips * no. of stops), SC = O(no. of stops)
class Solution1 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] numPassengers = new int[1001];
        for (int[] arr : trips) { // O(10 ^ 3)
            int fromPos = arr[1], toPos = arr[2];
            while (fromPos < toPos) { // O(10 ^ 3)
                numPassengers[fromPos] += arr[0];
                if (numPassengers[fromPos] > capacity)
                    return false;
                fromPos++;
            }
        }
        return true;
    }
}



/*
 * Process all trips, adding passenger count to the start location, and 
 * removing it from the end location. 
 * After processing all trips, a +ve value for the specific location tells that 
 * we are getting more passengers, a -ve value specifying more empty seats. 
 * Finally, scan all stops and check if we ever exceed our vehicle capacity.
 */
class Solution2 {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] stops = new int[1001];
        for (int[] trip : trips) {
            stops[trip[1]] += trip[0]; // more passengers to be accommodated
            stops[trip[2]] -= trip[0]; // more empty seats
        }
        for (int i = 0; i <= 1000 && capacity >= 0; i++)
            capacity -= stops[i];
        return capacity >= 0;
    }
}



/*
 * Aliter explanation of above :- 
 * Process all trips, adding the passenger count to the start location and 
 * subtracting it from the end. 
 * A +ve value for a certain location after processing all trips indicates that 
 * we are getting more passengers and a -ve value indicates that we are getting 
 * more empty seats.
 * Finally, go over all of the stops to see if we've ever exceeded our vehicle's 
 * capacity.
 */
