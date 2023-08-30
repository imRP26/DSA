/*
 * https://leetcode.com/problems/maximum-number-of-ones/
 */



/* 
 * Approaches from ->  
 * https://leetcode.com/problems/maximum-number-of-ones/solutions/377099/c-solution-with-explanation/
 * https://www.youtube.com/watch?v=FjIao84fqLg
 */
class Solution {
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        int[][] subGrid = new int[sideLength][sideLength];
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < width; j++)
        		subGrid[i % sideLength][j % sideLength]++;
        }
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < sideLength; i++) {
        	for (int j = 0; j < sideLength; j++)
        		maxPQ.offer(subGrid[i][j]);
        }
        int res = 0;
        while (maxOnes-- > 0)
        	res += maxPQ.poll();
       	return res;
    }
}
