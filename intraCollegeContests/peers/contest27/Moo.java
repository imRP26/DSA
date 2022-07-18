import java.util.*;

/*
 * https://binarysearch.com/problems/Moo
 */



// My Awesome Solution!
class Solution {
    public String solve(String cows) {
        List<Integer> chargingIndices = new ArrayList<>();
        char[] cowState = cows.toCharArray();
        int numCows = cowState.length;
        for (int i = 0; i < numCows; i++) { // store all the non-rest indices
            if (cowState[i] != '@')
                chargingIndices.add(i);
        }
        int numChargingIndices = chargingIndices.size();
        for (int i = 0; i < numChargingIndices; i++) {
            // easy case, make all the left rest positions as 'L'
            if (cowState[chargingIndices.get(i)] == 'L') {
                int j = chargingIndices.get(i) - 1;
                while (j >= 0 && cowState[j] == '@')
                    cowState[j--] = 'L';
            }
            else {
                // only twisted case, when we find an 'R' before an 'L'
                if (i < numChargingIndices - 1 && cowState[chargingIndices.get(i + 1)] == 'L') {
                    int low = chargingIndices.get(i) + 1, high = chargingIndices.get(i + 1) - 1;
                    while (low < high) {
                        cowState[low++] = 'R';
                        cowState[high--] = 'L';
                    }
                    i++;
                }
                else { // easy case, make all the right rest positions as 'R'
                    int j = chargingIndices.get(i) + 1;
                    while (j < numCows && cowState[j] == '@')
                        cowState[j++] = 'R';
                }
            }
        }
        return new String(cowState);
    }
}
