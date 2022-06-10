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

	// find the minimum element that's not < target in secondList
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

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
		List<int[]> result = new ArrayList<>();
		int firstSize = firstList.length, secondSize = secondList.length;
		if (firstSize > secondSize)
			return intervalIntersection(secondList, firstList);
		int firstPtr = 0, secondPtr = 0;
		while (firstPtr < firstSize) {
			int target = firstList[firstPtr][0];
			int position = binarySearch(secondList, secondPtr, secondSize - 1, 
                                        target);
			/*
             * no interval in secondList meets the condition that the 
             * interval's end >= target.
            */
			if (position == secondSize)
				break;
			if (secondList[position][0] <= firstList[firstPtr][1])
				result.add(new int[] {Math.max(firstList[firstPtr][0], 
                                               secondList[position][0]), 
                                      Math.min(firstList[firstPtr][1], 
                                               secondList[position][1])});
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
