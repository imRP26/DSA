import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/interval-list-intersections/
 */



// My Naive Solution -> TC = O(n * m)
class Solution1 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[] > list = new ArrayList<>();
        for (int[] list1 : firstList) {
            for (int[] list2 : secondList) {
                /* 
                 * No point in further looping, since interval values are anyways 
                 * going to increase, so no further intersection is possible.
                */
                if (list1[1] < list2[0]) 
                    break;
                /*
                 * somewhere down the line we may get some intersection, hence 
                 * continue looping
                 */
                if (list1[0] > list2[1])
                    continue;
                int startInterval = Math.max(list1[0], list2[0]);
                int endInterval = Math.min(list1[1], list2[1]);
                list.add(new int[] {startInterval, endInterval});
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}



// 2-Pointer bases solution -> TC = O(n + m)
class Solution2 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
		List<int[]> result = new ArrayList<>();
		int firstPtr = 0, secondPtr = 0, start, end;
		while (firstPtr < firstList.length && secondPtr < secondList.length) {
			start = Math.max(firstList[firstPtr][0], secondList[secondPtr][0]);
			end = Math.min(firstList[firstPtr][1], secondList[secondPtr][1]);
			if (end >= start) // check for a valid itersection
				result.add(new int[] {start, end});
            // no more intersection possible with the current element of firstList    
			if (firstList[firstPtr][1] == end)
				firstPtr++;
             // no more intersection possible with the current element of secondList
			if (secondList[secondPtr][1] == end)
				secondPtr++;
		}
		return result.toArray(new int[result.size()][2]);
	}
}



// Method of Binary Search -> TC = O(M * log (N))
class Solution3 {

	// find the minimum element that's not < target (in firstList) from secondList
	public int binarySearch(int[][] secondList, int low, int high, int target) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (secondList[mid][1] >= target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	/*
	 * Let's go step-by-step, shall we?
	 * 2 conditions for intersection that are satisfied during and after the 
	   "binarySearch()" call :- 
	 * (i) during the "binarySearch()" call -> 
						secondList[position][1] >= firstList[firstPtr][0]
	 * (ii) just after the "binarySearch()" call -> 
						secondList[position][0] <= firstList[firstPtr][1]
	 * Prior to this, if we have no such "position" during the "binarySearch()" 
	   call, i.e., for all the permissible indices, we have 
	   "secondList[position][1] < firstList[firstPtr][0]", then it does 
	   mean that no further interval intersection can be obtained from the 2 
	   lists.
	 * Now, we can assume that since we've come this far, then the 
	   "binarySearch()" call was valid. So, we can move forward with the 2 
	   checks (for increment of firstPtr and / or lastPtr) after the interval 
	   intersection checks :- 
	 * (i) firstList[firstPtr][0] <= secondList[position][1] (during binarySearch())
	       firstList[firstPtr][1] > secondList[position][1] (1st check)
		   e.g., firstList[firstPtr] = {1, 9} and secondList[position] = {5, 7}.
		   Thus, there's no more intersection possible between "firstList[firstPtr]" 
		   and "secondList[position]".
		   Hence, we increment "secondPtr", and not "firstPtr", since in case of the 
		   latter, there can never be any intersection possible.
	 * (ii) firstList[firstPtr][0] <= secondList[position][1] (during binarySearch())
		    firstList[firstPtr][1] <= secondList[position][1] (2nd check)
			e.g., firstList[firstPtr] = {1, 5} and secondList[position] = {4, 9}.
			Thus, there's no more intersection possible between "firstList[firstPtr]" 
			and "secondList[position]".
			Hence, we increment "firstPtr" and update "secondPtr". 
			"firstPtr" incrementation is necessary, since if we just increment 
			"secondPtr", then definitely no interval intersection would be 
			possible, while there's some possibility of intersection between 
			"firstList[firstPtr + 1]" and "secondList[position]".
			"secondPtr" is updated to "position" because now the search window 
			for "binarySearch()" needs to be reduced because of the incrementation 
			of "firstPtr".
	*/
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
		List<int[]> result = new ArrayList<>();
		int firstSize = firstList.length, secondSize = secondList.length;
		if (firstSize > secondSize)
			return intervalIntersection(secondList, firstList);
		int firstPtr = 0, secondPtr = 0;
		while (firstPtr < firstSize) { // O(M)
			int target = firstList[firstPtr][0];
			int position = binarySearch(secondList, secondPtr, secondSize - 1, target); // O(log N)
			// no interval in secondList meets the condition that the interval's end >= target
			if (position == secondSize)
				break;
			if (secondList[position][0] <= firstList[firstPtr][1])
				result.add(new int[] {Math.max(firstList[firstPtr][0], secondList[position][0]), 
									  Math.min(firstList[firstPtr][1], secondList[position][1])});
			if (firstList[firstPtr][1] > secondList[position][1])
				secondPtr = position + 1;
			else {
				secondPtr = position;
				firstPtr += 1;
			}
		}
		return result.toArray(new int[result.size()][2]);
	}
}
