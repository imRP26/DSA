/*
 * https://leetcode.com/problems/minimum-number-of-lines-to-cover-points/
 */



/*
 * Approach of Recursion from -> 
 * https://leetcode.com/problems/minimum-number-of-lines-to-cover-points/solutions/1745307/simple-java-solution-o-n-n-3-n-2/
 */
class Solution {

    private double findSlope(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        if (p1.getKey() == p2.getKey())
            return Double.MAX_VALUE;
        return (double)(p2.getValue() - p1.getValue()) / (p2.getKey() - p1.getKey());
    }

    private int recursion(List<Pair<Integer, Integer> > points) {
        //minExternal -> globally minimum w.r.t. all the points 
        int minExternal = Integer.MAX_VALUE, n = points.size();
        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> p1 = points.get(i);
            //minInternal -> minimum value w.r.t. points.get(i)
            int count = 1, minInternal = Integer.MAX_VALUE;
            for (int j = i + 1; j < n; j++) {
                Pair<Integer, Integer> p2 = points.get(j);
                double slope12 = findSlope(p1, p2);
                List<Pair<Integer, Integer> > notIn = new ArrayList<>();
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j)
                        continue;
                    Pair<Integer, Integer> p3 = points.get(k);
                    double slope23 = findSlope(p2, p3);
                    if (slope23 != slope12)
                        notIn.add(p3);
                }
                if (notIn.size() > 0)
                    count += recursion(notIn);
                minInternal = Math.min(minInternal, count);
                count = 1;
            }
            minExternal = Math.min(minExternal, minInternal);
            minInternal = Integer.MAX_VALUE;
        }
        return (minExternal != Integer.MAX_VALUE) ? minExternal : 1;
    }

    public int minimumLines(int[][] points) {
        List<Pair<Integer, Integer> > pairs = new ArrayList<>();
        for (int[] point : points)
            pairs.add(new Pair(point[0], point[1]));
        return recursion(pairs);
    }
}



/*
 * Approach of DP from -> 
 * 
 */

