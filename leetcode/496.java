import java.util.*;

/*
 * Question Link -> https://leetcode.com/problems/next-greater-element-i/
*/



// Brute Force
class Solution1 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length, i, j = 0, n2 = nums2.length;
		int[] result = new int[n1];
		for (int n : nums1) {
			for (i = 0; i < n2; i++) {
				if (nums2[i] == n)
					break;
			}
			i += 1;
			while (i < n2) {
				if (nums2[i] > n) {
					result[j++] = nums2[i];
					break;
				}
				i++;
			}
			if (i == n2)
				result[j++] = -1;
		}
		return result;
    }
}



// Monotonic Stack
class Solution2 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        /*
		 * We use a stack to keep a decreasing sub-sequence, whenever  we see 
         * a number x > stack.peek(), we pop off all those elements < x and 
         * for all the popped ones, their next greater element is x.
		 * For example, for the array [9, 8, 7, 3, 2, 1, 6], the stack will 
         * first contain [9, 8, 7, 3, 2, 1] and then we see 6 which is > 1, 
         * so we pop off 1, 2, 3, whose next greater element should be 6.	
		*/
		Map<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		for (int n : nums2) {
			while (!stack.isEmpty() && stack.peek() < n)
				map.put(stack.pop(), n);
			stack.push(n);
		}
		for (int i = 0; i < nums1.length; i++)
			nums1[i] = map.getOrDefault(nums1[i], -1);
		return nums1;
    }
}



/* 
 * Early classes of the Java API, such as Vector, Hashtable and StringBuffer, 
 * were synchronized to make them thread-safe. Unfortunately, synchronization 
 * has a big negative impact on performance, even when using these 
 * collections from a single thread.
 * It is better to use their new unsynchronized replacements:
 *    ArrayList or LinkedList instead of Vector.
 *    Deque instead of Stack.
 *    HashMap instead of Hashtable
 *    StringBuilder instead of StringBuffer
*/
class Solution3 {
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		Deque<Integer> deque = new ArrayDeque<>();
		for (int n : nums2) {
			while (!deque.isEmpty() && deque.peek() < n)
				map.put(deque.poll(), n);
			deque.addFirst(n);
		}
		for (int i = 0; i < nums1.length; i++)
			nums1[i] = map.getOrDefault(nums1[i], -1);
		return nums1;
	}
}