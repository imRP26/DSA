/*
 * https://leetcode.com/problems/boats-to-save-people/
 */



/*
 * Solution from Official LC Editorial
 */
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int result = 0, low = 0, high = people.length - 1;
        while (low <= high) {
            result++;
            //if (low == high) -> this condition isn't compulsory!
            //    break;
            if (people[low] + people[high] <= limit)
                low++;
            high--;
        }
        return result;
    }
}
