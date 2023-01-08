/*
 * https://leetcode.com/problems/max-points-on-a-line/
 */



/*
 * Approach from 
 * https://leetcode.com/problems/max-points-on-a-line/solutions/2910679/max-points-on-a-line/
 * Concept of atan2 from this SO Link -> 
 * https://stackoverflow.com/questions/283406/what-is-the-difference-between-atan-and-atan2-in-c
 */ 
class Solution1 {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 1)
            return 1;
        int result = 2;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> count = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i != j)
                    count.merge(Math.atan2(points[j][1] - points[i][1], points[j][0] - points[i][0]), 1, Integer::sum);
            }
            result = Math.max(result, Collections.max(count.values()) + 1);
        }
        return result;
    }
}



/*
 * Approach from 
 * https://leetcode.com/problems/max-points-on-a-line/solutions/47098/accepted-java-solution-easy-to-understand/
 */
class Solution2 {

    private int computeGCD(int a, int b) {
        if (b == 0)
            return a;
        return computeGCD(b, a % b);
    }

    public int maxPoints(int[][] points) {
        int numPoints = points.length;
        if (numPoints < 3)
            return numPoints;
        int result = 2;
        for (int i = 0; i < numPoints; i++) {
            Map<String, Integer> map = new HashMap<>();
            //int samePoint = 1;
            for (int j = 0; j < numPoints; j++) {
                if (i != j) {
                    if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                        samePoint++;
                        continue;
                    }
                    int numerator = points[j][1] - points[i][1], denominator = points[j][0] - points[i][0];
                    int gcd = computeGCD(numerator, denominator);
                    String k = numerator / gcd + "_" + denominator / gcd;
                    if (map.containsKey(k))
                        map.put(k, map.get(k) + 1);
                    else 
                        map.put(k, 2);
                    //result = Math.max(result, map.get(k) + samePoint - 1);
                    result = Math.max(result, map.get(k));
                }
            }
            //result = Math.max(result, samePoint);
        }
        return result;
    }
}
