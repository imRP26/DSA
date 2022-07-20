import java.util.*;

/*
 * https://leetcode.com/problems/process-restricted-friend-requests/
 */



/*
 * DSU is used to link all the persons with mutual friends to 1 common ancestor.
 * Now, for every request, its checked whether those ancestors have any 
 * restrictions or not.
 * TC = O(numRequests * numRestrictions * (log(n)) * (log(n)))
 */
class Solution {

    public int findParent(int[] parent, int u) { // O(log(n))
        while (u != parent[u]) {
            parent[u] = parent[parent[u]];
            u = parent[u];
        }
        return u;
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        int numRequests = requests.length;
        boolean[] result = new boolean[numRequests];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) // O(N)
            parent[i] = i;
        for (int i = 0; i < numRequests; i++) { // O(numRequests)
            int firstParent = findParent(parent, requests[i][0]); // O(log N)
            int secondParent = findParent(parent, requests[i][1]); // O(log N)
            if (firstParent == secondParent) {
                result[i] = true;
                continue;
            }
            boolean flag = true;
            for (int j = 0; j < restrictions.length; j++) { // O(numRestrictions)
                int firstRestriction = findParent(parent, restrictions[j][0]); // O(log N)
                int secondRestriction = findParent(parent, restrictions[j][1]); // O(log N)
                if ((firstParent == firstRestriction && secondParent == secondRestriction) || 
                    (secondParent == firstRestriction && firstParent == secondRestriction)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result[i] = true;
                parent[secondParent] = firstParent;
            }
        }
        return result;
    }
}
