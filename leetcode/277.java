/*
 * Question Link -> https://leetcode.com/problems/find-the-celebrity/
*/



// Naive Solution - just simulating the problem statement
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
// boolean knows(int a, int b) returns true if a knows b
class Solution1 extends Relation { // TC = O(n ^ 2), SC = O(1)
    public int findCelebrity(int n) {
        int celebrity = -1;
        for (int i = 0; i < n; i++) {
            int peopleKnowMe = 0;
            for (int j = 0; j < n; j++) {
                if (knows(j, i))
                    peopleKnowMe++;
            }
            if (peopleKnowMe == n) {
                int peopleIKnow = 0;
                for (int j = 0; j < n; j++) {
                    if (knows(i, j))
                        peopleIKnow++;
                }
                if (peopleIKnow == 1) {
                    if (celebrity != -1)
                        return -1;
                    celebrity = i;
                }
            }
        }
        return celebrity;
    }
}



// Efficient Solution - eliminating candidates -> TC = O(n), SC = O(1)
/* The knows API is defined in the parent class Relation.
   boolean knows(int a, int b); */
class Solution2 extends Relation {
    public int findCelebrity(int n) {
        /*
         * The 1st pass is to know the candidate. If a particular candidate knows i, 
         * then the candidate is changed.
        */
        int candidate = 0;
        for (int i = 0; i < n; i++) {
            if (knows(candidate, i))
                candidate = i;
        }
        for (int i = 0; i < n; i++) { // final check for validity
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate)))
                return -1;
        }
        return candidate;
    }
}



// Stack Based Solution -> TC = O(n), SC = O(n)
/* The knows API is defined in the parent class Relation.
   boolean knows(int a, int b); 
*/
class Solution3 extends Relation {
    public int findCelebrity(int n) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) // putting all people to the stack
            stack.push(i);
        int a = 0, b = 0;
        while (stack.size() > 1) {
            a = stack.pop();
            b = stack.pop();
            if (knows(a, b)) // a knows b, so a is not the celebrity, but b may be
                stack.push(b);
            else
                stack.push(a); // a doesn't know b, so b is not the celebrity, but a may be
        }
        a = stack.pop();
        for (int i = 0; i < n; i++) {
            if (i != a && (knows(a, i)) || !knows(i, a))
                return -1;
        }
        return a;
    }
}



// 2-Pointers based solution -> TC = O(n), SC = O(1)
/* The knows API is defined in the parent class Relation.
   boolean knows(int a, int b); */
class Solution4 extends Relation {
    public int findCelebrity(int n) {
        int low = 0, high = n - 1;
        while (low < high) {
            // if "low" knows "high", then "low" can never be the celebrity
            if (knows(low, high)) 
                low++;
            else // if "low" doesn't know high, then "high" can never be the celebrity
                high--;
        }
        for (int i = 0; i < n; i++) {
            if (i != low && (knows(low, i) || !knows(i, low)))
                return -1;
        }
        return low;
    }
}



// Slight modification of the 2nd solution
/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
class Solution5 extends Relation {
    public int findCelebrity(int n) {
        int celebrity = 0;
		for (int i = 0; i < n; i++) { // excluding all those people who can't be a celebrity
			if (knows(celebrity, i))
				celebrity = i;
		}
		/*
		 * Now that we have a celebrity, we have 2 conditions :- 
		 * (1) The celebrity doesn't know anyone.
		 * (2) Everyone knows the celebrity.	
		*/
		for (int i = 0; i < n; i++) {
			/*
			 * For the people who came before the celebrity, we need to check that both 
			 * the above conditions hold.
			 * For the people who came after the celebrity, we only need to check that 
			 * the 2nd condition holds.
			*/
			if (i < celebrity) {
				if (!knows(i, celebrity) || knows(celebrity, i))
					return -1;
			}
			else {
				if (!knows(i, celebrity))
					return -1;
			}
		} 
		return celebrity;
    }
}
