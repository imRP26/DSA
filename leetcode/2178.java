/*
 * https://leetcode.com/problems/maximum-split-of-positive-even-integers/
 */



/*
 * I AM NOT NON-TECHNICAL FFS!!!
 * FWIW, Greedy + Prefix Sums
 */
class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> result = new ArrayList<>();
        if (finalSum % 2 != 0)
            return result;
        /*
         * listSum -> sum of the elements present in the final list
         * sum -> basically the even elements of the list, starting from 2
         * cumulativeSum -> as a marker for not adding elements to the list beyond a certain point
         */
        long sum = 2, cumulativeSum = 2, listSum = 0;
        while (cumulativeSum <= finalSum) {
            result.add(sum);
            listSum += sum;
            sum += 2;
            cumulativeSum += sum;
        }
        /*
         * Upon verifying, got to know that the final list will always be of the form - 
         * {2, 4, 6, 8, ..., X} 
         */
        if (listSum < finalSum)
            result.set(result.size() - 1, result.get(result.size() -1) + finalSum - listSum);   
        return result; 
    }
}
