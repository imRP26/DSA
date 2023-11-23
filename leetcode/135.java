/*
 * https://leetcode.com/problems/candy/
 */



/*
 * Approach 1 from LC Official Editorial! - Simple simulation of the problem statement!
 */
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length, res = 0;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int i = 0; i < n; i++) {
                if (i < n - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = 1 + candies[i + 1];
                    hasChanged = true;
                }
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = 1 + candies[i - 1];
                    hasChanged = true;
                }
            }
        }
        for (int candy : candies)
            res += candy;
        return res;
    }
}



/*
 * Approach 2 from LC Official Editorial!
 */
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length, res = 0;
        int[] leftToRight = new int[n], rightToLeft = new int[n];
        Arrays.fill(leftToRight, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                leftToRight[i] = 1 + leftToRight[i - 1];
        }
        Arrays.fill(rightToLeft, 1);
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                rightToLeft[i] = 1 + rightToLeft[i + 1];
        }
        for (int i = 0; i < n; i++)
            res += Math.max(leftToRight[i], rightToLeft[i]);
        return res;
    }
}



/*
 * Approach 3 from LC Official Editorial!
 */
class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length, res = 0;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = 1 + candies[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], 1 + candies[i + 1]);
        }   
        for (int x : candies)
            res += x;
        return res;
    }
}



/*
 * Approach 4 from LC Official Editorial!
 */

